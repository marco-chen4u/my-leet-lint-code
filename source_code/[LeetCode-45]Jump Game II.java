/***
* LeetCode 45. Jump Game II
You are given a 0-indexed array of integers nums of length n. 
You are initially positioned at nums[0].

Each element nums[i] represents the maximum length of a forward jump from index i. 
In other words, if you are at nums[i], you can jump to any nums[i + j] where:
    0 <= j <= nums[i] and
    i + j < n
    Return the minimum number of jumps to reach nums[n - 1]. 
    The test cases are generated such that you can reach nums[n - 1].

Example 1
    Input: nums = [2,3,1,1,4]
    Output: 2
    Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2
    Input: nums = [2,3,0,1,4]
    Output: 2

Constraints
    1 <= nums.length <= 104
    0 <= nums[i] <= 1000
    It's guaranteed that you can reach nums[n - 1].

Link:
    https://leetcode.com/problems/jump-game-ii/
***/
//solution-1: dp
/*
step(1)
    dp[n] : each postion to jump having the min jump steps

step(2)
    dp[0] = 0;

step(3)
    i : current postion to jump
    j : previous position have jumped
    stepSize = nums[j]
    dp[i] = (j + stepSize >= i) ? dp[j] + 1 : dp[i]

step(4)
    return dp[n - 1]
*/
class Solution {
    public int jump(int[] nums) {

        int n = nums.length;

        int[] dp = new int[n];//each postion has the min step to reach
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int i = 1; i < n; i++) {

            for (int j = 0; j < i; j++) {
                if (dp[j] == Integer.MAX_VALUE) {
                    continue;
                }

                int stepSize = nums[j];

                if (j + stepSize < i) {
                    continue;
                }
                
                dp[i] = Math.min(dp[i], dp[j] + 1);
            }
        }

        return dp[n - 1];
        
    }
}

//solution-2: dp
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // State
        int[] dp = new int[nums.length];
        
        // Initialize
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }
        
        // Function
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] + j >= i && dp[j] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[j] + 1, dp[i]);
                }  
            }
        }
        
        // Answer
        return dp[nums.length - 1];
    }
}
