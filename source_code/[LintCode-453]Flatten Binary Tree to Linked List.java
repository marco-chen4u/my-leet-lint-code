/***
* LintCode 453. Flatten Binary Tree to Linked List
Flatten a binary tree to a fake "linked list" in pre-order traversal.
Here we use the right pointer in TreeNode as the next pointer in ListNode.

Example
	Example 1:
		Input:
			{1,2,5,3,4,#,6}
				 1
				/ \
			   2   5
			  / \   \
			 3   4   6
		Output:
			{1,#,2,#,3,#,4,#,5,#,6}
			   1
				\
				 2
				  \
				   3
					\
					 4
					  \
					   5
						\
						 6
			 
	Example 2:
		Input:
			{1}
			1
		Output:
			{1}
			1

Challenge
	Do it in-place without any extra memory.

Notice
	Don't forget to mark the left child of each node to null. 
	Or you will get Time Limit Exceeded or Memory Limit Exceeded.
	
URL
	https://www.lintcode.com/problem/flatten-binary-tree-to-linked-list/
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
 
//version-1: devide and conquar
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner case
		if (root == null) {
			return;
		}
		
		flattenHelper(root);
    }
	
	private TreeNode flattenHelper(TreeNode node) {
		if (node == null) {
			return null;
		}
		
		TreeNode leftLast = flattenHelper(node.left);
		TreeNode rightLast = flattenHelper(node.right);
		
		// connect leftlast to nde.right
		if (leftLast != null) {
			leftLast.right = node.right;
			node.right = node.left;
			node.left = null;
		}
		
		if (rightLast != null) {
			return rightLast;
		}
		
		return node;			
	}
}


//version-2: none-recursion
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
		// check corner case
		if (root == null || 
			root.left == null && root.right == null) {
			return root;
		}
		
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		
		while (!stack.isEmpty()) {
			TreeNode current = stack.pop();
			
			if (node.right != null) {
				stack.push(node.right);
			}
			
			if (node.left != null) {
				stack.push(node.left);
			}
			
			node.left = null;
			node.right = stack.isEmpty() ? stack.peek() : null;
		}
	}	
}