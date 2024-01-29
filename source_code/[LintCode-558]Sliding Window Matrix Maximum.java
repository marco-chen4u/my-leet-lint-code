/***
* LintCode 558. Sliding Window Matrix Maximum
Given an array of n * m matrix, and a moving matrix window (size k * k), 
move the window from top left to botton right at each iteration, 
find the maximum sum inside the window at each moving.
Return 0 if the answer does not exist.

Example 1
    Input：[[1,5,3],[3,2,1],[4,1,9]]，k=2
    Output：13
    Explanation：
        At first the window is at the start of the matrix like this

            [
              [|1, 5|, 3],
              [|3, 2|, 1],
              [4, 1, 9],
            ]
        ,get the sum 11;
        then the window move one step forward.

            [
              [1, |5, 3|],
              [3, |2, 1|],
              [4, 1, 9],
            ]
        ,get the sum 11;
        then the window move one step forward again.

            [
              [1, 5, 3],
              [|3, 2|, 1],
              [|4, 1|, 9],
            ]
        ,get the sum 10;
        then the window move one step forward again.

            [
              [1, 5, 3],
              [3, |2, 1|],
              [4, |1, 9|],
            ]
        ,get the sum 13;
        SO finally, get the maximum from all the sum which is 13.
	
 Example 2
    Input：[[10]，k=1
    Output：10
    Explanation：
    sliding window size is 1*1，and return 10.

Challenge
    O(n^2) time.
***/
public class Solution {
    private final int DEFAULT_VALUE = Integer.MIN_VALUE;
    /**
     * @param matrix: an integer array of n * m matrix
     * @param k: An integer
     * @return: the maximum number
     */
    public int maxSlidingMatrix(int[][] matrix, int k) {
        int result = 0;
        // check corner case
        if (matrix == null || matrix.length == 0 ||
            matrix[0] == null || matrix[0].length == 0 ||
            k <= 0) {
            return result;
        }

        result = DEFAULT_VALUE;
        int n = matrix.length; // row size
        int m = matrix[0].length; // column size

        // prefixSum preprocessing
        int[][] preSum = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                preSum[i + 1][j + 1] = matrix[i][j] +
                                        preSum[i + 1][j] +
                                        preSum[i][j + 1] -
                                        preSum[i][j];//overlap of preSum[i + 1][j] and preSum[i][j + 1]
            }// for j
        }// for i

        // finding the result
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int value = preSum[i][j] -
                            preSum[i][j - k] -
                            preSum[i - k][j] + 
                            preSum[i - k][j - k];//overlap of preSum[i][j - k] and preSum[i - k][j]

                result = Math.max(result, value);
            } //for j
        }// for i

        return (result == DEFAULT_VALUE) ? 0 : result;
    }
}
