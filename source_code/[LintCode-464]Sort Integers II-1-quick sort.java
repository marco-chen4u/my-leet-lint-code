/***
* LintCode 464. Sort Integers II
Given an integer array, sort it in ascending order. Use quick sort, merge sort, heap sort or any O(nlogn) algorithm.

Example
	Example1:
		Input: [3, 2, 1, 4, 5], 
		Output: [1, 2, 3, 4, 5].

	Example2:
		Input: [2, 3, 1], 
		Output: [1, 2, 3].
***/
public class Solution {
    
    // helper method
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void  quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        
        int left = start;
        int right = end;
        int mid = start + (end - start) / 2;
        int pivot = nums[mid];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                
                left++;
                right--;
            }
        }
        
        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
    
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public void sortIntegers2(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        quickSort(nums, 0, nums.length - 1);
    }
}