/***
* LintCode 177. Convert Sorted Array to Binary Search Tree With Minimal Height.
Given a sorted (increasing order) array, Convert it to a binary search tree with minimal height.

Example 1: 
    Input: []
    Output:  {}
    Explanation: The binary search tree is null

Example 2:
    Input: [1,2,3,4,5,6,7]
    Output:  {4,2,6,1,3,5,7}
    Explanation:
        A binary search tree with minimal height.

             4
           /   \
          2     6
         / \    / \
        1   3  5   7

Link:
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

//version-1:recursion with helper method
public class Solution {
    /*
     * @param A: an integer array
     * @return: A tree node
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return null;
        }

        return helper(nums, 0, nums.length -1);
    }

    // helper method 
    private TreeNode helper(int[] nums, int start, int end) {
        // check corner case
        if (start < end) {
            return null;
        }

        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1);
        node.right = helper(nums, mid + 1, end);

        return node;
    }
}

//verison-2:recursion
public class Solution {
    /*
     * @param A: an integer array
     * @return: A tree node
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return null;
        }

        int start = 0;
        int end = nums.length - 1;

        int mid = start + (end - start) / 2;

        TreeNode node = new TreeNode(nums[mid]);

        if (mid > start) {
            int[] leftNums = Arrays.copyOfRange(nums, start, mid);
            node.left = sortedArrayToBST(leftNums);
        }

        if (mid < end) {
            int[] rightNums = Arrays.copyOfRange(nums, mid + 1, end + 1);
            node.right = sortedArrayToBST(rightNums);
        }

        return node;
    }
}
