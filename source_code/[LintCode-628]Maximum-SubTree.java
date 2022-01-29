/***
* LintCode. 628 Maximum Subtree
Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

Example 1:
    Input:
        {1,-5,2,0,3,-4,-5}
    Output:3
    Explanation:
        The tree is look like this:
             1
           /   \
         -5     2
         / \   /  \
        0   3 -4  -5
        The sum of subtree 3 (only one node) is the maximum. So we return 3.

Example 2: 
    Input:
        {1}
    Output:1
    Explanation:
        The tree is look like this:
           1
        There is one and only one subtree in the tree. So we return 1.
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

//version-1: dfs(divide&conquer) with global variable
public class Solution {
    // fields
    private TreeNode result;
    private int max;
    
    /**
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public TreeNode findSubtree(TreeNode root) {
        // check corner case
        if (root == null || 
            (root.left == null && root.right == null)) {
            return root;
        }
        
        result = null;
        max = Integer.MIN_VALUE;
        
        helper(root);
        
        return result;
    }
    
    private int helper(TreeNode node) {
        // check corner case
        if (node == null) {
            return 0;
        }
        
        int leftValue = helper(node.left);
        int rightValue = helper(node.right);
        
        int value = node.val + leftValue + rightValue;
        
        if (value > max) {
            max = value;
            result = node;
        }
        
        return value;
    }
    
}
//version-2:dfs(divide&conquer) with tuple data type class 
public class Solution {
    
    // inner class
    class Result {
        // fields
        int sum;
        int maxSum;
        TreeNode node;
        
        // constructor
        public Result(int sum, int maxSum, TreeNode node) {
            this.sum = sum;
            this.maxSum = maxSum;
            this.node = node;
        }
    }
    
    /**
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public TreeNode findSubtree(TreeNode root) {
        Result result = dfs(root);
        
        return result.node;
    }
    
    // helper method
    private Result dfs(TreeNode node) {
        if (node == null) {
            return new Result(0, 0, node);
        }
        
        Result left = dfs(node.left);
        Result right =dfs(node.right);
        
        int currentSum = node.val + left.sum + right.sum;
        
        TreeNode maxNode = node;
        
        int maxSum = currentSum;
    
        if (maxSum < left.maxSum) {
            maxNode = left.node;
            maxSum = left.maxSum;
        }
        
        if (maxSum < right.maxSum) {
            maxNode = right.node;
            maxSum = right.maxSum;
        }
        
        return new Result(currentSum, maxSum, maxNode);
        
    }
}
