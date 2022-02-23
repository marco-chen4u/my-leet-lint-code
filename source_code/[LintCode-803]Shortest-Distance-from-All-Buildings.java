/**
* LintCode 803. Shortest Distance from All Buildings
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

    -Each 0 marks an empty land which you can pass by freely.
    -Each 1 marks a building which you cannot pass through.
    -Each 2 marks an obstacle which you cannot pass through.
    
Example 1
    Input: [[1,0,2,0,1],[0,0,0,0,0],[0,0,1,0,0]]
    Output: 7
    Explanation:
        In this example, there are three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2).
        1 - 0 - 2 - 0 - 1
        |   |   |   |   |
        0 - 0 - 0 - 0 - 0
        |   |   |   |   |
        0 - 0 - 1 - 0 - 0
        The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.
    
Example 2
    Input: [[1,0],[0,0]]
    Output: 1
        In this example, there is one buildings at (0,0).
        1 - 0
        |   |
        0 - 0
        The point (1,0) or (0,1) is an ideal empty land to build a house, as the total travel distance of 1 is minimal. So return 1.
**/
/**
* BFS(Breadth First Search)
* related problems:
* LintCode 912 Best Meeting Point
* LintCode 663 Walls and Gates
* LintCode 573 Build Post Office II
**/
//version-1:BFS
public class Solution {
    // constants
    private static final int EMPTY = 0;
    private static final int BUILDING = 1;
    private static final int OBSTACLE = 2;

    private static final int MAX = Integer.MAX_VALUE;

    private static final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private static final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};

    private int n;// row size
    private int m; // column size


    /**
     * @param grid: the 2D grid
     * @return: the shortest distance
     */
    public int shortestDistance(int[][] grid) {
        if (grid == null || grid.length == 0 || 
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        n = grid.length;
        m = grid[0].length;

        int houseCount = getHouseCount(grid);
        if (houseCount == 0) {
            return 0;
        }


        int minDistance = MAX;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != EMPTY) {
                    continue;
                }

                minDistance = Math.min(minDistance, getDistance(grid, houseCount, i, j));
            }
        }

        return minDistance == MAX ? -1: minDistance;
    }

    // helper methods
    private int getDistance(int[][] grid, int houseCount, int x, int y) {
        int totalDistance = 0;

        Queue<Integer> queueX = new ArrayDeque();
        Queue<Integer> queueY = new ArrayDeque();
        boolean[][] visited = new boolean[n][m];

        queueX.offer(x);
        queueY.offer(y);
        visited[x][y] = true;

        int step = 0;
        while (!queueX.isEmpty()) {
            int size = queueX.size();
            step += 1;
            for (int i = 0; i < size; i++) {
                int currentX = queueX.poll();
                int currentY = queueY.poll();

                for (int index = 0; index < 4; index++) {
                    int nextX = currentX + DIRECTION_X[index];
                    int nextY = currentY + DIRECTION_Y[index];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                        continue;
                    }

                    if (grid[nextX][nextY] == OBSTACLE || visited[nextX][nextY]) {
                        continue;
                    }

                    visited[nextX][nextY] = true;

                    if (grid[nextX][nextY] == BUILDING) {
                        totalDistance += step;
                        houseCount --;
                        continue;                        
                    }

                    queueX.offer(nextX);
                    queueY.offer(nextY);

                }// for index direciton

            }// for i
        }// while queue

        if (houseCount != 0) {
            return MAX;
        }

        return totalDistance;

    }

    private int getHouseCount(int[][] grid) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                count += (grid[i][j] == BUILDING) ? 1 : 0;
            }
        }
        return count;
    }
}
