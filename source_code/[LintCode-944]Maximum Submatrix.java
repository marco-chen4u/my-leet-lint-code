/***
* LintCode 944. Maximum Submatrix 
Given an n x n matrix of positive and negative integers, find the submatrix with the largest possible sum.
Example
	Example1
	Input:  
	matrix = [
		[1,3,-1],
		[2,3,-2],
		[-1,-2,-3]
	]
	Output: 9
	Explanation:
	the submatrix with the largest possible sum is:
	[
		[1,3],
		[2,3]
	]

	Example2

	Input:  
	matrix = [
		[1,1,1],
		[1,1,1],
		[1,1,1]
	]
	Output: 9
	Explanation:
	the submatrix with the largest possible sum is:
	[
		[1,1,1],
		[1,1,1],
		[1,1,1]
	]
***/
public class Solution {
	
	// fields
	private int n = 0;
	private int m = 0;
	
    /**
     * @param matrix: the given matrix
     * @return: the largest possible sum
     */
    public int maxSubmatrix(int[][] matrix) {
        // check corner cases
		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		
		if (matrix[0] == null || matrix[0].length == 0) {
			return 0;
		}
		
		// initialize
		n = matrix.length;
		m = matrix[0].length;
		
		// state
		int[][] prefixColSum = getPrefixColSum(matrix);
		
		int max = Integer.MIN_VALUE;
		
		// function
		for (int up = 0; up < n; up++) {
			for (int down = up; down < n; down++) {
				int[] nums = compression(matrix, up, down, prefixColSum);
				max = Math.max(max, getMaxSubarraySum(nums));
			}
		}
		
		return max;
    }
	
	// helper methods
	private int[][] getPrefixColSum(int[][] matrix) {
		int[][] prefixColSum = new int[n + 1][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				prefixColSum[i + 1][j] = prefixColSum[i][j] + matrix[i][j];
			}
		}
		
		return prefixColSum;
	}
	
	private int[] compression(int[][] matrix, int up, int down, int[][] prefixColSum) {
		int[] result = new int[m];
		
		for(int i = 0; i < m; i++) {
			result[i] = prefixColSum[down + 1][i] - prefixColSum[up][i];
		}
		
		return result;
	}
	
	private int getMaxSubarraySum(int[] nums) {
		int min = 0;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			max = Math.max(max, sum - min);
			min = Math.min(min, sum);
		}
		
		return max;
	}
}