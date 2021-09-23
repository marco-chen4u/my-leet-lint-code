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
    private int n; // row size
    private int m; // column size

    private final int EMPTY = 0;
    private final int HOUSE = 1;
    private final int DEFAULT_VALUE = Integer.MAX_VALUE;

    /**
     * @param grid: a 2D grid
     * @return: An integer
     */
    public int shortestDistance(int[][] grid) {        
        // check corner case
        if (grid == null || grid.length == 0 || 
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }

	// regular case
	// initialize
        n = grid.length;
        m = grid[0].length;

        int[] houseOfX = new int[n];
        Arrays.fill(houseOfX, 0);
        int[] houseOfY = new int[m];
        Arrays.fill(houseOfY, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == EMPTY) {
                    continue;
                }

                houseOfX[i] ++;
                houseOfY[j] ++;
            }
        }

	// calculate the Manhatan distance
        int result = DEFAULT_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == HOUSE) {
                    continue;
                }

                // calculate(sum up) the Manhatan distance from an empty place to all house places
                int currentDistance = 0;
                for (int x = 0; x < n; x++) {
                    currentDistance += Math.abs(i - x) * houseOfX[x];
                }

                for (int y = 0; y < m; y++) {
                    currentDistance += Math.abs(j - y) * houseOfY[y];
                }

                // compare with other spot and get the smallest distance
                result = Math.min(result, currentDistance);
            }
        }

        return result == DEFAULT_VALUE ? 0 : result;


    }
}
