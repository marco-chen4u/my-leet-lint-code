/***
* LeetCode 1861. Rotating the Box
You are given an m x n matrix of characters box representing a side-view of a box. Each cell of the box is one of the following:
    A stone '#'
    A stationary obstacle '*'
    Empty '.'

The box is rotated 90 degrees clockwise, causing some of the stones to fall due to gravity. 
Each stone falls down until it lands on an obstacle, another stone, or the bottom of the box. 
Gravity does not affect the obstacles' positions, 
and the inertia from the box's rotation does not affect the stones' horizontal positions.

It is guaranteed that each stone in box rests on an obstacle, another stone, or the bottom of the box.

Return an n x m matrix representing the box after the rotation described above.

Example 1
    image link: https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcodewithstones.png
    Input: box = [["#",".","#"]]
    Output: [["."],
             ["#"],
             ["#"]]
             
Example 2
    image link: https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcode2withstones.png
    Input: box = [["#",".","*","."],
                  ["#","#","*","."]]
    Output: [["#","."],
             ["#","#"],
             ["*","*"],
             [".","."]]
             
Example 3
    image link: https://assets.leetcode.com/uploads/2021/04/08/rotatingtheboxleetcode3withstone.png
    Input: box = [["#","#","*",".","*","."],
                  ["#","#","#","*",".","."],
                  ["#","#","#",".","#","."]]
    Output: [[".","#","#"],
             [".","#","#"],
             ["#","#","*"],
             ["#","*","."],
             ["#",".","*"],
             ["#",".","."]]

Constraints:
    m == box.length
    n == box[i].length
    1 <= m, n <= 500
    box[i][j] is either '#', '*', or '.'

LeetCode link: https://leetcode.com/problems/rotating-the-box/
***/
class Solution {
    public char[][] rotateTheBox(char[][] box) {
        char[][] rotatedBox = rotate(box);
        char[][] result = freeFall(rotatedBox);

        return result; 
    }

    // helper mehtods
    private char[][] rotate(char[][] box) {
        int n = box.length; // row size
        int m = box[0].length; // column size

        char[][] rotatedBox = new char[m][n];

        for (int i = 0, column = n - 1; i < n; i++, column--) {
            for (int j = 0, row = 0; j < m; j++, row++) {
                rotatedBox[row][column] = box[i][j];
            }
        }

        return rotatedBox;
    }

    private char[][] freeFall(char[][] box) {
        int n = box.length; // row size
        int m = box[0].length; // column size

        // scan process the state column by column with bottow-up direction
        for (int j = 0; j < m; j++) {// column from left to right
            
            int lastIndex = n - 1;//bottom line
            for (int i = n - 1; i >= 0; i--) { // row from bottom to top
                char val = box[i][j];
                if ('.' == val) {//empty
                    continue;
                }

                if ('*' == val) {//obstacle
                    lastIndex = i - 1;
                    continue;
                }

                if ('#' == val) {// stone, need to prcess free fall to last bound
                    box[i][j] = '.';// marked it as empty to move to fall next place 
                    box[lastIndex--][j] = val;
                }
            }
        }

        return box;
    }
}
