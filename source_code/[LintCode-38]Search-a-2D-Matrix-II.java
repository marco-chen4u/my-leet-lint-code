/***
* LintCode 38. Search a 2D Matrix II
Write an efficient algorithm that searches for a value in an m x n matrix, return The number of occurrence of it.

This matrix has the following properties:
    Integers in each row are sorted from left to right.
    Integers in each column are sorted from up to bottom.
    No duplicate integers in each row or column.

Example 1
    Input:
        matrix = [[3,4]]
        target = 3
    Output:
        1

Example 2
    Input:
        matrix = [
              [1, 3, 5, 7],
              [2, 4, 7, 8],
              [3, 5, 9, 10]
            ]
        target = 3
    Output:
        2

* LintCode link: https://www.lintcode.com/problem/38/
* LeetCode link: https://leetcode.com/problems/search-a-2d-matrix-ii/ (LeetCode 240)
***/
// version-1 : two pointers
public class Solution {
    /**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        int count = 0;

        int m = matrix.length;    // row size
        int n = matrix[0].length; // col size

        int x = m - 1;
        int y = 0; // start from left-bottom corner go right-up to left-top point

        while (x >= 0 && y < n) {
            if (matrix[x][y] < target) {
                y++;
                continue;
            }

            if (matrix[x][y] > target) {
                x--;
                continue;
            }

            if (matrix[x][y] == target) {
                count += 1;
                x--;
                y++;
            }
        }

        return count;
    }
}
