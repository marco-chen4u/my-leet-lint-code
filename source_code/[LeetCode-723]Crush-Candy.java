/***
* LeetCode 723. Crush Candy
This question is about implementing a basic elimination algorithm for Candy Crush.

Given an m x n integer array board representing the grid of candy where board[i][j] represents the type of candy. 
A value of board[i][j] == 0 represents that the cell is empty.

The given board represents the state of the game following the player's move. 
Now, you need to restore the board to a stable state by crushing candies according to the following rules:

If three or more candies of the same type are adjacent vertically or horizontally, 
crush them all at the same time - these positions become empty.
After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, 
then these candies will drop until they hit a candy or bottom at the same time. 
No new candies will drop outside the top boundary.

After the above steps, there may exist more candies that can be crushed. 
If so, you need to repeat the above steps.
If there does not exist more candies that can be crushed (i.e., the board is stable), then return the current board.
You need to perform the above rules until the board becomes stable, then return the stable board.

Example 1
    Input:
      [
        [110,5,112,113,114],
        [210,211,5,213,214],
        [310,311,3,313,314],
        [410,411,412,5,414],
        [5,1,512,3,3],
        [610,4,1,613,614],
        [710,1,2,713,714],
        [810,1,2,1,1],
        [1,1,2,2,2],
        [4,1,4,4,1014]
      ]
    Output:
      [
        [0,0,0,0,0],
        [0,0,0,0,0],
        [0,0,0,0,0],
        [110,0,0,0,114],
        [210,0,0,0,214],
        [310,0,0,113,314],
        [410,0,0,213,414],
        [610,211,112,313,614],
        [710,311,412,613,714],
        [810,411,512,713,1014]
      ]
    Explaination: https://assets.leetcode.com/uploads/2018/10/12/candy_crush_example_2.png
      

Example 2
    Input: [[1,3,5,5,2],[3,4,3,3,1],[3,2,4,5,2],[2,4,4,5,5],[1,4,4,1,1]]
    Output: [[1,3,0,0,0],[3,4,0,5,2],[3,2,0,3,1],[2,4,0,5,2],[1,4,3,1,1]]
    
Constraints:
    m == board.length
    n == board[i].length
    3 <= m, n <= 50
    1 <= board[i][j] <= 2000

LeetCode Link: https://leetcode.com/problems/candy-crush/
LintCode link: https://www.lintcode.com/problem/858/
***/
//version-simulation
class Solution {
    public int[][] candyCrush(int[][] board) {
        int m = board.length; // row size
        int n = board[0].length; // column size
        boolean end = false;
        while (!end) {
            end = true;

            // scan candies to mark crash
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {

                    int val = Math.abs(board[i][j]);

                    if (board[i][j] == 0) {
                        continue;
                    }

                    // column scan for 3 ajenction elements                    
                    if (j + 2 < n && Math.abs(board[i][j + 1]) == val && Math.abs(board[i][j + 2]) == val) {
                        end = false;
                        for (int k = j; k <= j + 2; k++) {
                            board[i][k] = -val;
                        }
                    }

                    // row scan for 3 consecutive elements
                    if (i + 2 < m && Math.abs(board[i + 1][j]) == val && Math.abs(board[i + 2][j]) == val) {
                        end = false;
                        for (int k = i; k <= i + 2; k++) {
                            board[k][j] = -val;
                        }
                    }
                }
            }

            // if there found candy to crash and drop
            if (!end) {
                // process dropping candies with cloumn by column and from the bottom to the top 
                for (int j = 0; j < n; j++) {
                    int index = m - 1;
                    for (int i = m - 1; i >= 0; i--) {
                        if (board[i][j] <= 0) {// if it is empty or makred crash candies, then skip to process
                            continue;
                        }

                        board[index--][j] = board[i][j];// move the candies to the bottom
                    }

                    // mark the rest[from index to top] to empty
                    for (int i = index; i >= 0; i--) {
                        board[i][j] = 0;
                    }
                }
            }
        }

        return board;
    }
}
