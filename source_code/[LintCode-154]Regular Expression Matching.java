/***
* LintCode 154. Regular Expression Matching
Implement regular expression matching with support for '.' and '*'.

    '.' Matches any single character.
    '*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

The function prototype should be:

    bool isMatch(string s, string p)

Example
    isMatch("aa","a") → false
    isMatch("aa","aa") → true
    isMatch("aaa","aa") → false
    isMatch("aa", "a*") → true
    isMatch("aa", ".*") → true
    isMatch("ab", ".*") → true
    isMatch("aab", "c*a*b") → true
***/
/*
* 要求s的前m个字符和p前n个字符能否匹配，
  需要知道s前m个字符和(正则表达式)p的前n-1个字符，s前m-1个字符和(正则表达式)p前n个字符，以及s前m个字符和(正则表达式)p前n-2个字符能否匹配。
  
* dp[i][j]表示为s前i个字符s[0..i-1]和(正则表达式)p前j个字符p[0..j-1]能否匹配。
  存在如下两种情况:
    -dp[i][j] = dp[i - 1][j - 1], 如果i > 0, 且 s[i - 1] = p[j - 1]或者p[i - 1] = '.'
    -如果p[j - 1] = '*'
        dp[i][j] = dp[i][j - 2]
                        OR
                    dp[i - 1][j] 且s[i - 1] = p[j - 2]或者p[i - 2] = '.'
* 注意：
    空串和空正则表达式，是匹配的，因此dp[0][0] = true
    空的正则表达式，是不能匹配长度>0的串，因此dp[1][0]=...=dp[m][0] = FALSE
* 
* 时间复杂度O(m * n), 空间复杂度O(m * n), 可以滚动数组优化至O(n)
*/
//version-1: DFS
public class Solution {

    // helper methods
    private boolean isEmpty(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i += 2) {
            if (i + 1 >= p.length() || p.charAt(i + 1) != '*') {
                return false;
            }
        }

        return true;
    }

    private boolean isCharMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '.');
    }

    private boolean isMatchHelper(String s, int sIndex, 
                                    String p, int pIndex, 
                                    boolean[][] visited, 
                                    boolean[][] memo) {
        // check corner cases
        if (p.length() == pIndex) {
            return s.length() == sIndex;
        }

        if (s.length() == sIndex) {
            return isEmpty(p, pIndex);
        }

        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }

        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match = false;

        if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*') {
            match = isMatchHelper(s, sIndex, p, pIndex + 2, visited, memo) ||
                    isCharMatch(sChar, pChar) && isMatchHelper(s, sIndex + 1, p, pIndex, visited, memo);
        }
        else {
            match = isCharMatch(sChar, pChar) && isMatchHelper(s, sIndex + 1, p, pIndex + 1, visited, memo);
        }

        memo[sIndex][pIndex] = match;
        visited[sIndex][pIndex] = true;

        return match;		
    }

    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        // check corner case
        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return false;
        }

        boolean[][] visited = new boolean[s.length()][p.length()];
        boolean[][] memo = new boolean[s.length()][p.length()];

        return isMatchHelper(s, 0, p, 0, visited, memo);
    }
}

//version-2: DP
public class Solution {
    /**
     * @param ss: A string 
     * @param pp: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String ss, String pp) {
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        int n = s.length;
        int m = p.length;

        boolean[][] dp = new boolean[n + 1][m + 1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                dp[i][j] = false;

                if (p[j - 1] != '*') {
                    if (i > 0 && (p[j - 1]==s[i - 1] || p[j - 1] == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }

                if (p[j - 1] == '*') {
                    // *c
                    // 0 c's
                    if (j > 1) {
                        dp[i][j] = dp[i][j - 2];
                    }

                    // >=1 c's, c: p[j -2]
                    if (i > 0 && j > 1 && (p[j - 2] == s[i - 1] || p[j - 2] == '.')) {
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                    }
                }
            }
        }

        return dp[n][m];

    }		
}
