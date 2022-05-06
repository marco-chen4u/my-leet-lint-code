/***
* LintCode 608. Two Sum II - Input array is sorted
Given an array of integers that is already sorted in ascending order, 
find two numbers such that they add up to a specific target number.

The function twoSum should return indices of the two numbers such that they add up to the target, 
where index1 must be less than index2. 
Please note that your returned answers (both index1 and index2) are not zero-based.

Example 1:
    Input: nums = [2, 7, 11, 15], target = 9 
    Output: [1, 2]

Example 2:
    Input: nums = [2,3], target = 5
    Output: [1, 2]

Notice
    You may assume that each input would have exactly one solution.
***/
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: target = nums[index1] + nums[index2]
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[0];
        // check corner cases
        if (nums == null || nums.length == 0 || target < nums[0]) {
            return result;
        }
        
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        while (left < right) {
            int twoSum = nums[left] + nums[right];
            if (twoSum > target) {
                right--;
            }
            else if (twoSum < target) {
                left++;
            }
            else {
                result = new int[] {left + 1, right + 1};
                break;
            }
        }
        
        return result;
    }
}
