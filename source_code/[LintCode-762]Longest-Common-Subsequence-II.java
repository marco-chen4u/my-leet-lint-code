/**
* LintCode 762 · Longest Common Subsequence II
* Given two sequence P and Q of numbers. 
The task is to find Longest Common Subsequence of two sequence 
if we are allowed to change at most k element in first sequence to any value.

Example 1
    Input:
        [8,3]
        [1,3]
        1
    Output:
        2
    Explanation:
        chang 8 to 1,the longest common subsuquence is[1,3]

Example 2
    Input:
        [1, 2, 3, 4, 5]
        [5, 3, 1, 4, 2]
        1
    Output:
        3

related problems:
    LintCode 762.  Longest Common Subsequence II https://www.lintcode.com/problem/762
    LintCode 79.   Longest Common Substring  https://www.lintcode.com/problem/79
    LintCode 77.   Longest Common Subsequence https://www.lintcode.com/problem/77
**/

//version-1
public class Solution {
    /**
     * @param P: an integer array P
     * @param Q: an integer array Q
     * @param K: the number of allowed changes
     * @return: return LCS with at most k changes allowed.
     */
    public int longestCommonSubsequence2(int[] P, int[] Q, int K) {
        
        // state
        int n = P.length;
        int m = Q.length;
        int[][][] dp = new int[n + 1][m + 1][K + 1];

        // initialize
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <= m; j++) {
                if (P[i - 1] == Q[j - 1]) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    continue;
                }

                dp[i][j][0] = Math.max(dp[i - 1][j][0], dp[i][j - 1][0]);
            }
        }

        // calculation
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k<= K; k++) {
                    if (P[i - 1] == Q[j - 1]) {
                        dp[i][j][k] = max(dp[i - 1][j - 1][k] + 1,
                                          dp[i - 1][j][k],
                                          dp[i][j - 1][k]);
                        continue;
                    }

                    dp[i][j][k] = max(dp[i - 1][j][k],
                                      dp[i][j - 1][k],
                                      dp[i - 1][j - 1][k - 1] + 1);

                }
            }
        }

        return dp[n][m][K];
    }

    // helper method
    private int max(int i, int j, int k) {
        return Math.max(i, Math.max(j, k));
    }
}

//version-2:
public class Solution {
    /**
     * @param P: an integer array P
     * @param Q: an integer array Q
     * @param K: the number of allowed changes
     * @return: return LCS with at most k changes allowed.
     */
    public int longestCommonSubsequence2(int[] P, int[] Q, int K) {
        
        // state
        int n = P.length;
        int m = Q.length;
        int[][][] dp = new int[n + 1][m + 1][K + 1];

        // initialize
        for (int i = 1; i <=n; i++) {
            for (int j = 1; j <= m; j++) {
                if (P[i - 1] == Q[j - 1]) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    continue;
                }

                dp[i][j][0] = max(dp[i - 1][j][0], dp[i][j - 1][0]);
            }
        }

        // calculation
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                for (int k = 1; k<= K; k++) {
                    if (P[i - 1] == Q[j - 1]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k] + 1;
                        continue;
                    }

                    dp[i][j][k] = max(dp[i - 1][j][k],
                                      dp[i][j - 1][k],
                                      dp[i - 1][j - 1][k - 1] + 1);

                }
            }
        }

        return dp[n][m][K];
    }

    // helper method
    private int max(int i, int j) {
        return Math.max(i, j);
    }

    private int max(int i, int j, int k) {
        return Math.max(i, Math.max(j, k));
    }
}
