/***
* LintCode 1301. Game of Life
According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):
	1.Any live cell with fewer than two live neighbors dies, as if caused by under-population.
	2.Any live cell with two or three live neighbors lives on to the next generation.
	3.Any live cell with more than three live neighbors dies, as if by over-population.
	4.Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state. The next state is created by applying the above rules simultaneously to every cell in the current state, where births and deaths occur simultaneously.

Example
	Example :
		Input: 
			[
			  [0,1,0],
			  [0,0,1],
			  [1,1,1],
			  [0,0,0]
			]
		Output: 
			[
			  [0,0,0],
			  [1,0,1],
			  [0,1,1],
			  [0,1,0]
			]

Challenge
	1.Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
	2.In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?

***/
public class Solution {
	// fields
	private int n; // row size
	private int m; // column size
	private final int MARKED_TO_DIE = Integer.MIN_VALUE;// but still alive
	private final int MARKED_TO_LIVE = Integer.MAX_VALUE;
	private final int DIE = 0;
	private final int LIVE = 1;
	
	private final int[] DIRECTIONS_X = new int[] {0, 0, 1, -1, 1, -1, -1, 1};
	private final int[] DIRECTIONS_Y = new int[] {1, -1, 0, 0, 1, 1, -1, -1};
	
    /**
     * @param board: the given board
     * @return: nothing
     */
    public void gameOfLife(int[][] board) {
        // check corner case
		if (board == null || board.length == 0 || 
			board[0] == null || board[0].length == 0) {
			return;
		}
		
		n = board.length;    // row size
		m = board[0].length; // column size
		
		// scan all element and mark its next state
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				int count = getNeighborLiveCount(board, i, j);
				if (board[i][j] == DIE && count == 3) {// rule-#4
					board[i][j] = MARKED_TO_LIVE;
					continue;
				}
				
				if (board[i][j] == LIVE && (count < 2 || count > 3)) {// rule-#1 or rule-#3
					board[i][j] = MARKED_TO_DIE;
				}
			}
		}
		
		// update all the sate for its marked state perspective
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == MARKED_TO_DIE) {
					board[i][j] = DIE;
					continue;
				}
				
				if (board[i][j] == MARKED_TO_LIVE) {
					board[i][j] = LIVE;
				}
			}
		}
    }
	
	// helper methods
	private int getNeighborLiveCount(int[][] board, int x, int y) {
		int result = 0;
		
		for (int i = 0; i < 8; i++) {
			int nextX = x + DIRECTIONS_X[i];
			int nextY = y + DIRECTIONS_Y[i];
			
			if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
				continue;
			}
			
			result += (board[nextX][nextY] == LIVE || board[nextX][nextY] == MARKED_TO_DIE) ? 1 : 0;
		}
		
		return result;
	}
}