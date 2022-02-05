/**
* LintCode 1359. Convert Sorted Array to Binary Search Tree
Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, 
a height-balanced binary tree is defined as a binary tree 
in which the depth of the two subtrees of every node never differ by more than 1.

Example 1:
    Input: [-10,-3,0,5,9]
    Output: [0,-3,9,-10,#,5]
    Explanation:
        One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:
              0
             / \
           -3   9
           /   /
         -10  5
         For this tree, you function need to return a tree node which value equals 0.

Example 2:
    Input: [1,3]
    Output: [3,1]
    Explanation:
        One possible answer is: [3,1], which represents the following height balanced BST:
          3
         / 
        1   
        For this tree, you function need to return a tree node which value equals 3.
**/
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
//version-1: recursion, devide and conquer
public class Solution {
    /**
     * @param nums: the sorted array
     * @return: the root of the tree
     */
    public TreeNode convertSortedArraytoBinarySearchTree(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return null;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    // helper method
    private TreeNode helper(int[] nums, int startPos, int endPos) {
        // check corner case
        if (startPos > endPos) {
            return null;
        }
        
        int midPos = startPos + (endPos - startPos) / 2;
        TreeNode node = new TreeNode(nums[midPos]);
        node.left = helper(nums, startPos, midPos - 1);
        node.right = helper(nums, midPos + 1, endPos);
        
        return node;
    }
}
