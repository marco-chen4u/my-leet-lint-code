/***
* LintCode 802. Sudoku Solver
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the number 0.

You may assume that there will be only one unique solution.

Link: https://www.lintcode.com/problem/802/
***/
public class Solution {
    // field
    private final int EMPTY = 0;
    
    /**
     * @param board: the sudoku puzzle
     * @return: nothing
     */
    public void solveSudoku(int[][] board) {
        // check corner cases
        if (board == null || board.length != 9 || board[0].length != 9) {
            return;
        }
        
        boolean result = helper(board, 0, 0);
    }

    // helper methods
    private boolean helper(int[][] board, int row, int col) {
        // check corner case
        if (board == null || board.length != 9 || board[0].length != 9) {
            return false;
        }

	// find the first available place
        while (row < 9 && col < 9) {
            if (board[row][col] == EMPTY) {
                break;
            }

            if (col == 8) {
                col = 0;
                row++;
            }
            else {
                col++;
            }
        }
        if (row >= 9) {// check out of bound after getting location
            return true;
        }

        int nextX = row + (col / 8);
        int nextY = (col + 1) % 9;
		
        for (int i = 1; i <= 9; i++) {//numberate the potential figuare to fill in
            if (!isValid(board, row, col, i)) {
                continue;
            }

            int value = i;
            board[row][col] = value;

            if (helper(board, nextX, nextY)) {// to find the next available position to fill in another figure
                return true;
            }

            board[row][col] = EMPTY;// backtracking
        }

        return false;
    }

    // helper method
    private boolean isValid(int[][] board, int row, int col, int num) {
        int value = num;

        // check associated-row&column elements if there already exists this value
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == value || 
                board[i][col] == value) {
                return false;
            }
        }

        // check associate square if there already exists this value
        int rowOffset = (row / 3) * 3;
        int colOffset = (col / 3) * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowOffset + i][colOffset + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
