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
        // corner case
        if (heights == null || heights.length <= 1) {
            return result;
        }

        // regular case
        int size = heights.length;
        int left = 0;
        int leftHeight = heights[left];
        int right = size - 1;
        int rightHeight = heights[right];

        while (left + 1 < right) {
            if (leftHeight < rightHeight) {//moving the left part
                
                left++;

                if (leftHeight > heights[left]) {
                    result += leftHeight - heights[left];
                }
                else {
                    leftHeight = heights[left];
                }

                continue;
            }

            if (leftHeight >= rightHeight) {// moving the right part

                right--;

                if (rightHeight > heights[right]) {
                    result += rightHeight - heights[right];
                }
                else {
                    rightHeight = heights[right];
                }


                continue;
            }
        }

        return result;
    }
}
