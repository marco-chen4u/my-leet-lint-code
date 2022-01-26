/*** 
* LintCode 573. Build Post Office
* Given a 2D grid, each cell is either a wall 2, 
an house 1 or empty 0 (the number zero, one, two), 
find a place to build a post office 
so that the sum of the distance from the post office to all the houses is smallest.

Return the smallest sum of distance. Return -1 if it is not possible.

Example 1:
    Input：[[0,1,0,0,0],[1,0,0,2,1],[0,1,0,0,0]]
    Output：8
    Explanation： 
        Placing a post office at (1,1), 
        the distance that post office to all the house sum is smallest.

Example 2:
    Input：[[0,1,0],[1,0,1],[0,1,0]]
    Output：4
    Explanation： 
        Placing a post office at (1,1), 
        the distance that post office to all the house sum is smallest.

Challenge
    Solve this problem within O(n^3) time.

Notice
    You cannot pass through wall and house, but can pass through empty.
    You only build post office on an empty.
    
Link: https://www.lintcode.com/problem/573/
***/
public class Solution {
    // inner class
    class Point {
        // fields
        public int x;
        public int y;

        // constructor
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // fields
    private static final int EMPTY_SPACE = 0;
    private static final int HOUSE = 1;
    private static final int WALL = 2;

    private static final int DEFAULT_VALUE = -1;
    private static final int MAX_VALUE = Integer.MAX_VALUE;

    private int n; // matrix's row size
    private int m; // matrix's column size

    private static final int[] directionX = new int[] {0, 1, -1, 0};
    private static final int[] directionY = new int[] {1, 0, 0, -1};

    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {
        int result = DEFAULT_VALUE;
        // check corner case
        if (grid == null || grid.length == 0) {
            return 0;
        }

        n = grid.length; // row size
        m = grid[0].length; // column size

        int minDistance = MAX_VALUE;

        int houseCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == HOUSE) {
                    houseCount++;
                }
            }
        }

        if (houseCount == 0) {
            return 0;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != EMPTY_SPACE) {
                    continue;
                }

                minDistance = Math.min(minDistance, getDistance(grid, houseCount, i, j));
            }
        }

        return result = (minDistance == MAX_VALUE) ? result : minDistance;
    }
	
    //helper methods
    private int getDistance(int[][] grid, int houseCount,int x, int y) {
        Queue<Point> queue = new LinkedList<Point>();
        boolean[][] visited = new boolean[n][m];

        queue.offer(new Point(x, y));
        visited[x][y] = true;

        int totalDistance = 0;
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();
                for (int index = 0; index < 4; index++) {
                    int nextX = current.x + directionX[index];
                    int nextY = current.y + directionY[index];
					
                    if (nextX < 0 || nextX >= n ||
                            nextY < 0 || nextY >= m ||
                            visited[nextX][nextY] ||
                            grid[nextX][nextY] == WALL) {
                        continue;
                    }

                    visited[nextX][nextY] = true;

                    if (grid[nextX][nextY] == HOUSE) {
                        totalDistance += distance;
                        houseCount--;//mark one of houses visited
                        continue;
                    }
	
                    if (grid[nextX][nextY] == EMPTY_SPACE) {
                        queue.offer(new Point(nextX, nextY));
                    }
                }
            }
        }

        // check if there exist some house never visited
        if (houseCount != 0) {
            return MAX_VALUE;
        }

        return totalDistance;
    }
}
