/***
* LintCode 1357. Path Sum II
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
Example
	Example 1:
		Input: root = {5,4,8,11,#,13,4,7,2,#,#,5,1}, sum = 22
					  5
					 / \
					4   8
				   /   / \
				  11  13  4
				 /  \    / \
				7    2  5   1
		Output: [[5,4,11,2],[5,8,4,5]]
		Explanation:
			The sum of the two paths is 22：
			5 + 4 + 11 + 2 = 22
			5 + 8 + 4 + 5 = 22
	Example 2:
		Input: root = {10,6,7,5,2,1,8,#,9}, sum = 18
					  10
					 /  \
					6    7
				  /  \   / \
				  5  2   1  8
				   \ 
					9  
		Output: [[10,6,2],[10,7,1]]
		Explanation:
			The sum of the two paths is 18：
			10 + 6 + 2 = 18
			10 + 7 + 1 = 18
Notice
	A leaf is a node with no children.
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
     * @param root: a binary tree
     * @param sum: the sum
     * @return: the scheme
     */
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null) {
            return result;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        helper(result, path, root, sum);
        
        return result;
    }
    
    // helper method
    private void helper(List<List<Integer>> result, List<Integer> path, TreeNode node, int sum) {
        // check corner case
        if (node == null || sum <= 0) {
            
            if (sum == 0 && node == null) {
                if (!result.contains(path)) {
                    result.add(new ArrayList<Integer>(path));
                }
            }
            
            return;
        }
        
        // normal case
        path.add(node.val);
        
        helper(result, path, node.left, sum - node.val);
        helper(result, path, node.right, sum- node.val);
        
        path.remove(path.size() -1);
    }
}