/***
* LintCode 89. k Sum
Given n distinct positive integers, integer k (k <= n) and a number target.
Find k numbers where sum is target. Calculate how many solutions there are?

Example
    Example 1
        Input:
            List = [1,2,3,4]
            k = 2
            target = 5
        Output: 2
        Explanation: 1 + 4 = 2 + 3 = 5
    Example 2
        Input:
            List = [1,2,3,4,5]
            k = 3
            target = 6
        Output: 1
        Explanation: There is only one method. 1 + 2 + 3 = 6
***/
/*
* 最后一步： 最后一个数A[n -1],是否选入这k个数
* 情况1： A[n - 1]不选入， 需要在前n-1个数中，选出k个，使得它们的和等于target
* 情况2： A[n - 1]选入，需要在前n-1个数中，选出k-1个，使得它们的和等于tareget - A[n - 1]
* 但是，要知道还有几个数可以选，以及它们的和是多少：序列+状态

* 状态: dp[i][j][t],表示有多少种方法，可以在前i个数种选出j个，使得它们的和，等于t。
*      dp[i][j][t] = dp[i - 1][j][t]                  即不选当前第i个数
                        +
					 dp[i - 1][j - 1][t - A[i - 1]]   即选了当前第i个数，其中， A【i-1]为序列A中，第i个数的值。
* 
*                    解释： dp[i-1][j -1][t-A[i - 1]], 表示有多少种方法，可以在前 i - 1个数中，选出k-1个，使得它们的和等于t - A[i - 1]
*
* 因此， 这道题和计数型背包，以及股票买卖类型的问题，是同一个类型的问题。
* 初始条件
*        dp[0][0][0] = 1;
*        dp[0][0][t] = 0, t = 1, 2, 3, ..., target
* 边界条件
*        如果t < A[i - 1], 那么不考虑选取当前第i个数，只考虑dp[i - 1][j][t]即不选当前第i个数。
* 计算顺序
*        dp[0][0~k][0~target]
*        dp[1][0~k][0~target]
*        ...
*        dp[n][0~k][0~target]
*
* 答案
*        dp[n][k][target]
* 实践复杂度： O(n * k * target) ,空间复杂度 O(n * k * target), 空间复杂度可以优化为O(k * target)
*/
//version-1:DP-backpack
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // check corner cases
        if (A == null || A.length == 0 || k <= 0 || target <= 0) {
            return 0;
        }

        // state
        int n = A.length;
        int[][][] dp = new int[n + 1][k + 1][target + 1];

        // intialize
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i][0][0] = 1;//initialize

            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i][j][t] = 0;
                    if (t >= A[i - 1]) {
                        dp[i][j][t] += dp[i - 1][j -1][t - A[i - 1]];
                    }

                    dp[i][j][t] += dp[i - 1][j][t];
                }// for t
            }// for j

        }// for i

        // answer
        return dp[n][k][target];
    }
}// Time complexicty-O(n*k*target), space complexity-O(n*k*target)

//version-2: DP-backpack with Rotated Array to optimize space complexicty
public class Solution {
    /**
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // check corner cases
        if (A == null || A.length == 0 || k <= 0 || target <= 0) {
            return 0;
        }

        // state
        int n = A.length;
        int[][][] dp = new int[2][k + 1][target + 1];

        // intialize
        dp[0][0][0] = 1;

        for (int i = 1; i <= n; i++) {
            dp[i % 2][0][0] = 1;//initialize

            for (int j = 1; j <= k; j++) {
                for (int t = 1; t <= target; t++) {
                    dp[i % 2][j][t] = 0;
                    if (t >= A[i - 1]) {
                        dp[i % 2][j][t] += dp[(i - 1) % 2][j -1][t - A[i - 1]];
                    }

                    dp[i % 2][j][t] += dp[(i - 1) % 2][j][t];
                }// for t
            }// for j
        }// for i

        // answer
        return dp[n % 2][k][target];
    }
}// Time complexicty-O(n*k*target), space complexity-O(k*target)
