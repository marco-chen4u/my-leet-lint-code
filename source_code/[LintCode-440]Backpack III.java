/***
* LintCode 440. Backpack III
Given n kinds of items, and each kind of item has an infinite number available. 
The i-th item has size A[i] and value V[i].

Also given a backpack with size m. 
What is the maximum value you can put into the backpack?

Example 1:
    Input: A = [2, 3, 5, 7], V = [1, 5, 2, 4], m = 10
    Output: 15
    Explanation: Put three item 1 (A[1] = 3, V[1] = 5) into backpack.
	
Example 2:
    Input: A = [1, 2, 3], V = [1, 2, 3], m = 5
    Output: 5
    Explanation: Strategy is not unique. For example, 
    put five item 0 (A[0] = 1, V[0] = 1) into backpack.

Notice
    1.You cannot divide item into small pieces.
    2.Total size of items you put into backpack can not exceed m.
****/
/***
* 思路：
* 设定 dp[i][w] 表示前 i 种物品装到容量为 w 的背包里, 能获取的最大价值为多少.
* 比较简单的转移是直接枚举第i种物品取用多少个: 
    dp[i][w] = max{dp[i - 1][w - k * A[i-1]] + k * V[i-1]}
    dp[i][w] 表示用前i种物品拼出总量w时的最大总价值
    dp[i - 1][w - k * A[i-1]] + k * V[i-1] 表示用i-1个物品拼出重量w - k * A[i-1]时，最大总价值，加上k个第i个物品的价值。
                                            其中A[i - 1]为当前第i个物品的总量， V[i - 1]为当前第i个物品的价值，k表示选择当前第i个物品的数量

* 但是这样速度较慢, 可以优化成 dp[i][w] 直接由 dp[i][w - A[i]] 转移, 并且从小到大枚举 w, 
* 这样做的含义就是在已经拿过第 i 个物品的之后还可以再拿它. 也就是说: 
* 计算 dp[i][w] 时, 初始设置为 dp[i - 1][w], 然后 dp[i][w] = max(dp[i - 1][w], dp[i][w - A[i -1]] + V[i -1])
    dp[i][w] = max(dp[i - 1][w], dp[i][w - A[i -1]] + V[i -1])
    dp[i - 1][w] 表示用前i-1个物品拼出重量w
    dp[i][w - A[i -1]] + V[i -1] 表示前i种物品拼出w-A[i - 1]时最大价值，再加上第i个物品价值。
	
***/
//version-1: time complexity O(n*m), space complexity O(n * m)
public class Solution {
    /**azs
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // check corner case
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }

        int n = A.length;
        // state
        int[][] dp = new int[n + 1][m + 1];

        // initialiize
        /* all default to 0 value (no chose, no size, no value)*/

        // function
        for (int i = 1; i <= n; i++) {// number of item kinds to choose(every kind of item is available to take by infinite count) 
            for (int w = 0; w <= m; w++) {// size availibility of the bag
		int currentValue = V[i - 1];
		int curentWeight = A[i - 1];
		    
                if (w - curentWeight < 0) {// when current bag size is not suitable for the current item's 
                    dp[i][w] = dp[i - 1][w];// don't take, no choose
                    continue;
                }

                dp[i][w] = Math.max(dp[i - 1][w], // not choose option
                                    dp[i][w - curentWeight] + currentValue);// choose option
                                    /*[current item]     [current value]*/
            } // for w
        }// for i

        return dp[n][m];
    }
}

//version-2: DP, time complexity O(n*m), space complexity O(m)
public class Solution {
    /**azs
     * @param A: an integer array
     * @param V: an integer array
     * @param m: An integer
     * @return: an array
     */
    public int backPackIII(int[] A, int[] V, int m) {
        // check corner case
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }

        int n = A.length;
        // state
        int[][] dp = new int[2][m + 1];

        // initialiize
        /* all default to 0 value (no chose, no size, no value)*/

        // function
        for (int i = 1; i <= n; i++) {// number of item kinds to choose(every kind of item is available to take by infinite count) 
            for (int w = 0; w <= m; w++) {// size availibility of the bag
                // when current bag size is not suitable for the current item's 
                dp[i % 2][w] = dp[(i - 1) % 2][w];// don't take, no choose

                if (w >= A[i - 1]) {
                    dp[i % 2][w] = Math.max(dp[(i - 1) % 2][w], // not choose option
                                            dp[i % 2][w - A[i - 1]] + V[i - 1]);// choose option
                                            /*[current item]         [current value]*/
				}
			} // for w
		}// for i
		
		return dp[n % 2][m];		
    }
}
