/***
* LeetCode 727. Minimum Window Subsequence
Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.

If there is no such window in s1 that covers all characters in s2, return the empty string "". 
If there are multiple such minimum-length windows, return the one with the left-most starting index.
***/
//solution-1: DP, dual serial type DP
/*
step(1)
    int minLength = MAX_VALUE (for s1 string)
    int startIndex = 0; (for s1 string) 
    
    dp[m][n] = the length of minimum contiguous substring part of s1[0:m], so that s2[0:n] is a subsequence of the part.

step(2)    
    dp[i][j] = dp[i - 1][j - 1] + 1 (if character s1[i] == s2[j]) or default-value(if dp[i - 1][j - 1] == default-value which means there no preceding match)
                     or
               dp[i - 1][j] + 1 or default-value(if dp[i - 1][j] ==  default-value which means there no preceding match)

               if (j == n && 
                   dp[i][j] != default-value && 
                   dp[i][j] < minLength) {
                   
                    minLength = dp[i][j]
                    startIndex = i - minLength;
               }

step(3)
    return s1.substring(startIndex, startIndex + minLength) or empty string
*/
class Solution {
    private static final int DEFAULT_VALUE = -1;
    private static final int MAX = Integer.MAX_VALUE;

    public String minWindow(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int j = 1; j <= n; j++) {
            dp[0][j] = DEFAULT_VALUE;
        }

        int minLength = MAX;
        int startIndex = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] == DEFAULT_VALUE ? DEFAULT_VALUE : dp[i - 1][j - 1] + 1;
                }
                else {
                    dp[i][j] = dp[i - 1][j] == DEFAULT_VALUE ? DEFAULT_VALUE : dp[i - 1][j] + 1;
                }

            }

            //when compare the whole string of s2 at index i on string s2
            if (dp[i][n] != DEFAULT_VALUE && dp[i][n] < minLength) {
               minLength = dp[i][n];
               startIndex = i - minLength;
            } 
        }

        if (minLength == MAX) {
            return "";
        }

        //System.out.println("s1 = " + s1 + ", startIndex = " + startIndex + ", minLength = " + minLength);
        return s1.substring(startIndex, startIndex + minLength);
    }
}
