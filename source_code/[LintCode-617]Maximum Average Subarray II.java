/***
* LintCode 617. Maximum Average Subarray II
Given an array with positive and negative numbers, 
find the maximum average subarray which length should be greater or equal to given length k.

Example
	Example 1:
		Input:
			[1,12,-5,-6,50,3]
			3
		Output:
			15.667
		Explanation:
			(-6 + 50 + 3) / 3 = 15.667
		
	Example 2:
		Input:
			[5]
			1
		Output:
			5.000	
Notice
	It's guaranteed that the size of the array is greater or equal to k.
***/
public class Solution {
	private final double EPS = 1e-8;
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // check corner cases
		if (nums == null || nums.length == 0 || k <= 0) {
			return 0;
		}
		
		int size = nums.length;
		int min = nums[0];
		int max = nums[0];
		for (int num : nums) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		
		double start = (double)min;
		double end = (double)max;
		while (start + EPS < end) {
			double mid = start + (end - start) / 2;
			if (isVailableToAverage(nums, k, mid)) {
				start = mid;
			}
			else {
				end = mid;
			}
		}
		
		if (isVailableToAverage(nums, k, start)) {
			return start;
		}
		else {
			return end;
		}		
    }
	
	// helper method
	/***
	 * check the target value is avaible to average the nums where its subarray (size >= k) can achieve
	***/
	private boolean isVailableToAverage(int[] nums, int k, 
										double averageCandidate) {
		int size = nums.length;
		double preSumRight = 0, preSumLeft = 0, minPreSumLeft = 0;
		//loading the window(size of k) for the right preSum
		for (int i = 0; i < k; i++) {
			preSumRight += (nums[i] - averageCandidate);
		}
		
		// start search max {preSumRight - preSumLeft} that satisify the relative contidtions
		for (int i = k; i <= size; i++) {
			if (preSumRight - minPreSumLeft >= 0) {// once satisify the conditions
				return true;
			}
			
			if (i < size) {
				preSumLeft += (nums[i - k] - averageCandidate);
				preSumRight += (nums[i] - averageCandidate);
				
				minPreSumLeft = Math.min(minPreSumLeft, preSumLeft);
			}
		}
		
		return false;
	}
}