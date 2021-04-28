/***
* LeetCode 55. Jump Game
Given an array of non-negative integers, you are initially positioned at the first index of the array.
Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:
    Input: [2,3,1,1,4]
    Output: true
    Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.

Example 2:
    Input: [3,2,1,0,4]
    Output: false
    Explanation: You will always arrive at index 3 no matter what. Its maximum
                    jump length is 0, which makes it impossible to reach the last index.
***/
//version-1: dynamic programming
class Solution {
    public boolean canJump(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return false;
        }
        
        int n = nums.length;
        int startPos = 0;
        int lastPos = n - 1;
        
        // state
        boolean[] dp = new boolean[n];
        
        // initialize
        Arrays.fill(dp, false);
        dp[0] = true;
        
        // function
        for (int i = 1; i <= lastPos; i++) {
            for (int j = startPos; j < i; j++) {
                if (dp[j] && nums[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        // answer
        return dp[lastPos];
    }
}
