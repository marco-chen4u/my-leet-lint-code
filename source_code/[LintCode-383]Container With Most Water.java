/***
* LintCode 383. Container With Most Water
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Example
	Example 1:
		Input: [1, 3, 2]
		Output: 2
		Explanation:
			Selecting a1, a2, capacity is 1 * 1 = 1
			Selecting a1, a3, capacity is 1 * 2 = 2
			Selecting a2, a3, capacity is 2 * 1 = 2

	Example 2:
		Input: [1, 3, 2, 2]
		Output: 4
		Explanation:
			Selecting a1, a2, capacity is 1 * 1 = 1
			Selecting a1, a3, capacity is 1 * 2 = 2
			Selecting a1, a4, capacity is 1 * 3 = 3
			Selecting a2, a3, capacity is 2 * 1 = 2
			Selecting a2, a4, capacity is 2 * 2 = 4
			Selecting a3, a4, capacity is 2 * 1 = 2
Notice
	You may not slant the container.
***/
public class Solution {
    /**
     * @param heights: a vector of integers
     * @return: an integer
     */
    public int maxArea(int[] heights) {
		int result = 0;
		// check corner cases
		if (heights == null || heights.length == 0) {
			return result;
		}
		
		int left = 0;
		int right = heights.length - 1;
		while (left < right) {
			result = Math.max(result, getArea(left, right, heights));
			
			if (heights[left] < heights[right]) {
				left++;
			}
			else {
				right--;
			}
		}
		
		return result;
	}
	
	// helper method
	private int getArea(int i, int j, int[] heights) {
		return (j - i) * Math.min(heights[i], heights[j]);
	}
}