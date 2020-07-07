/***
* LintCode 400. Maximum Gap
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
Return 0 if the array contains less than 2 elements.

Example
    Example 1:
        Input: [1, 9, 2, 5]
        Output: 4
        Explanation: The sorted form is [1, 2, 5, 9], and the maximum gap is between 5 and 9.
    Example 2:
        Input: [1]
        Output: 0
        Explanation: The array contains less than 2 elements.

Challenge
    Sort is easy but will cost O(nlogn) time. Try to solve it in linear time and space.

Notice
    You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
***/
//version-1: sort and cost O(nlogn) time
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        int maxGap = 0;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return maxGap;
        }
        
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 1; i < size; i++) {
            int diff = nums[i] - nums[i - 1];
            
            maxGap = Math.max(maxGap, diff);
        }
        
        return maxGap;
    }
}

//version-2: O(n)
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        
    }
}
