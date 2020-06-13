/***
* LintCode 149. Best Time to Buy and Sell Stock
Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Example
    Example 1
        Input: [3, 2, 3, 1, 2]
        Output: 1
        Explanation: You can buy at the third day and then sell it at the 4th day. The profit is 2 - 1 = 1

    Example 2
        Input: [1, 2, 3, 4, 5]
        Output: 4
        Explanation: You can buy at the 0th day and then sell it at the 4th day. The profit is 5 - 1 = 4

    Example 3
        Input: [5, 4, 3, 2, 1]
        Output: 0
        Explanation: You can do nothing and get nothing.
***/
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

        int maxPrice = Integer.MIN_VALUE;
        int minPrice = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            minPrice = Math.min(minPrice, current);

            if (minPrice == current) {
                maxPrice = Integer.MIN_VALUE;
                continue;
            }

            maxPrice = Math.max(maxPrice, current);

            result = Math.max(result, maxPrice - minPrice);
        }// for

        return result;
    }
}


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

        result = Integer.MIN_VALUE;
        int min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            result = Math.max(result, current - min);

            min = Math.min(min, current);
        }

        return result;
    }
}
