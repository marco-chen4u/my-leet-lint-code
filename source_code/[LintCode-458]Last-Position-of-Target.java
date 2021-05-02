/***
* LintCode. 458 Last Position of Target
Find the last position of a target number in a sorted array. Return -1 if target does not exist.

Example 1:
    Input: nums = [1,2,2,4,5,5], target = 2
    Output: 2
Example 2:
    Input: nums = [1,2,2,4,5,5], target = 6
    Output: -1
***/
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int lastPosition(int[] nums, int target) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }
}
