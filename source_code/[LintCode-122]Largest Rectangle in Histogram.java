/***
* LintCode 122. Largest Rectangle in Histogram
Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

Example
	Example 1:
		Input：[2,1,5,6,2,3]
		Output：10
		Explanation：
			The third and fourth rectangular truncated rectangle has an area of 2*5=10.
	Example 2:
		Input：[1,1]
		Output：2
		Explanation：
			The first and second rectangular truncated rectangle has an area of 2*1=2.
***/
public class Solution {
    /**
     * @param height: A list of integer
     * @return: The area of largest rectangle in the histogram
     */
    public int largestRectangleArea(int[] heights) {
		// check corner cases
		if (heights == null || heights.length == 0) {
			return 0;
		}
		
		int result = Integer.MIN_VALUE;
		Stack<Integer> stack = new Stack<Integer>();
		int size = heights.length;
		
		for (int i = 0; i <= size; i++) {
			int current = (i == size) ? -1 : heights[i];
			
			while (!stack.isEmpty() && current <= heights[stack.peek()]) {
				int height = heights[stack.peek()];
				stack.pop();
				int width = stack.isEmpty() ? i : (i - stack.peek() - 1);
				int area = height * width;
				result = Math.max(result, area);
			}
			
			stack.push(i);
		}
		
		return result;
	}
}