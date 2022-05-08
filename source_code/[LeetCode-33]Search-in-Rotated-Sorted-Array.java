/***
* LeetCode 33. Search in Rotated Sorted Array
There is an integer array nums sorted in ascending order (with distinct values).

Prior to being passed to your function, 
nums is rotated at an unknown pivot index k (0 <= k < nums.length) 
such that the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed). 

For example, [0,1,2,4,5,6,7] might be rotated at pivot index 3 and become [4,5,6,7,0,1,2].

Given the array nums after the rotation and an integer target, 
return the index of target if it is in nums, or -1 if it is not in nums.
 

Example 1:
    Input: nums = [4,5,6,7,0,1,2], target = 0
    Output: 4

Example 2:
    Input: nums = [4,5,6,7,0,1,2], target = 3
    Output: -1

Example 3:
    Input: nums = [1], target = 0
    Output: -1


Constraints:
    1 <= nums.length <= 5000
    -10^4 <= nums[i] <= 10^4
    All values of nums are unique.
    nums is guaranteed to be rotated at some pivot.
    -10^4 <= target <= 10^4
 

Follow up: Can you achieve this in O(log n) time complexity?

Link: https://leetcode.com/problems/search-in-rotated-sorted-array/
***/
//version-1
class Solution {
    public int search(int[] nums, int target) {
        // check corner case 
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int size = nums.length;
        int lastPos = size - 1;
        int start = 0;
        int end = lastPos;
        int bottomPos = findBottomPos(nums);
        int peakPos = bottomPos== start ? lastPos : bottomPos - 1;
        
        int left = nums[0];
        int peak = nums[peakPos];
        int bottom = nums[bottomPos];
        int right = nums[lastPos];
        
        if (target >= bottom && target <= right) {
            return search(nums, bottomPos, end, target);
        }
        
        if (target >= left && target <= peak) {
            return search(nums, start, peakPos, target);
        }
        
        return -1;

    }
    
    // helper methods
    private int findBottomPos(int[] nums) {
        int size = nums.length;
        int lastPos = size - 1;
        int start = 0;
        int end = lastPos;
        
        int pivot = nums[lastPos];
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] > pivot) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] <= pivot) {
            return start;
        }
        
        if (nums[end] <= pivot) {
            return end;
        }
        
        return -1;
    }
    
    private int search(int[] nums, int start, int end, int target) {
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

//version-2
class Solution {
    public int search(int[] nums, int target) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length -1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[start] < nums[mid]) {//ascending part
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
            else { //gap part
                if (nums[mid] <= target && target <= nums[end]) {
                    start = mid;
                }
                else {
                    end = mid;
                }
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

//version-3: binary search + pivot with hte last element
public class Solution {
    /**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        int pivot = nums[right];

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] >= pivot) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        if (nums[left] <= pivot) {
            return nums[left];
        }

        if (nums[right] <= pivot) {
            return nums[right];
        }

        return -1;
    }
}
