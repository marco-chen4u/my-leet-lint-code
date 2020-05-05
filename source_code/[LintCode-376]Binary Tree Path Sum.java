/***
* LintCode 376. Binary Tree Path Sum
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
A valid path is from root node to any of the leaf nodes.

Example
	Example 1:
		Input:
			{1,2,4,2,3}
			5
		Output: [[1, 2, 2],[1, 4]]
		Explanation:
			The tree is look like this:
					 1
					/ \
				   2   4
				  / \
				 2   3
			For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
	Example 2:
		Input:
			{1,2,4,2,3}
			3
		Output: []
		Explanation:
			The tree is look like this:
					 1
					/ \
				   2   4
				  / \
				 2   3
			Notice we need to find all paths from root node to leaf nodes.
			1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5 
			There is no one satisfying it.
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
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<Integer>();
        //check corner case
        if (root == null) {
            return result;
        }
        
        findBinaryTreePathSum(result, subset, root, target);
        
        return result;
    }
    
    private void findBinaryTreePathSum(List<List<Integer>> result, List<Integer> subset,
                                        TreeNode node, int target) {
        if (node == null) {
            return;
        }
            
        subset.add(node.val);
        if (node.left == null && node.right == null && node.val == target) {
            result.add(new ArrayList<Integer>(subset));
        }
        else {
            findBinaryTreePathSum(result, subset, node.left, target - node.val);
            findBinaryTreePathSum(result, subset, node.right, target - node.val);
        }
        subset.remove(subset.size() - 1);
    }
}