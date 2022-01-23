/***
* LintCode 69 · Binary Tree Level Order Traversal
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

Example 1:
    Input:
        tree = {1,2,3}
    Output:
        [[1],[2,3]]
    Explanation:
           1 
          / \ 
         2   3 
    it will be serialized {1,2,3}
    
Example 2:
    Input:
        tree = {1,#,2,3} 
    Output:
        [[1],[2],[3]] 
    Explanation:
        1 
         \ 
          2 
         / 
        3 
    it will be serialized {1,#,2,3}
    
Challenge 1: Using only 1 queue to implement it.

Challenge 2: Use BFS algorithm to do it.
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

//version-1: BFS
public class Solution {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                list.add(current.val);
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            
            result.add(list);
        }
        
        return result;
    }
}

//version-2: BFS with double queue
public class Solution {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            Queue<TreeNode> nextQueue = new LinkedList<>();
            List<Integer> list = getLevelList(queue);
            result.add(list);

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                if (current.left != null) {
                    nextQueue.offer(current.left);
                }
                
                if (current.right != null) {
                    nextQueue.offer(current.right);
                }
            }
            
            queue = nextQueue;
        }
        
        return result;
    }

   // helper method 
    private List<Integer> getLevelList(Queue<TreeNode> queue) {
        List<Integer> list = new ArrayList<>();
        
        for (TreeNode node : queue) {
            list.add(node.val);
        }

        return list;
    }
}

//version-3: BFS with dummy node
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

public class Solution {
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(null);

        List<Integer> levelList = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current == null) {

                if (levelList.isEmpty()) {
                    break;
                }

                result.add(levelList);
                levelList = new ArrayList<>();

                queue.offer(null);

                continue;
            }

            levelList.add(current.val);
            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }
}
