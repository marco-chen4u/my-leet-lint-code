/***
* LeetCode 53. Maximum Subarray
Given an integer array nums, find the contiguous subarray (containing at least one number) 
which has the largest sum and return its sum.

Example:
	Input: [-2,1,-3,4,-1,2,1,-5,4],
	Output: 6
	Explanation: [4,-1,2,1] has the largest sum = 6.

Follow up:
	If you have figured out the O(n) solution, 
	try coding another solution using the divide and conquer approach, which is more subtle.
***/
//version-1: recursion
class Solution {
    private final int DEFAULT = Integer.MIN_VALUE;
    
    public int maxSubArray(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        return helper(nums, 0, nums.length - 1);
    }
    
    // helper method
    private int helper(int[] nums, int start, int end) {
        // check corner case
        if (start > end) {
            return DEFAULT;
        }
        
        int mid = start + (end - start) / 2;
        int leftMax = helper(nums, start, mid - 1);
        int rightMax = helper(nums, mid + 1, end);
        
        int leftContinuousSum = 0;
        int leftSumToCurrent = 0;
        for(int i = mid - 1; i >= start; i--) {
            leftSumToCurrent += nums[i];
            leftContinuousSum = Math.max(leftContinuousSum, leftSumToCurrent);
        }
        
        int rightContinuousSum = 0;
        int rightSumToCurrent = 0;
        for (int i = mid + 1; i <= end; i++) {
            rightSumToCurrent += nums[i];
            rightContinuousSum = Math.max(rightContinuousSum, rightSumToCurrent);
        }
        
        return Math.max(leftMax, Math.max(rightMax, leftContinuousSum + nums[mid] + rightContinuousSum));
    }
}

//version-2: prefix
class Solution {
    public int maxSubArray(int[] nums) {
        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;
        
        for (int num : nums) {
            
            if (currentSum < 0) {
                currentSum = 0;
            }
            
            currentSum += num;
            
            
            maxSum = Math.max(maxSum, currentSum);
        }
        
        return maxSum;
    }
}

//version-3: prefix(refined)
class Solution {
    public int maxSubArray(int[] nums) {
        int prefixSum = 0;
        int maxSum = Integer.MIN_VALUE;
        
        for (int num : nums) {
            prefixSum = Math.max(num, prefixSum + num);
            
            maxSum = Math.max(maxSum, prefixSum);
        }
        
        return maxSum;
    }
}
