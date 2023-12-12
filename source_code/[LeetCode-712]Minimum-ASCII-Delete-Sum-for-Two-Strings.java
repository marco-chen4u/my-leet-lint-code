/***
* LeetCode 712. Minimum ASCII Delete Sum for Two Strings
Given two strings s1 and s2, return the lowest ASCII sum of deleted characters to make two strings equal.

Example 1
    Input: s1 = "sea", s2 = "eat"
    Output: 231
    Explanation: 
        Deleting "s" from "sea" adds the ASCII value of "s" (115) to the sum.
        Deleting "t" from "eat" adds 116 to the sum.
        At the end, both strings are equal, and 115 + 116 = 231 is the minimum sum possible to achieve this.

Example 2
    Input: s1 = "delete", s2 = "leet"
    Output: 403
    Explanation: 
        Deleting "dee" from "delete" to turn the string into "let",
        adds 100[d] + 101[e] + 101[e] to the sum.
        Deleting "e" from "leet" adds 101[e] to the sum.
        At the end, both strings are equal to "let", and the answer is 100+101+101+101 = 403.
        If instead we turned both strings into "lee" or "eet", we would get answers of 433 or 417, which are higher.

Constraints:
    1 <= s1.length, s2.length <= 1000
    s1 and s2 consist of lowercase English letters.

Link
    LeetCode: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
    LintCode: https://www.lintcode.com/problem/1076/
***/

//solution-1: DP
/*
  dp[i][j]: minimum ascii delete sum for two stringsto make word1[0:i] and word2[0:j] the same content.

  if word1[i] == word[j]
      dp[i][j] = dp[i - 1][j - 1];
  else 
      dp[i][j] = (dp[i - 1][j] + word.charAt[i], dp[i][j - 1] + word.charAt[j]) //删除其中一个字符的操作，并把那个被删的字符ascii值加上的，求2次删除不同字符串的字符操作的最小值

ref: https://www.youtube.com/watch?v=YPRHj16y6N4
*/
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        dp[0][0] = 0;
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] +  s1.charAt(i - 1);
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j - 1] + s2.charAt(j - 1); 
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[m][n];
    }
}
