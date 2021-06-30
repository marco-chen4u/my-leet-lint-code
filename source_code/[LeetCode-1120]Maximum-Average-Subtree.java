/***
* LeetCode 1120. Maximum Average Subtree
Given the root of a binary tree, find the maximum average value of any subtree of that tree.

(A subtree of a tree is any node of that tree plus all its descendants. The average value of a tree is the sum of its values, divided by the number of nodes.)

Example 1:
          5
         / \
        6   1

Input: [5,6,1]
Output: 6.00000
Explanation: 
    For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
    For the node with value = 6 we have an average of 6 / 1 = 6.
    For the node with value = 1 we have an average of 1 / 1 = 1.
    So the answer is 6 which is the maximum.

Note:
    1.The number of nodes in the tree is between 1 and 5000.
    2.Each node will have a value between 0 and 100000.
    3.Answers will be accepted as correct if they are within 10^-5 of the correct answer.


Link: https://leetcode.com/problems/maximum-average-subtree/
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

//version-1: recursion with grobal variables, divide and conquer
class Solution {
    // inner class
    class Node {
        // fields
        int sum;
        int size;
        
        // constructor
        public Node(int sum, int size) {
            this.sum = sum;
            this.size = size;
        }
        
        public double getAverage() {
            return (double)sum / (double)size;
        }
        
    }
    
    // fields
    private double maximumAverage = 0.0;
    
    public double maximumAverageSubtree(TreeNode root) {
        if (root == null) {
            return maximumAverage;
        }
        
        find(root);
        
        return maximumAverage;
    }
    
    // helper method
    private Node find(TreeNode root) {
        if (root == null) {
            return new Node(0, 0);
        }
        
        Node left = find(root.left);
        Node right = find(root.right);
        
        int sum = root.val + left.sum + right.sum;
        int size = 1 + left.size + right.size;
        
        Node node = new Node(sum, size);
        
        maximumAverage = Math.max(maximumAverage, node.getAverage());
        
        return node;
    }
}
