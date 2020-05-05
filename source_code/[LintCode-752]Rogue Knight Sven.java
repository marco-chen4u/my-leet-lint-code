/***
* LintCode 752. Rogue Knight Sven
In material plane "reality", there are n + 1 planets, namely planet 0, planet 1, ..., planet n.
Each planet has a portal through which we can reach the target planet directly without passing through other planets.

But portal has two shortcomings.
	First, planet i can only reach the planet whose number is greater than i, and the difference between i can not exceed the limit.
	Second, it takes cost[j] gold coins to reach the planet j through the portal.
	
Now, Rogue Knight Sven arrives at the planet 0 with m gold coins, how many ways does he reach the planet n through the portal?

Example
	Example 1:
		Input:
			n = 1
			m = 1
			limit = 1
			cost = [0, 1]
		Output:
			1
		Explanation:
			Plan 1: planet 0 → planet 1

	Example 2:
		Input:
			n = 1
			m = 1
			limit = 1
			cost = [0,2]
		Output:
			0
		Explanation:
			He can not reach the target planet.

Notice
	1 <= n <= 50, 0 <= m <= 100, 1 <= limit <= 50,0 <= cost[i] <= 100。
	The problem guarantees cost [0] = 0, cause cost[0] does not make sense
***/
/*
* 这是一道类似于Frog Jum Game的问题。
* 最后一步： 从某个星球i<n,跳到星球n，需要保证i + limit >= n.
* 假设状态dp[i], 表示有多少种方式，从星球0，跳到星球i
* 从星球i，跳到星球n，需要花费cost[n]枚金币，需要知道，到达星球i时，目前手里有多少枚金币。但是目前不知道这个值，怎么办？那么把它放到状态值里面
* 因此，设dp[i][j], 表示有多少种方式，星球0，跳到星球i， Rogue Knight Sven手里还有j枚金币。(这样呢，就有点类似于股票交易市场的问题)
*
* 这是一种坐标+状态类型的问题。
* 
* 转移方程：
*         dp[i][j] = Sum{dp[k][j + cost[i]]}，其中 i - limit<=k<i, j + cost[i] <= m
*                        即从星球k，跳到星球i，要求手里有j + cost[i]枚金币。      
*                          从星球k，跳到星球i之前，手里j + cost[i]枚金币，需要花cost[i]的过路费，所以目前到星球i，只剩下j枚金币，这就是dp[i][j]的目前表示状态。
*                          手里的金币只会越来越少。  
* 
* 初始条件：
*        dp[i][m] = 1 ，初始时，在星球0，手里有m枚金币。
* 边界情况
*        k >= 0
*        j + cost[i] <= m (一共有m枚金币)
*
* 计算顺序：
*        dp[0][0], dp[0][1], ..., dp[0][m]
*        dp[1][0], dp[1][1], ..., dp[1][m]
*        ...
*        dp[n][0], dp[n][1], ..., dp[n][m]
*
* 答案：
*        dp[n][0] + dp[n][1] + ... + dp[n][m]
* 
*/
//version-1:dp, time complexity O(n * n * m), space complexity(n * m)
public class Solution {
    /**
     * @param n: the max identifier of planet.
     * @param m: gold coins that Sven has.
     * @param limit: the max difference.
     * @param cost: the number of gold coins that reaching the planet j through the portal costs.
     * @return: return the number of ways he can reach the planet n through the portal.
     */
    public long getNumberOfWays(int n, int m, int limit, int[] cost) {
        long[][] dp = new long[n + 1][m + 1];
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 && j == m) {
					dp[i][j] = 1;
					continue;
				}
				
				if (i == 0) {
					dp[i][j] = 0;
					continue;
				}
				
				// Rogue Knight Sven is now at planet i, with j coins, AFTER paying cost[i] coins
				dp[i][j] = 0;
				for (int k = i - limit; k < i; k++) {
					// corner cases
					if (k < 0) {
						continue;
					}
					
					if (j + cost[i] > m) {
						continue;
					}
					
					// Rogue Knight Sven is jumping from planet-k to planet-i
					dp[i][j] += dp[k][j + cost[i]];
					
				}// for k
			}// for j
		}// for i
		
		long result = 0;
		for (int i = 0; i <=m; i++) {
			result += dp[n][i];
		}

		return result;
    }
}

//version-2: dp, with Rotated Array, time complexity O(n * n * m), space complexity O(m)
public class Solution {
    /**
     * @param n: the max identifier of planet.
     * @param m: gold coins that Sven has.
     * @param limit: the max difference.
     * @param cost: the number of gold coins that reaching the planet j through the portal costs.
     * @return: return the number of ways he can reach the planet n through the portal.
     */
    public long getNumberOfWays(int n, int m, int limit, int[] cost) {
        long[][] dp = new long[2][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == m) {
                    dp[i%2][j] = 1;
                    continue;
                }
                
                if (i == 0) {
                    dp[i%2][j] = 0;
                    continue;
                }
                
                if (j + cost[i] > m) {
                    continue;
                }
                
                // Rogue Knight Sven is now at planet-i, AFTER paying off cost[i]
                dp[i%2][j] = 0;
                for (int k = i - limit; k < i; k++) {
                    if (k < 0) {
                        continue;
                    }
                    
                    dp[i%2][j] += dp[k][j + cost[i]];
                } // for k
            }// for j
        }// for i
        
        long result = 0;
        for (int i = 0; i <= m; i++) {
            result += dp[n%2][i];
        }
        
        return result;
    }
}

//version-3: dp, pefix sum, time complexity O(n * m), space complexity O(n * m)
public class Solution {
    /**
     * @param n: the max identifier of planet.
     * @param m: gold coins that Sven has.
     * @param limit: the max difference.
     * @param cost: the number of gold coins that reaching the planet j through the portal costs.
     * @return: return the number of ways he can reach the planet n through the portal.
     */
    public long getNumberOfWays(int n, int m, int limit, int[] cost) {
        long[][] dp = new long[n + 1][m + 1];
		long[][] sum = new long[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0 && j == m) {
                    dp[i][j] = 1;
					sum[i][j] = 1;
                    continue;
                }
                
                if (i == 0) {
                    dp[i][j] = 0;
					sum[i][j] = 0;
                    continue;
                }
                
				
				sum[i][j] = sum[i - 1][j];
				
                if (j + cost[i] > m) {
                    continue;
                }
                
				dp[i][j] = 0;
                // Rogue Knight Sven is now at planet-i, AFTER paying off cost[i]
                
                dp[i][j] = sum[i - 1] [j + cost[i]];
				if (i - 1 - limit >= 0) {
					dp[i][j] -= sum[i - 1 - limit][j + cost[i]];
				}
				
				sum[i][j] += dp[i][j];// sum[i][j] = dp[0][j] + ... + dp[i][j]
            }// for j
        }// for i
        
        long result = 0;
        for (int i = 0; i <= m; i++) {
            result += dp[n][i];
        }
        
        return result;
    }
}