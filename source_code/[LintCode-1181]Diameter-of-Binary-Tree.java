/***
* LintCode 1181. Diameter of Binary Tree
Given a binary tree, you need to compute the length of the diameter of the tree. 
The diameter of a binary tree is the length of the longest path between any two nodes in a tree.

Note:
    The length of path between two nodes is represented by the number of edges between them.
    
Example 1:
    Given a binary tree 
              1
             / \
            2   3
           / \     
          4   5    
    Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Example 2:
    Input:[2,3,#,1]
    Output:2
    Explanation:
          2
        /
       3
     /
    1
***/
//version-1: DFS
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

    // field
    private static int maxDiameter;

    /**
     * @param root: a root of binary tree
     * @return: return a integer
     */
    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;

        dfs(root);

        return maxDiameter;
    }

    // helper method
    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int left = dfs(node.left);
        int right = dfs(node.right);

        maxDiameter = Math.max(maxDiameter, left + right);

       int result = Math.max(left, right) + 1;
        return result;
    }
}
