/***
* LintCode 1075. Subarray Product Less Than K
Your are given an array of positive integers nums.

Count and print the number of (contiguous) subarrays where the product of all the elements in the subarray is less than k.

Example
	Example 1:
		Input:  nums = [10, 5, 2, 6], k = 100
		Output:  8	
		Explanation:
		The 8 subarrays that have product less than 100 are: [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6].
		Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
		
	Example 2:
		Input: nums = [5,10,2], k = 10
		Output:  2	
		Explanation:
		Only [5] and [2].
Notice
	0 < nums.length <= 50000.
	0 < nums[i] < 1000.
	0 <= k < 10^6.

***/
public class Solution {
    /**
     * @param nums: an array
     * @param k: an integer
     * @return: the number of subarrays where the product of all the elements in the subarray is less than k
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int result = 0;
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int j = i;
            int product = 1;
            while (j < n && product*nums[j] < k) {
                result +=1;
                product *= nums[j];
                
                j++;
            }
        }
        
        return result;
    }
}