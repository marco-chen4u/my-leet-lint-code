/***
* LintCode 900. Closest Binary Search Tree Value
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Example
	Example1
		Input: root = {5,4,9,2,#,8,10} and target = 6.124780
		Output: 5

	Example2
		Input: root = {3,2,4,1} and target = 4.142857
		Output: 4

Notice
	Given target value is a floating point.
	You are guaranteed to have only one unique value in the BST that is closest to the target.
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
	
	// helper methods
	private TreeNode findLowerClosest(TreeNode node, double target) {
	    if (node == null) {
	        return null;
	    }
	    
	    TreeNode result = null;
	    if (node.val < target) {
	        result =  findLowerClosest(node.right, target);
	    }
	    else {
	        return findLowerClosest(node.left, target);
	    }
	    
	    if (result != null) {
	        return result;
	    }
	    
	    return node;
	}
	
	private TreeNode findUpperClosest(TreeNode node, double target) {
	    if (node == null) {
	        return null;
	    }
	    
	    TreeNode result = null; 
	    if (node.val >= target) {
	        result = findUpperClosest(node.left, target);
	    }
	    else {
	        return findUpperClosest(node.right, target);
	    }
	    
	    if (result != null) {
	        return result;
	    }
	    
	    return node;
	}
	
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // check corner case
		if (root == null) {
			return 0;
		}
		
		TreeNode lowerClosest = findLowerClosest(root, target);
		TreeNode upperClosest = findUpperClosest(root, target);
		
		if (lowerClosest == null) {
		    return upperClosest.val;
		}
		
		if (upperClosest == null) {
		    return lowerClosest.val;
		}
		
		if (Math.abs(target - lowerClosest.val) < Math.abs(upperClosest.val - target)) {
			return lowerClosest.val;
		}
		else {
			return upperClosest.val;
		}		
    }
}