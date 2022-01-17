/***
* LintCode 457. Classical Binary Search
Find any position of a target number in a sorted array. Return -1 if target does not exist.

Example 1:
    Input: nums = [1,2,2,4,5,5], target = 2
    Output: 1 or 2

Example 2:
    Input: nums = [1,2,2,4,5,5], target = 6
    Output: -1

Example 3:
    Input: nums = [], target = 4
    Output: -1
***/
//version-1: traditional binary seach
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // regular case
        int start = 0;
        int end = nums.length -1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }
}

//version-2: recursion
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start + 1 >= end) {
            if (nums[start] == target) {
                return start;
            }

            if (nums[end] == target) {
                return end;
            }

            return -1;
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] < target) {
            return binarySearch(nums, mid, end, target);
        }

        if (nums[mid] >= target) {
            return binarySearch(nums, start, mid, target);
        }

        return -1;
    }
}

//version-3: recursion
public class Solution {
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        }

        if (nums[mid] < target) {
            return binarySearch(nums, mid, end, target);
        }
        else {
            return binarySearch(nums, start, mid, target);
        }
    }
}
