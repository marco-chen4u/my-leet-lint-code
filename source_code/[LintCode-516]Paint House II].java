/***
* LintCode 516. Paint House II
There are a row of n houses, each house can be painted with one of the k colors. 
The cost of painting each house with a certain color is different. 
You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n*k cost matrix. 

For example, 
    costs[0][0] is the cost of painting house 0 with color 0; 
    costs[1][2] is the cost of painting house 1 with color 2, and so on... 
	      
Find the minimum cost to paint all houses.

Example 1
    Input:
        costs = [[14,2,11],[11,14,5],[14,3,10]]
    Output: 10
    Explanation:
        The three house use color [1,2,1] for each house. The total cost is 10.

Example 2
    Input:
        costs = [[5]]
    Output: 5
    Explanation:
        There is only one color and one house.

Challenge
    Could you solve it in O(nk)?

Notice
    All costs are positive integers.
***/
//version-1: traditional DP, time complexity: O(nk^2)
public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
        // check corner cases
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        if (costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        
        if (costs.length == 1) {
            int value = Integer.MAX_VALUE;
            for (int cost : costs[0]) {
                value = Math.min(value, cost);
            }
            
            return value;
        }
        
        // normal case
        int result = Integer.MAX_VALUE;
        int n = costs.length;
        int m = costs[0].length;
        
        // state
        int[][] dp = new int[n + 1][m];
        
        // initialize
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        Arrays.fill(dp[0], 0);
        
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < m; k++) {
                    if (k == j) {
                        continue;
                    }
                    
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][k] + costs[i - 1][j]);
                }
            }
        }
        
        for (int k = 0; k < m; k++) {
            result = Math.min(result, dp[n][k]);
        }
        
        return result;
        
    }
}

version-2: DP , time complexity : O(nk)
public class Solution {
    /**
     * @param costs: n x k cost matrix
     * @return: an integer, the minimum cost to paint all houses
     */
    public int minCostII(int[][] costs) {
		
	}
}
