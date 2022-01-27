/***
* LintCode 598. Zombie in Matrix
Given a 2D grid, each cell is either a wall 2, 
a zombie 1 or people 0 (the number zero, one, two).
Zombies can turn the nearest people(up/down/left/right) into zombies every day, 
but can not through wall. 
How long will it take to turn all people into zombies? 
Return -1 if can not turn all people into zombies.

Example 1:
    Input:
        [[0,1,2,0,0],
         [1,0,0,2,1],
         [0,1,0,0,0]]
    Output:
        2
	
Example 2:
    Input:
        [[0,0,0],
         [0,0,0],
         [0,0,1]]
    Output:
        4
***/
//verion-1:BFS with helper class Point for holding all the x&y position information
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

    //fields
    private final int WALL = 2;
    private final int ZOMBIE = 1;
    private final int PEOPLE = 0;
    private final int[] directionX = new int[] {1, 0, 0, -1};
    private final int[] directionY = new int[] {0, 1, -1, 0};
	
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        int result = -1;
        // check corner case 
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int n = grid.length; // row size
        int m = grid[0].length; // column size		
        int days = 0;
        int peopleCount = 0;

        Queue<Point> queue = new LinkedList<Point>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    peopleCount++;
                }

                if (grid[i][j] == ZOMBIE) {
                    Point zombie = new Point(i, j);
                    queue.offer(zombie);
                }
            }
        }

        if (peopleCount == 0) {
            return 0;
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            days++;
            for (int i = 0; i < size; i++) {
                Point current = queue.poll();

                for (int index = 0; index < 4; index++) {
                    int nextX = current.x + directionX[index];
                    int nextY = current.y + directionY[index];

                    if (nextX < 0 || nextX >= n ||
                            nextY < 0 || nextY >= m ||
                            grid[nextX][nextY] != PEOPLE) {
                        continue;
                    }

                    grid[nextX][nextY] = ZOMBIE;
                    peopleCount--;
                    if (peopleCount == 0) {
                        return days;
                    }

                    Point next = new Point(nextX, nextY);
                    queue.offer(next);				
                }
            }
        }

        return result;
    }
}

//verion-2: use a pair of Queue to track all source positions(which are zombies) x and y values repectively, whenever offer&poll() they will act together at the same time
public class Solution {
	//fields
	private final int WALL = 2;
	private final int ZOMBIE = 1;
	private final int PEOPLE = 0;
	private final int[] directionX = new int[] {1, 0, 0, -1};
	private final int[] directionY = new int[] {0, 1, -1, 0};
	
    /**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return -1;
        }
        
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        
        // initialize
        Queue<Integer> queueX = new LinkedList<Integer>();
        Queue<Integer> queueY = new LinkedList<Integer>();
        
        int peopleCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == PEOPLE) {
                    peopleCount++;
                    continue;
                }
                
                if (grid[i][j] == ZOMBIE) {//account all source positions
                    queueX.offer(i);
                    queueY.offer(j);
                }
            }
        }
        
        if (peopleCount == 0) {
            return -1;
        }
        //System.out.println("peopleCount = " + peopleCount);
        
        int days = 0;
        while (!queueX.isEmpty()) {
            days++;
            
            int size = queueX.size();
            for (int i = 0; i < size; i++) {
                int x = queueX.poll();
                int y = queueY.poll();
                
                
                for (int index = 0; index < 4; index++) {
                    int nextX = x + directionX[index];
                    int nextY = y + directionY[index];
                    
                    if (nextX < 0 || nextX >= n || 
                        nextY < 0 || nextY >= m || 
                        grid[nextX][nextY] != PEOPLE) {
                        continue;
                    }
                    
                    grid[nextX][nextY] = ZOMBIE;
                    queueX.offer(nextX);
                    queueY.offer(nextY);
                    
                    peopleCount--;
                    
                    if (peopleCount == 0) {
                        return days;
                    }
                }
            }
        }

        return -1;
    }
}
