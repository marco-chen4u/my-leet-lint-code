/***
* LintCode 43. Maximum Subarray III
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

Example
	Example 1
		Input: 
			List = [1,2,3]
			k = 1
		Output: 6
		Explanation: 1 + 2 + 3 = 6

	Example 2
		Input:
			List = [-1,4,-2,3,-2,3]
			k = 2
		Output: 8
		Explanation: 4 + (3 + -2 + 3) = 8

Notice
	The subarray should contain at least one number
***/
public class Solution {
    // field 
    private int DEFAULT_MIN =  Integer.MIN_VALUE;
    
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length < k) {
            return 0;
        }
        
        int size = nums.length;
        
        // state
        int[][] localMax = new int[k + 1][size + 1];
        int[][] globalMax = new int[k + 1][size + 1];
        
        // initialize
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= size; j++) {
                localMax[i][j] = (i == 0) ? 0 : DEFAULT_MIN;
                globalMax[i][j] = 0;
            }
        }
        
        // function
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= size; j++) {
                localMax[i][j] = Math.max(localMax[i][j - 1], globalMax[i - 1][j - 1]) + nums[j - 1];
                
                if (i == j) {
                    globalMax[i][j] = localMax[i][j];
                    continue;
                }
                
                globalMax[i][j] = Math.max(globalMax[i][j - 1], localMax[i][j]);
            }
            
        }
        
        // return
        return globalMax[k][size];
    }
}