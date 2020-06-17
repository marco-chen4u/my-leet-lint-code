/***
* LeetCode 154. Find Minimum in Rotated Sorted Array II
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).

Find the minimum element.

The array may contain duplicates.

Example 1:
    Input: [1,3,5]
    Output: 1

Example 2:
    Input: [2,2,2,0,1]
    Output: 0

Note:
    This is a follow up problem to Find Minimum in Rotated Sorted Array.
    Would allow duplicates affect the run-time complexity? How and why?
***/
//version-1: brute force
class Solution {
    public int findMin(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int min = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
        }
        
        return min;
    }
}

//verion-2: recursion
class Solution {
    // fields
    private final int DEFAULT_VALUE = -1;
    
    public int findMin(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return DEFAULT_VALUE;
        }
        
        return findMin(nums, 0, nums.length - 1);
    }
    
    // helper method
    private int findMin(int[] nums, int start, int end) {
        // check corner case
        if (start == end) {
            return nums[start];
        }
        
        int mid = start + (end - start) / 2;
        int pivot = nums[end];
        
        if (nums[mid] > pivot) {
            return findMin(nums, mid + 1, end);
        }
        
        if (nums[mid] < pivot) {
            return findMin(nums, start, mid);
        }
        
        if (nums[mid] == pivot) {
            return Math.min(findMin(nums, start, mid), 
                            findMin(nums, mid + 1, end));
        }
        
        return DEFAULT_VALUE;
    }
}
