/***
* LintCode 109. Triangle
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

Example 1
    Input the following triangle:
        [
             [2],
    	    [3,4],
           [6,5,7],
          [4,1,8,3]
        ]
    Output: 11
        Explanation: The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Example 2
    Input the following triangle:
        [
             [2],
            [3,2],
           [6,5,7],
	  [4,4,8,1]
         ]
    Output: 12
        Explanation: The minimum path sum from top to bottom is 12 (i.e., 2 + 2 + 7 + 1 = 12).
Notice
    Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
    
LintCode link: https://www.lintcode.com/problem/109/
LeetCode link: https://leetcode.com/problems/triangle/
***/
// version-1: memorization search
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // check corner cases
        if (triangle == null || triangle.length == 0) {
            return 0;
        }
	
        if (triangle[0] == null || triangle[0].length == 0) {
            return 0;
        }
	
        int n = triangle.length;
        int[][] hash =  new int[n][n];
	
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hash[i][j] = Integer.MAX_VALUE;
            }
        }
	
        int result = traverse(triangle, hash, 0, 0);

        return result;
    }

    // helper method
    private int traverse(int[][] triangle, int[][] hash, int x, int y) {
        if (x == triangle.length) {
            return 0;
        }
	
        if (hash[x][y] != Integer.MAX_VALUE) {
            return hash[x][y];
        }
	
        int left = traverse(triangle, hash, x + 1, y);
        int right = traverse(triangle, hash, x + 1, y + 1);
	
        hash[x][y] = Math.min(left, right) + triangle[x][y];

        return hash[x][y];		
    }
}

// version-2: DP(bottom-up)
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // check corner cases
        if (triangle == null || triangle.length == 0) {
            return 0;
        }

        if (triangle[0] == null || triangle[0].length == 0) {
            return 0;
        }

        // state
        int n = triangle.length;
        int[][] f = new int[n][n];

        // initialize
        for (int i = 0; i < n; i++) {
            f[n - 1][i] = triangle[n - 1][i];
        }
	
        // fucntion
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                f[i][j] = Math.min(f[i + 1][j], f[i + 1][j + 1])+triangle[i][j];
            }
        }

        // result
        return f[0][0];
    }
}

//version-3: DP(top-bottom)
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // check corner cases
        if (triangle == null || triangle.length == 0) {
            return 0;
        }

        if (triangle[0] == null || triangle[0].length == 0) {
            return 0;
        }

        // state
        int n = triangle.length; 
        int[][] f = new int[n][n];

        // initialize
        f[0][0] = triangle[0][0];
        for (int i = 1; i < n; i++) {
            f[i][0] = f[i - 1][0] + triangle[i][0];
            f[i][i] = f[i - 1][i - 1] + triangle[i][i];
        }

        // state
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < i; j++) {
                f[i][j] = Math.min(f[i - 1][j], f[i - 1][j - 1]) + triangle[i][j];
            }
        }

        // result
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            result = Math.min(result, f[n - 1][i]);
        }

        return result;
    }
}

//version-4: top-down regular iteration, O(n) with extra space
public class Solution {
    /**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // check corner cases
        if (triangle == null || triangle.length == 0 ||
            triangle[0] == null || triangle[0].length == 0) {
            return 0;
        }
        
        int size = triangle.length;
        
        if (size == 1) {
            return triangle[0][0];
        }
        
        // regular case
        int last = size - 1;
        int minPath = Integer.MAX_VALUE;
        
        for (int i = 1; i < size; i++) {
            int[] current = triangle[i];
            int[] pre = triangle[i - 1];
            int[] newValues = new int[current.length];
            
            int length = current.length;
            int start = 0;
            int end = length - 1;
            
            for (int j = 0; j < length; j++) {
                int value = current[j];
                
                if (j == start) { 
                    value += pre[start];
                }
                else if (j == end) { 
                    value += pre[end - 1];
                }
                else { 
                    value += Math.min(pre[j - 1], pre[j]);
                }
                
                newValues[j] = value;
            }
            
            triangle[i] = newValues;//repalce the current array into the triangle to save the current path sum calculation.
        }
        
        // find the minimum path sum value in the last row of path calculation inside this triangle
        for (int pathSum : triangle[last]) {
            minPath = Math.min(minPath, pathSum);
        }
        
        return minPath;
    }
}
