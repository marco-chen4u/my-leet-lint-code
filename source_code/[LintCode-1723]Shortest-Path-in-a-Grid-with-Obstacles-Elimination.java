/**
* LintCode 1723. Shortest Path in a Grid with Obstacles Elimination
Given a m * n grid, where each cell is either 0 (empty) or 1 (obstacle). 
In one step, you can move up, down, left or right from and to an empty cell.

Return the minimum number of steps to walk from the upper left corner (0, 0) to the lower right corner (m-1, n-1) 
given that you can eliminate at most k obstacles. 

If it is not possible to find such walk return -1.

Example 1:
    Input: 
        grid = 
        [[0,0,0],
         [1,1,0],
         [0,0,0],
         [0,1,1],
         [0,0,0]], 
        k = 1
    Output: 6
    Explanation: 
        The shortest path without eliminating any obstacle is 10. 
        The shortest path with one obstacle elimination at position (3,2) is 6. Such path is (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2) -> (3,2) -> (4,2).

Example 2:
    Input: 
        grid = 
        [[0,1,1],
         [1,1,1],
         [1,0,0]], 
        k = 1
    Output: -1
    Explanation: 
        We need to eliminate at least two obstacles to find such a walk.
**/
//vesion-1: BFS
public class Solution {

    // constants
    private static final int[] DIRECTIONS_X = new int[] {0, 1, -1, 0};
    private static final int[] DIRECTIONS_Y = new int[] {1, 0, 0, -1};

    private static final int OBSTACLE = 1;
    private static final int EMPTY = 0;

    private static final int MAX = Integer.MAX_VALUE;


    // fields
    private int m; // row size
    private int n; // column size

    // inner class
    class Position{
        // fields
        int x;
        int y;
        int obstacleSize;

        // constructor
        public Position(int x, int y, int obstacleSize) {
            this.x = x;
            this.y = y;
            this.obstacleSize = obstacleSize;
        }
    }

    /**
     * @param grid: a list of list
     * @param k: an integer
     * @return: Return the minimum number of steps to walk
     */
    public int shortestPath(int[][] grid, int k) {
        // initializing
        m = grid.length;
        n = grid[0].length;

        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], MAX);
        }

        Queue<Position> queue = new ArrayDeque<>();

        queue.offer(new Position(0, 0, 0));       
        visited[0][0] = 0;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position current = queue.poll();
                int x = current.x;
                int y = current.y;
                int currentObstacleSize = current.obstacleSize;

                if (x == m - 1 && y == n - 1) {
                    return step;
                }

                for (int index = 0; index < 4; index++) {
                    int nextX = x + DIRECTIONS_X[index];
                    int nextY = y + DIRECTIONS_Y[index];

                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                        continue;
                    }

                    int nextObstacleSize = (grid[nextX][nextY] == OBSTACLE) ? currentObstacleSize + 1 : currentObstacleSize;
                    
                    if (nextObstacleSize > k || nextObstacleSize >= visited[nextX][nextY]) {
                        continue;
                    }

                    queue.offer(new Position(nextX, nextY, nextObstacleSize));
                    visited[nextX][nextY] = nextObstacleSize;

                }
            }
            
            step++;
        }

        return -1;
    }
}

//version-2: BFS
public class Solution {

    // constants
    private static final int[] DIRECTIONS_X = new int[] {0, 1, -1, 0};
    private static final int[] DIRECTIONS_Y = new int[] {1, 0, 0, -1};

    private static final int OBSTACLE = 1;
    private static final int EMPTY = 0;

    private static final int MAX = Integer.MAX_VALUE;


    // fields
    private int m; // row size
    private int n; // column size

    // inner class
    class Position{
        // fields
        int x;
        int y;
        int obstacleSize;
        int step;

        // constructor
        public Position(int x, int y, int obstacleSize, int step) {
            this.x = x;
            this.y = y;
            this.obstacleSize = obstacleSize;
            this.step = step;
        }
    }

    /**
     * @param grid: a list of list
     * @param k: an integer
     * @return: Return the minimum number of steps to walk
     */
    public int shortestPath(int[][] grid, int k) {
        // initializing
        m = grid.length;
        n = grid[0].length;

        int[][] visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(visited[i], MAX);
        }

        Queue<Position> queue = new ArrayDeque<>();

        queue.offer(new Position(0, 0, 0, 0));       
        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Position current = queue.poll();
            int x = current.x;
            int y = current.y;
            int currentObstacleSize = current.obstacleSize;
            int step = current.step;

            if (x == m - 1 && y == n - 1) {
                return step;
            }

            for (int index = 0; index < 4; index++) {
                int nextX = x + DIRECTIONS_X[index];
                int nextY = y + DIRECTIONS_Y[index];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }

                int nextObstacleSize = (grid[nextX][nextY] == OBSTACLE) ? currentObstacleSize + 1 : currentObstacleSize;
                
                if (nextObstacleSize > k || nextObstacleSize >= visited[nextX][nextY]) {
                    continue;
                }

                int nextStep = step + 1;

                queue.offer(new Position(nextX, nextY, nextObstacleSize, nextStep));
                visited[nextX][nextY] = nextObstacleSize;

            }
            
        }

        return -1;
    }
}
