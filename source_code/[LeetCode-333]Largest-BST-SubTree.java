/***
* LeetCode 333. Largest BST SubTree
Given the root of a binary tree, find the largest subtree, 
which is also a Binary Search Tree (BST), where the largest means subtree has the largest number of nodes.

A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
    The left subtree values are less than the value of their parent (root) node's value.
    The right subtree values are greater than the value of their parent (root) node's value.

Note: A subtree must include all of its descendants.

Example 1
    Input:
        [10,5,15,1,8,null,7]
    Output：
        3

    Explanation:
        10
        / \
       5  15
      / \   \ 
     1   8   7
    The Largest BST Subtree in this case is :
       5
      / \
     1   8. 
    The return value is the subtree's size, which is 3.

Example 2
    Input: root = [4,2,7,2,3,5,null,2,null,null,null,null,null,1]
    Output: 2
    
Example 3
    Input:
    {1}
    Output：
    1
    
*Link: https://leetcode.com/problems/largest-bst-subtree/description/
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

//version-1: DFS, Divide&Conquer
class Solution {

    //inner class
    class ResultType {
        // fields
        int count;
        boolean isBST;
        TreeNode min;
        TreeNode max;

        // constructor
        public ResultType(boolean isBST, TreeNode min, TreeNode max) {
            this.count = 0;
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }

    public int largestBSTSubtree(TreeNode root) {

        ResultType result = findMaxSub(root);

        return result.count;
    }

    // helper method 
    private ResultType findMaxSub(TreeNode node) {
        
        if (node == null) {
            return new ResultType(true, null, null);
        }

        ResultType left = findMaxSub(node.left);
        ResultType right = findMaxSub(node.right);
        
        ResultType result = new ResultType(false, null, null); 

        if (left.isBST && right.isBST && 
            (left.max == null || left.max.val < node.val) &&
            (right.min == null || right.min.val > node.val)) {
                result.isBST = true;
                result.min = left.min == null ? node : left.min;
                result.max = right.max == null ? node : right.max;

                result.count = 1 + left.count + right.count;
        }
        else {
            result.count = Math.max(left.count, right.count);
        }

        return result;
    }
}
