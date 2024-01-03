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

//version-3: BFS
class Solution {

    private static int m; // row size
    private static int n; // column size

    private static final char MARKED = '1';

    private static final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private static final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};

    public void solve(char[][] board) {
        m = board.length;
        n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] == 'O' ? MARKED : board[i][j];
            }
        }

        for (int i = 0 ; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!isOnborder(i, j)) {
                    continue;
                }

                bfs(board, i, j);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (board[i][j] == MARKED) {
                    board[i][j] = 'X';
                    continue;
                }

            }
        }
    }

    // helper method
    private boolean isOnborder(int x, int y) {
        return x == 0 || x == m - 1 || y == 0 || y == n - 1;
    }

    private void bfs(char[][] board, int x, int y) {

        if (board[x][y] != MARKED) {
            return;
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(x * n + y);
        board[x][y] = 'O';

        while (!queue.isEmpty()) {
            int current = queue.poll();
            int currentX = current / n;
            int currentY = current % n;

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTION_X[i];
                int nextY = currentY + DIRECTION_Y[i];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || board[nextX][nextY] != MARKED) {
                    continue;
                }

                board[nextX][nextY] = 'O';
                queue.offer(nextX * n + nextY);
            }
        }
    }
}

//version4: BFS with Queue and Pair object
class Solution {
    private final char MARKER = 'M';
    private int n;// row size
    private int m;// column size
    
    private int[] DIRECTION_X = new int[]{1, 0, 0, -1};
    private int[] DIRECTION_Y = new int[]{0, 1, -1, 0};
    
    public void solve(char[][] board) {
        // check corner case
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return;
        } 
        
        // regular case
        n = board.length;
        m = board[0].length;
        
        setMarked(board, 'O', MARKER);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (isOnBorder(i, j) && board[i][j] == MARKER) {
                    traverseByBFS(board, i, j, MARKER);               
                }
            }
        }
        
        setMarked(board, MARKER, 'X');
    }
    
    // helper method
    private void traverseByBFS(char[][] board, int x, int y, char marker) {
        
        board[x][y] = 'O';
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair(x, y));
        
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> current = queue.poll();
            int currentX = current.getKey();
            int currentY = current.getValue();
            
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTION_X[i];
                int nextY = currentY + DIRECTION_Y[i];
                
                if (nextX < 0 || nextX >= n ||
                    nextY < 0 || nextY >= m ||
                    board[nextX][nextY] != marker) {
                    continue;
                } 
                
                board[nextX][nextY] = 'O';
                queue.offer(new Pair(nextX, nextY));
            }

        }
    }
    
    private boolean isOnBorder(int x, int y) {
        if (x == 0 || y == 0) {
            return true;
        }
        
        if (x == n - 1 || 
            y == m - 1) {
            return true;
        }
        
        return false;
    } 
    
    private void setMarked(char[][] board, char markerA, char markerB) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == markerA) {
                    board[i][j] = markerB;
                }
            }
        }
    }
}
