/***
* LintCode 404. Subarray Sum II (DP & Three Pointers)
Given an positive integer array A and an interval. 
Return the number of subarrays whose sum is in the range of given interval.

Example
    Example 1:
        Input: A = [1, 2, 3, 4], start = 1, end = 3
        Output: 4
        Explanation: All possible subarrays: [1], [1, 2], [2], [3].

    Example 2:
        Input: A = [1, 2, 3, 4], start = 1, end = 100
        Output: 10
        Explanation: Any subarray is ok.

Notice
    Subarray is a part of origin array with continuous index.
***/
public class Solution {
    /**
     * @param nums: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] nums, int start, int end) {
        int result = 0;
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        // normal case
        int size = nums.length;
        int[] preSum = new int[size + 1];
        for (int i = 0; i < size; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        
        int left = 0;
        int right = 0;

        for (int i = 0; i <= size; i++) {
            while (left < i && getRange(preSum, left, i) > end) {
                left++;
            }

            while (right < i && getRange(preSum, right, i) >= start) {
                right++;
            }

            result += right - left;
        }

        return result;

    }

    // helper method
    private int getRange(int[] preSum, int i, int j) {
        return preSum[j] - preSum[i];
    }
}
