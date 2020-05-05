/***
* LintCode 669. Coin Change
You are given coins of different denominations and a total amount of money amount. 
Write a function to compute the fewest number of coins that you need to make up that amount. 

If that amount of money cannot be made up by any combination of the coins, return -1.

Example
	Example 1
		Input: 
			[1, 2, 5]
			11
		Output: 3
		Explanation: 11 = 5 + 5 + 1
	Example 2
		Input: 
			[2]
			3
		Output: -1
***/
// version-1: memorization search(Runtime Error, amount <= 2685, otherwise it will get runtime exception)
public class Solution {
    /**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        // check corner case
		if (coins == null || coins.length == 0) {
			return -1;
		}
		
		if (amount < 1) {
			return 0;
		}
		
		return findChange(coins, amount, new int[amount]);
    }
	
	// helper methods
	private int findChange(int[] coins, int remain, int[] count) {
		// check corner cases
		if (remain < 0) {
			return -1;
		}
		
		if (remain == 0) {
			return 0;
		}
		
		if (count[remain - 1] != 0) {
			return count[remain - 1];
		}
		
		// normal case
		int min = Integer.MAX_VALUE;
		
		for (int coin : coins) {
			if (coin == 0) {
				continue;
			}
			
			int result = findChange(coins, remain - coin, count);
			
			if (result >= 0 && result < min) {
				min = result + 1;
			}
		}
		
		count[remain - 1] = (count[remain - 1] == Integer.MAX_VALUE) ? -1 : min;
		
		return count[remain - 1];
	}
}
//version-2 DP
/*
一般来说，使用动态规划解决问题的流程： 确定状态->设计转移方程->确定初始状态边界->按照一定顺序计算
*/
public class Solution {
    // fields
    private final int MAX = Integer.MAX_VALUE;
    private final int DEFAULT = -1;
    
    /**
     * @param coins: a list of integer
     * @param amount: a total amount of money amount
     * @return: the fewest number of coins that you need to make up
     */
    public int coinChange(int[] coins, int amount) {
        // check corner case
        if (coins == null || coins.length == 0) {
            return DEFAULT;
        }
        
        if (amount == 0) {
            return 0;
        }
        
        // state
		// coins = {2, 5, 7}
        // f(x) = min{f(x - 2), f(x - 5), f(x - 7)} + 1
        int[] dp = new int[amount + 1];
        
        // initialize
        // f(0) = 0
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        
        // function
        for (int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != MAX) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        
        dp[amount] = (dp[amount] == MAX) ? DEFAULT : dp[amount];
        
        return dp[amount];
    }
}