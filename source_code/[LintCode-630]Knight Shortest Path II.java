/***
* LintCode 630. Knight Shortest Path II
Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze position is (0, 0) and he wants to reach position (n - 1, m - 1), Knight can only be from left to right. Find the shortest path to the destination position, return the length of the route. Return -1 if knight can not reached.

Example
	Example 1:
		Input:
			[[0,0,0,0],[0,0,0,0],[0,0,0,0]]
		Output:
			3
		Explanation:
			[0,0]->[2,1]->[0,2]->[2,3]

	Example 2:
		Input:
			[[0,1,0],[0,0,1],[0,0,0]]
		Output:
			-1
		
Clarification
	If the knight is at (x, y), he can get to the following positions in one step:
		(x + 1, y + 2)
		(x - 1, y + 2)
		(x + 2, y + 1)
		(x - 2, y + 1)

***/
//version-1: BFS
public class Solution {
    // inner class 
    class Point {
        // fields
        int x;
        int y;

        // constructor
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // constants
    private static final int[] DIRECTION_X = new int[]{1, -1, 2, -2};
    private static final int[] DIRECTION_Y = new int[]{2, 2, 1, 1};

    // fields
    private int n; // row size
    private int m; // column size

    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    public int shortestPath2(boolean[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }

        // initialize
        n = grid.length;
        m = grid[0].length;

        if (n == 1 && m == 1) {
            return 0;
        }

        Point source = new Point(0, 0);
        Point destination = new Point(n - 1, m - 1);
        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        //grid[0][0] = true; // marked as visited

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                int x = current.x;
                int y = current.y;
                
                if (x == n - 1 && y == m - 1) {
                    return step;
                }
                
                for (int index = 0; index < 4; index ++) {
                    int nextX = x + DIRECTION_X[index];
                    int nextY = y + DIRECTION_Y[index];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m || grid[nextX][nextY]) {
                        continue;
                    }

                    grid[nextX][nextY] = true;
                    Point next = new Point(nextX, nextY);
                    queue.offer(next);
                }// for index
            }// for i
            step ++;
        }// for while

        return -1;
    }
}

//version-2: DP, time complexity : O(n^2)
public class Solution {
    // fields
    private final int DEFAULT_MAX = Integer.MAX_VALUE;
    private final int[] directionX = new int[] {-1, 1, -2, 2};
    private final int[] directionY = new int[] {-2, -2, -1, -1};
    
    /**
     * @param grid: a chessboard included 0 and 1
     * @return: the shortest path
     */
    public int shortestPath2(boolean[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int n = grid.length;   // row size
        int m = grid[0].length;// column size
        
        // state
        int[][] dp = new int[n][m];
        
        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = DEFAULT_MAX;
            }
        }
        
        dp[0][0] = 0;
        
        // function
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j]) {// if it is a barrier position
                    continue;
                }
                
                // if is a empty position
                // analyzing the source position where jump to current pos[i, j]
                for (int k = 0; k < 4; k++) {
                    int x = i + directionX[k];// source position on row
                    int y = j + directionY[k]; // cource position on column
                    
					// check if inbound
                    if (x >= 0 && x < n && 
                        y >= 0 && y < m && 
                        dp[x][y] != DEFAULT_MAX) {
                        dp[i][j] = Math.min(dp[i][j], dp[x][y] + 1);
                    }
                }// for k
            }// for i
        }// for j
        
        if (dp[n - 1][m - 1] == DEFAULT_MAX) {
            return -1;
        }
        
        return dp[n - 1][m - 1];
    }
}
