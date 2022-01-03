/***
* LintCode 995. Best Time to Buy and Sell Stock with Cooldown
Suppose you have an array where the ith element represents the price of the given stock on the ith day.

Design an algorithm to find the maximum profit. 
You may complete as many transactions as you like 
(ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)


Example 1:
    Input: [1, 2, 3, 0, 2]
    Output: 3
    Explanation:
        transactions = [buy, sell, cooldown, buy, sell]

Example 2:
    Input: [3,2,6,5,0,3]
    Output: 7

***/

//version-1: DP
/**
* 解题思路：重要有3种状态【hold-持有状态（包含买入），sold-交易卖出状态， rest-休息状态】，在3种状态随着天数增加，而发生不同的状态转移
  hold[n + 1]
  sold[n + 1]
  rest[n + 1]
初始状态：
  hold[0] = 0;
  sold[0] = 0;
  rest[0] = MIN_VALUE; // 方便打擂台（之前的状态只比较）
  
状态变换及演变：
  hold[i] = Math.max(hold[i - 1], hold[i - 1] - prices[i]) // hold[i - 1]:为前一天的持有状态，即保持前一天的状态，
                                                           //(hold[i - 1] - prices[i]) : 买入当前的股票，做减计处理
                                                           // 所以持有持有状态，是2种状态的其中之一，两者择优取其一.
                                                           
  sold[i] = hold[i - 1] + prices[i]                        // 前一天的持有状态 + 当前卖出的收获。
  
  rest[i] = Math.max(hold[i - 1], sold[i - 1])             // 前一天的持有状态, 或者是， 前一天的卖出所得状态，两者择优取其一。
  
结果：
  Math.max(rest[n], sold[n])
**/
public class Solution {
    /**
     * @param prices: a list of integers
     * @return: return a integer
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[] sold = new int[n + 1];
        int[] rest = new int[n + 1];
        int[] hold = new int[n + 1];

        sold[0] = 0;
        rest[0] = 0;
        hold[0] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int currentPrice = prices[i - 1];
            
            sold[i] = hold[i - 1] + currentPrice;
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - currentPrice);
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
        }

        return Math.max(rest[n], sold[n]);
    }
}
