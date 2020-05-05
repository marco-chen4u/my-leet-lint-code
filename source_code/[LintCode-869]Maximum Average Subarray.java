/***
* LintCode 868. Maximum Average Subarray
Given an array of integers, find the continuous subarray of the given length of k that has the maximum average value.
You need to output the maximum average value.

Example
	Example1
		Input:  nums = [1,12,-5,-6,50,3] and k = 4
		Output: 12.75
		Explanation:
			Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

	Example2
		Input:  nums = [4,2,1,3,3] and k = 2
		Output: 3.00
		Explanation:
			Maximum average is (3+3)/2 = 6/2 = 3.00
Notice
	1.1 <= k <= n <= 30,000.
	2.Elements of the given array will be in the range [-10,000, 10,000].
***/
public class Solution {
	/**
	* @param nums: an array
	* @param k: an integer
	* @return : the maximum average value
	*/
	public double findMaxAverage(int[] nums, int k) {
		double result = 0;
		// check corner case
		if (nums == null || nums.length == 0 || k <= 0) {
			return result;
		}
		
		double max = 0;
		int sum = 0;
		int size = nums.length;
		for (int i = 0; i< size; i++) {
			if (i >= k) {
				sum -= nums[i - k];
			}
			
			sum += nums[i];
			
			if (i >= k - 1) {
				max = Math.max(max, sum);
			}
		}
		
		result = max / k;
		
		return result;
	}
}