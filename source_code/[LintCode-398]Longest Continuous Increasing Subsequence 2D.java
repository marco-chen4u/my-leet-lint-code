/***
* LintCode 398. Longest Continuous Increasing Subsequence 2D
Given an integer matrix. 
Find the longest increasing continuous subsequence in this matrix and return the length of it.

The longest increasing continuous subsequence here can start at any position and go up/down/left/right.

Example
    Example 1:
        Input: 
            [
              [1, 2, 3, 4, 5],
              [16,17,24,23,6],
              [15,18,25,22,7],
              [14,19,20,21,8],
              [13,12,11,10,9]
            ]
        Output: 25
        Explanation: 1 -> 2 -> 3 -> 4 -> 5 -> ... -> 25 (Spiral from outside to inside.)

    Example 2:
        Input: 
            [
              [1, 2],
              [5, 3]
            ]
        Output: 4
        Explanation: 1 -> 2 -> 3 -> 5

Challenge
    Assume that it is a N x M matrix. Solve this problem in O(NM) time and memory.
***/
// version-1: Memorized Search
public class Solution {
    // fields
    int n;// row size
    int m;// column size
    boolean [][] visited;
    int[][] values;
    int[] directionX = new int[] {0, 1, -1, 0};
    int[] directionY = new int[] {1, 0, 0, -1};
    
    /**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        // check corner cases
        if (matrix == null || matrix.length == 0) {
            return 0;
        }        
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        n = matrix.length;
        m = matrix[0].length;
        visited = new boolean[n][m];
        values = new int[n][m];
        
        int result = 0;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                values[i][j] = search(i, j, matrix);
                result = Math.max(result, values[i][j]);
            }
        }
        
        return result;
    }
    
    // helper method
    private int search(int x, int y, int[][] matrix) {
        if (visited[x][y]) {
            return values[x][y];
        }
        
        int value = 1;        
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];
            
            if (nextX < 0 || nextX >= n || 
                nextY < 0 || nextY >= m ||
				matrix[x][y] <= matrix[nextX][nextY]) {
                continue;
            }
            
            value = Math.max(value, search(nextX, nextY, matrix) + 1);
        }
        
        visited[x][y] = true;
        values[x][y] = value;
        
        return values[x][y];
    }
}
