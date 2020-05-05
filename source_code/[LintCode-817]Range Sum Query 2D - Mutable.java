/***
* LintCode 817. Range Sum Query 2D - Mutable
Given a 2D matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2). And the elements of the matrix could be changed.

You have to implement three functions:
	-NumMatrix(matrix) The constructor.
	-sumRegion(row1, col1, row2, col2) Return the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
	-update(row, col, val) Update the element at (row, col) to val.
	
Example
	Example 1:
		Input:
			NumMatrix(
			[[3,0,1,4,2],
			 [5,6,3,2,1],
			 [1,2,0,1,5],
			 [4,1,0,1,7],
			 [1,0,3,0,5]]
			)
			sumRegion(2,1,4,3)
			update(3,2,2)
			sumRegion(2,1,4,3)
		Output:
			8
			10
	  
	Example 2:
		Input:
			NumMatrix([[1]])
			sumRegion(0, 0, 0, 0)
			update(0, 0, -1)
			sumRegion(0, 0, 0, 0)
		Output:
			1
			-1
  
Notice
	1.The matrix is only modifiable by update.
	2.You may assume the number of calls to update and sumRegion function is distributed evenly.
	3.You may assume that row1 ≤ row2 and col1 ≤ col2.
***/
//version-1
// helper class
class FenwickTree {
	// fields
	int[][] prefixSum;
	
	// helper method
	private int lowbit(int x) {
		return x & (-x);
	}
	
    // public void print() {
    //     for (int[] row : prefixSum) {
    //         System.out.println(Arrays.toString(row));
    //     }
    // }
	
	// constructor
	public FenwickTree(int n, int m) {
		prefixSum = new int[n + 1][m + 1];
		
		for (int[] row : prefixSum) {
			Arrays.fill(row, 0);
		}
	}
	
	// methods
	public void update(int x, int y, int val) {
		int n = prefixSum.length;
		int m = prefixSum[0].length;
		
		for (int i = x; i < n; i += lowbit(i)) {
			for (int j = y; j < m; j += lowbit(j)) {
				prefixSum[i][j] += val;
			}
		}
	}
	
	public int query(int x, int y) {
		int sum = 0;
		
		for (int i = x; i > 0; i -= lowbit(i)) {
			for (int j = y; j > 0; j -= lowbit(j)) {
				sum += prefixSum[i][j];
			}
		}
		
		return sum;
	}	
}

class NumMatrix {
	// fields
	private int[][] matrix;
	private FenwickTree tree;
	

    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
		int n = matrix.length;
		int m = matrix[0].length;
		
		this.tree = new FenwickTree(n, m);
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				tree.update(i + 1, j + 1, matrix[i][j]);
			}
		}
		
		//tree.print();
		
    }
    
    public void update(int row, int col, int val) {
        int delta = val - matrix[row][col];
		tree.update(row + 1, col + 1, delta);
		
		matrix[row][col] = val;
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return tree.query(row2 + 1, col2 + 1) - 
					tree.query(row1, col2 + 1) - 
					tree.query(row2 + 1, col1) + 
					tree.query(row1, col1);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */

//version-2
class NumMatrix {
	// fields
	private int[][] matrix;
	private int[][] prefixSum;
	
	// helper methods
	private int lowbit(int x) {
		return x & (-x);
	}
	
	private int getPrefixSum(int x, int y) {
		int sum = 0;
		
		for (int i = x + 1; i > 0; i -= lowbit(i)) {
			for (int j = y + 1; j > 0; j -= lowbit(j)) {
				sum += prefixSum[i][j];
			}
		}
		
		return sum;
	}

	// constructor
    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
		int m = matrix[0].length;
		
		this.matrix = new int[n][m];
		this.prefixSum = new int[n + 1][m + 1];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				update(i, j, matrix[i][j]);
			}
		}
    }
    
    public void update(int row, int col, int val) {
        int delta = val - matrix[row][col];
		this.matrix[row][col] = val;
		
		int n = matrix.length;
		int m = matrix[0].length;
		
		for (int i = row + 1; i <= n; i += lowbit(i)) {
			for (int j = col + 1; j <= m; j += lowbit(j)) {
				prefixSum[i][j] += delta;
			}
		}
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return getPrefixSum(row2, col2) - 
                getPrefixSum(row1 - 1, col2) - 
                getPrefixSum(row2, col1 - 1) + 
                getPrefixSum(row1 - 1, col1 - 1);
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * obj.update(row,col,val);
 * int param_2 = obj.sumRegion(row1,col1,row2,col2);
 */