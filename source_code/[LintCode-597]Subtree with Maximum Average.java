/***
* LintCode 597. Subtree with Maximum Average
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

Example
	Example 1
		Input：
			 1
		   /   \
		 -5     11
		 / \   /  \
		1   2 4    -2 
		Output：11(it's a TreeNode)

	Example 2
		Input：
			 1
		   /   \
		 -5     11
		Output：11(it's a TreeNode)

Notice
	LintCode will print the subtree which root is your return node.
	It's guaranteed that there is only one subtree with maximum average.
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
        int sum;
        int size;
        TreeNode node;
        
        // constructor
        public ResultType(int sum, int size, TreeNode node) {
            this.sum = sum;
            this.size = size;
            this.node = node;
        }
    }
    
    private ResultType max_result;
    
    // helper method
    private ResultType findSubtree2Helper(TreeNode node) {
        if (node == null) {
            return new ResultType(0, 0, null);
        }
        
        // divide
        ResultType left = findSubtree2Helper(node.left);
        ResultType right = findSubtree2Helper(node.right);
        
        // conquer
        int value = node.val + left.sum + right.sum;
        int size = 1 + left.size + right.size;
        double average = (double)value / size;
        ResultType result = new ResultType(value, size, node);
        
        if (max_result == null) {
            max_result = result;
            return result;
        }
        
        if (((double)max_result.sum / max_result.size) < average) {
            max_result.sum = result.sum;
            max_result.size = result.size;
            max_result.node = result.node;
        }
        
        return result;
    }
    
    /**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // check corner case
        if (root == null) {
            return null;
        }
        
        findSubtree2Helper(root);
        
        return (max_result != null) ? max_result.node : null;
    }
}