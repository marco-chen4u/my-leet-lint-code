/***
* LintCode 28. Search a 2D Martix
Write an efficient algorithm that searches for a target value in an m x n matrix.

This matrix has the following properties:
    Integers in each row are sorted from left to right.
    The first integer of each row is greater than the last integer of the previous row.

Example 1:
    Input:
        matrix = [[5]]
        target = 2
    Output:
        false
    Explanation:
        The matrix does not include 2 ， returns false.

Example 2:
    Input:
        matrix = [
          [1, 3, 5, 7],
          [10, 11, 16, 20],
          [23, 30, 34, 50]
        ]
        target = 3
    Output:
        true

Explanation:
    The matrix includes 3, return true.

Challenge
    O(log(n) + log(m)) time
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

        if (matrix[0] == null || matrix[0].length == 0) {
            return false;
        }

        int m = matrix.length; // row count
        int n = matrix[0].length; // column count

        int start = 0;
        int end = n * m - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            int row = mid / n;
            int col = mid % n;
            if (matrix[row][col] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (matrix[start / n][start % n] == target) {
            return true;
        }

        if (matrix[end / n][end % n] == target) {
            return true;
        }

        return false;
    }
}

