/***
* LintCode 92. Backpack
Given n items with size Ai, an integer m denotes the size of a backpack. 
How full you can fill this backpack?

Example 1
    Input:  [3,4,8,5], backpack size=10
    Output:  9

Example 2
    Input:  [2,3,5,7], backpack size=12
    Output:  12
	
Challenge
    O(n x m) time and O(m) memory.
    O(n x m) memory is also acceptable if you do not know how to optimize memory.

Notice
    You can not divide any item into small pieces.
***/
/*
* 背包问题
    -你有1个背包，背包由最大承重。
    -商店里由若干个商品，都是免费的。
    -每隔物品由重量和价值
    目标：[不撑爆背包的前提下]
        -装下最多重量的物品
        -装下最大总价值物品
        -有多少种方式正好满满带走一书包物品
    直觉
        -逐个放物品，看当前物品是否要（还能）放进书包
        -两个关键点：
                    -还有几件物品
                    -还剩多少承重

* 本题题意： 给定n个物品，重量分别是正整数A0, A1, ..., A(n-1)
          一个书包的最大承重是正整数m
          最多能带走多少重量的物品？
    分析：
        -每一个装入物品进书包的方案中， 其总重量[0..m]
        -如果对于每一个总重量，我们能否知道有没有某一方案能做到，就可以解决
            即
                -------------------------
               |0   1   2  ...  m-1  m  |
                -------------------------
               |ok  n/a ok ...  ok   n/a|
                -------------------------
                                ^
                                |
        注意： 背包问题中，数组大小，（至少有一维）和书包总承重有关
		
        -从最后一步下手
            -需要知道n个物品能否拼出重量w（w=[0..m]）
            -最后一步： 最后一个物品（重量A{n - 1}）是否放进书包
                --情况[1]：如果前n-1个物品能拼出w，当然前n个物品也能拼出w
                --情况[2]:如果前n-1个物品能拼出w-A{n - 1},再加上最后一个物品A{n - 1}, 拼出w
            -状态dp[i][w] = 能否用前i个物品拼出重量(true/false)
                dp[i][w] = dp[i - 1][w] OR dp[i - 1][w - A{i - 1}]
                            -dp[i - 1][w]能否用前i-1个物品拼出重量
                            -dp[i - 1][w - A{i - 1}]能否用前i-1个物品拼出重量w - A{i - 1}，再加上第i个物品重量 
                初始化：
                    dp[0][0] = true  0个物品可以拼出重量0
                    dp[0][1..m] = false 0个物品不能拼出大于0的重量
                边界情况
                dp[i-1][w - A{i - 1}]只能拿w >= A{i - 1}时使用		
    总结：
        要求不超过m时能拼出的最大重量
        记录前i个物品能拼出哪些重量，把每个重量的方案可能性都计算出来
        前i个物品能拼出的重量：
            -前i-1个物品拼出的重量；[即不取当前物品]
            -前i-1个物品拼出的重量 + 第i个物品重量A{i - 1};[即取当前物品]
*
//version-1
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // check corner case
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
		
        int n = A.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
		
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {//前i个物品的取舍
            for (int w = 0; w <= m; w++) {//设定总承重量
                //不取当前物品
                dp[i][w] = dp[i - 1][w];
				
                //取当前物品
                if (w >= A[i - 1] &&          //w >= A[i - 1]是符合取当前物品的前提
                    dp[i - 1][w - A[i - 1]]) {//当取当前物品时,之前包中的重量符合条件
                    dp[i][w] = true;
                }
            }
        }

        // get the max 
        for (int w = m; w >= 0; w--) {
            if (dp[n][w]) {
                return w;
            }
        }
		
        return 0;
    }
}

//version-2
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // check corner case
        if (A == null || A.length == 0 || m <= 0) {
            return 0;
        }
		
        int n = A.length;
        boolean[][] dp = new boolean[2][m + 1];
		
        dp[0][0] = true;
        for (int i = 1; i <= n; i++) {
            for (int w = 0; w <= m; w++) {
                dp[i % 2][w] = dp[(i - 1) % 2][w];
                if (w >= A[i - 1] && dp[(i - 1) % 2][w - A[i - 1]]) {
                    dp[i % 2][w] = true;
                }
            }
        }
		
        for (int i = m; i >= 0; i--) {
            if (dp[n % 2][i]) {
                return i;
            }
        }
		
        return 0;
    }
}

//version-3
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @return: The maximum size
     */
    public int backPack(int m, int[] A) {
        // corner case
        if ((A == null || A.length == 0) || 
            m <= 0) {
            return 0;
        }
        
        int n = A.length;
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                // corner case
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                    continue;
                }
                
                if (i == 0) {
                    dp[i][j] = false;
                    continue;
                }
                
                // normal case
                dp[i][j] = dp[i - 1][j];
                
                if (j - A[i - 1] >= 0) {
                    dp[i][j] = dp[i][j] | dp[i - 1][j - A[i - 1]];
                }
            }
        }
        
        for (int w = m; w >= 0; w--) {
            if (dp[n][w]) {
                return w;
            }
        }
        
        return 0;
    }
}
