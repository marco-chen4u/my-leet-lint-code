/***
* LintCode 788. The Maze II
There is a ball in a maze with empty spaces and walls. 
The ball can go through empty spaces by rolling up, down, left or right, 
but it won't stop rolling until hitting a wall. 
When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, 
find the shortest distance for the ball to stop at the destination. 

The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). 
If the ball cannot stop at the destination, return -1.
The maze is represented by a binary 2D array. 
1 means the wall and 0 means the empty space. 
You may assume that the borders of the maze are all walls. 
The start and destination coordinates are represented by row and column indexes.


Example 1:
    Input:  
        (rowStart, colStart) = (0,4)
        (rowDest, colDest)= (4,4)
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 1 0
        1 1 0 1 1
        0 0 0 0 0
    Output:  12	
    Explanation:
        (0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)
Example 2:
    Input:
        (rowStart, colStart) = (0,4)
        (rowDest, colDest)= (0,0)
        0 0 1 0 0
        0 0 0 0 0
        0 0 0 1 0
        1 1 0 1 1
        0 0 0 0 0
    Output:  6	
    Explanation:
        (0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)	
Notice
    1.There is only one ball and one destination in the maze.
    2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
    3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
    4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
***/
//version-1: BFS
public class Solution {
    // fields
    private final int DEFAULT_MAX = Integer.MAX_VALUE;
    private final int EMPTY = 0;

    private final int[] directionX = new int[]{0, 0, 1, -1};
    private final int[] directionY = new int[]{1, -1, 0, 0};
	
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int n = maze.length; // row size 
        int m = maze[0].length; // column size

        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], DEFAULT_MAX);
        }
        distance[start[0]][start[1]] = 0;

        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(start);

        while (!queue.isEmpty()) {
            int[] currentPos = queue.poll();

            for (int i = 0; i < 4; i++) {
                int x = currentPos[0];
                int y = currentPos[1];
                int step = 0;

                while (x >= 0 && x < n &&  //row boundary check
                        y >= 0 && y < m && //column boundary check
                        maze[x][y] == EMPTY) {
                    x += directionX[i];
                    y += directionY[i];
                    step++;
                }// while

                // ball is already in the wall, retreat back 1 step where it should stop
                x -= directionX[i];
                y -= directionY[i];
                step--;

                int pathLength = distance[currentPos[0]][currentPos[1]] + step;

                if (pathLength < distance[x][y]) {
                    distance[x][y] = pathLength;
                    queue.offer(new int[]{x, y});
                }
            }// for
        }// while

        return (distance[destination[0]][destination[1]] == DEFAULT_MAX) ? -1 : distance[destination[0]][destination[1]];

    }
}

//version-2: DFS
public class Solution {
    // inner class
    class Point {
        // fileds
        int x;  // row pos
        int y;  // column pos
        int length; // distance from the start point
        // constructor
        public Point(int x, int y, int length) {
            this.x = x;
            this.y = y;
            this.length = length;
        }
    }
	
    // fields
    private final int DEFAULT_MAX = Integer.MAX_VALUE;
    private final int[] directionX = new int[] {0, 0, 1, -1};
    private final int[] directionY = new int[] {1, -1, 0, 0};
    private final int EMPTY = 0;	
    private int n; // row size
    private int m; // column size
	
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        n = maze.length;
        m = maze[0].length;		
        int[][] distance = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distance[i], DEFAULT_MAX);
        }

        //distance[start[0]][start[1]] = 0;
        Point point = new Point(start[0], start[1], 0);

        helper(maze, point, distance);

        return (distance[destination[0]][destination[1]] == DEFAULT_MAX) ? -1 : distance[destination[0]][destination[1]];
    }
	
    private void helper(int[][] maze, Point currentPos, int[][] distance) {
        int x = currentPos.x;
        int y = currentPos.y;
        int length = currentPos.length;		
        // check corner case
        if (length >= distance[x][y]) {
            return;
        }

        distance[x][y] = length;		
        for (int i = 0; i < 4; i++) {
            x = currentPos.x;
            y = currentPos.y;
            length = currentPos.length;
            while (x >= 0 && x < n &&
                    y >= 0 && y < m &&
                    maze[x][y] == EMPTY) {
                x += directionX[i];
                y += directionY[i];
                length ++;
            }// while

            x -= directionX[i];
            y -= directionY[i];
            length --;

            helper(maze, new Point(x, y, length), distance);
        }// for
    }
}
