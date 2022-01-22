/***
* LeetCode 153. Find Minimum in Rotated Sorted Array
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
(i.e.,  [0,1,2,4,5,6,7] might become  [4,5,6,7,0,1,2]).
Find the minimum element.

You may assume no duplicate exists in the array.

Example 1:
    Input: [3,4,5,1,2] 
    Output: 1

Example 2:
    Input: [4,5,6,7,0,1,2]
    Output: 0
***/
//version-1: binary search
class Solution {
    public int findMin(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int size = nums.length;
        if (size == 1) {
            return nums[0];
        }
        
        int start = 0;
        int end = size - 1;
        
        while (start < end) {
            int mid = start + (end - start) / 2;
            int pivot = nums[end];
            
            if (nums[mid] > pivot) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        
        return nums[start];
    }
}

//version-2: binary search
class Solution {
    public int findMin(int[] nums) {
        // check corner case 
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        int pivot = nums[end];
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] >= pivot) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        return Math.min(nums[start], nums[end]);
    }
}
