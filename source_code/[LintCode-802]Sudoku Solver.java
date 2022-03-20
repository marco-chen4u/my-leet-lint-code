/***
* LintCode 802. Sudoku Solver
Write a program to solve a Sudoku puzzle by filling the empty cells.

Empty cells are indicated by the number 0.

You may assume that there will be only one unique solution.

Example
    Given a Sudoku puzzle:
        [
        [0,0,9,7,4,8,0,0,0],
        [7,0,0,0,0,0,0,0,0],
        [0,2,0,1,0,9,0,0,0],
        [0,0,7,0,0,0,2,4,0],
        [0,6,4,0,1,0,5,9,0],
        [0,9,8,0,0,0,3,0,0],
        [0,0,0,8,0,3,0,2,0],
        [0,0,0,0,0,0,0,0,6],
        [0,0,0,2,7,5,9,0,0]
        ]
    
    Return its solution:
        [
        [5,1,9,7,4,8,6,3,2],
        [7,8,3,6,5,2,4,1,9],
        [4,2,6,1,3,9,8,7,5],
        [3,5,7,9,8,6,2,4,1],
        [2,6,4,3,1,7,5,9,8],
        [1,9,8,5,2,4,3,6,7],
        [9,7,5,8,6,3,1,2,4],
        [8,3,2,4,9,1,7,5,6],
        [6,4,1,2,7,5,9,8,3]
        ]

Link: https://www.lintcode.com/problem/802/
***/

/**
* to get more understanding, please watch this vedio: https://bit.ly/34jGGse
* 这是一个固定的9*9的矩阵，
* 其中，每一行9个格子包含有唯一的1～9的数字，
*      每一列9个格子也包好有唯一的1～9的数字，
*      这个9*9的矩阵中，包含了3*3的正方形，
*         每一个正方形包含有唯一的1～9的数字。
* 也即，任意一行或一列，都是1～9的唯一数字；任意3*3的正方形也都是1～9的唯一数字。
*
* 我们要在空出来的格子，看怎么填满数字，符合上述要求，看是否存在这么一个唯一的solution。
**/
//version-1: iteration & dfs
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

//version-2: dfs + iteration
public class Solution {

    // constants
    private static final int EMPTY = 0;

    /**
     * @param board: the sudoku puzzle
     * @return: nothing
     */
    public void solveSudoku(int[][] board) {
        dfs(board, 0, 0);
    }

    // helper methods
    private boolean dfs(int[][] board, int x, int y) {
        
        //[1]move the current position[x, y] to the one with empty value to fill
        while (x < 9 && y < 9 && 
	       board[x][y] != EMPTY) {
            x = x + (y / 8);
            y = (y + 1) % 9;
        }

	// check corner case
        if (x >= 9) {
            return true;
        }

        // [2] fill the potential value to check if it's valid the sudoku rule, then move forward
        for (int value = 1; value <= 9; value++) {
            if (!isSudoku(board, x, y, value)) {
                continue;
            }

            board[x][y] = value;
            int nextX = x + (y / 8);
            int nextY = (y + 1) % 9;
            if (dfs(board, nextX, nextY)) {
                return true;
            }
            board[x][y] = EMPTY;//backtracking
        }

	// default
        return false;
    }

    private boolean isSudoku(int[][] board, int x, int y, int value) {
        // check the cross lines[row-wise, column-wise] to see if there's duplicate with value
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == value ||
                board[i][y] == value) {
                return false;
            }
        }

        // check the small box(3*3) to see if there's duplcate with value
        int rowOffset = (x / 3) * 3;
        int columnOffset = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowOffset + i][columnOffset + j] == value) {
                    return false;
                }
            }
        }

	// default
        return true;
    }
}

//vresion-3: dfs + memorized search
public class Solution {
    // constants
    private static final int EMPTY = 0;
    private static final int GOOD = 1;
    private static final int BAD = 2;

    // fields
    private int n; // row size
    private int m; // column size

    /**
     * @param board: the sudoku puzzle
     * @return: nothing
     */
    public void solveSudoku(int[][] board) {
        n = board.length;
        m = board[0].length;
        int[][] visited = new int[n + 1][m + 1];
        boolean result = dfs(board, visited, 0, 0);
    }

    // helper methods
    private boolean dfs(int[][] board, int[][] visited, int x, int y) {
        if (GOOD == visited[x][y]) {
            return true;
        }
        
        int currentX = x;
        int currentY = y;

        while (currentX < 9 && currentY < 9 && 
                board[currentX][currentY] != EMPTY) {
            currentX = currentX + currentY / 8;
            currentY = (currentY + 1) % 9;
        }

        // corner case
        if (currentX == 9) {
            visited[x][y] = GOOD;
            return true;
        }

        for (int value = 1; value <= 9; value ++) {
            if (!isSudoku(board, currentX, currentY, value)) {
                continue;
            }

            board[currentX][currentY] = value;
            int nextX = currentX + (currentY / 8);
            int nextY = (currentY + 1) % 9;
            if (dfs(board, visited, nextX, nextY)) {
                visited[x][y] = GOOD;
                return true;
            }
            board[currentX][currentY] = EMPTY;//back tracking
        }

        visited[x][y] = BAD;
        return false;
    }

    private boolean isSudoku(int[][] board, int x, int y, int value) {
        // check cross position with row-wise & column-wise if there's a duplicate with value
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == value || board[i][y] == value) {
                return false;
            }
        }

        int rowOffset = (x / 3) * 3;
        int columnOffset = (y / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[rowOffset + i][columnOffset + j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
