/***
* LintCode 254. Drop Eggs
There is a building of n floors. If an egg drops from the k th floor or above, it will break. 
If it's dropped from any floor below, it will not break.

You're given two eggs, Find k while minimize the number of drops for the worst case. 
Return the number of drops in the worst case.

Example
	Example 1:
		Input: 100
		Output: 14
	Example 2:
		Input: 10
		Output: 4

Clarification
	For n = 10, a naive way to find k is drop egg from 1st floor, 2nd floor ... kth floor. 
	But in this worst case (k = 10), you have to drop 10 times.

Notice that you have two eggs, so you can drop at 4th, 7th & 9th floor, 
in the worst case (for example, k = 9) you have to drop 4 times.
***/
//version-1: partial past all test cases(floor-max = 24548)
public class Solution {
	private final int DEFAULT_VALUE_MAX = Integer.MAX_VALUE;
	private final int EGG_COUNT = 2;
    /**
     * @param n: An integer
     * @return: The sum of a and b
     */
    public int dropEggs(int n) {
        // check corner case
        if (n <= 1) {
            return n;
        }
        
		// state
        int[][] dp = new int[n + 1][			 + 1];
        // initialize
        for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= EGG_COUNT; j++) {
				if (i == 1) {
					dp[i][j] = 1;
					continue;
				}
				
				if (j == 1) {
					dp[i][j] = i;
					continue;
				}
				
				dp[i][j] = DEFAULT_VALUE_MAX;
			}
		}
		
		// function
		int dropCount = 0;
		for (int i = 2; i <= n; i++) {// total number of floors
			for (int x = 1; x <= i; x++) { // the No. floor where the egg drop breaks
				dropCount = Math.max(dp[x - 1][EGG_COUNT - 1], dp[i - x][EGG_COUNT]) + 1;
				dp[i][EGG_COUNT] = Math.min(dp[i][EGG_COUNT], dropCount);
			}
		}
        
        return dp[n][EGG_COUNT];
    }
}
/***
学习进展小节：

两种解法：DP和math
DP：
设f（n，k）为n蛋k层最优解。从第i层丢，碎了的话还要试 f（n-1，i-1）次，不碎要试f（n，k-i）次。所以任意i的策略是 f(n,k) = 1 + max(f(n-1, i-1), f(n, k-i)). 求最优解： f(n,k) = min(1 + max(f(n-1, i-1), f(n, k-i))) for any i in {1..n}.
其中：f(1, k) = k, f(1, 0) = 0, f(0,k) = 0.

数学法：
设x为最优次数，求x能测试的最大楼层数k。第一次在第x层丢，碎了还有x-1层需要测试
x + （x-1）+ （x-2）+ ... + 1 = x*(x+1)/2 >= k
***/

//version-2: 
public class Solution {
    /**
     * @param n: An integer
     * @return: The sum of a and b
     */
    public int dropEggs(int n) {
        // 其实就是求x : x + (x - 1) + (x - 2)+ ... + 1 > = n, 即 (x + 1) * x / 2 >= n
        // 先倍增法找右边界，然后二分法找first position >= n， 类似Search in a Big Sorted Array 
        // 需要注意的是n可能是最大整数，所以用long 
        long index = 1;
        while (index * (index + 1) / 2 < n) {
            index = index * 2;
        }
        
        long start = 1;
        long end = index;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * (mid + 1) / 2 >= n) {
                end = mid;
            } else {
                start = mid;
            } 
        }
        
        if (start * (start + 1) / 2 >= n) {
            return (int)start;
        } else {
            return (int)end;
        }
    }
}