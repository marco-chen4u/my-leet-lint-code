/***
* LintCode 406. Minimum Size Subarray Sum
Given an array of n positive integers and a positive integer s, 
find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return -1 instead.

Example
    Example 1:
        Input: [2,3,1,2,4,3], s = 7
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    Example 2:
        Input: [1, 2, 3, 4, 5], s = 100
        Output: -1

Challenge
    If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).
***/
public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        int result = -1; 
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        result = Integer.MAX_VALUE;

        int i = 0, j = 0;

        int size = nums.length;
        int sum = 0;

        for (; i < size; i++) {
            while (j < size && sum < s) {
                sum += nums[j];
                j++;
            }

            if (sum >= s) {
                result = Math.min(result, j - i);				
            }

            sum -= nums[i];
        }

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }	
}
