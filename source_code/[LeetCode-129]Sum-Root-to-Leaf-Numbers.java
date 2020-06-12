/***
* LeetCode 129. Sum Root to Leaf Numbers
Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

An example is the root-to-leaf path 1->2->3 which represents the number 123.

Find the total sum of all root-to-leaf numbers.

Note: A leaf is a node with no children.

Example 1:
    Input: [1,2,3]
            1
           / \
          2   3
    Output: 25
    Explanation:
        The root-to-leaf path 1->2 represents the number 12.
        The root-to-leaf path 1->3 represents the number 13.
        Therefore, sum = 12 + 13 = 25.

Example 2:
    Input: [4,9,0,5,1]
            4
           / \
          9   0
         / \
        5   1
    Output: 1026
    Explanation:
        The root-to-leaf path 4->9->5 represents the number 495.
        The root-to-leaf path 4->9->1 represents the number 491.
        The root-to-leaf path 4->0 represents the number 40.
        Therefore, sum = 495 + 491 + 40 = 1026.

Link: https://leetcode.com/problems/sum-root-to-leaf-numbers/
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
//version-1: recursion, similar to findPathSum problem
class Solution {
    public int sumNumbers(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }
        
        if (isLeaf(root)) {
            return root.val;
        }
        
        List<Integer> pathValues = new ArrayList<Integer>();
        findPath(pathValues, root, 0);
        
        int result = 0;
        for (int value : pathValues) {
            result += value;
        }
        
        return result;
    }
    
    // helper method
    private void findPath(List<Integer> pathValues, TreeNode node, int pathValue) {
        int newPathValue = pathValue * 10 + node.val;
        
        if (isLeaf(node)) {
            pathValue = pathValue * 10 + node.val;
            pathValues.add(pathValue);
            return;
        }
        
        if (node.left != null) {
            findPath(pathValues, node.left, newPathValue);
        }
        
        if (node.right != null) {
            findPath(pathValues, node.right, newPathValue);
        }
        
    }
    
    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }
}
