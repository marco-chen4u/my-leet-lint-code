/***
* LintCode 95. Valid Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
	-The left subtree of a node contains only nodes with keys less than the node's key.
	-The right subtree of a node contains only nodes with keys greater than the node's key.
	-Both the left and right subtrees must also be binary search trees.
	-A single node tree is a BST

Example
	Example 1:
		Input:  For the following binary tree（only one node）:
			-1
		Output：true
		
	Example 2:
		Input:  For the following binary tree:		
			  2
			 / \
			1   4
			   / \
			  3   5			
		Output: true
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
    // inner class
    class ResultType {
        // fields
        boolean isBst;
        TreeNode maxNode;
        TreeNode minNode;
        // constructors
        public ResultType(boolean isBst) {
            this.isBst = isBst;
            this.maxNode = null;
            this.minNode = null;
        }

        public ResultType(boolean isBst, TreeNode max, TreeNode min) {
            this.isBst = isBst;
            this.maxNode = max;
            this.minNode = min;
        }
    }
	
    // helper method
    private ResultType helper(TreeNode node) {
        if (node == null) {
            return new ResultType(true);
        }

        ResultType leftResult = helper(node.left);
        ResultType rightResult = helper(node.right);
	
        if (!leftResult.isBst || !rightResult.isBst) {
            return new ResultType(false);
        }
	
        if ((leftResult.maxNode != null && leftResult.maxNode.val >= node.val) ||
                (rightResult.minNode != null && rightResult.minNode.val <= node.val)) {
            return new ResultType(false);
        }

        return new ResultType(true, (rightResult.maxNode != null) ? rightResult.maxNode : node, 
                                    (leftResult.minNode != null) ? leftResult.minNode : node);
    }
	
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
	    return true;
        }
		
        return helper(root).isBst;
    }
}
