/***
* LintCode 93. Balanced Binary Tree
Given a binary tree, determine if it is height-balanced.

For this problem, 
a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:
    Input:
        tree = {1,2,3}
    Output:
        true
    Explanation:
        This is a balanced binary tree.
              1  
             / \                
            2   3

Example 2:
    Input:
        tree = {1,#,2,3,4}
    Output:
        false
    Explanation:
        This is not a balanced tree. 
        The height of node 1's right sub-tree is 2 but left sub-tree is 0.
               1  
                \  
                 2   
                / \ 
               3   4

Link: https://www.lintcode.com/problem/93/
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

//solution: recursion
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // check corner case
        if (root == null) {
            return true;
        }

        // divide 
        boolean isLeftBalanced = isBalanced(root.left);
        boolean isRightBalanced = isBalanced(root.right);
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);

        // conquer
        return isLeftBalanced && isRightBalanced && Math.abs(leftDepth - rightDepth) <= 1;
    }

    // helper method
    private int getDepth(TreeNode node) {
        // check corner cases
        if (node == null) {
            return 0;
        }

        if (node.left == null && node.right == null) {
            return 1;
        }

        // normal case
        int leftDepth = getDepth(node.left);
        int rightDepth = getDepth(node.right);

        //return
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
