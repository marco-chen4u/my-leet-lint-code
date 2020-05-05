/***
* LintCode 810. Swim in Rising Water
On an N x N grid, each square grid[i][j] represents the elevation at that point (i,j).

Now rain starts to fall. At time t, the depth of the water everywhere is t. You can swim from a square to another 4-directionally adjacent square if and only if the elevation of both squares individually are at most t. You can swim infinite distance in zero time. Of course, you must stay within the boundaries of the grid during your swim.

You start at the top left square (0, 0). What is the least time until you can reach the bottom right square (N-1, N-1)?

Example
	Example 1
		Input: [[0,2],[1,3]]
		Output: 3
		Explanation:
			At time 0, you are in grid location (0, 0).
			You cannot go anywhere else because 4-directionally adjacent neighbors have a higher elevation than t = 0.
			You cannot reach point (1, 1) until time 3.
			When the depth of water is 3, we can swim anywhere inside the grid.

	Example 2
		Input: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
		Output: 16
		Explanation:
			 0  1  2  3  4
			24 23 22 21  5
			12 13 14 15 16
			11 17 18 19 20
			10  9  8  7  6
			The final route is 0 -> 1 -> 2 -> 3 -> 4 -> 5 -> 16 -> 15 -> 14 -> 13 -> 12 -> 11 -> 10 -> 9 -> 8 -> 7 -> 6.
			We need to wait until time 16 so that (0, 0) and (4, 4) are connected.

Notice
	2 <= N <= 50.
	grid[i][j] is a permutation of$ [0, ..., N*N - 1]$.
***/
/*
 *  这道题目的意思其实可以简化成：
 *  求一条路径从地图 左上角 走到 右小角，使得该路径上所有节点中节点的最大值最小。求该最小值为多少？
 *
 * 可以很明显地看出，这是一道 带权 的 求单源最短路径 问题。
 * 只不过这里将我们平时求的 路径和 换成了 路径上节点的最大值 而已。
 * 做法仍然是一样的，使用 Dijkstra 即可轻松解决。
 *
 * 时间复杂度：O(ElogV) => O(n^2 * log(n^2)) => O(n^2 * logn)
 *
 * 对于 Dijkstra's Algorithm 不清楚的可以参考：
 *  https://github.com/cherryljr/LeetCode/blob/master/Network%20Delay%20Time.java
 *
 */
// helper class
class Element implements Comparable<Element> {
    // fields
    int x;
    int y;
    int value;
    // constructor
    public Element(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }
    
    // method
    @Override
    public int compareTo(Element other) {
        return this.value - other.value;
    }
}

public class Solution {
    // fields
    private int n; // row size
    private int m; // column size
    private final int[] DIRECTIONS_X = new int[] {0, 0, 1, -1};
    private final int[] DIRECTIONS_Y = new int[] {1, -1, 0, 0};
    
    /**
     * @param grid: the grid
     * @return: the least time you can reach the bottom right square
     */
    public int swimInWater(int[][] grid) {
        int result = 0;
        // check corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return result;
        }
        
        n = grid.length;
        m = grid[0].length;
        Queue<Element> queue = new PriorityQueue<Element>(); // minHeap
        boolean[][] visited = new boolean[n][m];
        
        //statring from the source point
        queue.offer(new Element(0, 0, grid[0][0]));
        visited[0][0] = true;
        
        while (!queue.isEmpty()) {
            Element current = queue.poll();
            int currentX = current.x;
            int currentY = current.y;
            result = Math.max(result, current.value);
            
            // check corner case, check if it has already reached to the destination
            if (currentX == n - 1 && currentY == m - 1) {
                return result;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTIONS_X[i];
                int nextY = currentY + DIRECTIONS_Y[i];
                
                if (nextX < 0 || nextX >= n || 
                    nextY < 0 || nextY >= m || 
                    visited[nextX][nextY]) {
                   continue; 
                }
                
                queue.offer(new Element(nextX, nextY, grid[nextX][nextY]));
                visited[nextX][nextY] = true;
            }
        }
        
        return -1;
    }
}