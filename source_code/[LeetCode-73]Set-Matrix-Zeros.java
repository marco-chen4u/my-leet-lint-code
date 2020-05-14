/***
* LeetCode 73. Set Matrix Zeroes
Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:
    Input: 
    [
      [1,1,1],
      [1,0,1],
      [1,1,1]
    ]
    Output: 
    [
      [1,0,1],
      [0,0,0],
      [1,0,1]
    ]

Example 2:
    Input: 
    [
      [0,1,2,0],
      [3,4,5,2],
      [1,3,1,5]
    ]
    Output: 
    [
      [0,0,0,0],
      [0,4,5,0],
      [0,3,1,0]
    ]

Follow up:
    A straight forward solution using O(mn) space is probably a bad idea.
    A simple improvement uses O(m + n) space, but still not the best solution.
    Could you devise a constant space solution?
***/
//version-1
class Solution {
    private final int MARK = Integer.MAX_VALUE-1;
    private final int ZERO = 0;
    
    private int n; // row size
    private int m; // column size
    
    public void setZeroes(int[][] matrix) {
        // check corner case
        if (matrix == null || matrix.length == 0) {
            return;
        }
        
        // normal case
        n = matrix.length; // row size
        m = matrix[0].length; // column size
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                if (matrix[i][j] != ZERO) {
                    continue;
                }
                
                setValue(matrix, i, j, MARK);
            }
        }
        
        setValues(matrix, MARK, ZERO);
    }
    
    private void setValue(int[][] matrix, int rowPos, int colPos, int markValue) {

        if (rowPos < 0 || rowPos >= n || // row size check
                colPos < 0 || colPos >= m) { // column size check
            return;
        }
        
        for (int i = 0; i < n; i++) {
            int currentValue = matrix[i][colPos];//same column with different row
            
            if (currentValue == ZERO || 
                    currentValue == markValue) {
                continue;
            }
            
            matrix[i][colPos] = markValue;
        }
        
        for (int j = 0; j < m; j++) {
            int currentValue = matrix[rowPos][j];// same row with different columns
            
            if (currentValue == ZERO ||
                    currentValue == markValue) {
                continue;
            }
            
            matrix[rowPos][j] = markValue;
        }
    }
    
    private void setValues(int[][] matrix, int predicateValue, int value) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int currentValue = matrix[i][j];
                matrix[i][j] = (currentValue == predicateValue) ? value : currentValue;
            }
        }
    }
}

//version-2
public class Solution {
    /**
     * @param matrix: A lsit of lists of integers
     * @return: nothing
     */
    public void setZeroes(int[][] matrix) {
        // check corner case
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0) {
            return;
        }
        
        // initialize
        int n = matrix.length; // row size
        int m = matrix[0].length; // column size
        
        // pre check the corner case
        boolean isFirstRowZeros = false;
        boolean isFirstColumnZeros = false;
        // first row check 
        for (int j = 0; j < m; j++) {
            if (matrix[0][j] == 0) {
                isFirstRowZeros = true;
                break;
            }
        }
        // first column check
        for (int i = 0; i < n; i++) {
            if (matrix[i][0] == 0) {
                isFirstColumnZeros = true;
                break;
            }
        }
        
        
        // normal case check
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        // row of zero setting
        for (int i = 1; i < n; i++) {
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }
        
        // column of zero setting
        for (int j = 1; j < m; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < n; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        
        // corner case process
        if (isFirstRowZeros) {
            Arrays.fill(matrix[0], 0);
        }
        
        if (isFirstColumnZeros) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
