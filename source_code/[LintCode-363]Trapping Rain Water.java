/***
* LintCode 363. Trapping Rain Water
Given n non-negative integers representing an elevation map where the width of each bar is 1, 
compute how much water it is able to trap after raining.

Trapping Rain Water

Example
	Example 1:
		Input: [0,1,0]
		Output: 0
	Example 2:
		Input: [0,1,0,2,1,0,1,3,2,1,2,1]
		Output: 6

Challenge
	O(n) time and O(1) memory
	O(n) time and O(n) memory is also acceptable.
***/
public class Solution {
    /**
     * @param heights: a list of integers
     * @return: a integer
     */
    public int trapRainWater(int[] heights) {
        int result = 0;
        // check corner case
        if (heights == null || heights.length == 0) {
            return result;
        }
        
        int left = 0, right = heights.length - 1;
        int leftHeight = heights[left], rightHeight = heights[right];
        
        while (1 + left < right) {
            if (leftHeight < rightHeight) {
                left++;
                
                if (leftHeight < heights[left]) {
                    leftHeight = heights[left];
                }
                else {
                    result += leftHeight - heights[left];
                }
            }
            else {
                right--;
                
                if (rightHeight < heights[right]) {
                    rightHeight = heights[right];
                }
                else {
                    result += rightHeight - heights[right];
                }
            }
        }
        
        return result;
    }
}