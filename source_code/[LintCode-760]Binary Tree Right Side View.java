/***
* LintCode 760. Binary Tree Right Side View
Given a binary tree, imagine yourself standing on the right side of it, 
return the values of the nodes you can see ordered from top to bottom

Example
    Example 1
        Input: {1,2,3,#,5,#,4}
        Output: [1,3,4]
        Explanation:
               1
             /   \
            2     3
             \     \
              5     4
 
    Example 2
        Input: {1,2,3}
        Output: [1,3]
        Explanation:
               1
             /   \
            2     3
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

public class Solution {
    /**
     * @param root: the root of the given tree
     * @return: the values of the nodes you can see ordered from top to bottom
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner cases
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastPos = size - 1;

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
                
                if (i == lastPos) {
                    result.add(current.val);
                }
            }
        }

        return result;
    }
}
