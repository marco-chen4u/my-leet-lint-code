/***
* LintCode 44. Minimum Subarray
Given an array of integers, find the continuous subarray with smallest sum.

Return the sum of the subarray.

Note:
    The subarray should contain one integer at least.
    
Example 1:
    Input:
        array = [1,-1,-2,1]
    Output:
        -3
    Explanation:
        The sum of the sub-arrays [-1,-2] is the minimum value -3.

Example 2:
    Input:
        array = [1,-1,-2,1,-4]
    Output:
        -6
    Explanation:
        The sum of the sub-arrays [-1,-2,1,-4] is the minimum value -6.
***/
//version-1: prefix sum calculation
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: A integer indicate the sum of minimum subarray
     */
    public int minSubArray(List<Integer> nums) {
        int result = Integer.MAX_VALUE;

        int prefixSum = 0;
        for (int num: nums) {
            prefixSum = Math.min(prefixSum + num, num);

            result = Math.min(result, prefixSum);
        }

        return result;
    }
}
