/***
* LintCode 611. Knight Shortest Path
Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, 
find the shortest path to a destination position, return the length of the route.
Return -1 if destination cannot be reached.

Notice
    Source and destination must be empty.
    Knight can not enter the barrier.

Clarification
    If the knight is at (x, y), he can get to the following positions in one step:
        (x + 1, y + 2)
        (x + 1, y - 2)
        (x - 1, y + 2)
        (x - 1, y - 2)
        (x + 2, y + 1)
        (x + 2, y - 1)
        (x - 2, y + 1)
        (x - 2, y - 1)

Example 1:
    Input:
        [[0,0,0],
         [0,0,0],
         [0,0,0]]
        source = [2, 0] destination = [2, 2] 
        Output: 2
    Explanation:
        [2,0]->[0,1]->[2,2]

Example 2:
    Input:
        [[0,1,0],
         [0,0,1],
         [0,0,0]]
        source = [2, 0] destination = [2, 2] 
    Output:-1
***/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
// BFS
public class Solution {
    private static final int[] directionX = {1, 1, -1, -1, 2, 2, -2, -2};
    private static final int[] directionY = {2, -2, 2, -2, 1, -1, 1, -1};

    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        
        int m = grid.length; // row size
        int n = grid[0].length; // col size

        boolean[][] visited = new boolean[m][n];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(source);
        visited[source.x][source.y] = true;

        int step = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                if (current.x == destination.x && current.y == destination.y) {
                    return step;
                }

                for (int index = 0; index < 8; index++) {
                    int nextX = current.x + directionX[index];
                    int nextY = current.y + directionY[index];

                    if (nextX < 0 || nextX >= m || 
                        nextY < 0 || nextY >= n || 
                        grid[nextX][nextY] || // barrier
                        visited[nextX][nextY]) {
                        continue;
                    }

                    visited[nextX][nextY] = true;
                    queue.offer(new Point(nextX, nextY));
                }
            }

            step++;
        }

        return -1;

    }
}
