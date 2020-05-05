/***
* LintCode 52. Next Permutation
Given a list of integers, which denote a permutation.
Find the next permutation in ascending order.

Example
	Example 1:
		Input:[1]
		Output:[1]

	Example 2:
		Input:[1,3,2,3]
		Output:[1,3,3,2]

	Example 3:
		Input:[4,3,2,1]
		Output:[1,2,3,4]

Notice
	The list may contains duplicate integers.
***/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        int[] result = nums;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        // normal case
        int size = nums.length;
        int lastPos = size - 1;
        int index = lastPos;
        while (index > 0 && nums[index - 1] >=nums[index]) {
            index--;
        }
        
        reverse(nums, index, lastPos);
        
        if (index != 0) {
            int pivotIndex = index - 1;
            int pivot = nums[index - 1];
            
            int i = index;
            while (i < size && nums[i] <= pivot) {
                i++;
            }
            
            swapItem(nums, pivotIndex, i);
        }
        
        return result;
    }
    
    // helper methods
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swapItem(nums, left, right);
            
            left++;
            right--;
        }
    }
    
    private void swapItem(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}