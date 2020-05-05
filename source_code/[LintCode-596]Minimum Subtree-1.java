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
// version-1 : global variable
public class Solution {
	private TreeNode minSumSubtree = null;
	private int minSubtreeSum = Integer.MAX_VALUE
    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // check corner case
		if (root == null) {
			return null;
		}
		
		findSubtreeHelper(root);
		
		return minSubtreeSum;
    }
	
	private int findSubtreeHelper(TreeNode node) {
		if (node == null) {
			return 0;
		}
		
		int sum = node.val + findSubtreeHelper(node.left) + findSubtreeHelper(root.right);
		
		if (sum < minSubtreeSum) {
			minSubtreeSum = sum;
			minSumSubtree = node;
		}
		
		return sum;
	}
}