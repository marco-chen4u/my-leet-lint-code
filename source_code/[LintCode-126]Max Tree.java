/***
* LintCode 126. Max Tree
Given an integer array with no duplicates. A max tree building on this array is defined as follow:

The root is the maximum number in the array
The left subtree and right subtree are the max trees of the subarray divided by the root number.
Construct the max tree by the given array.

Example
Example 1:

Input：[2, 5, 6, 0, 3, 1]
Output：{6,5,3,2,#,0,1}
Explanation：
the max tree constructed by this array is:
    6
   / \
  5   3
 /   / \
2   0   1
Example 2:

Input：[6,4,20]
Output：{20,6,#,#,4}
Explanation： 
     20
     / 
    6
     \
      4

Challenge
O(n) time and memory.

Reference:
	LeetCode 654. Maximum Binary Tree
	(url: https://leetcode.com/problems/maximum-binary-tree/)
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
// version-1 recursion(unstable)
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] nums) {
		if (nums == null || nums.length == 0) {
			return null;
		}
		
		return getMaxTree(nums, 0, nums.length - 1);
	}
	
	// helper method
	private TreeNode getMaxTree(int[] nums, int start, int end) {
		// check corner case
		if (start > end) {
			return null;
		}
		
		if (start == end) {
			return new TreeNode(nums[start]);
		}
		
		int maxValue = nums[start];
		int index = start;
		for (int i = start; i <= end; i++) {
			if (maxValue < nums[i]) {
				maxValue = nums[i];
				index = i;
			}
		}
		
		TreeNode node = new TreeNode(maxValue);
		node.left = getMaxTree(nums, start, index - 1);
		node.right = getMaxTree(nums, index + 1, end);
		
		return node;
	}
}

//version-2 (stack, and its a monotonous stack)
public class Solution {
    /**
     * @param A: Given an integer array with no duplicates.
     * @return: The root of max tree.
     */
    public TreeNode maxTree(int[] nums) {
		// check corner case
		if (nums == null || nums.length == 0) {
			return null;
		}
		
		int size = nums.length;
		Stack<TreeNode> stack = new Stack<TreeNode>();
		for (int i = 0; i <= size; i++) {
			int value = (i < size) ? nums[i] : Integer.MAX_VALUE;
			TreeNode current = TreeNode(value);
			
			while (!stack.isEmpty() && current.val > stack.peek().val) {
				TreeNode smaller = stack.pop();
				
				if (!stack.isEmpty() && current.val > stack.peek().val) {
					stack.peek().right = smaller;
				}
				else {
					current.left = smaller;
				}
			}
			
			stack.push(current);
		}
		
		return stack.pop().left;
	}
}