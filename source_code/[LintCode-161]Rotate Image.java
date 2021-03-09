/***
* LintCode 161. Rotate Image
You are given an n x n 2D matrix representing an image.
Rotate the image by 90 degrees (clockwise).

Example
    Example 1：
        Input:[[1,2],[3,4]]
        Output:[[3,1],[4,2]]
    Example 2:
        Input:[[1,2,3],[4,5,6],[7,8,9]]
        Output:[[7,4,1],[8,5,2],[9,6,3]]

Challenge
    Do it in-place.
***/
/*
*(0)四角旋转
*(1)先外层后里层
*(2）一层一层往里进
*/
public class Solution {
    /**
     * @param matrix: a lists of integers
     * @return: nothing
     */
    public void rotate(int[][] matrix) {
        // check corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return;
        }

        int n = matrix.length;

        int top = 0;
        int left = 0;
        int right = n - 1;
        int bottom = n - 1;

        while (n > 1) {

            // doing rotation process
            for (int i = 0; i < n - 1; i++) {
                int tmp = matrix[top][left + i];
                matrix[top][left + i] = matrix[bottom - i][left];
                matrix[bottom - i][left] = matrix[bottom][right - i];
                matrix[bottom][right - i] = matrix[top + i][right];
                matrix[top + i][right] = tmp;
            }

            top++;
            left++;
            right--;
            bottom--;

            n -= 2;
        }
    }
}
