/***
* LeetCode 583. Delete Operation for Two Strings
Given two strings word1 and word2, return the minimum number of steps required to make word1 and word2 the same.

In one step, you can delete exactly one character in either string.

Example 1:
    Input: word1 = "sea", word2 = "eat"
    Output: 2
    Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".

Example 2:
    Input: word1 = "leetcode", word2 = "etco"
    Output: 4

Constraints:
    1 <= word1.length, word2.length <= 500
    word1 and word2 consist of only lowercase English letters.

Link
    LeetCode: https://leetcode.com/problems/delete-operation-for-two-strings/
    LintCode: https://www.lintcode.com/problem/1156/

similar question:
    LeetCode 712. inimum ASCII Delete Sum for Two Strings   (link: https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/)
***/
//solution-1:DP
/*
  dp[i][j]: minimum number of step required to make word1[0:i] and word2[0:j] the same content.

  if word1[i] == word[j]
      dp[i][j] = dp[i - 1][j - 1];
  else 
      dp[i][j] = (dp[i - 1][j] + 1, dp[i][j - 1] + 1) //删除其中一个字符的操作，并判断那个操作的最小值

ref: https://www.youtube.com/watch?v=O1K_T4ZqstM
*/
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }
}
