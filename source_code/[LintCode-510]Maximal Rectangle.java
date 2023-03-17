/*** LintCode 510. Maximal Rectangle
Given a 2D boolean matrix filled with False and True, find the largest rectangle containing all True and return its area.

Example 1
    Input:
        [
          [1, 1, 0, 0, 1],
          [0, 1, 0, 0, 1],
          [0, 0, 1, 1, 1],
          [0, 0, 1, 1, 1],
          [0, 0, 0, 0, 1]
        ]
    Output: 6

Example 2
    Input:
        [
            [0,0],
            [0,0]
        ]
    Output: 0
***/
public class Solution {
    /**
     * @param matrix: a boolean 2D matrix
     * @return: an integer
     */
    public int maximalRectangle(boolean[][] matrix) {
        // check corner case
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int row = matrix.length;
        int column = matrix[0].length;
        
        // convert the 2-D matrix into  height array
        int[] heights = new int[column];
        int maxArea = Integer.MIN_VALUE;
		
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == true) {
                    heights[j]++; 
                }
                else {
                    heights[j] = 0;
                }
            }
            //get the area based on the bar-heights
            int area = largestRectangleArea(heights);
            
            maxArea = Math.max(maxArea, area);
        }
        
        return maxArea;
    }
    
    // helper method to get the largest rectangle area
    private int largestRectangleArea(int[] heights) {
        // check corner case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        
        int result = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>(); // monotone stack
		
        for (int i = 0; i <= heights.length; i++) {
            int currrent = (i == heights.length) ? -1 : heights[i];
            
            while (!stack.isEmpty() && currrent <= heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = height * width;
                
                result = Math.max(result, area);
            }
			
            stack.push(i);
        }
        
        return result;
    }
}
