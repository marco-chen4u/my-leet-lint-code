/***
* LintCode 631. Maximal Square II
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square which diagonal is all 1 and others is 0.
Only consider the main diagonal situation.

Example
	Example 1:
		Input:
			[[1,0,1,0,0],[1,0,0,1,0],[1,1,0,0,1],[1,0,0,1,0]]
		Output:
			9
		Explanation:
			[0,2]->[2,4]

	Example 2:
		Input:
			[[1,0,1,0,1],[1,0,0,1,1],[1,1,1,1,1],[1,0,0,1,0]]
		Output:
			4
		Explanation:
			[0,2]->[1,3]
	Example 3:
		Input:			
			[[1],[1],[1],[1],[1],[1]]
		Output:
			1
***/
public class Solution {
    /**
     * @param matrix: a matrix of 0 an 1
     * @return: an integer
     */
    public int maxSquare2(int[][] matrix) {
        int maxLength = 0;
        // check corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int n = matrix.length;
        int m = matrix[0].length;
        
        // state
        int[][] dp = new int[n][m];// left-> right diagonal anchor side continuous 1 length
        int[][] upper = new int[n][m];// left side position's continuous 0 length
        int[][] left = new int[n][m];// upper side position's continuous 0 length
        
        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                    upper[i][j] = (matrix[i][j] == 0) ? 1 : 0;
                    left[i][j] = (matrix[i][j] == 0) ? 1 : 0;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        
        // function
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(upper[i - 1][j], left[i][j - 1])) + 1;
                }
                else {
                    dp[i][j] = 0;
                    upper[i][j] = upper[i - 1][j] + 1;// length of continuous 0 
                    left[i][j] = left[i][j - 1] + 1;// length of continuous 0
                } // if
                
                maxLength = Math.max(maxLength, dp[i][j]);
            }// for j
        }// for i
        
        return maxLength * maxLength;
    }
}