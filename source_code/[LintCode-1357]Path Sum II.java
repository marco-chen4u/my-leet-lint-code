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
//version-1[when the tree is children has the same value like, root = {0, 1, 1}, sum = 1], it will get failure
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

//version-2
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> path = new ArrayList<Integer>();
        
        // check corner cases
        if (root == null) {
            return result;
        }
        
        // normal case
        List<List<Integer>> pathSet = new ArrayList<List<Integer>>();
        helper(pathSet, path, root, sum);
        
        result.addAll(pathSet);
        
        return result;
    }
    
    // helper method
    private void helper(List<List<Integer>> pathSet, 
                            List<Integer> path, 
                            TreeNode node, 
                            int sum) {
        // check corner case
        if (node == null) {
            return;
        }
        
        sum = sum - node.val;
        TreeNode leftNode = node.left;
        TreeNode rightNode = node.right;
        
        path.add(node.val);
        
        if (sum == 0 && 
	    leftNode == null && rightNode == null) {
            pathSet.add(new ArrayList<Integer>(path));
        }
        else {
            helper(pathSet, path, leftNode, sum);
            helper(pathSet, path, rightNode, sum);
        }
        
        path.remove(path.size() - 1);
    }
}

//version-3
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
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
        if (node == null) {
            return;
        }
        
        // normal case
        path.add(node.val);
        sum = sum - node.val;
        if (sum == 0 && isLeaf(node)) {
            result.add(new ArrayList<Integer>(path));
        }
        else {
            helper(result, path, node.left, sum);
            helper(result, path, node.right, sum);            
        }
        
        path.remove(path.size() -1);
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
