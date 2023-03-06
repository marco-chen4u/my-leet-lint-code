/***
* LintCode 616. Course Schedule II
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
    Example 1:
	Input: n = 2, prerequisites = [[1,0]] 
	Output: [0,1]

	Example 2:
	Input: n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] 
	Output: [0,1,2,3] or [0,2,1,3]
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
//version-1: BFS
public class Solution {

    private static int[] directionX = {1, 1, -1, -1, 2, 2, -2, -2};
    private static int[] directionY = {2, -2, 2, -2, 1, -1, 1, -1};

    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        
        int step = 0;
        Queue<Point> queue = new LinkedList<>();

        int m = grid.length; // row size
        int n = grid[0].length; // col size
        boolean[][] visited = new boolean[m][n];

        queue.offer(source);
        visited[source.x][source.y] = true;

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

            step += 1;

        }

        return -1;

    }
}

