/***
* LintCode 603. Largest Divisible Subset
Given a set of distinct positive integers, find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

Example 1:
    Input: nums =  [1,2,3], 
    Output: [1,2] or [1,3]
	
Example 2:
    Input: nums = [1,2,4,8], 
    Output: [1,2,4,8]

Notice
    If there are multiple solutions, return any subset is fine.
***/
public class Solution {
    /*
     * @param nums: a set of distinct positive integers
     * @return: the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();

        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        int n = nums.length;

        // state
        int[] dp = new int[n];
        int[] preIndexes = new int[n];
		
        // initialize
        Arrays.fill(dp, 1);
		
        // function
        for (int i = 0; i < n; i++) {
            preIndexes[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                    preIndexes[i] = j;
                }
            }
        }

        int max = Integer.MIN_VALUE;
        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > max) {
                max = dp[i];
                maxIndex = i;
            }
        }

        result.add(nums[maxIndex]);

        while (maxIndex != preIndexes[maxIndex]) {
            maxIndex = preIndexes[maxIndex];
            result.add(nums[maxIndex]);
        }

        return result;
    }
}
