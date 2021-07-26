/***
* LintCode 31. Partition Array

***/
//version-1: two pointers, partition the array in-place, time complexity: O(n)
public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        int result = 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        // normal case
        int left = 0;
        int right = nums.length - 1;

        int pivot = k;

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] >= pivot) {
                right--;
            }

            // check corner case
            if (left > right) {
                break;
            }

            // swap values in index-left and inedex-right
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left++;
            right--;
        }

        return left;
    }
}
