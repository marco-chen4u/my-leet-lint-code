/***
* LintCode 663. Walls and Gates
You are given a m x n 2D grid initialized with these three possible values.
	-1 - A wall or an obstacle.
	0 - A gate.
	INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
	
Fill each empty room with the distance to its nearest gate. If it is impossible to reach a Gate, that room should remain filled with INF

Example
	Example1
		Input:
			[[2147483647,-1,0,2147483647],[2147483647,2147483647,2147483647,-1],[2147483647,-1,2147483647,-1],[0,-1,2147483647,2147483647]]
		Output:
			[[3,-1,0,1],[2,2,1,-1],[1,-1,2,-1],[0,-1,3,4]]
		Explanation:
			the 2D grid is:
			INF  -1  0  INF
			INF INF INF  -1
			INF  -1 INF  -1
			  0  -1 INF INF
			the answer is:
			  3  -1   0   1
			  2   2   1  -1
			  1  -1   2  -1
			  0  -1   3   4
	  
	Example2
		Input:
			[[0,-1],[2147483647,2147483647]]
		Output:
			[[0,-1],[1,2]]
***/
/*
* 经典的BFS解法，唯一的tricky就是要使用结对的Queue同时记录横坐标x和纵坐标y，在每一次Queue.offer（）和Queue.poll()的时候，都结对出现。
* 本体除了可以level-pace BFS处理（详见version-1），也可以用普通的BFS处理（详见version-2）因为用普通的BFS处理也是可以的为什么呢？因为每个源点（Gate）出发后，每个路径所标识的empty spot的值就最近的-“谁（源点）先下手谁为王，后下手则遭殃”。
*
*/
public class Solution {
    // feilds
    private final int EMPTY = Integer.MAX_VALUE;
    private final int GATE = 0;
    private final int WALL = -1;
    
    private final int[] DIRECTION_X = new int[] {0, 0, 1, -1};
    private final int[] DIRECTION_Y = new int[] {1, -1, 0, 0};
    
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        // check corner case
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        
        int n = rooms.length;// row size
        int m = rooms[0].length;// column size
        
        Queue<Integer> queueX = new LinkedList<Integer>();
        Queue<Integer> queueY = new LinkedList<Integer>();
        
        // initialize
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == GATE) {// find all source point(s)
                    queueX.offer(i);
                    queueY.offer(j);
                }
            }
        }
        
        // start Breadth First Searching
        while (!queueX.isEmpty()) {
            
            int size = queueX.size();
            
            for (int i = 0; i < size; i++) {
                // get the current distance-calculated room position 
                int currentX = queueX.poll();
                int currentY = queueY.poll();
                int currentDistance = rooms[currentX][currentY];
                
                for (int index = 0; index < 4; index++) {
                    int nextX = currentX + DIRECTION_X[index];
                    int nextY = currentY + DIRECTION_Y[index];
                    
                    if (nextX < 0 || nextX >= n || 
                        nextY < 0 || nextY >= m ||
                        rooms[nextX][nextY] != EMPTY) {
                        continue;
                    }
                    
                    rooms[nextX][nextY] = currentDistance + 1;
                    
                    queueX.offer(nextX);
                    queueY.offer(nextY);
                }
                
            }
        }
    }
}

//version-2: BFS
public class Solution {
    // fields
    private final int EMPTY = Integer.MAX_VALUE;
    private final int GATE = 0;
    private final int WALL = -1;
    
    private final int[] DIRECTIONS_X = new int[] {0, 0, 1, -1};
    private final int[] DIRECTIONS_Y = new int[] {1, -1, 0, 0};
    
    /**
     * @param rooms: m x n 2D grid
     * @return: nothing
     */
    public void wallsAndGates(int[][] rooms) {
        // check corner cases
        if (rooms == null || rooms.length == 0 ||
            rooms[0] == null || rooms[0].length == 0) {
            return;
        }
        
        // normal case
        int n = rooms.length; // row size;
        int m = rooms[0].length; // column size
        
        // initialize
        Queue<Integer> queueX = new LinkedList<Integer>();
        Queue<Integer> queueY = new LinkedList<Integer>();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (rooms[i][j] == GATE) {
                    queueX.offer(i);
                    queueY.offer(j);
                }
            }
        }
        
        // start Breadth First Searching
        while (!queueX.isEmpty()) {
            int currentX = queueX.poll();
            int currentY = queueY.poll();
            int currentDistance = rooms[currentX][currentY];
            
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTIONS_X[i];
                int nextY = currentY + DIRECTIONS_Y[i];
                
                if (nextX < 0 || nextX >= n ||
                    nextY < 0 || nextY >= m || 
                    rooms[nextX][nextY] != EMPTY) {
                    continue;
                }
                
                rooms[nextX][nextY] = currentDistance + 1;
                queueX.offer(nextX);
                queueY.offer(nextY);
            }
        }
    }
}