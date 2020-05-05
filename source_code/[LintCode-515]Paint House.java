/***
* LintCode 515. Paint House
There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color, and you need to cost the least. Return the minimum cost.

The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.

Example
	Example 1:
		Input: [[14,2,11],[11,14,5],[14,3,10]]
		Output: 10
		Explanation: Paint house 0 into blue, paint house 1 into green, 
		paint house 2 into blue. Minimum cost: 2 + 5 + 3 = 10.
	Example 2:
		Input: [[1,2,3],[1,4,6]]
		Output: 3
Notice
	All costs are positive integers.
***/
public class Solution {
	// filed
	private final int DEFAULT_MAX = Integer.MAX_VALUE;
	
	private final int RED = 0;
	private final int BLUE = 1;
	private final int GREEN = 2;
	
    /**
     * @param costs: n x 3 cost matrix
     * @return: An integer, the minimum cost to paint all houses
     */
    public int minCost(int[][] costs) {
		// check corner cases
		if (costs == null || costs.length == 0 || costs[0].length == 0) {
			return 0;
		}
		
		int n = costs.length;
		
		// state
		int[][] dp = new int[n + 1][3];
		
		// initialize
		for (int i = 0; i < 3; i++) {
			dp[0][i] = 0;
		}
		for (int i = 1; i <=n; i++) {
			Arrays.fill(dp[i], DEFAULT_MAX);
		}
		
		// function
		for (int i = 1; i <= n; i++) {// No. of the house
			for (int j = 0; j < 3; j++) {// color choose for this house
				for (int k = 0; k < 3; k++) {// the color previous house(neighbor) has painted
					if (k == j) {
						continue;// to avoid the color conflicts
					}
					
					dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
				}// for k
			}// for j
		}// for i
		
		return Math.min(dp[n][RED], Math.min(dp[n][BLUE], dp[n][GREEN]));
	}
}