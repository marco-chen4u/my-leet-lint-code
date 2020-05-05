/***
* LintCode 553. Bomb Enemy
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), 
return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.

Example
	Example1
		Input:
			grid =[
				 "0E00",
				 "E0WE",
				 "0E00"
			]
		Output: 3
		Explanation:
			Placing a bomb at (1,1) kills 3 enemies
	Example2
		Input:
			grid =[
				 "0E00",
				 "EEWE",
				 "0E00"
			]
		Output: 2
		Explanation:
			Placing a bomb at (0,0) or (0,3) or (2,0) or (2,3) kills 2 enemies
	
Notice
	You can only put the bomb at an empty cell.
***/
public class Solution {
	// fields
    private final char WALL = 'W';
    private final char ENEMY = 'E';
    private final char EMPTY = '0';
    
    int n; // row size
    int m; // column size
    
    /**
     * @param grid: Given a 2D grid, each cell is either 'W', 'E' or '0'
     * @return: an integer, the maximum enemies you can kill using one bomb
     */
    public int maxKilledEnemies(char[][] grid) {
        int result = 0;
        // check corner cases
        if (grid == null || grid.length == 0 || 
            grid[0] == null || grid[0].length == 0) {
            return result;
        }
        
        n = grid.length;// row size
        m = grid[0].length;// column size
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!isEmpty(grid[i][j])) {
                    continue;
                }
                
                int value = getKilledEnemies(grid, i, j);
                
                result = Math.max(result, value);
            }
        }
        
        return result;
    }
    
    // helper methods
    private int getKilledEnemies(char[][] grid, int x, int y) {
        int result = 0;
        if (!isInBound(x, y)) {
            return result;
        }
        
        int countX = 0;// horizontal calculating
        for (int i = x - 1; i >= 0 && !isWall(grid[i][y]); i--) {
            countX += isEnemy(grid[i][y]) ? 1 : 0;
        }
        for (int i = x + 1; i < n && !isWall(grid[i][y]); i++) {
            countX += isEnemy(grid[i][y]) ? 1 : 0;
        }
        
        int countY = 0;// vertical calculating
        for (int j = y - 1; j >= 0 && !isWall(grid[x][j]); j--) {
            countY += isEnemy(grid[x][j]) ? 1 : 0;
        }
        for (int j = y + 1; j < m && !isWall(grid[x][j]); j++) {
            countY += isEnemy(grid[x][j]) ? 1 : 0;
        }
        
        result += countX + countY;
        
        return result;
    }
    
    private boolean isInBound(int x, int y) {
        return x >= 0 && x < n && // row check
                y >= 0 && y < m;  // column check
    }
    
    private boolean isWall(char ch) {
        return WALL == ch;
    }
    
    private boolean isEnemy(char ch) {
        return ENEMY == ch;
    }
    
    private boolean isEmpty(char ch) {
        return EMPTY == ch;
    }
}