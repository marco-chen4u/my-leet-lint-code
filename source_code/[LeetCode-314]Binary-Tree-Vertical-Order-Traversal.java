/***
* LeetCode 314. Binary Tree Vertical Order Traversal
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.

Example 1
        3
      /   \
     9    20
         /   \
       15     7  
    Input: root = [3,9,20,null,null,15,7]
    Output: [[9],[3,15],[20],[7]]

Example 2
                  3
                /    \
               9      8
             /   \   /  \
            4     0 1    7
    Input: root = [3,9,8,4,0,1,7]
    Output: [[4],[9],[3,0,1],[8],[7]]

Example 3
                  3
                /    \
               9      8
             /   \   /  \
            4     0 1    7
                 /   \
                5     2 
    Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
    Output: [[4],[9,5],[3,0,1],[8,2],[7]]

Constraints:
    The number of nodes in the tree is in the range [0, 100].
    -100 <= Node.val <= 100
***/
//version-1: BFS, time complexity: O(nlogn), space complexity: O(n)
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        
        Map<Integer, List<Integer>> columnMap = new HashMap<>();
        Pair<Integer, TreeNode> rootPair = new Pair(0, root);
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.offer(rootPair);

        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> current = queue.poll();

            int currentCol = current.getKey();
            TreeNode currentNode = current.getValue();

            if (currentNode == null) {
                continue;
            }

            columnMap.putIfAbsent(currentCol, new LinkedList<Integer>());
            columnMap.get(currentCol).add(currentNode.val);

            queue.offer(new Pair(currentCol - 1, currentNode.left));
            queue.offer(new Pair(currentCol + 1, currentNode.right));
        }

        List<Integer> sortedKeyList = new ArrayList<>(columnMap.keySet());
        Collections.sort(sortedKeyList);

        for (int key : sortedKeyList) {
            results.add(columnMap.get(key));
        }

        return results;
    }
}

//version-2: BFS, time complexity:O(n), space complexity: O(n)
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        Map<Integer, List<Integer>> columnMap = new HashMap<>();
        Pair<Integer, TreeNode> rootPair = new Pair(0, root);
        Queue<Pair<Integer, TreeNode>> queue = new LinkedList<>();
        queue.offer(rootPair);
        int maxColumn = 0;
        int minColumn = 0;

        while (!queue.isEmpty()) {
            Pair<Integer, TreeNode> current = queue.poll();
            int currentColumn = current.getKey();
            TreeNode currentNode = current.getValue();

            columnMap.putIfAbsent(currentColumn, new ArrayList<Integer>());
            columnMap.get(currentColumn).add(currentNode.val);

            if (currentNode.left != null) {
                queue.offer(new Pair(currentColumn - 1, currentNode.left));
            }
            
            if (currentNode.right != null) {
                queue.offer(new Pair(currentColumn + 1, currentNode.right));
            }
            
            maxColumn = Math.max(maxColumn, currentColumn);
            minColumn = Math.min(minColumn, currentColumn); 
        }

        for (int i = minColumn; i <= maxColumn; i++) {
            results.add(columnMap.get(i));
        }

        return results;
    }
}

//version-3: DFS, time complexity: O(w * logh)[w: the width of tree, h: the height of the tree], space complexity: O(n)
class Solution {

    // fields
    private Map<Integer, List<Pair<Integer, Integer>>> columnMap = new HashMap<>();
    private int minColumn = 0;
    private int maxColumn = 0;

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }

        dfs(root, 0, 0);

        for (int i = minColumn; i <= maxColumn; i++) {
            List<Pair<Integer, Integer>> pairList = columnMap.get(i);
            Collections.sort(pairList, (a, b)-> a.getKey() - b.getKey());//Pair<row, node.val>
            List<Integer> sortedColumn = new ArrayList<>();
            for (Pair<Integer, Integer> pair : pairList) {
                sortedColumn.add(pair.getValue());
            }

            results.add(sortedColumn);
        }

        return results;
    }

    // helper methods
    private void dfs(TreeNode node, int row, int column) {
        if (node == null) {
            return;
        }

        columnMap.putIfAbsent(column, new ArrayList<Pair<Integer, Integer>>());
        columnMap.get(column).add(new Pair(row, node.val));
        maxColumn = Math.max(maxColumn, column);
        minColumn = Math.min(minColumn, column);

        dfs(node.left, row + 1, column - 1);
        dfs(node.right, row + 1, column + 1);
    }
}
