/***
* LintCode 787. The Maze
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

Example
	Example 1:
		Input:
			map = 
			[
			 [0,0,1,0,0],
			 [0,0,0,0,0],
			 [0,0,0,1,0],
			 [1,1,0,1,1],
			 [0,0,0,0,0]
			]
			start = [0,4]
			end = [3,2]
		Output:
			false
		Explanation: There is no way for the ball to stop at the destination, no wall(bottom side) against the destination.
			
	Example 2:
		Input:
			map = 
			[
			 [0,0,1,0,0],
			 [0,0,0,0,0],
			 [0,0,0,1,0],
			 [1,1,0,1,1],
			 [0,0,0,0,0]
			]
			start = [0,4]
			end = [4,4]
		Output:
			true
		Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right。
Notice
	1.There is only one ball and one destination in the maze.
	2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
	3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
	4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.
***/
//version-1: BFS
public class Solution {
	//fields
	private final int[] directionX = new int[] {0, 0, 1, -1};
	private final int[] directionY = new int[] {1, -1, 0, 0};
	private final int WALL = 1;
	private final int EMPTY = 0;
	private int n; // row size
	private int m; // column size
	
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        n = maze.length;// row size
		m = maze[0].length; // column size		
		boolean[][] visited = new boolean[n][m];
		Queue<int[]> queue = new LinkedList<int[]>();
		
		queue.offer(start);
		while (!queue.isEmpty()) {
			int[] currentPos = queue.poll();
			
			// check corner case
			if (currentPos[0] == destination[0] && currentPos[1] == destination[1]) {
				return true;
			}
			
			for (int i = 0; i < 4; i++) {
				int x = currentPos[0];// row
				int y = currentPos[1];// column				
				// check if the ball get bounded or hit a wall
				while (x >= 0 && x < n &&// row boundary check
					y >= 0 && y < m &&   // column boundary check
					maze[x][y] == EMPTY) {
					x += directionX[i];
					y += directionY[i];
				}
				
				// ball is already in the wall, need to retreat back 1 step where the position is empty and ball stops
				x -= directionX[i];
				y -= directionY[i];
				
				if (!visited[x][y]) {
					visited[x][y] = true;
					queue.offer(new int[]{x, y});
				}// if
			}// for i
		}// while
		
		return false;
    }
}

//version-2:DFS
public class Solution {
	// fields
	private final int EMPTY = 0;	
	private final int[] directionX = new int[]{0, 0, 1, -1};
	private final int[] directionY = new int[]{1, -1, 0, 0};	
	private int n; // row size
	private int m; // column size	
	
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        n = maze.length;
		m = maze[0].length;		
		boolean[][] visited = new boolean[n][m];
		
		return findPath(maze, visited, start, destination);
    }
	
	// helper method
	private boolean findPath(int[][] maze, boolean[][] visited, int[] currentPos, int[] destination) {
		int x = currentPos[0]; //row pos
		int y = currentPos[1]; // col pos
		
		// check corner case
		if (visited[x][y]) {
			return false;
		}
		
		if (x == destination[0] && y == destination[1]) {
			return true;
		}
		
		// marked visited
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			x = currentPos[0];// reset to the current pos
			y = currentPos[1];// reset to the current pos
			
			while (x >= 0 && x < n && // row boundary check
					y >= 0 && y < m && // column boundary check
					maze[x][y] == EMPTY) {
				x += directionX[i];
				y += directionY[i];
			}
			
			// ball is already in the wall, retreat back 1 step where it should stop
			x -= directionX[i];
			y -= directionY[i];
			
			if (findPath(maze, visited, new int[]{x, y}, destination)) {
				return true;
			}		
		}
		
		return false;
	}
}