/***
* LintCode 603. Largest Divisible Subset
Given a set of distinct positive integers, 
find the largest subset such that every pair (Si, Sj) of elements in this subset satisfies: Si % Sj = 0 or Sj % Si = 0.

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
    /**
     * @param nums: a set of distinct positive integers
     * @return: the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        int n = nums.length;
	    
	//[1]initialize
        int[] dp = new int[n];// the count of last divisible num subset for each position on the sorted array
        Arrays.fill(dp, 1);

        int[] preIndex = new int[n];// mark each position's num previous divisor prvious neighboring position
        for (int i = 0; i < n; i++) {
            preIndex[i] = i;
        }

	//[2]function
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] != 0) {
                    continue;
                }

                if (dp[i] >= dp[j] + 1) {
                    continue;
                }

                dp[i] = dp[j] + 1;
                preIndex[i] = j; // mark the current's previous divisible num position
            }
        }
	    
	//[3] return

        // find the max divisable num and it's prev-index(of pervious neighboring position)
        int maxCount = 0;
        int currentPos = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] > maxCount) {
                maxCount = dp[i];
                currentPos = i;
            }
        }

        result.add(0, nums[currentPos]);

        while (currentPos != preIndex[currentPos]) {
            currentPos = preIndex[currentPos];
            result.add(0, nums[currentPos]);            
        }

        return result;

    }
}
