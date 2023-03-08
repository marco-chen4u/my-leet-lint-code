/***
* LeetCode 302. Smallest Rectangle Enclosing Black Pixels
* You are given an m x n binary matrix image where 0 represents a white pixel and 1 represents a black pixel.

The black pixels are connected (i.e., there is only one black region). Pixels are connected horizontally and vertically.

Given two integers x and y that represents the location of one of the black pixels, 
return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

You must write an algorithm with less than O(mn) runtime complexity

Example 1:
    Input: image = [["0","0","1","0"],["0","1","1","0"],["0","1","0","0"]], x = 0, y = 2
    Output: 6

Example 2:
    Input: image = [["1"]], x = 0, y = 0
    Output: 1

Constrainst:
    m == image.length
    n == image[i].length
    1 <= m, n <= 100
    image[i][j] is either '0' or '1'.
    0 <= x < m
    0 <= y < n
    image[x][y] == '1'.
    The black pixels in the image only form one component.

* Link: https://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/
***/
// version-1: binary search /w first-index and last-index of target to search 
class Solution {

    // constants
    private static final int WHITE = '0';

    // fields
    private static int m; // row size
    private static int n; // col size

    public int minArea(char[][] image, int x, int y) {

        if (image == null || image.length == 0 || image[0] == null || image[0].length == 0) {
            return 0;
        }

        m = image.length;
        n = image[0].length;

        if (x < 0 || x >= m || y < 0 || y >= n) {
            return 0;
        }

        int left = getLeftBoundary(image, y);
        int right = getRightBoundary(image, y);

        int top = getTopBoundary(image, x);
        int bottom = getBottomBoundary(image, x);

        return (right - left + 1) * (bottom - top + 1);        
    }

    // helper method
    private int getLeftBoundary(char[][] image, int y) { // colum boundary
        int start = 0; 
        int end = y;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (isEmptyColumn(image, mid)) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (!isEmptyColumn(image, start)) {
            return start;
        }

        return end;
    }

    private int getRightBoundary(char[][] image, int y) { // column boundary
        int start = y;
        int end = n - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) /2;

            if (isEmptyColumn(image, mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (!isEmptyColumn(image, end)) {
            return end;
        }

        return start;
    }

    private boolean isEmptyColumn(char[][] image, int col) {
        if (col < 0 || col >= n) {
            return false;
        }

        for (int row = 0; row < m; row++) {
            if (image[row][col] != WHITE) {
                return false;
            }
        }

        return true;
    }

    private int getTopBoundary(char[][] image, int x) { // row boundary
        int start = 0;
        int end = x;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (isEmptyRow(image, mid)) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (!isEmptyRow(image, start)) {
            return start;
        }

        return end;
    }

    private int getBottomBoundary(char[][] image, int x) { // row boundary
        int start = x;
        int end = m - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) /2;

            if (isEmptyRow(image, mid)) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (!isEmptyRow(image, end)) {
            return end;
        }

        return start;
    }

    private boolean isEmptyRow(char[][] image, int row) {
        if (row < 0 || row >= m) {
            return false;
        }

        for (int col = 0; col < n; col++) {
            if (image[row][col] != WHITE) {
                return false;
            }
        }

        return true;
    }
}
