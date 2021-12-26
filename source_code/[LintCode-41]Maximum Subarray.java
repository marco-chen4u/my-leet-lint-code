/***
* LintCode 41. Maximum Subarray
Given an array of integers, find a contiguous subarray which has the largest sum.


Example1:
    Input: [−2,2,−3,4,−1,2,1,−5,3]
    Output: 6
    Explanation: the contiguous subarray [4,−1,2,1] has the largest sum = 6.

Example2:
    Input: [1,2,3,4]
    Output: 10
    Explanation: the contiguous subarray [1,2,3,4] has the largest sum = 10.

Challenge
    Can you do it in time complexity O(n)?

Notice
    The subarray should contain at least one number.
***/
//version-1: DFS
public class Solution {
    // helper method
    private int helper(int[] nums, int left, int right) {
        if (left > right) {
            return Integer.MIN_VALUE;
        }

        int mid = left + (right - left) / 2;		
        int leftMax = helper(nums, left, mid - 1);		
        int rightMax = helper(nums, mid + 1, right);

        int prefixSum = 0;
        int maxOfLeftContiguous = 0;
        for (int i = mid - 1; i >= left; i--) {
            prefixSum += nums[i];
            maxOfLeftContiguous = Math.max(maxOfLeftContiguous, prefixSum);
        }

        prefixSum = 0; // reset for the right side
        int maxOfRightContiguous = 0;
        for (int j = mid + 1; j <= right; j++) {
            prefixSum += nums[j];
            maxOfRightContiguous = Math.max(maxOfRightContiguous, prefixSum);
        }

        int centerMax = maxOfLeftContiguous + nums[mid] + maxOfRightContiguous;

        return Math.max(leftMax, Math.max(rightMax, centerMax));
    }
	
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return helper(nums, 0, nums.length - 1);
    }
}

//version-2: prefixSum calculation
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A integer indicate the sum of max subarray
     */
    public int maxSubArray(int[] nums) {
        int result = Integer.MIN_VALUE;

        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum = Math.max(nums[i], prefixSum + nums[i]);

            if (prefixSum > result) {
                result = prefixSum;
            }
        }

        return result;
    }
}
