/***
* LintCode 62. Search in Rotated Sorted Array
Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Example 1:
    Input:
        array = [4, 5, 1, 2, 3]
        target = 1
    Output:
        2
    Explanation:
        1 is indexed at 2 in the array.
        
Example 2:
    Input:
        array = [4, 5, 1, 2, 3]
        target = 0
    Output:
        -1
    Explanation:
        0 is not in the array. Returns -1.
        
Challenge
    O(logN) time
***/
//version-1: Binary Search, time complexity: O(logn)
public class Solution {
    /**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
          
            // search in 2 parts: (1)ascending part (2)gap part
            
            if (nums[mid] > nums[start]) {//(1) ascending part
                if (nums[start] <= target && target <= nums[mid]) {
                    end = mid;
                }
                else {
                    start = mid;
                }
            }
            else { //(2) gap part
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

//version-2: Binary Search, time complexity: O(logn)
public class Solution {
    /**
     * @param nums: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            int pivot = nums[mid];

            if (isInAscendingPart(nums, start, pivot)) {
                if (isInTheMiddle(nums, start, mid, target)) {
                    end = mid;                    
                }
                else {
                    start = mid;
                }
            }
            else {
                if (isInTheMiddle(nums, mid, end, target)) {
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

    // helper methods
    private boolean isInAscendingPart(int[] nums, int start, int pivot) {
        return pivot > nums[start];
    }

    private boolean isInTheMiddle(int[] nums, int left, int right, int pivot) {
        return nums[left] <= pivot && pivot <= nums[right];
    }
}
