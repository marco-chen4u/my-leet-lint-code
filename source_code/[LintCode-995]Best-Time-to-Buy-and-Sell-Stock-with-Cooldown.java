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


//version-2: DP
/**
* 解题思路：有2中状态【buy-买入状态, sell-买出状态(包含休息状态)】，在2种状态随着天数增加，而发生不同的状态转移。
  buy[n + 1]
  sell[n + 1]
初始状态：
  buy[0] = MIN_VALUE //方便打擂台（之前的状态只比较）
  sell[0] = 0;
  
状态变换及演变：
  buy[i] = Math.max(buy[i - 1], (i == 1? 0 : sell[i - 2]) - currentPrice) // buy[i - 1] 为前一天的买入（持有)状态，即保持前一天的状态
                                                                          // sell[i - 2] 前2天(卖后需要cooldown 1天)交易完所得减计当天买入的股票支持成本
                                                                          
  sell[i] = Math.max(sell[i - 1], buy[i - 1] + currentPrice)              // sell[i - 1] 为前一天的卖出股票（持有)后的状态，即保持前一天的状态，属于休息状态
                                                                          // 前一天的状态（注意：这里只能是买入状态题， 可能是：持有状态或卖出cooldown完毕后的状态），加上今天卖出股票的获得
结果：
 Math.max(buy[n], sell[n])
**/
public class Solution {
    /**
     * @param prices: a list of integers
     * @return: return a integer
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        int[] sell = new int[n + 1];
        int[] buy = new int[n + 1];

        sell[0] = 0;
        buy[0] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            int currentPrice = prices[i - 1];
            
            buy[i] = Math.max(buy[i - 1], (i == 1 ? 0: sell[i - 2]) - currentPrice);
            sell[i] = Math.max(sell[i -1], buy[i - 1] + currentPrice);
        }

        return Math.max(buy[n], sell[n]);
    }
}
