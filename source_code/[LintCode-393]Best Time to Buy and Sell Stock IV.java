/***
* LintCode 393. Best Time to Buy and Sell Stock IV
Given an array prices and the i-th element of it represents the price of a stock on the i-th day.

You may complete at most k transactions. What's the maximum profit?

Example
	Example 1:
		Input: k = 2, prices = [4, 4, 6, 1, 1, 4, 2 ,5]
		Output: 6
		Explanation: Buy at 4 and sell at 6. Then buy at 1 and sell at 5. Your profit is 2 + 4 = 6.

	Example 2:
		Input: k = 1, prices = [3, 2, 1]
		Output: 0
		Explanation: No transaction.

Challenge
	O(nk) time. n is the size of prices.

Notice
	You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).
***/
//Verison-1 DP
public class Solution {
    // fileds
    private final int DEFAULT_MIN = Integer.MIN_VALUE / 2;
    
    /**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int k, int[] prices) {
        int result = 0;
        //--(1) check corner case
        if (k <= 0 || prices == null || prices.length == 0) {
            return result;
        }
        
        int n = prices.length;
        
        if (k > n / 2) {
            int currentProfit = 0;
            for (int i = 1; i < n; i++) {
                currentProfit = prices[i] - prices[i - 1];// do as many as prossible transactions
                
                result += (currentProfit > 0) ? currentProfit : 0;
            }
            
            return result;
        }
        
        //--(2) normal case
        // state
        int[] hold = new int[k + 1];
        int[] sold = new int[k + 1];
        
        // initialize
        Arrays.fill(hold, DEFAULT_MIN);
        Arrays.fill(sold, DEFAULT_MIN);
        hold[0] = 0;
        sold[0] = 0;
        
        // function
        for (int i = 0; i < n; i++) {
            int[] oldHold = hold.clone();
            int[] oldSold = sold.clone();
            
            for (int j = 1; j <= k; j++) {
                hold[j] = Math.max(oldSold[j - 1] - prices[i], // buy new stock
                                    oldHold[j]);// hold
                                    
                sold[j] = Math.max(oldHold[j] + prices[i], // sell the holding stock
                                    oldSold[j]);// hold
            }
        }
        
        for (int transactionMount : sold) {
            result = Math.max(result, transactionMount);
        }
        
        return result;
    }
}

//Verison-2: DP
public class Solution {
    /**
     * @param K: An integer
     * @param prices: An integer array
     * @return: Maximum profit
     */
    public int maxProfit(int K, int[] prices) {
		int result = 0;
		// check corner case
		if (prices == null || prices.length <= 1) {
			return result;
		}
		
		int n = prices.length;
		if (K > n / 2) {
			int currentProfit = 0;
			for (int i = 1; i < n; i++) {
				currentProfit = prices[i] - prices[i - 1];
				
				result += (currentProfit > 0) ? currentProfit : 0;
			}
			
			return result;
		}
		
		// normal case
		// state		
		int[][] dp = new int[n + 1][(2 * K + 1) + 1];
		
		// initialize
		for (int[] row :dp) {
			Arrays.fill(row, Integer.MIN_VALUE);// which means impossible
		}
		dp[0][1] = 0;
		
		// function
		for (int i = 1; i <= n; i++) {
			// phase 1, 3, 5, ..., 2 * K + 1 no stock holding state
			// dp[i][j] = max{dp[i - 1][j], dp[i - 1][j - 1] + (Pi-1 - Pi-2)}
			for (int j = 1; j <= 2 * K + 1; j += 2) {
				dp[i][j] = dp[i - 1][j];
				
				if (i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
				}
			}
			
			// phase 2, 4, ..., 2 * K stock holding state
			// dp[i][j] = max{dp[i - 1][j] + (Pi-1 - Pi-2), dp[i - 1][j -1]}
			for (int j = 2; j <= 2 * K; j += 2) {
				dp[i][j] = dp[i -1][j - 1];
				
				if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
				}
			}			
		}
		
		// answer
		// get the max Profit sum in the phase 1, 3 and 5
		for (int i = 1; i <= 2 * K + 1; i += 2) {
			result = Math.max(result, dp[n][i]);
		}
		
		return result;
    }
}