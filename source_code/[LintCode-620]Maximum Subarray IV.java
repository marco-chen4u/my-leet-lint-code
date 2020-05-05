/***
* LintCode 620. Maximum Subarray IV
Given an integer arrays, find a contiguous subarray which has the largest sum and length should be greater or equal to given length k.
Return the largest sum, return 0 if there are fewer than k elements in the array.

Example
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
        // check corner case
		if (nums == null || nums.length < k) {
			return result;
		}
		
		int size = nums.length;
		
		int[] prefixSum = new int[size + 1];
		for (int i = 1; i <= size; i++) {
			prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
		}
		
		result = prefixSum[size];
		
		int min = 0;
		for (int i = k; i <= size; i++) {
			if (prefixSum[i] - min > result) {
				result = prefixSum[i] - min;
			}
			
			min = Math.min(min, prefixSum[i - k + 1]);
		}
		
		
		return result;
    }
}