/***
* LintCode 665. Range Sum Query 2D - Immutable
Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Example
	Example1
		Input:
			[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]
			sumRegion(2, 1, 4, 3)
			sumRegion(1, 1, 2, 2)
			sumRegion(1, 2, 2, 4)
		Output:
			8
			11
			12
		Explanation:
			Given matrix = 
			[
			  [3, 0, 1, 4, 2],
			  [5, 6, 3, 2, 1],
			  [1, 2, 0, 1, 5],
			  [4, 1, 0, 1, 7],
			  [1, 0, 3, 0, 5]
			]
			sumRegion(2, 1, 4, 3) = 2 + 0 + 1 + 1 + 0 + 1 + 0 + 3 + 0 = 8
			sumRegion(1, 1, 2, 2) = 6 + 3 + 2 + 0 = 11
			sumRegion(1, 2, 2, 4) = 3 + 2 + 1 + 0 + 1 + 5 = 12

	Example2
		Input:
			[[3,0],[5,6]]
			sumRegion(0, 0, 0, 1)
			sumRegion(0, 0, 1, 1)
		Output:
			3
			14
		Explanation:
			Given matrix = 
			[
			  [3, 0],
			  [5, 6]
			]
			sumRegion(0, 0, 0, 1) = 3 + 0 = 3
			sumRegion(0, 0, 1, 1) = 3 + 0 + 5 + 6 = 14

Notice
	You may assume that the matrix does not change.
	There are many calls to sumRegion function.
	You may assume that row1 ≤ row2 and col1 ≤ col2.
***/
class NumMatrix {
	// fields
	private int rowSize;
	private int columnSize;
	private int[][] prefixSum;

    public NumMatrix(int[][] matrix) {
		int rowSize = matrix.length;
		int columnSize = matrix[0].length;
		// initialize
		prefixSum = new int[rowSize + 1][columnSize + 1];
		
		//function
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				prefixSum[i + 1][j + 1] = matrix[i][j] + prefixSum[i][j + 1] + prefixSum[i + 1][j] - prefixSum[i][j];
			}
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return (prefixSum[row2 + 1][col2 + 1] - prefixSum[row1][col2 + 1] - prefixSum[row2 + 1][col1] + prefixSum[row1][col1]);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */