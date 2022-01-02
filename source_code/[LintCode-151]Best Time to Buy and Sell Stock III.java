/***
* LintCode 151. Best Time to Buy and Sell Stock III
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Example
    Example 1
        Input : [4,4,6,1,1,4,2,5]
        Output : 6
Notice
    You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
***/
//version-1: scan line solution
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        // check corner case
        if (prices == null || prices.length == 0) {
            return result;
        }
        
        int size = prices.length;
        
        int[] left = new int[size];
        int[] right = new int[size];
        
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < size; i++) {
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            }
            else if (prices[i] >= minPrice) {
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
            
            left[i] = maxProfit;
        }
        
        int maxPrice = Integer.MIN_VALUE;
        maxProfit= 0;
        for (int i = size - 1; i >= 0; i--) {
            if (prices[i] > maxPrice) {
                maxPrice = prices[i];
            }
            else if (prices[i] <= maxPrice) {
                maxProfit = Math.max(maxProfit, maxPrice - prices[i]);
            }
            
            right[i] = maxProfit;
        }// for
        
        for (int i = 0; i < size; i++) {
            result = Math.max(result, left[i] + right[i]);
        }
        
        return result;
    }
}

//version-2: DP, backpack solution
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        // check corner case
        if (prices == null || prices.length <= 1) {
            return result;
        }

        // state
        int n = prices.length;
        int[][] dp = new int[n + 1][5 + 1];

        // initialize
        for (int[] row :dp) {
            Arrays.fill(row, Integer.MIN_VALUE);// which means impossible
        }
        dp[0][1] = 0;

        // function
        for (int i = 1; i <= n; i++) {
            // phase 1, 3, 5 no stock holding state
            // dp[i][j] = max{dp[i - 1][j], dp[i - 1][j - 1] + (Pi-1 - Pi-2)}
            for (int j = 1; j <= 5; j += 2) {
                dp[i][j] = dp[i - 1][j];

                if (i > 1 && dp[i - 1][j - 1] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + prices[i - 1] - prices[i - 2]);
                }
            }

            // phase 3, 4 stock holding state
            // dp[i][j] = max{dp[i - 1][j] + (Pi-1 - Pi-2), dp[i - 1][j -1]}
            for (int j = 2; j <= 5; j += 2) {
                dp[i][j] = dp[i -1][j - 1];

                if (i > 1 && dp[i - 1][j] != Integer.MIN_VALUE) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + prices[i - 1] - prices[i - 2]);
                }
            }			
        }

        // answer
        // get the max Profit sum in the phase 1, 3 and 5
        for (int i = 1; i <= 5; i += 2) {
            result = Math.max(result, dp[n][i]);
        }

        return result;
    }
}

//version-3: dp(backpack) traditional solution
public class Solution {
    
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // check corner case
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        int n = prices.length;
        int[] profits = new int[n];
        for (int i = 1; i < n; i++) {
            profits[i] = prices[i] - prices[i - 1];
        }

        // state
        int k = 2;
        int[][] local = new int[n + 1][k + 1];
        int[][] global = new int[n + 1][k + 1];

        // intialize
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j < k; j++) {
                local[i][j] = 0;
                global [i][j] = 0; 
            }
        }

        // calculate
        for (int i = 1; i <= n; i++) {
            int currentProfit = profits[i - 1];
            for (int j = 1; j <= k; j++) {
                if (i == j) {
                    local[i][j] = local[i - 1][j - 1] + currentProfit;
                    global[i][j] = global[i - 1][j - 1] + currentProfit;
                }
                else {
                    local[i][j] = Math.max(local[i - 1][j], global[i - 1][j - 1]) + currentProfit;
                    global[i][j] = Math.max(global[i - 1][j], local[i][j]);
                }
            }
        }

        // return
        return global[n][k] > 0 ? global[n][k] : 0;
        
    }
}
