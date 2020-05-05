/***
* LintCode 395. Coins in a Line II
There are n coins with different value in a line. 
Two players take turns to take one or two coins from left side until there are no more coins left. 
The player who take the coins with the most value wins.

Could you please decide the first player will win or lose?
If the first player wins, return true, otherwise return false.

Example
	Example 1:
		Input: [1, 2, 2]
		Output: true
		Explanation: The first player takes 2 coins.
	Example 2:
		Input: [1, 2, 4]
		Output: false
		Explanation: Whether the first player takes 1 coin or 2, the second player will gain more value.
***/
/*
* 如果先手拿到超过总价值的一半，那么先手就必胜
  确定状态：
		dp[i] 在剩下i个coins的时候先手最多可以获得的价值，即双方在剩下的i个coins时先手最多可以获得的价值。
		
		coins : 4, 1, 2, 3
		最大化dp[4]? 子状态有dp[3]和dp[2]， （而dp[3]是剩下的3个coins[1,2,3]的情况下的状态值，dp[2]是剩下2个coins[2,3]的情况下的状态值)
			-想要先手最大，因为总和固定不变，只需保证先手取完coins后，对方能取到的价值最小（即如何坑对方）
			-去看dp[4]中的2个子状态(dp[3]和dp[2])更小，如果dp[3]<dp[2], 那么，先手就偏向于去1个coin，使得对方陷入3个coins的状态，反之同理。
		因此：
			dp[i] = sum[i] - min{dp[i - 1], dp[i - 2]}
			注意： sum[i]当前剩下i个coins的总价值，所以其值是前缀和的反向处理之和，即sum[i] = values[n - 1] + values[n - 2] + ... + values[n - i]
					所以sum[i]的值， 是反向从最后一个开始累加，共i个的item的值。
						// coins :   4, 1, 2, 3
						// values = {4, 1, 2, 3}, size = 4
						sum[i]: sum[4] = 4 + 1 + 2 + 3 = 10, 
							   sum[3] = 1 + 2 + 3 = 6
							   sum[2] = 2 + 3 = 5
							   sum[1] = 3 
						
		
*/
public class Solution {
    /**
     * @param values: a vector of integers
     * @return: a boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int[] values) {
        
        int n = values.length;
        int[] suffixSum = new int[n + 1];//后缀和
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            suffixSum[i] = suffixSum[i - 1] + values[n - i];//add last i one
            sum += values[i - 1];
        }
        
        int[] dp = new int[n + 1];
        dp[0] = 0;// 剩下0个coin的情况
        dp[1] = values[n - 1];// 剩下1个coin的情况 last one left
        
        for (int i = 2; i <= n; i++) {
            int currentSum = suffixSum[i];// 当前剩下i个coins的总价值
            dp[i] = currentSum - Math.min(dp[i - 1], dp[i - 2]);
        }
        
        return dp[n] > sum / 2;//dp[n] > suffixSum[n] / 2;
    }
}