/***
* LintCode 394. Coins in a Line
There are n coins in a line. Two players take turns to take one or two coins from right side until there are no more coins left. 
The player who take the last coin wins.

Could you please decide the first player will win or lose?
If the first player wins, return true, otherwise return false.

Example
	Example 1:
		Input: 1
		Output: true

	Example 2:
		Input: 4
		Output: true
		Explanation:
			The first player takes 1 coin at first. Then there are 3 coins left.
			Whether the second player takes 1 coin or two, then the first player can take all coin(s) left.

Challenge
	O(n) time and O(1) memory
***/
/*
* 博弈型动态规划，通常从第一步开始分析，而不是最后一步。

  面对n个石子，先手Alice第1步可以拿1个或2个石子。
  
  这样后手Bob就面对n-1 或 n-2个石子的局面。
  
  先手Alice到底是取1个石子还是取2个石子呢？Alice一定会选择让自己赢的一步
	-因为双方都是采用最优策略
  
  博弈规划： 必胜 vs 必败
	-怎么选择让自己赢的一步
	-就是走了这一步之后，对手面对剩下的石子局面，他必输
	-如果取2个石子或取1个石子之后， 能让剩下的局面先手比输， 则当前先手必胜。
	-如果不管你怎么走， 剩下的局面都是先手必胜，那么当前先手必败。
	-必胜： 在当下的局面走出一步， 让对手无路可逃
	-必败： 自己无路可逃
  子问题
	-要求面对n个石子的局面，是否先手必胜？
	-需要知道面对n-1个石子和n-2个石子的局面，是否先手必胜
	状态：设dp[i]表示面对i个石子的局面，是否先手必胜（dp[i] = true/false）
		dp[i] = true, dp[i - 2] = false & dp[i - 1] = false. 即拿1或2个石子都必胜
		dp[i] = true, dp[i - 2] = false & dp[i - 1] = true.  即拿2个石子必胜 
		dp[i] = true, dp[i - 2] = true & dp[i - 1] = false.  即拿1个石子必胜
		dp[i] = false,dp[i - 2] = true & dp[i - 1] = true.   必败
			即 dp[i] = [(dp[i - 2] == false) OR (dp[i - 1] == false)]
			  dp[0] = false  面对0个石子局面，先手必败【初始化】
			  dp[1] = true   面对1个石子局面，先手必胜【初始化】
			  dp[2] = true   面对2个石子局面，先手必胜【初始化】
			  从小到大来进行计算
			  
* time complexity: O(n), space complexity: O(n), if using rotated array, it's O(1)
  
*/
//version-1: DP, time complexity O(n), space complexity O(n) 
public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
        // check corner cases
		if (n == 0) {
			return false;
		}
		
		if (n == 1 || n == 2) {
			return true;
		}
		
		boolean[] dp = new boolean[n + 1];
		
		// initialize
		Arrays.fill(dp, false);
		dp[1] = true;
		dp[2] = true;
		
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i - 2] == false || dp[i - 1] == false);
		}
		
		return dp[n];
    }
}

//version-2: DP, time complexity O(n), space complexity O(1)
public class Solution {
    /**
     * @param n: An integer
     * @return: A boolean which equals to true if the first player will win
     */
    public boolean firstWillWin(int n) {
		// check corner cases
		if (n == 0) {
			return false;
		}
		
		if (n == 1 || n == 2) {
			return true;
		}
		
		// state
		boolean[] dp = new boolean[3];
		
		// initialize
		dp[0] = false;
		dp[1] = true;
		dp[2] = true;
		
		for (int i = 3; i <= n; i++) {
			dp[i % 3] = !dp[(i - 2) % 3] || !dp[(i - 1) % 3];
		}
		
		return dp[n % 3];
	}
}