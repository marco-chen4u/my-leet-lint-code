/***
* LintCode 508. Wiggle Sort
Given an unsorted array nums, reorder it in-place such that

nums[0] <= nums[1] >= nums[2] <= nums[3]....
Example
	Example 1:
		Input: [3, 5, 2, 1, 6, 4]
		Output: [1, 6, 2, 5, 3, 4]
		Explanation: This question may have multiple answers, and [2, 6, 1, 5, 3, 4] is also ok.
	Example 2:
		Input: [1, 2, 3, 4]
		Output: [1, 4, 2, 3]
Noticen
	Please complete the problem in-place.
***/
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
		// check corner case 
		if (isEmpty(nums)) {
			return;
		}
		
		int size = nums.length;
		for (int i = 0; i < size; i += 2) {
			if (i > 0 && nums[i - 1] < nums[i]) {
				swap(nums, i - 1, i);
			}
			
			if (i < size - 1 && nums[i] > nums[i + 1]) {
				swap(nums, i, i + 1);
			}
		}
	}
	
	// helper methods
	private boolean isEmpty(int[] nums) {
		return (nums == null || nums.length == 0);
	}
	
	private void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
	}
}