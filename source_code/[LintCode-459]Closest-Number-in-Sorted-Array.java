/***
* LintCode. 459 Closet Number in Sorted Array
Given a target number and an integer array A sorted in ascending order, find the index i in A such that A[i] is closest to the given target.

Return -1 if there is no element in the array.

note:
    There can be duplicate elements in the array, and we can return any of the indices with same value.

Example 1:
    Input: [1, 2, 3] , target = 2
    Output: 1.
Example 2:
    Input: [1, 4, 6], target = 3
    Output: 1.
Example 3:
    Input: [1, 4, 6], target = 5,
    Output: 1 or 2.

Challenge
    O(logn) time complexity.

***/
public class Solution {
    /**
     * @param A: an integer array sorted in ascending order
     * @param target: An integer
     * @return: an integer
     */
    public int closestNumber(int[] nums, int target) {
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        if (nums[0] >= target) {
            return 0;
        }
        
        if (nums[nums.length - 1] <= target) {
            return nums.length - 1;
        }
        
        // initialize 
        // tow pointers
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        // afer minimize the scope of two pointers
        if (nums[start] == target) {
            return start;
        }
        
        if (nums[end] == target) {
            return end;
        }
        
        return (Math.abs(target-nums[start]) <= Math.abs(target - nums[end])) ? start : end;
    }
}
