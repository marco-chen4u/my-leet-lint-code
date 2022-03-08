/**
* LintCode 767. Reverse Array
Reverse the given array nums inplace.

Example 1:
    Input : nums = [1,2,5]
    Output : [5,2,1]
**/
public class Solution {
    /**
     * @param nums: a integer array
     * @return: nothing
     */
    public void reverseArray(int[] nums) {
        // corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        int size = nums.length;
        int left = 0;
        int right = size - 1;

        while (left < right) {
            swap(nums, left, right);
            left ++;
            right--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
