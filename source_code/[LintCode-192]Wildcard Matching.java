/***
* LintCode 192. Wildcard Matching
Implement wildcard pattern matching with support for '?' and '*'.
    '?' Matches any single character.
    '*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

Example
    isMatch("aa","a") → false
    isMatch("aa","aa") → true
    isMatch("aaa","aa") → false
    isMatch("aa", "*") → true
    isMatch("aa", "a*") → true
    isMatch("ab", "?*") → true
    isMatch("aab", "c*a*b") → false
***/
/*
* 给定字符串s, p
* p是一个正则表达式，里面含有'?'和'*'
    -'?'可以匹配任何单个字符
    -'*'可以匹配0个或多个任意字符
  问s和p能否匹配？
* 双系列类型动态规划
* 和LintCode 154. Regular Expression Matching很相似，因为'.'和'?'作用相同，
  但是，这个题中，* 可以匹配0个或多个任意字符
* 设s长度为m，p长度为n
* 现在考虑s和p如何匹配。
*
* 主要取决于p[n - 1](即尾巴)是什么
        如果p[n - 1]是一个正常符号， 如果s[m - 1] == p[n - 1], 能否匹配取决于s[0..m - 2]和p[0..n - 2]能否匹配。
        如果p[n - 1]是一个‘？’， 则s[m - 1]一定和p[n - 1]匹配，能否匹配取决于s[0..m - 2]和p[0..n - 2]能否匹配。
        如果p[n - 1]是‘*’， 它可以匹配0个或任何字符，
            如果表示0个字符（即把p这个当前字符扔掉），则结果能否匹配取决于s[0..m-1]和p[0..n-2]能否匹配。
            如果表示任意多个字符，则s[m-1]与当前*匹配上，则结果能否匹配取决于s[0..m-2]和p[0..n-1]能否匹配。
* 状态： dp[i][j]表示为s前i字符s[0..i-1]和p的前j个字符p[0..j-1]能否匹配。
*
* 转移方程： 
       dp[i][j] = dp[i - 1][j - 1] 【如果i > 0 且p[j - 1] = s[i - 1]或p[j - 1] = '?'】
	                 OR
				  dp[i][j - 1] 【如果p[j - 1] = '*', 且*是0个字符】
				     or 
				  dp[i - 1][j] 【如果p[j - 1] = '*'， 且*是或多个任意字符】
*  
* 初始条件：
        空串s和空wildcard匹配： dp[0][0] = true
        空的wildcard不能匹配长度>0的串， dp[i][0] = false
*
* 时间复杂度O(m * n), 空间复杂度 O(m * n), 空间复杂度可以通过滚动数组优化O(n)
* 
*/
//version-1: DFS & memorized search
public class Solution {

    // helper methods
    private boolean isAllStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }

        return true;
    }
	
    private boolean isCharMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '?');
    }

    private boolean isMatchHelper(String s, int sIndex, 
                                    String p, int pIndex, 
                                    boolean[][] visited, 
                                    boolean[][] memo) {
        // check corner cases
        if (p.length() == pIndex) {
            return s.length() = sIndex;
        }

        if (s.length() == sIndex) {
            isAllStar(p, pIndex);
        }

        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }

        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match = false;

        if (pChar != '*') {
            match = isCharMatch(sChar, pChar) && 
                        isMatchHelper(s, sIndex + 1, p, pIndex + 1, visited, memo);
        }
        else {
            match = isMatchHelper(s, sIndex + 1, p, pIndex, visited, memo) ||
                        isMatchHelper(s, sIndex, p, pIndex + 1, visited, memo);
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

//version-2
public class Solution {
    /**
     * @param ss: A string 
     * @param pp: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String ss, String pp) {
        char[] s = ss.toCharArray();
        char[] p = pp.toCharArray();

        int m = s.length;
        int n = p.length;

        boolean[][] dp = new boolean[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }

                if (j == 0) {
                    dp[i][j] = false;
                    continue;
                }

                if (p[j - 1] != '*') {
                    if ((i > 0 && j > 0) && (p[j - 1] == s[i - 1] || p[j - 1] == '?')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                    continue;
                }

                if (p[j - 1] == '*') {
                    // *
                    // 0-arbitary alphabet
                    if (j > 0) {
                        dp[i][j] = dp[i][j - 1];
                    }

                    // 1/n arbitary alphabet
                    if (i > 0) {
                        dp[i][j] = dp[i][j] | dp[i - 1][j];
                    }
                }
            }
        }

        return dp[m][n];
    }
}
