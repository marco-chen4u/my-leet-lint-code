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
// version-3 heap sort
public class Solution {
    
    // helper methods
	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	private void heapify(int[] nums, int heapSize, int i) {
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		
		int largest = i;
		
		if (left < heapSize && nums[left] > nums[largest]) {
			largest = left;
		}
		
		if (right < heapSize && nums[right] > nums[largest]) {
			largest = right;
		}
		
		if (largest != i) {
			swap(nums, i, largest);
			heapify(nums, heapSize, largest);
		}
	}
	
	private void buildHeap(int[] nums, int heapSize) {
		for (int i = nums.length / 2; i >= 0; i--) {
			heapify(nums, heapSize, i);
		}
	}
    
	private void heapSort(int[] nums) {
		int heapSize = nums.length;
		buildHeap(nums, heapSize);
		
		while (heapSize > 1) {
		    swap(nums, 0, heapSize -1);
		    heapSize--;
		    heapify(nums, heapSize, 0);
		}
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
        
        heapSort(nums);
    }
}