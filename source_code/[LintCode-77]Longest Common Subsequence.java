/***
* LintCode 77. Longest Common Subsequence
Given two strings, find the longest common subsequence (LCS).
Your code should return the length of LCS.

Example 1:
    Input:  "ABCD" and "EDCA"
    Output:  1	
    Explanation:
        LCS is 'A' or  'D' or 'C'

Example 2:
    Input: "ABCD" and "EACB"
    Output:  2	
    Explanation: 
        LCS is "AC"	
Clarification
    What's the definition of Longest Common Subsequence?
        https://en.wikipedia.org/wiki/Longest_common_subsequence_problem
        http://baike.baidu.com/view/2020307.htm
***/
public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        // check corner cases
        if (A == null || A.length() == 0 ||B == null || B.length() == 0) {
            return 0;
        }

        // state
        int n = A.length();
        int m = B.length();
        int[][] dp = new int[n + 1][m + 1];		
        // initialize
        dp[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int j = 1; j <= m; j++) {
            dp[0][j] = 0;
        }		
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = 0;
            }
        }

        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }

                dp[i][j] = Math.max(dp[i][j],
                                    Math.max(dp[i - 1][j], dp[i][j - 1]));
            }
        }

        // return
        return dp[n][m];
    }
}

//version-2 printer
public class Solution {
    /**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        // check corner cases
        if (isEmpty(A) || isEmpty(B)) {
            return 0;
        }
        
        char[] charArrayA = A.toCharArray();
        char[] charArrayB = B.toCharArray();
        
        int n = charArrayA.length;
        int m = charArrayB.length;
        
        // state
        int[][] dp = new int[n + 1][m + 1];
        int[][] state = new int[n + 1][m + 1];
        
        // initialize & function
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    continue;
                }
                
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (dp[i][j] == dp[i - 1][j]) {
                    state[i][j] = 1;
                }
                else {
                    state[i][j] = 2;
                }
                
                if (charArrayA[i - 1] == charArrayB[j - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                    if (dp[i][j] == dp[i - 1][j - 1] + 1) {
                        state[i][j] = 3;
                    }
                }
            }
        }
        
        // printing out the longest common subsequence string
        int size = dp[n][m];
        char[] result = new char[size];
        int i = n;
        int j = m;
        int index = size - 1;
        while (i > 0 && j > 0) {
            switch (state[i][j]) {
                case 1 : 
                    i--;
                    break;
                case 2 :
                    j--;
                    break;
                case 3 :
                    result[index--] = charArrayA[i - 1]; // or charArrayB[j - 1] ,they are the same character
                    i--;
                    j--;
                    break;
            }
        }
        
        //System.out.println("Longest Common Subsequence : " + String.valueOf(result));
        
        return dp[n][m];
    }

    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
