/***
* LintCode 609. Two Sum - Less Than or Equal To Target
Given an array of integers, find how many pairs in the array such that their sum is less than or equal to a specific target number. P
lease return the number of pairs.

Example 1
    Input: nums = [2, 7, 11, 15], target = 24. 
    Output: 5. 
    Explanation:
        2 + 7 < 24
        2 + 11 < 24
        2 + 15 < 24
        7 + 11 < 24
        7 + 15 < 24

Example 2
    Input: nums = [1], target = 1. 
    Output: 0. 

Link:
    https://www.lintcode.com/problem/609/
***/
// solution-1: two pointers
public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: an integer
     * @return: an integer
     */
    public int twoSum5(int[] nums, int target) {
        // write your code here
        int count = 0;
        if (nums == null || nums.length <= 1) {
            return count;
        }

        Arrays.sort(nums);

        int size = nums.length;
        int left = 0;
        int right = size - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum > target) {
                right--;
                continue;
            }

            if (sum <= target) {
                count += right - left;
                left ++;
            }
        }

        return count;
    }
}
