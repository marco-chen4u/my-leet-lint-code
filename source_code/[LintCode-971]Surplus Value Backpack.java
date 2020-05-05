/***
* LintCode 971. Surplus Value Backpack
There is a backpack with a capacity of c.
There are n Class A items, the volume of the i th Class A item is a[i], and the value of the item is the remaining capacity of the backpack after loading the item * k1.
There are m Class B items, the volume of the i th Class B item is b[i], and the value of the item is the remaining capacity of the backpack after loading the item * k2.
Find the maximum value can be obtained.

Example
	Example 1:
		Given k1 = `3`,k2 = `2`,c = ` 7`,n = `2`,m = `3`,a = `[4,3]`,b = `[1,3,2]`，return `23`.
		Input:
			3 2 7 2 3
			[4,3]
			[1,3,2]
		Output:
			23
		Explanation:
			2 * (7-1)+2*(6-2) + 3 * (4-3) = 23

	Example 2:
		Given k1 = `1`,k2 = `2`,c = ` 5`,n = `1`,m = `1`,a = `[2]`,b = `[1]`，return `10`.
		Input:
			1 2 5 1 1
			[2]
			[1]
		Output:
			10
		Explanation:
			2 * (5-1)+1*(4-2) = 10

Notice
	1 <= k1, k2, c, a[i], b[i] <= 10^7
	1 <= n, m <= 1000
***/
/*
* 证明对于依次放入的两个同类物品，重量必须从小到大。(这个证明，必须理解)
* 因此， A类物品一定是从小到大依次选的， B类同理。
* 只是A，B之间的顺序需要确定。
* 如果将A类和B类的物品，分别按重量从小到大排序，任何方案都是取A类的前i个和B类的前j个。
*
* 双序列动态规划
*
* 确定状态：
*        取A类的前i个和取B类的前j个
*        最后一步： 最后一个物品是A[i - 1]，还是B[j - 1].
*                这个物品的得分是确定的，因为，剩下的空间，一定是fixed_value = c - (A[0] + ... + A[i - 1]) - (B[0] + ... + B[j - 1]), 这个值是确定的
*                
*                得分，取决于前(i + j) - 1个物品，的最大得分。越大越好。要求其最大值，只能寄托于其前（i + j）-1个物品的子问题求其最大值。
*        设dp[i][j]，表示取A类物品的前i个和B类物品的前j个，放到背包，所得到的最大价值。
*        dp[i][j] = max{
*	                       dp[i - 1][j] + k1(fixed_value)      即最后一个物品是A[i - 1]
*                          ,
*                          dp[i][j - 1]  + k2(fixed_value)     即最后一个物品是B[j - 1]
*                      }
*
* 初始条件： dp[0][0] = 0
* 
* 边界情况：
*        如果i = 0, 就不考虑情况1; 如果j = 0, 就不考虑情况2.
*        如果fixed_value = c - (A[0] + ... + A[i - 1]) - (B[0] + ... + B[j - 1]) < 0, 那么dp[i][j] = 0
*
* 计算顺序
*       dp[0][0], ..., dp[0][m]
*       dp[1][0], ..., dp[1][m]
*       dp[2][0], ..., dp[2][m]
*       ...
*       dp[n][0], ..., dp[n][m]
*
* 答案
*       dp[n][m], 时间复杂度O(n * m)， 空间复杂度O(n * m)
* 
* 注意： 关于fixed_value = c - (A[0] + ... + A[i - 1]) - (B[0] + ... + B[j - 1])的求值，可以用前缀和来进行求算。
*/
public class Solution {
    /**
     * @param k1: The coefficient of A
     * @param k2: The  coefficient of B
     * @param c: The volume of backpack
     * @param n: The amount of A
     * @param m: The amount of B
     * @param a: The volume of A
     * @param b: The volume of B
     * @return: Return the max value you can get
     */
    public long getMaxValue(int k1, int k2, int c, int n, int m, int[] a, int[] b) {
		// check corner case
		if (c == 0 || (isEmpty(a) && isEmpty(b))) {
			return 0;
		}
        
        // sort all the items by ascending order
		Arrays.sort(a);
		long[] prefixSumA = getPrefixSum(a);
		
		Arrays.sort(b);
		long[] prefixSumB = getPrefixSum(b);
		
		// state
		long result = 0;
		long[][] dp = new long[n + 1][m + 1];
		
		// initialize
		//dp[0][0] = 0;
		
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				if (i == 0 && j == 0) {
					dp[i][j] = 0;
					continue;
				}
				
				long remainSpace = c - (prefixSumA[i] + prefixSumB[j]);
				
				if (remainSpace < 0) {
				    continue;
				}
				
				if (i > 0) {
				    dp[i][j] = Math.max(dp[i][j],
				                        dp[i - 1][j] + k1 * remainSpace);
				}
				
				if (j > 0) {
				    dp[i][j] = Math.max(dp[i][j],
				                        dp[i][j - 1] + k2 * remainSpace);
				}
				
				result = Math.max(result, dp[i][j]);
			}
		}
		
		return result;
		
    }
	
	// get it's prefix sum
	private long[] getPrefixSum(int[] values) {
	    int n = values.length;
	    long[] prefixSum = new long[n + 1];
	    
		for (int i = 1; i <= n; i ++) {
			prefixSum[i] = prefixSum[i - 1] + values[i - 1];
		}
		
		return prefixSum;
	}
	
	private boolean isEmpty(int[] values) {
		return values == null || values.length == 0;
	}
}