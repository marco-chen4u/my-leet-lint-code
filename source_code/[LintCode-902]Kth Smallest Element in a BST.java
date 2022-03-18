/***
* LintCode 902. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example
    Given root = {1,#,2}, k = 2, return 2.

Challenge
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
    How would you optimize the kthSmallest routine?

Notice
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
***/
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */
// version-1: no-recursion & stack
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // check corner case
        if (root == null || k < 1) {
            return 0;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        
        for (int i = 1; i < k; i++) {
            current = stack.pop();
            
            if (current.right != null) {
                current = current.right;
                
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
        }
        
        return stack.isEmpty() ? 0 : stack.peek().val;
    }
}

//version-2: iteration with stack
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        int result = -1;
        
        if (root == null || k <= 0) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        getAllLeftNodes(current, stack);

        for (int i = 1; i <= k; i++) {
            current = stack.pop();
            result = current.val;

            getAllLeftNodes(current.right, stack);
        }

        return result;
    }

     // helper method
     private void getAllLeftNodes(TreeNode current, Stack<TreeNode> stack) {
         while (current != null) {
             stack.push(current);
             current = current.left;
         }
     }
}

// version-3: recursion(divide&conquer) with HashMap(keep record of children count by current node)
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        int result = -1;
        
        if (root == null || k <= 0) {
            return result;
        }

        Map<TreeNode, Integer> map = new HashMap<>();
        dfs(root, map);

        result = find(root, k, map);

        return result;
    }

     // helper method
    private int dfs(TreeNode node, Map<TreeNode, Integer> map) {
        
        if (node == null) {
            return 0;
        }

        int leftCount = dfs(node.left, map);
        int rightCount = dfs(node.right, map);

        int count = 1 + leftCount + rightCount;

        map.put(node, count);

        return count;
    }

    private int find(TreeNode node, int k, Map<TreeNode, Integer> map) {
        if (node == null) {
            return 0;
        }

        int leftCount = map.getOrDefault(node.left, 0);
        if (k <= leftCount) {
            return find(node.left, k, map);
        }

        if (k == leftCount + 1) {
            return node.val;
        }

        if (k > leftCount + 1) {
            k = k - (leftCount + 1);
            return find(node.right, k, map);
        }

        return 0;
    }
}

//version-4: recursion with tuple
public class Solution {

    // inner class
    class ResultType{
        // fields
        int count;
        int value;
        boolean found;

        // constructor
        public ResultType(int count, int value, boolean found) {
            this.count = count;
            this.value = value;
            this.found = found;
        }
    }

    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        ResultType result = dfs(root, k);

        return (result.found) ? result.value : 0;
    }

    // helper method
    private ResultType dfs(TreeNode node, int k) {
        ResultType result = new ResultType(0, 0, false);// default
        if (node == null) {
            return result;
        }

        ResultType left = dfs(node.left, k);        

        if (left.found) {
            result.value = left.value;
            result.found = true;
            return result;
        }

        if (k - left.count == 1) {
            result.value = node.val;
            result.found = true;
            return result;
        }

        ResultType right = dfs(node.right, k - left.count - 1);
        if (right.found) {
            result.value = right.value;
            result.found = true;
            return result;
        }

        int count = left.count + right.count + 1;
        result.count = count;
        return result;

    }
}
