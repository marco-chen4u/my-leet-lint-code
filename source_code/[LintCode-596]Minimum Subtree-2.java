/***
* LintCode 596. Minimum Subtree
Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.

Example
	Example 1:
		Input:
				 1
			   /   \
			 -5     2
			 / \   /  \
			0   2 -4  -5 
		Output:1

	Example 2:
		Input:
		   1
		Output:1

Notice
	LintCode will print the subtree which root is your return node.
	It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.
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
// version-2 : devide & conquer - introduce new class definition of the pair holding TreeNode and min value
public class Solution {
	// inner class
	class ResultType {
		// fields
		TreeNode node;
		int sum;
		int minSum;
		// construction
		public ResultType(TreeNode node, int sum, int minSum) {
			this.node = node;
			this.sum = sum;
			this.minSum = minSum;
		}
	}
	
	// helper method
	private ResultType findSubtreeHelper(TreeNode node) {
		if (node == null) {
			return new ResultType(null, 0, Integer.MAX_VALUE);
		}
		ResultType leftResult = findSubtreeHelper(node.left);
		ResultType rightResult = findSubtreeHelper(node.right);
		ResultType result = new ResultType(node, 
											node.val + leftResult.sum + rightResult.sum,
											node.val + leftResult.sum, rightResult.sum);
		
		if (result.minSum > leftResult.minSum) {
			result.node = leftResult.node;
			result.minSum = leftResult.minSum;
		}
		
		if (result.minSum > rightResult.minSum) {
			result.node = rightResult.node;
			result.minSum = rightResult.minSum;
		}
		
		return result;
	}
	
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
		
		ResultType result = findSubtreeHelper(root);
		return result.node;		
	}
}