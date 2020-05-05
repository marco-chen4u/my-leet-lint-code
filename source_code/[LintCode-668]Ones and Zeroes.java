/**
* LintCode 668. Ones and Zeroes
In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings consisting of only 0s and 1s.
Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be used at most once.

(The given numbers of 0s and 1s will both not exceed 100
The size of given string array won't exceed 600.)

Example
	Example1
	Input:
		["10", "0001", "111001", "1", "0"]
		5
		3
	Output: 4
	Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are "10", "0001", "1", "0"

	Example2
	Input:
		["10", "0001", "111001", "1", "0"]
		7
		7
	Output: 5
	Explanation: All strings can be formed by the using of 7 0s and 7 1s.

Notice
	1.The given numbers of 0s and 1s will both not exceed 100
	2.The size of given string array won't exceed 600.
*/
/*
* 确定状态：
		最后一步，最优策略组成了最多的01串，其中，要考虑有和没有最后一个字符串S[t-1]
			-情况1，没有考虑最后一个字符串S[t-1]（即不选择，类似背包）
					需要知道前t-1个字符串中，用m个0和n个1，最多能组成多少个符合条件的01串。
					
			-情况2，有考虑最后一个字符串S[t-1]（即选择，类似背包）
					设第T个字符串（即S[t-1]）中有a(t-1)个0， b(t-1)个1。
					需要知道前T-1个01串中，用m - a（t-1）个0，和用n - b（t-1）个1，最多能组成多少个01串。
		子问题
		0和1的个数变化，如何记录？
			-直接放入状态中
			-状态： 设dp[i][j][k]为前i个01串最多能有多少个被j个0和k个1组成。（由于i，j，k之间没有函数等式关系，所以不能对3为数组进行降维处理）
		
		设S(i)中有a(i)个0，b(j)个1
			dp[i][j][k]
					=Max{dp[i - 1][j][k],                    【不取】
					     dp[i - 1][j - a(i)][k - b(j)] + 1}  【取，j > a（i）， 且k > b(j)】
	初始条件
		dp[0][0..m][0..n] = 0
	计算顺序
		dp[0][0][0],dp[0][0][1],...,dp[0][m][n]
		dp[1][0][0],dp[1][0][1],...,dp[1][m][n]
		...
		dp[T][0][0],dp[T][0][1],...,dp[T][m][n]
	答案Max{dp[0][m][n],dp[1][m][n],dp[2][m][n], ..., dp[T][m][n]}
*
* 时间复杂度O(T* m * n), 空间复杂度O(T * m * n)， 可以通过滚动数组优化为O(m * n)
*/
//version-1: DP-double sequence + Backpack 
public class Solution {
    /**
     * @param strs: an array with strings include only 0 and 1
     * @param m: An integer
     * @param n: An integer
     * @return: find the maximum number of strings
     */
    public int findMaxForm(String[] strs, int m, int n) {
		// initialize
        int t = strs.length;
		int[] countBy1 = new int[t];
		int[] countBy0 = new int[t];
		
		for (int i = 0; i < t; i++) {
			for (char ch : strs[i].toCharArray()) {
				switch (ch) {
					case '0':
						countBy0[i]++;
						break;
					case '1':
						countBy1[i]++;
				}					
			}
		}
		
		// state
		int[][][] dp = new int[t + 1][m + 1][n + 1];
		
		// function
		for (int i = 0; i <= t; i++) {
			for (int j = 0; j <= m; j++) {
				for (int k = 0; k <= n; k++) {
					if (i == 0) {
						dp[i][j][k] = 0;// initialize
						continue;
					}
					
					// j-0(s) and k-1(s)
					// not take current
					dp[i][j][k] = dp[i - 1][j][k];
					
					// take current
					if (j >= countBy0[i - 1] && k >= countBy1[i - 1]) {
						dp[i][j][k] = Math.max(dp[i][j][k], 
												1 + dp[i - 1][j - countBy0[i - 1]][k - countBy1[i - 1]]);
					}
				} // for k
			} // for j
		} // for i
		
		// get answer
		int result = 0;
		for (int i = 1; i <= t; i++) {
			result = Math.max(result, dp[i][m][n]);
		}
		
		return result;
    }
}