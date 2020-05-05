/***
* LintCode 789. The Maze III
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the ball position, the hole position and the maze, find out how the ball could drop into the hole by moving the shortest distance. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the hole (included). Output the moving directions by using 'u', 'd', 'l' and 'r'. Since there could be several different shortest ways, you should output the lexicographically smallest way. If the ball cannot reach the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Example
	Example 1:
		Input:
			[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
			[4,3]
			[0,1]
		Output:
			"lul"
	Example 2:
		Input:
			[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
			[0,0]
			[1,1]
			[2,2]
			[3,3]
		Output:
			"impossible"

Notice
	1.There is only one ball and one hole in the maze.
	2.Both the ball and hole exist on an empty space, and they will not be at the same position initially.
	3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
	4.The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.
***/

//version-1: BFS
// helper class
class Point implements Comparable<Point> {
	// fileds
	public int x;
	public int y;
	public int length;
	public String path;
	
	// constructor
	public Point(int x, int y, int length, String path) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.path = path;
	}
	
	// methods
	public int compareTo(Point other) {
		if (this.length != other.length) {
			return this.length - other.length;
		}
		
		return this.path.compareTo(other.path);
	}
	
	public boolean isLessThan(Point other) {
		return (compareTo(other) < 0) ? true : false;
	}
}

public class Solution {
	// field
	private final int DEFAULT_MAX = Integer.MAX_VALUE;
	private final String DEFAULT_EMPTY = "";
	private final int WALL = 1;
	
	private final int[] directionX = new int[] {0, 0, 1, -1};
	private final int[] directionY = new int[] {1, -1, 0, 0};
	private final String[] direcitons = new String[]{"r", "l", "d", "u"};// mapping to directionX&directionY
	
	private int n; // row size
	private int m; // column size
	
    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		String result = DEFAULT_EMPTY;
        n = maze.length;
		m = maze[0].length;
		
		Point[][] position = new Point[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				position[i][j] = new Point(i, j, DEFAULT_MAX, DEFAULT_EMPTY);
			}
		}
		
		int x = ball[0];
		int y = ball[1];
		
		Queue<Point> queue = new LinkedList<Point>();		
		queue.offer(new Point(x, y, 0, DEFAULT_EMPTY));
		
		while (!queue.isEmpty()) {
			Point current = queue.poll();
			x = current.x;
			y = current.y;
			
			if (!current.isLessThan(position[x][y])){
				continue;
			}
			
			position[x][y] = current;
			
			if (x == hole[0] && y == hole[1]) {
				continue;
			}
			
			for (int i = 0; i < 4; i++) {
				int length = current.length;
				String path = current.path;
				x = current.x;
				y = current.y;
				
				while (x >= 0 && x < n &&
						y >= 0 && y < m &&
						maze[x][y] != WALL &&
						(x != hole[0] || y != hole[1])) {
					x += directionX[i];
					y += directionY[i];
					length++;					
				}// while
				
				// if it is not trapped in the hole, retreat it back from the wall inside
                if (x != hole[0] || y != hole[1]) {
                	x -= directionX[i];
                	y -= directionY[i];
                	length--;
                }
                
                path = path + direcitons[i];
                
                queue.offer(new Point(x, y, length, path));			
			}// for
			
		}// while
		
		result = (position[hole[0]][hole[1]].length == DEFAULT_MAX) ? "impossible" : position[hole[0]][hole[1]].path;
		
		return result;
    }
}


//version-2: BFS + MinHeap
// helper class
class Point implements Comparable<Point> {
	// fileds
	public int x;
	public int y;
	public int length;
	public String path;
	
	// constructor
	public Point(int x, int y, int length, String path) {
		this.x = x;
		this.y = y;
		this.length = length;
		this.path = path;
	}
	
	// methods
	public int compareTo(Point other) {
		if (this.length != other.length) {
			return this.length - other.length;
		}
		
		return this.path.compareTo(other.path);
	}
}

public class Solution {
	// field
	private final int DEFAULT_MAX = Integer.MAX_VALUE;
	private final String DEFAULT_EMPTY = "";
	private final int WALL = 1;
	
	private final int[] directionX = new int[] {-1, 1, 0, 0};
	private final int[] directionY = new int[] {0, 0, -1, 1};
	private final String[] directions = new String[]{"u", "d", "l", "r"};// mapping to directionX&directionY

	
	private int n; // row size
	private int m; // column size

    /**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		String result = "impossible";
        n = maze.length;
		m = maze[0].length;
		
		Point[][] position = new Point[n][m];
		boolean[][] visited = new boolean[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				position[i][j] = new Point(i, j, DEFAULT_MAX, DEFAULT_EMPTY);
			}
		}
				
		Queue<Point> minHeap = new PriorityQueue<Point>();
		minHeap.offer(new Point(ball[0], ball[1], 0, DEFAULT_EMPTY));
		
		while (!minHeap.isEmpty()) {
			Point current = minHeap.poll();
			
			if (current.x == hole[0] && current.y == hole[1]) {
				return current.path;
			}
			
			for (int i = 0; i < 4; i++) {
				int x = current.x;
				int y = current.y;
				int length = current.length;				
				
				while (x >= 0 && x < n &&  // row boundary check
						y >= 0 && y < m && // column boundary check
						maze[x][y] != WALL &&
						(x != hole[0] || y != hole[1])) {
					x += directionX[i];
					y += directionY[i];
					length++;
				}
				
				// if it is not trapped in the hole, retreat it back from the wall inside
				if (x != hole[0] || y != hole[1]) {
					x -= directionX[i];
					y -= directionY[i];
					length--;
				}
				
				String path = current.path;
				
				if (!visited[x][y]) {
					visited[current.x][current.y] = true;
					minHeap.offer(new Point(x, y, length, path + directions[i]));
				}
				
			}
		}
		
		return result;
    }
}