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
// version-2 merge sort
public class Solution {
    
    // helper methods
    private void merge(int[] nums, int start, int end, int[] temp) {
        int left= start;
        int mid = start + (end - start) / 2;
        int right = mid + 1;
        
        int index = left;
        while (left <= mid && right <= end) {
            if (nums[left] < nums[right]) {
                temp[index++] = nums[left++];
            }
            else {
                temp[index++] = nums[right++];
            }
        }
        
        while (left <= mid) {
            temp[index++] = nums[left++];
        }
        
        while (right <= end) {
            temp[index++] = nums[right++];
        }
        
        for (int i = start; i <= end; i++) {
            nums[i] = temp[i];
        }
    }
    
    private void mergeSort(int[] nums, int start, int end, int[] temp) {
        if (start >= end) {
            return;
        }
        
        int mid = start + (end - start) / 2;
        
        // divide
        mergeSort(nums, start, mid, temp);
        mergeSort(nums, mid + 1, end, temp);
        
        // conquer
        merge(nums, start, end, temp);
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
        
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
    }
}