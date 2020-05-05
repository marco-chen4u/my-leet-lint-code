/***
* LintCode 405. Submatrix Sum
Given an integer matrix, find a submatrix where the sum of numbers is zero. 
Your code should return the coordinate of the left-up and right-down number.

If there are multiple answers, you can return any of them.

Example
	Example 1:
		Input:
			[
			  [1, 5, 7],
			  [3, 7, -8],
			  [4, -8 ,9]
			]
		Output: [[1, 1], [2, 2]]

	Example 2:
		Input:
			[
			  [0, 1],
			  [1, 0]
			]
		Output: [[0, 0], [0, 0]]

Challenge
	O(n3) time.
***/
public class Solution {
	
	// helper methods
	private int[][] getPrefixSum(int[][] matrix, int rowSize, int columnSize) {
		int[][] prefixSum = new int[rowSize + 1][columnSize + 1];
		
		for (int i = 0; i < rowSize; i++) {
			for (int j = 0; j < columnSize; j++) {
				prefixSum[i + 1][j + 1] = matrix[i][j] + prefixSum[i + 1][j] + prefixSum[i][j + 1] - prefixSum[i][j];
			}
		}
		
		return prefixSum;
	}
	
    /*
     * @param matrix: an integer matrix
     * @return: the coordinate of the left-up and right-down number
     */
    public int[][] submatrixSum(int[][] matrix) {
		int[][] result = new int[][]{{-1,-1},{-1, -1}};
        // check corner case
		if (matrix == null || matrix.length == 0) {
			return result;
		}
		
		if (matrix[0] == null || matrix[0].length == 0) {
			return result;
		}
		
		int rowSize = matrix.length;
		int columnSize = matrix[0].length;
		int[][] prefixSum = getPrefixSum(matrix, rowSize, columnSize);
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int upperRow = 0; upperRow < rowSize; upperRow++) {
			for (int lowerRow = upperRow + 1; lowerRow <= rowSize; lowerRow++) {
				
				map.clear();//reset for every to row boundary scan
				
				for (int col = 0; col <= columnSize; col++) {
					int diff = prefixSum[lowerRow][col] - prefixSum[upperRow][col];
					if (map.containsKey(diff)) {
						result[0][0] = upperRow;
						result[0][1] = map.get(diff);
						
						result[1][0] = lowerRow - 1;
						result[1][1] = col - 1;
						
						return result;
					}
					else {
						map.put(diff, col);
					}
				}		
			}
		}
		
		return result;		
    }
}