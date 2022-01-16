/***
* LintCode 168. Burst Ballons
Given n balloons, indexed from 0 to n-1. 
Each balloon is painted with a number on it represented by array nums. 
You are asked to burst all the balloons. 
If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. 
Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Example 1:
    Input：[4, 1, 5, 10]
    Output：270
    Explanation：
        nums = [4, 1, 5, 10] burst 1, get coins 4 * 1 * 5 = 20
        nums = [4, 5, 10]   burst 5, get coins 4 * 5 * 10 = 200 
        nums = [4, 10]    burst 4, get coins 1 * 4 * 10 = 40
        nums = [10]    burst 10, get coins 1 * 10 * 1 = 10
        Total coins 20 + 200 + 40 + 10 = 270
	
Example 2:
    Input：[3,1,5]
    Output：35
    Explanation：
        nums = [3, 1, 5] burst 1, get coins 3 * 1 * 5 = 15
        nums = [3, 5] burst 3, get coins 1 * 3 * 5 = 15
        nums = [5] burst 5, get coins 1 * 5 * 1 = 5
        Total coins 15 + 15 + 5  = 35

Notice
    --You may imagine nums[-1] = nums[n] = 1. 
        They are not real therefore you can not burst them.
    --0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
***/
/*
* 反向思维，从最后一个被扎破的气球分析起，观察最后一个被扎破的球球，分为左右2个空间
  设dp[i][j]为扎破i+1~j-1号气球，最多获得的金币数。
  dp[i][j] = Max{dp[i][k] + dp[k][j] + nums[i]*nums[k]*nums[j]}, i<k<j
*/

//version-1 memorized search
public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int n = nums.length;
        
        int[][] dp = new int[n + 2][n + 2];
        boolean[][] visited = new boolean[n + 2][n + 2];
        
        int[] values = new int[n + 2];
        values[0] = values[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }
        
        return search(1, n, visited, dp, values);
    }
    
    // helper method
    private int search(int left, int right, 
                        boolean[][] visited, int[][] dp, 
                        int[] values) {
        if (visited[left][right]) {
            return dp[left][right];
        }
        
        if (left > right) {
            return 0;
        }
        
        for (int i = left; i <= right; i++) {
            int leftMax = search(left, i - 1, visited, dp, values);// burst left part of i
            int rightMax = search(i + 1, right, visited, dp, values); // burst  right part of i
            int current = values[left - 1] * values[i] * values[right + 1]; // now only i, values[0] and values[n + 1] left to burst 
            int value = leftMax + current + rightMax;
            
            dp[left][right] = Math.max(dp[left][right], value);
        }
        
        visited[left][right] = true;
        return dp[left][right];
    }
}

//version-2 dp(space type dp)
public class Solution {
    /**
     * @param nums: A list of integer
     * @return: An integer, maximum coins
     */
    public int maxCoins(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;

        int[] values = new int[n + 2];
        values[0] = values[n + 1] = 1;
        for (int i = 1; i <= n; i++) {
            values[i] = nums[i - 1];
        }

        int[][] dp = new int[n + 2][n + 2];

        for (int length = 3; length <= n + 2; length++) {
            for (int i = 0; i <= n + 2 - length; i++) {
                int j = i + length - 1;
                dp[i][j] = 0;

                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j],
                                        dp[i][k] + 
                                        dp[k][j] + 
                                        values[i]* values[k] * values[j]);
                } // for k				
            } // for i
        } // for length

    return dp[0][n + 1];
    }
}
