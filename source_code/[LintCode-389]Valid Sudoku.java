/***
* LintCode 389. Valid Sudoku
Determine whether a Sudoku is valid.
The Sudoku board could be partially filled, where empty cells are filled with the character .

Example
    The following partially filed sudoku is valid.
    Valid Sudoku (Image can display here: http://www.lintcode.com/en/problem/valid-sudoku/#)

Note
    A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

Clarification
    What is Sudoku?
    http://sudoku.com.au/TheRules.aspx
    https://zh.wikipedia.org/wiki/%E6%95%B8%E7%8D%A8
    https://en.wikipedia.org/wiki/Sudoku
    http://baike.baidu.com/subview/961/10842669.htm

Tags
    Matrix
***/

/*
    Thoughts:
    Each row/col/block can only 1 ~ 9, no duplicates
    Use HashSet, reinitiate 3 times.
    traverse row, col, block
    O(n^2)
*/

public class Solution {
    // field
    private final char EMPTY = '.';
    
    /**
     * @param board: the board
     * @return: whether the Sudoku is valid
     */
    public boolean isValidSudoku(char[][] board) {
        // check corner case
        if (board == null || board.length != 9 || board[0] == null || board[0].length != 9) {
            return false;
        }
        
        for (int i = 0; i < 9; i++) {
            
            Set<Character> row = new HashSet<Character>();
            Set<Character> col = new HashSet<Character>();
            Set<Character> block = new HashSet<Character>();
            
            for (int j = 0; j < 9; j++) {
                // row scan
                if (!check(board, i, j, row)) {
                    return false;
                }
                
                // col scan
                if (!check(board, j, i, col)) {
                    return false;
                }
                
                // box(3*3) scan
                int rowOffset = (i % 3) * 3;
                int colOffset = (i / 3) * 3;
                int boxRowPos = rowOffset + j % 3;
                int boxColPos = colOffset + j / 3;
                if (!check(board, boxRowPos, boxColPos, block)) {
                    return false;
                }
            }
        }
        
        return true;
    }
    
    // helper method
    private boolean check(char[][] board, int row, int col, Set<Character> visited) {
        char value = board[row][col];
        
        if (!visited.contains(value)) {
            visited.add(value);
        }
        else if (value != EMPTY) {
            return false;
        }
        
        return true;
    }
}
