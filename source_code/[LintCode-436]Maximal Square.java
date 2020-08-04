/***
* LintCode 436. Maximal Square
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing all 1's and return its area.
Example
    Example 1:
        Input:
            [
              [1, 0, 1, 0, 0],
              [1, 0, 1, 1, 1],
              [1, 1, 1, 1, 1],
              [1, 0, 0, 1, 0]
            ]
        Output: 4
    Example 2:
        Input:
            [
              [0, 0, 0],
              [1, 1, 1]
            ]
        Output: 1
***/
/*
* 像这类型（长方形，正方形面积问题）的跟序列型问题差不多，突破口是看最后一个点（长方形、正方形的最后一个点是其右下角的那个点，序列则是最右一个点），从此点最为最后一步进行分析 
*
* 如果以(i - 1, j), (i, j - 1), 和（i - 1， j - 1）为右下角的最大（全是1的）正方形的变成分别为l1, l2和l3， 而（i，j）的值也是1， 
* 那么以（i，j）为右下角的最大（全是1的）正方形的边长，应该是min{l1, l2, l3} + 1  【即木桶原理】
* 
* 确定状态：
*        dp[i][j],表示以(i, j)为右下角的最大全1正方形的边长。
*         
*        dp[i][j] = 0                                                      即该点(i,j)的值为0，不是1
*                   OR
*                   min{dp[i - 1][j], dp[i][j - 1], dp[i - 1][j -1]} + 1   即该点(i, j)值为1
*
* 初始边界情况：
*           i = 0,或j = 0，即最左边一列和最上边一行，为各个点的值。
* 
* 计算顺序：
*         dp[0][0], ..., dp[0][n - 1]
*         ...
*         dp[m - 1][0], ...,dp[m - 1][n - 1]
*
* 时间复杂度O(m * n)， 空间复杂度O(m * n),空间复杂度可以优化为O(n) 
*/

// solution-1: DP
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // check corner cases
        if (matrix == null || matrix.length == 0) {
            return 0;
        }        
        if (matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;// row size
        int m = matrix[0].length; // column size
        // state
        int[][] f = new int[n][m];        
        int length = 0;
        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    f[i][j] = 1;
                    length = 1;
                }
            }
        }
        // function
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    f[i][j] = Math.min(Math.min(f[i - 1][j - 1], 
                                        f[i][j - 1]), f[i - 1][j]) +
                                        1;
                    
                    length = Math.max(length, f[i][j]);
                }
            }
        }
        // return answer
        return length * length;
    }
}

// solution-2: DP
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // check corner cases
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        if (matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;// row size
        int m = matrix[0].length; // column size
        
        // state
        int[][] f = new int[n][m];
        
        int length = 0;
        
        // function
        for (int i = 0; i < n; i++) {//row
            // initialize
            f[i][0] = matrix[i][0];
            if (matrix[i][0] == 1) {
                length = Math.max(f[i][0], length);
            }
            
            for (int j = 1; j < m; j++) {// column
                
                if (i == 0) {
                    // initialize
                    f[i][j] = matrix[i][j];
                    if (matrix[i][j] == 1) {
                        length = Math.max(f[i][j], length);
                    }
                    
                    continue;
                }
                
                if (matrix[i][j] == 1) {
                    f[i][j] = Math.min(Math.min(f[i - 1][j - 1], 
                                        f[i][j - 1]), f[i - 1][j]) +
                                        1;
                    
                    length = Math.max(length, f[i][j]);
                }
            }
        }
        
        return length * length;
    }
}

// solution-3: rotated Array and DP
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // check corner cases
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        if (matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;// row size
        int m = matrix[0].length; // column size
        
        // state
        int[][] f = new int[2][m];
        
        int length = 0;
        
        // function
        for (int i = 0; i < n; i++) {//row
            // initialize
            f[i % 2][0] = matrix[i][0];
            length = Math.max(f[i % 2][0], length);
            
            for (int j = 1; j < m; j++) {// column
                
                if (i == 0) {
                    // initialize
                    f[i % 2][j] = matrix[i][j];
                    length = Math.max(f[i % 2][j], length);
                    
                    continue;
                }
                
                if (matrix[i][j] > 0) {
                    f[i % 2][j] = Math.min(Math.min(f[(i - 1) % 2][j - 1], 
                                    f[i % 2][j - 1]), f[(i - 1) % 2][j]) +
                                    1;
                }
                else {
                    f[i % 2][j] = 0;
                }
                
                length = Math.max(length, f[i % 2][j]);
            }
        }
        
        return length * length;
    }
}

// solution-4: more elegant code but similar to solution-1
public class Solution {
    /**
     * @param matrix: a matrix of 0 and 1
     * @return: an integer
     */
    public int maxSquare(int[][] matrix) {
        // check corner case
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        int maxLength = 0;
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (matrix[i - 1][j - 1] == 0) {
                    continue;
                }
                
                dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                
                maxLength = Math.max(maxLength, dp[i][j]);
            }
        }
        
        return maxLength * maxLength;
        
    }
}
