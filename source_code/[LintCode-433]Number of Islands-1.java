/***
* LintCode 433. Number of Islands
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. 
We only consider up/down/left/right adjacent.

Find the number of islands.

Example
	Example 1:
		Input:
			[
			  [1,1,0,0,0],
			  [0,1,0,0,1],
			  [0,0,0,1,1],
			  [0,0,0,0,0],
			  [0,0,0,0,1]
			]
		Output:
			3
	Example 2:
		Input:
			[
			  [1,1]
			]
		Output:
			1
***/
// version-1: BFS
public class Solution {
    
    // inner class
    class Element {
        int x;
        int y;
        public Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        int count = 0;
        
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return count;
        }
        
        int n = grid.length;
        int m = grid[0].length;
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    markVisted(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    // helper methods
    private void markVisted(boolean[][] grid, int rowIndex, int colIndex) {
        int[] directionX = new int[]{0,1,-1,0};
        int[] directionY = new int[]{1,0,0,-1};
        
        grid[rowIndex][colIndex] = false;
        Element current = new Element(rowIndex, colIndex);
        Queue<Element> queue = new LinkedList<Element>();
        queue.offer(current);
        
        while (!queue.isEmpty()) {
            current = queue.poll();
            
            for (int i = 0; i < 4; i++) {
                int x = current.x + directionX[i];
                int y = current.y + directionY[i];
                
                if (!isInBound(grid, x, y)) {
                    continue;
                }
                
                if (!grid[x][y]) {
                    continue;
                }
                
                grid[x][y] = false;
                
                queue.offer(new Element(x, y));
            }
        }
    }
    
    private boolean isInBound(boolean[][] grid, int x, int y) {
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        
        return x >=0 && x < n &&  // row boundary check
                y >= 0 && y < m;  // column boundary check
    }
}