/***
* LintCode 480. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.

Example
	Example 1:
		Input:
			   1
			 /   \
			2     3
			 \
			  5
		Output:
			[
			  "1->2->5",
			  "1->3"
			]

	Example 2:
		Input:
			   1
			 /   
			2
		Output:
			[
			  "1->2"
			]

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
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
		
		// check corner case
		if (root == null) {
			return result;
		}
		
		// divide
		List<String> leftPaths = binaryTreePaths(root.left);
		List<String> rightPaths = binaryTreePaths(root.right);
		
		// conquer
		for(String path : leftPaths) {
			result.add(root.val + "->" + path);
		}
		
		for (String path : rightPaths) {
			result.add(root.val + "->" + path);
		}
		
		if (result.isEmpty()) {
			result.add(String.valueOf(root.val));
		}
		
		return result;
    }
}