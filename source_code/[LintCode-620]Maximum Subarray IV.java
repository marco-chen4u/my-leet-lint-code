/***
* LintCode 620. Maximum Subarray IV
Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or equal to given length k.
Return the largest sum, return 0 if there are fewer than k elements in the array.

Example 1:
    Input:
        [-2,2,-3,4,-1,2,1,-5,3]
        5
    Output:
        5
    Explanation:
        [2,-3,4,-1,2,1]
        sum=5

Example 2:
    Input:
        [5,-10,4]
        2
    Output:
        -1

Notice
    1.Ensure that the result is an integer type.
    2.k > 0
***/
public class Solution {
    /**
     * @param nums: an array of integer
     * @param k: an integer
     * @return: the largest sum
     */
    public int maxSubarray4(int[] nums, int k) {
        int result = 0;
        if (nums == null || nums.length < k) {
            return result;
        }

        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        int min = 0;
        result = Integer.MIN_VALUE;
        for (int i = k; i <= n; i++) {
            int sum = prefixSum[i];
            result = Math.max(result, sum - min);

            min = Math.min(min, prefixSum[i - k + 1]);
        }

        return result;
    }
}
