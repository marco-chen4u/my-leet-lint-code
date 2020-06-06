/***
* LeetCode 112. Path Sum
Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:
    Given the below binary tree and sum = 22,

          5
         / \
        4   8
       /   / \
      11  13  4
     /  \      \
    7    2      1
    return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
***/
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
//solution-1: recursion
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        
        if (root == null) {
            return false;
        }
        
        sum = sum - root.val;
        
        if (sum == 0 && root.left == null && root.right == null) {
            return true;
        }
        
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
        
    }
}

//solution-2: PreOrder traversal with Stack
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        // check corner case
        if (root == null) {
            return false;
        }
        
        root.val = sum - root.val;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        stack.push(current);
        
        while(!stack.isEmpty()) {
            current = stack.pop();
            sum = current.val;
            TreeNode leftNode = current.left;
            TreeNode rightNode = current.right;
            
            if (sum == 0 && leftNode == null && rightNode == null) {
                return true;
            }
            
            /*if (sum < 0) {
                continue;
            }*/
            
            if (leftNode != null) {
                leftNode.val = sum - leftNode.val;
                stack.push(leftNode);
            }
            
            if (rightNode != null) {
                rightNode.val = sum - rightNode.val;
                stack.push(rightNode);
            }
        }
        
        return false;
    }
}

