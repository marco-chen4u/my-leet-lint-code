/**
* LintCode 1080 · Max Area of Island
Given a non-empty 2D array grid of 0's and 1's, 
an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)

Example 1:
    input:
        [[0,0,1,0,0,0,0,1,0,0,0,0,0],
         [0,0,0,0,0,0,0,1,1,1,0,0,0],
         [0,1,1,0,1,0,0,0,0,0,0,0,0],
         [0,1,0,0,1,1,0,0,1,0,1,0,0],
         [0,1,0,0,1,1,0,0,1,1,1,0,0],
         [0,0,0,0,0,0,0,0,0,0,1,0,0],
         [0,0,0,0,0,0,0,1,1,1,0,0,0],
         [0,0,0,0,0,0,0,1,1,0,0,0,0]]
    output : 6.
    Explanation : Note the answer is not 11, because the island must be connected 4-directionally.
    
Example 2:
    input:
        [[1,1,0],[1,1,1]]
    output:
        5
**/
//version-1: BFS
public class Solution {
    // constants
    private static final int ISLAND = 1;

    private static final int[] DIRECTION_X = new int[] {1, 0, 0, -1};
    private static final int[] DIRECTION_Y = new int[] {0, 1, -1, 0};

    // fields
    private int n;// row size
    private int m;// column size

    /**
     * @param grid: a 2D array
     * @return: the maximum area of an island in the given 2D array
     */
    public int maxAreaOfIsland(int[][] grid) {
        // corner case
        if (grid == null || grid.length == 0 || 
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        // initialized
        n = grid.length;
        m = grid[0].length;

        boolean[][] visited = new boolean[n][m];

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] || grid[i][j] != ISLAND) {
                    continue;
                }

                int count = getIslands(grid, visited, i, j);

                result = Math.max(result, count);
            }
        }

        return result;
    }

    // helper methods
    private int getIslands(int[][] grid, 
                          boolean[][] visited, 
                          int x, 
                          int y) {
        Queue<Integer> queueX = new ArrayDeque<>();
        Queue<Integer> queueY = new ArrayDeque<>();

        queueX.offer(x);
        queueY.offer(y);

        visited[x][y] = true;

        int count = 0;
        while (!queueX.isEmpty()) {
            int currentX = queueX.poll();
            int currentY = queueY.poll();
            count += 1;
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTION_X[i];
                int nextY = currentY + DIRECTION_Y[i];

                if (nextX < 0 || nextX >= n || 
                    nextY < 0 || nextY >= m) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                if (grid[nextX][nextY] != ISLAND) {
                    continue;
                }

                queueX.offer(nextX);
                queueY.offer(nextY);
                visited[nextX][nextY] = true;
            }// for            

        }// while

        return count;
    }
}
