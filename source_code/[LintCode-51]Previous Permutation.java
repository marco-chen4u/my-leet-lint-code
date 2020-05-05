/***
* LintCode 51. Previous Permutation
Given a list of integers, which denote a permutation.
Find the previous permutation in ascending order.

Example
	Example 1:
		Input:[1]
		Output:[1]

	Example 2:
		Input:[1,3,2,3]
		Output:[1,2,3,3]

	Example 3:
		Input:[1,2,3,4]
		Output:[4,3,2,1]

Notice
	The list may contains duplicate integers.
***/
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public List<Integer> previousPermuation(List<Integer> nums) {
        List<Integer> result = nums;
        
        // check corner case
        if (nums == null || nums.isEmpty() || nums.size() <= 1) {
            return result;
        }
        
        // normal case
        int size = nums.size();
        int lastPos = size - 1;
        
        int index = lastPos;
        while (index > 0 && nums.get(index - 1) <= nums.get(index)) {
            index--;
        }
        
        reverse(nums, index, lastPos);
        
        if (index != 0) {
            int pivotIndex = index - 1;
            int pivot = nums.get(index - 1);
            
            int i = index;
            while (i < size && nums.get(i) >= pivot) {
                i++;
            }
            
            swapItem(nums, i, pivotIndex);
        }
        
        return result;
    }
    
    // helper method
    private void reverse(List<Integer> nums, int left, int right) {
        while (left < right) {
            swapItem(nums, left, right);
            
            left++;
            right--;
        }
    }
    
    private void swapItem(List<Integer> nums, int i, int j) {
        if (i == j) {
            return;
        }
        
        int tmp = nums.get(i);
        nums.set(i, nums.get(j));
        nums.set(j, tmp);
    }
}