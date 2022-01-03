/***
* LintCode 1000. Best Time to Buy and Sell Stock with Transaction Fee
Given an array of integers prices, for which the i-th element is the price of a given stock on day i; 
and a non-negative integer fee representing a transaction fee. (You need to pay fee only on selling.)

You can complete as many transactions as you like, but you need to pay the transaction fee for each transaction. 
You can not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.
    0 < prices.length <= 50000.
    0 < prices[i] < 50000.
    0 <= fee < 50000.
    
Example 1
    Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
    Output: 8
    Explanation: The maximum profit can be achieved by:
        Buying  at prices[0] = 1
        Selling at prices[3] = 8
        Buying  at prices[4] = 4
        Selling at prices[5] = 9
        The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
        
Example 2
    Input: prices = [1, 4, 6, 2, 8, 3, 10, 14], fee = 3
    Output: 13
***/
//version-1: DP
public class Solution {
    /**
     * @param prices: a list of integers
     * @param fee: a integer
     * @return: return a integer
     */
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        
        int[] sold = new int[n + 1];
        int[] hold = new int[n + 1];
        
        hold[1] = - prices[0];
        sold[1] = 0;
        
        for (int i = 2; i <= n; i++) {
            int currentPrice = prices[i - 1];
            
            hold[i] = Math.max(hold[i - 1], sold[i - 1] - currentPrice);
            sold[i] = Math.max(sold[i - 1], hold[i - 1] + currentPrice - fee);
        }

        //System.out.println("hold[1] = " + hold[1]);
        //System.out.println("sold[1] = " + sold[1]);

        return sold[n];
    }
}
