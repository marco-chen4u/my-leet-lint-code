/***
* LintCode 719. Calculate Maximum Value
Given a string of numbers, write a function to find the maximum value calculated one by one from front to back, 
you can add + or * between two numbers.

Example 1
    Input:  str = "01231"
    Output: 10
    Explanation: 
        ((((0 + 1) + 2) * 3) + 1) = 10 we get the maximum value 10

Example 2
    Input:  str = "891"
    Output: 73
    Explanation: 
        As 8 * 9 * 1 = 72 and 8 * 9 + 1 = 73 so 73 is maximum.

Link
   LintCode: https://www.lintcode.com/problem/719

Similar question
    LintCode 741. Calculate Maximum Value II (https://www.lintcode.com/problem/741)
***/
solution-1: dp, it's serial position type dp  
/*
    int currentVal = str.charAt(n - 1) - '0';
    dp[n] = max(dp[n - 1] + currentVal, dp[n - 1] * currentVal);
*/
public class Solution {
    /**
     * @param str: the given string
     * @return: the maximum value
     */
    public int calcMaxValue(String str) {
        // write your code here
        int n = str.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int currentVal = str.charAt(i - 1) - '0';
            dp[i] = Math.max(dp[i - 1] + currentVal, dp[i - 1] * currentVal);
        }

        return dp[n];
    }
}
