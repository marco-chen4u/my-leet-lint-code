/***
* LintCode 667. Longest Palindromic Subsequence
Given a string s, find the longest palindromic subsequence's length in s. 
You may assume that the maximum length of s is 1000.

Example
	Example1
		Input: "bbbab"
		Output: 4
		Explanation:
			One possible longest palindromic subsequence is "bbbb".
	Example2
		Input: "bbbbb"
		Output: 5
***/
/*
* 确定状态：
		从最后一步开始分析
		最优策略产生最长回文子序列T，长度为M
			-情况1：回文长度为1，即1个字母
			-情况2：回文长度>1, 那么必定有T[0] = T[M-1],即当前长度的首尾字母相同
					S  0 1 2  ...  n-4 n-3 n-2 n-1
					   a b b  ...   d   x   b   g
					     *                  *
						T[0]              T[M-1]
		
				设定T[0]是S[i], T[M-1]是[j]
				T剩下的部分T[1..M-2]仍然是一个回文串， 而且是S[i+1 ... j -1]的最长回文子序列,
				而这个S[i+1 ... j -1]的最长回文子序列值dp[i+1][j -1]，其实就是dp[i][j]的子问题
			因此
				要求S[i..j]的最长回文子序列(存在下面3中情况中的1种)
					-如果S[i] = S[j]想要知道S[i+1..j-1]的最长回文子序列，dp[i[j] = Math.max(dp[i][j], dp[i + 1][j -1] + 2)
					-如果S[i] != S[j],则dp[i][j]是S[i+1..j]或S[i..j-1]这2者中，的最长子序列值Math.max(dp[i+1][j], dp[i][j -1])
			
			dp[i][j] = max{dp[i+1][j], dp[i][j-1], dp[i+1][j-1] + 2|S[i]==S[j]}
				dp[i][j]   表示S[i..j]的最长回文子序列长度
				dp[i+1][j] 表示S[i+1..j]的最长回文子序列长度
				dp[i][j-1] 表示S[i..j-1]的最长回文子序列长度
				dp[i+1][j-1] 表示S[i+1..j-1]的最长回文子序列长度
	初始化条件：
			-dp[0][0] = dp[1][1] = .... = dp[n -1][n -1] = 1
				-一个字母也是一个长度为1的回文串
			-如果S[i]=S[i+1], dp[i][i+1] = 2
			-如果S[i]!=S[i+1], dp[i][i+1] = 1	
*/
//version-1: DP, time complexity O(n^2), space complexity O(n^2), bottom-up
public class Solution {
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        if (n < 2) {
            return n;
        }
        
        int[][] dp = new int[n][n];
        int length = 1;
        
        // initialize
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        
        for (int i = 0; i < n - 1; i++) {
            dp[i][i + 1] = (s.charAt(i) == s.charAt(i + 1)) ? 2 : 1;
            length = Math.max(length, dp[i][i + 1]);
        }
        
		// function
		// 不能按i（从左到右）的顺序进行计算，而应该按照j-i的长度，从小到大，进行计算。【区间动态规划】
        for (length = 3; length <= n; length++) {
            for (int i = 0; i <= n - length; i++) {
                int j = i + length - 1;
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1] + 2);
                }
            }
        }
        
        return dp[0][n - 1];
    }
}

//version-2: Memorized Search, DFS   top-down
/*
* 计算f(0, n-1)
		-需递归计算f(0, n-2), f(1, n-1), 和发（1, n-2）
		-记忆化： 计算f（i，j）后，需要讲结果保存到数组f[i][j]中，下次如果需要再次计算f(i,j),直接返回f[i][j]
		
	time complexity: O(3*n*n + 1)
*/
public class Solution {
	// field
	private char[] charArray;
	private int n;
	private int[][] f;
	private final int DEFAULT_VALUE = -1;
    /**
     * @param s: the maximum length of s is 1000
     * @return: the longest palindromic subsequence's length
     */
    public int longestPalindromeSubseq(String s) {
		charArray = s.toCharArray();
		n = s.length();
		
		// check corner case
		if (s == null || s.length() <= 1) {
			return n;
		}
		
		f = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				f[i][j] = DEFAULT_VALUE; // not been computed yet 
			}
		}
		
		dfs(0, n-1);
		
		return f[0][n-1];
	}
	
	// helper method
	private void dfs(int i, int j) {
		// check corner cases
		if (i > j) {
			return;
		}
		
		if (f[i][j] != DEFAULT_VALUE) {
			return;// it's already computed
		}
		
		if (i == j) {
			f[i][j] = 1;
			return;
		}
		
		if (i + 1 == j) {
			f[i][j] = (charArray[i] == charArray[j]) ? 2 : 1;
			return;
		}
		
		// normal case
		dfs(i, j - 1);// make sure f[i][j-1] has been computed
		dfs(i + 1, j);// make sure f[i + 1][j] has been computed
		dfs(i + 1, j - 1); // make sure f[i + 1][j - 1] has been computed
		
		f[i][j] = Math.max(f[i][j - 1], f[i + 1][j]);
		if (charArray[i] == charArray[j]) {
			f[i][j] = Math.max(f[i][j], f[i + 1][j - 1] + 2);
		}
	}
}