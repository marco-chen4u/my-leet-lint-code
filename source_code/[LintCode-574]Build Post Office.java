/***
* LintCode 574. Build Post Office
Given a 2D grid, each cell is either an house 1 or empty 0 (the number zero, one), 
find the place to build a post office, the distance that post office to all the house sum is smallest. 

Return the smallest distance. Return -1 if it is not possible.


Example 1:
    Input：[[0,1,0,0],[1,0,1,1],[0,1,0,0]]
    Output： 6
    Explanation：
        Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
Example 2:
    Input：[[0,1,0],[1,0,1],[0,1,0]]
    Output： 4
    Explanation：
        Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
	
Notice
    You can pass through house and empty.
    You only build post office on an empty.
    The distance between house and the post office is Manhattan distance
***/
public class Solution {
    // fields
    private final int EMPTY = 0;
    private final int HOUSE = 1;
    private final int DEFAULT_VALUE = Integer.MAX_VALUE;
    
    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        
        int[] houseOfX = new int[m];
        int[] houseOfY = new int[n];
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == EMPTY) {
                    continue;
                }
                
                houseOfX[i]++;
                houseOfY[j]++;
            }
        }
        
        int result = DEFAULT_VALUE;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == HOUSE) {
                    continue;
                }
                
                int currentDistance = 0;
                
                for (int x = 0; x < m; x++) {
                    currentDistance += Math.abs(i - x) * houseOfX[x];
                }
                
                for (int y = 0; y < n; y++) {
                    currentDistance += Math.abs(j - y) * houseOfY[y];
                }
                
                result = Math.min(currentDistance, result);
                
            }
        }
        
        return (result == DEFAULT_VALUE) ? -1 : result;
    }
}
