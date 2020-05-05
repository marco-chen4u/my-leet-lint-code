/***
* LeetCode 417. Pacific Atlantic Water Flow
Given an m x n matrix of non-negative integers representing the height of each unit cell in a continent, the "Pacific ocean" touches the left and top edges of the matrix and the "Atlantic ocean" touches the right and bottom edges.

Water can only flow in four directions (up, down, left, or right) from a cell to another one with height equal or lower.

Find the list of grid coordinates where water can flow to both the Pacific and Atlantic ocean.

Note:
The order of returned grid coordinates does not matter.
Both m and n are less than 150.

Example
	Given the following 5x5 matrix:
	  Pacific ~   ~   ~   ~   ~ 
		   ~  1   2   2   3  (5) *
		   ~  3   2   3  (4) (4) *
		   ~  2   4  (5)  3   1  *
		   ~ (6) (7)  1   4   5  *
		   ~ (5)  1   1   2   4  *
			  *   *   *   *   * Atlantic
	Return:
	[[0, 4], [1, 3], [1, 4], [2, 2], [3, 0], [3, 1], [4, 0]] (positions with parentheses in above matrix).
***/
/*
* 反向思维，从4边作为源点，然后借助dfs和4个方向坐标的移动，从低往高做判断。
*/
//Version-1: DFS, run time exceeded error
class Solution {
	// fields
	private int n; // row size
	private int m; // column size
	private final int MIN = Integer.MIN_VALUE;
	private final int[] DIRECTION_X = new int[] {0,0, 1, -1};
	private final int[] DIRECTION_Y = new int[] {1, -1, 0, 0};
	
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
		// check corner case
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
			return result;
		}
		
		n = matrix.length; // row size
		m = matrix[0].length; // column size
		
		boolean[][] pacific = new boolean[n][m];
		boolean[][] atlantic = new boolean[n][m];
		
		// initialize
		// first-row --> pacific ocean, last-row --> atlantic ocean
		for (int j = 0; j < m; j++) {
			dfs(matrix, pacific, 0, j, MIN); // pacific ocean
			dfs(matrix, atlantic, n - 1, j, MIN); // atlantic ocean
		}
		
		// first-column --> pacific ocean, last-column --> atlantic ocean
		for (int i = 0; i < n; i++) {
			dfs(matrix, pacific, i, 0, MIN); // pacific ocean
			dfs(matrix, atlantic, i, m - 1, MIN); // atlantic ocean
		}
		
		// check the positions that are both availible for pacific ocean and atlantic ocean
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (pacific[i][j] && atlantic[i][j]) {
					List<Integer> list = Arrays.asList(i, j);
					result.add(list);
				}
			}
		}
		
		return result;
    }
	
	// helper method
	private void dfs(int[][] matrix, boolean[][] visited, int x, int y, int lastHeight) {
		// check corner cases
		if (x < 0 || x >= n ||
			y < 0 || y >= m) {
			return;
		}
		
		if (visited[x][y]) {
			return;
		}
		
		if (matrix[x][y] < lastHeight) {//需要从低往高逆向走，否则终止返回递归 
			return;
		}
		
		visited[x][y] = true;
		
		for (int i = 0; i < 4; i++) {
			int nextX = x + DIRECTION_X[i];
			int nextY = y + DIRECTION_Y[i];
			
			dfs(matrix, visited, nextX, nextY, matrix[x][y]);
		}
	}
}