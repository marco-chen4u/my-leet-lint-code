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
    // constants
    private static final int[] directionX = new int[] {1,1,-1,-1,2,2,-2,2};
    private static final int[] directionY = new int[] {2,-2,2,-2,1,-1,1,-1};

    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        int result = -1; // default value

        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return result;
        }

        if (source == null || destination == null) {
            return result;
        }

        if (source.x == destination.x && source.y == destination.y) {
            return 0;
        }

        // normal case
        Queue<Point> queue = new LinkedList<Point>();
        Point current = source;

        queue.offer(current);

        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                
                if (current.x == destination.x && current.y == destination.y) {
                    result = steps;
                    return result;
                }

                for (int index = 0; index < 8; index++) {
                    int x = current.x + directionX[index];
                    int y = current.y + directionY[index];
                    
                    if (!isInBound(grid, x, y)) {
                        continue;
                    }
                    
                    if (grid[x][y]) {// if it is a barrier or visited
                        continue;
                    }
                    
                    grid[x][y] = true;
                    Point next = new Point(x, y);
                    queue.offer(next);
                }

            }

            steps++;
        }

        return result;
    }
    
    // helper methods
    private boolean isInBound(boolean[][] grid, int x, int y) {
        int n = grid.length; // row size
        int m = grid[0].length; // column size

        return x >= 0 && x < n &&   // row size boundary check
                y >= 0 && y < m;    // column size boundary check
    }
}
