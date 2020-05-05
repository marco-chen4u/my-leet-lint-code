/***
* LintCode 125. Backpack II
There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.
What's the maximum value can you put into the backpack?
Example
	Example 1:
		Input: m = 10, A = [2, 3, 5, 7], V = [1, 5, 2, 4]
		Output: 9
		Explanation: Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9 
	Example 2:
		Input: m = 10, A = [2, 3, 8], V = [2, 5, 8]
		Output: 10
		Explanation: Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10 
Challenge
	O(nm) memory is acceptable, can you do it in O(m) memory?
Notice
	1.A[i], V[i], n, m are all integers.
	2.You can not split an item.
	3.The sum size of the items you want to put into backpack can not exceed m.
	4.Each item can only be picked up once
***/
/*
*设定dp[i][w] = 用前i个物品p拼出总重量w时的最大价值（-1表示不能拼出w）
		dp[i][w] = max{dp[i - 1][w], dp[i - 1][w - A(i - 1)] + V[i - 1]} , 且w >= A(i - 1) 而且 dp[i - A(i - 1)] ！= -1
					dp[i][w]     表示用前i个物品p拼出总重量w时的最大价值
					dp[i - 1][w] 表示前i-1个物品p拼出总重量w时的最大价值
					 dp[i - 1][w - A(i - 1)] + V[i - 1] 表示用前i-1个物品拼出总重量w - A(i - 1)时最大总价值，再加上第i个物品的价值.其中，A（i - 1）表示当前第i个物品的单个重量。
* time complexity : O(m*n). 
  space complexity : O(m*n), it could be optimize to O(m) by using rotated array 					 
*/
//version-1: DP, initialize value with -1
public class Solution {
	// field
	private final int DEFAULT_VALUE = -1;
	
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // check corner cases
        if (isEmpty(A) || isEmpty(V) || m <= 0) {
            return 0;
        }
        
        // state
        int n = A.length;//物品的个数
        int[][] dp = new int[n + 1][m + 1]; // 总价值
		                  //   |      |
		                  // count   size
        
        // initialize
		Arrays.fill(dp[0], DEFAULT_VALUE);//0个物品不能拼出相关的重量w=[1, m]，所以其价值为-1
        dp[0][0] = 0;//0个物品拼出总重量0，其最大价值为0
		
        // function
        for (int i = 1; i <= n; i++) {//items
            for (int w = 1; w <= m; w++) {// size
                //current item's size >= current size(j)
				//so not choose current item, and use previous state
				dp[i][w] = dp[i - 1][w];
				
                if (w >= A[i - 1] && dp[i - 1][w - A[i - 1]] != DEFAULT_VALUE) {
                    // current size-j, 
                    // current item: A[i-1], current item's value: V[i - 1]
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - A[i - 1]] + V[i - 1]);
                }
            }
        }
        
        // return
		int result = 0;
		for(int w = 0; w <= m; w++) {
			if (dp[n][w] == DEFAULT_VALUE) {
				continue;
			}
			
			result = Math.max(result, dp[n][w]);
		}
        
		return result;
    }
    
	// helper method
    private boolean isEmpty(int[] nums) {
        return (nums == null || nums.length == 0);
    }
}

//version-2:DP, initialize value with 0
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // check corner cases
        if (A == null || A.length == 0) {
            return 0;
        }
        
        if (m == 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[n + 1][m + 1];
        
        //initialize 
        
        // function
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= m; w++) {
                dp[i][w] = dp[i - 1][w];
                
                if (w >= A[i - 1]) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - A[i - 1]] + V[i - 1]);
                }
            }
        }
        
        // return
        return dp[n][m];
    }
}

//version-3: using rotated array, space complexity O(m)
public class Solution {
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // check corner cases
        if (A == null || A.length == 0) {
            return 0;
        }
        
        if (m == 0) {
            return 0;
        }
        
        int n = A.length;
        
        int[][] dp = new int[2][m + 1];
        
        //initialize 
        
        // function
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= m; w++) {
                dp[i % 2][w] = dp[(i - 1) % 2][w];
                
                if (w >= A[i - 1]) {
                    dp[i % 2][w] = Math.max(dp[(i - 1) % 2][w], dp[(i - 1) % 2][w - A[i - 1]] + V[i - 1]);
                }
            }
        }
        
        // return
        return dp[n%][m];
    }
}


//version-4: DFS but run time exception
public class Solution {
	
	int result;
	int length;
	int maxSize;
	
    /**
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
		// initialize
		result = 0;
		length = A.length;// or V.length
		maxSize = m;
		
		dfs(A, V, 0, 0, 0);
		
		return result;
	}
	
	// helper method
	private void dfs(int[] items, int[] values, int currentPos, int currentSize, int currentValue) {
		// check corner cases
		if (currentPos > length || 
			currentSize > maxSize) {
			
			return;
		}
		
		result = Math.max(result, currentValue);
		
		for (int i = currentPos; i < length; i++) {
			dfs(items, values, i + 1, currentSize + items[i], currentValue + values[i]);
		}
	}
}