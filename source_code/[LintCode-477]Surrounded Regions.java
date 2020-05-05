/***
* LintCode 477. Surrounded Regions
Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
A region is captured by flipping all 'O''s into 'X''s in that surrounded region.
Example
	Example 1:
		Input:
		  X X X X
		  X O O X
		  X X O X
		  X O X X
		Output:
		  X X X X
		  X X X X
		  X X X X
		  X O X X
	Example 2:
		Input:
		  X X X X
		  X O O X
		  X O O X
		  X O X X
		Output:
		  X X X X
		  X O O X
		  X O O X
		  X O X X
***/
//version-1: BFS
/*
* （1）先把所有的'O'的坐标位置点标识为MARKED
* (2)然后，先把外围(left-side,right-side, bottom-side, top-side)带有MARKED标识的点，还原为‘O’，然后通过BFS结合相邻4个方向推进，直至把所有相邻的MARKED标识值的点还原成原来的‘O’，因为这些点是不会被surounded并消灭的。
* (3)把所有标识为MARKED存下的点进行逐个消灭(赋值为'X'),因为这些点已经被包围，只能被‘消灭’。
*/
public class Solution {
	// fields
    private final char MARKED = '-';
    private int n = 0;
    private int m = 0;
    private int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
        // check corner case
        if (board == null || board.length == 0) {
            return;
        }
        
        if (board[0] == null || board[0].length == 0) {
            return;
        }
        
        n = board.length;
        m = board[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = MARKED;
                }
            }
        }
        
        // left side
        for (int i = 0; i < n; i++) {
            traverseByBFS(board, i, 0);
        }
        
        // right side
        for (int i = 0; i < n; i++) {
            traverseByBFS(board, i, m - 1);
        }
        
        // upper side
        for (int j = 1; j < m - 1; j++) {
            traverseByBFS(board, 0, j);
        }
        
        // bottom side
        for (int j = 1; j < m - 1; j++) {
            traverseByBFS(board, n - 1, j);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == MARKED) {
                    board[i][j] = 'X';
                }
            }
        }
    }
    
    // helper
    private void traverseByBFS(char[][] board, int row, int col) {
        // check corner cases
        if (row < 0 || row >= n || col < 0 || col >= m) {
            return;
        }
        
        if (board[row][col] != MARKED) {
            return;
        }
        
		// normal case
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(row * m + col);
        
        while (!queue.isEmpty()) {
            int currentPos = queue.poll();
            int x = currentPos / m;
            int y = currentPos % m;
            
            board[x][y] = 'O';
            
            for (int i = 0; i < 4; i++) {
                int nextX = x + DIRECTION_X[i];
                int nextY = y + DIRECTION_Y[i];
                
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m ||
                    board[nextX][nextY] != MARKED) {
                    continue;
                }
                
                queue.offer(nextX * m + nextY);
            }
        }
    }
}

//version-2: BFS
/*
* 利用反向思维
*(1)先把外围各个洼地低点(board[x][y] == 'O')进行灌水(board[x][y] == MARKED);
*（2）沿着各个源点(board[x][y] == MARKED)向相邻4个方向进行灌水(BFS)直至没有存在地点位置。
*(3)把没有‘盆地’中没有被灌水成功的各个洼地低点(board[x][y] == 'O')进行消灭（board[x][y] == 'X'）.
*/
public class Solution {
	// fields
    private final char MARKED = '-';
    private int n = 0;
    private int m = 0;
    private int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    
    /*
     * @param board: board a 2D board containing 'X' and 'O'
     * @return: nothing
     */
    public void surroundedRegions(char[][] board) {
		// check corner cases
		if (board == null || board.length == 0 ||
			board[0] == null || board[0].length == 0) {
			return;
		}
		
		n = board.length; // row size
		m = board[0].length; // column size
		// normal case
		// check from sourrounded board sides
		for (int i = 0; i < n; i++) {// row
			// left-side
			traverseByBFS(board, i, 0);
			// right-side
			traverseByBFS(board, i, m - 1);
		}
		
		for (int j = 0; j < m; j++) {// column
			// top-side
			traverseByBFS(board, 0, j);
			// bottom-side
			traverseByBFS(board, n - 1, j);
		}
		
		// kill all the sourrounded regions, turn all marked regions into their origin value which are not able to sourround
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (board[i][j] == MARKED) {
					board[i][j] = 'O';
					continue;
				}
				
				if (board[i][j] == 'O') {
					board[i][j] = 'X';
				}
			}
		}
		
	}
	
	// helper method
	private void traverseByBFS(char[][] board, int row, int col) {
		// check corner case
		if (row < 0 || row >= n ||
			col < 0 || col >= m || 
			board[row][col] != 'O') {
			return;
		}
		
		// initialize
		Queue<Integer> queueX = new LinkedList<Integer>();
		Queue<Integer> queueY = new LinkedList<Integer>();
		
		queueX.offer(row);
		queueY.offer(col);
		board[row][col] = MARKED;
		
		// start Breadth First Searching
		while (!queueX.isEmpty()) {
			int currentX = queueX.poll();
			int currentY = queueY.poll();
			
			for (int i = 0; i < 4; i++) {
				int nextX = currentX + DIRECTION_X[i];
				int nextY = currentY + DIRECTION_Y[i];
				
				if (nextX < 0 || nextX >= n || 
					nextY < 0 || nextY >= m ||
					board[nextX][nextY] != 'O') {
					continue;
				}
				
				board[nextX][nextY] = MARKED;
				queueX.offer(nextX);
				queueY.offer(nextY);
			}
		}
	}
}