/***
* LintCode 741. Calculate Maximum Value II
Given a string of numbers, write a function to find the maximum value from the string, 
you can add a + or * sign between any two numbers，

It's different with Calculate Maximum Value, You can insert parentheses anywhere.

Example 1
    Input: str = 01231
    Output: 12
    Explanation:
        (0 + 1 + 2) * (3 + 1) = 12 we get the maximum value 12

Example 2
    Input: str = 891
    Output: 80
    Explanation:
        As 8 * (9 + 1) = 80`, so `80` is maximum.

Link
    LintCode: https://www.lintcode.com/problem/741

Similar question:
    LintCode 719. Calculate Maximum Value (https://www.lintcode.com/problem/719)
    LintCode 476. Stone Game (https://www.lintcode.com/problem/476)
    LintCode 593. Stone Game II (https://www.lintcode.com/problem/593)
***/
//solution-1: DP, it's a zone type dp
/*
step(1)  
  int n = str.length()
  dp[i][i] = str[i] - '0'
step(2)  
  dp[i][j] = the max value within the zone str[i:j], where 
        for (int k = i; k < j; k++) {
            dp[i][j] = max(dp[i][k] * dp[k + 1][j], dp[i][k] + dp[k + 1][j])
        }
step(3)
  return dp[0][n - 1]
*/

public class Solution {
    /**
     * @param str: a string of numbers
     * @return: the maximum value
     */
    public int maxValue(String str) {
        // write your code here
        int n = str.length();
        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            dp[i][i] = str.charAt(i) - '0';
        }

        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                for (int k = i; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] * dp[k + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
