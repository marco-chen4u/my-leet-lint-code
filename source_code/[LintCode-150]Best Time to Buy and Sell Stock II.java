/***
* LintCode 150. Best Time to Buy and Sell Stock II
Given an array prices, which represents the price of a stock in each day.

You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). 
However, you may not engage in multiple transactions at the same time 
(ie, if you already have the stock, you must sell it before you buy again).

Design an algorithm to find the maximum profit.


Example 1:
    Input: [2, 1, 2, 0, 1]
    Output: 2
    Explanation: 
        1. Buy the stock on the second day at 1, and sell the stock on the third day at 2. Profit is 1.
        2. Buy the stock on the 4th day at 0, and sell the stock on the 5th day at 1. Profit is 1.
        Total profit is 2.
Example 2:
    Input: [4, 3, 2, 1]
    Output: 0
    Explanation: No transaction, profit is 0.
***/
//version-1: Greedy
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int result = 0;
        if (prices == null || prices.length == 0) {
            return result;
        }
        
        int currentProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            currentProfit = prices[i] - prices[i - 1];
            
            result += (currentProfit > 0) ? currentProfit : 0;
        }
        
        return result;
    }
}

//version-2: Greedy
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int[] profits = new int[size];
        for (int i = 1; i < size; i++) {
            profits[i] = prices[i] - prices[i - 1];
        }

        int result = 0;
        for (int profit : profits) {
            result += profit > 0 ? profit : 0;
        }

        return result;
    }
}


//version-3: DP
/**
* 当在最后一天时，所获得的利润最大值，取决于2个条件
 dp[n] = Math.max(dp[n - 1]               //没有选择当天最后一天的利润所得，以计算全局最大的利润所得
                   ，                     //或者
                  dp[n - 1] + profit[n])  //选择当天最后一天的利润所得，以计算全局作答的利润所得
                  
                  两者取其1（最大值的），这是背包问题的经典动归处理
**/
public class Solution {
    /**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        int size = prices.length;
        int[] profits = new int[size];
        for (int i = 1; i < size; i++) {
            profits[i] = prices[i] - prices[i - 1];
        }

        int[] dp = new int[size + 1];
        Arrays.fill(dp, 0);

        for (int i = 1; i <= size; i++) {
            // dp[i - 1]                   表示前一天的所有最大利润获得【dp[i - 1]】，没有选择第i天【profits[i - 1]】的利润所得
            // dp[i - 1] + profits[i - 1]  表示前一天的所得利润并选择加上第i天的利润所得
            dp[i] = Math.max(dp[i - 1], dp[i - 1] + profits[i - 1]);
        }

        return dp[size];
    }
}
