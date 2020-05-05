/***
* LintCode 191. Maximum Product Subarray
Find the contiguous subarray within an array (containing at least one number) which has the largest product.
Example
	Example 1:
		Input:[2,3,-2,4]
		Output:6
	Example 2:
		Input:[-1,2,4,1]
		Output:8
Notice
	The product of the largest subsequence of the product, less than 2147483647
***/
//version-1 very traditional(time complexity: O(n), space complexity: O(n))
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
		int result = 0;
		// chec corner case
		if (nums == null || nums.length == 0) {
			return result;
		}
		
		int size = nums.length;
		int[] minProducts = new int[size];
		int[] maxProducts = new int[size];
		
		// initialize
		result = maxProducts[0] = minProducts[0] = nums[0];
		
		for (int i = 1; i < size; i++) {
			maxProducts[i] = minProducts = nums[i];
			
			if (nums[i] > 0) {
				maxProducts[i] = Math.max(maxProducts[i], maxProducts[i - 1] * nums[i]);
				minProducts[i] = Math.min(minProducts[i], minProducts[i - 1] * nums[i]);
			}
			else {
				maxProducts[i] = Math.max(maxProducts[i], minProducts[i - 1] * nums[i]);
				minProducts[i] = Math.min(minProducts[i], maxProducts[i - 1] * nums[i]);
			}
			
			result = Math.max(result, maxProducts[i]);
		}
		
		return result;
	}
}

//version-2  better solution(time complexity: O(n), space complexity: O(n))
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int maxProduct(int[] nums) {
		int result = 0;
        // check corner case
		if (nums == null || nums.length == 0) {
			return result;
		}
		
		int size = nums.length;
		
		int min = nums[0], max = nums[0];
		int preMin = nums[0], preMax = nums[0];
		result = nums[0];
		for (int i  = 1; i < size; i++) {
			int current = nums[i];
			max = Math.max(current, Math.max(preMax * current, preMin * current));
			min = Math.min(current, Math.min(preMax * current, preMin * current));
			
			preMax = max;
			preMin = min;
			
			result = Math.max(result, max);
		}
		
		retur result;
    }
}

