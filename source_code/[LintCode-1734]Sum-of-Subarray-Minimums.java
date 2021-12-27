/***
* LintCode 1734. Sum of Subarray Minimums
Given an array of integers A, find the sum of min(B), where B ranges over every (contiguous) subarray of A.

Since the answer may be large, return the answer modulo 10^9 + 7.

Note:
    1≤A≤30000
    1≤A[i]≤30000
    
Example 1:
    Input: [3,1,2,4]
    Output: 17
    Explanation: 
        Subarrays are [3], [1], [2], [4], 
                        [3,1], [1,2], [2,4], 
                        [3,1,2], [1,2,4], 
                        [3,1,2,4]. 
        Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.  Sum is 17.

Example 2:
    Input: [95,58,46,67,100]
    Output: 859
    Explanation: 
        Subarrays are [95], [58], [46], [67], [100], 
                        [98,58], [58,46], [46,67], [67,100], 
                        [95,58,67],[46,67,100],
                        [95,58,46,67],[58,46,67,100],
                        [95,58,46,67,100]. 
        Minimums are 95, 58, 46, 67, 100, 58, 46, 46, 67, 46, 46, 46, 46, 46, 46.  Sum is 859.

***/
//version-1: enumeration
public class Solution {
    private long MOD_VALUE = (long)(1_000_000_000 + 7);
    /**
     * @param nums: an array
     * @return: the sum of subarray minimums
     */
    public int sumSubarrayMins(int[] nums) {
        long result = 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return (int)result;
        }

        // normal case
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int min = nums[i];
            result += min;

            for (int j = i + 1; j < size; j++) {
                min = Math.min(min, nums[j]);

                result += min;
            }
        }

        result = (long) (result % MOD_VALUE);

        return (int) result;
    }
}
