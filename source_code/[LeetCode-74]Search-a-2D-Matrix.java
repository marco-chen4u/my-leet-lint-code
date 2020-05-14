/***
*LeetCode 74. Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

Example 1:
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 3
    Output: true

Example 2:
    Input:
    matrix = [
      [1,   3,  5,  7],
      [10, 11, 16, 20],
      [23, 30, 34, 50]
    ]
    target = 13
    Output: false

***/
//version-1
public class Solution {
    /**
     * @param matrix: matrix, a list of lists of integers
     * @param target: An integer
     * @return: a boolean, indicate whether matrix contains target
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        // check corner case
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        
        int start = 0;
        // locate row
        int end = rowSize - 1;
        int row;
        while (start + 1 < end) {
            int mid = start + (end -start) / 2;
            if (matrix[mid][0] == target) {
                return true;
            }
            else if (matrix[mid][0] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (matrix[end][0] <= target) {
            row = end;
        }
        else if (matrix[start][0] <= target) {
            row = start;
        }
        else {
            return false;
        }
        
        
        start = 0;
        // locate column
        end = columnSize -1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (matrix[row][mid] == target) {
                return true;
            }
            else if (matrix[row][mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        } 
        
        if (matrix[row][start] == target) {
            return true;
        }
        
        if (matrix[row][end] == target) {
            return true;
        }
        
        return false;
    }
}
