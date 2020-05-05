/***
* LintCode 164. Unique Binary Search Trees II
Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

Example
	Example 1:
		Input:3
		Output:
			1         3     3       2    1
			 \       /     /       / \    \
			  3     2     1       1   3    2
			 /     /       \                \
			2     1         2                3
	
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
/*
* 递归调用
*/
public class Solution {
    /**
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> generateTrees(int n) {
        return helper(1, n);
    }
    
    // helper method
    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        // check corner case
        if (start > end) {
            result.add(null);
            return result;
        }
        
        for (int i = start; i <= end; i++) {
            List<TreeNode> left = helper(start, i - 1);
            List<TreeNode> right = helper(i + 1, end);
            
            for (TreeNode leftNode : left) {
                for (TreeNode rightNode : right) {
                    TreeNode currentNode = new TreeNode(i);
                    currentNode.left = leftNode;
                    currentNode.right = rightNode;
                    
                    result.add(currentNode);
                }
            }
        }
        
        return result;
    }
}