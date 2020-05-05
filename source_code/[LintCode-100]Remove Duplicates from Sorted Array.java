/***
* LintCode 100. Remove Duplicates from Sorted Array
Given a sorted array, 'remove' the duplicates in place such that each element appear only once and return the 'new' length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Example
	Example 1:
		Input:  []
		Output: 0

	Example 2:
		Input:  [1,1,2]
		Output: 2	
		Explanation:  uniqued array: [1,2]
***/
//version-1: two pointers
public class Solution {
    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length <=1) {
            return nums.length;
        }
        
        int size = nums.length;
        int left = 0;
        int right = 0;
        
        while (right < size) {
            while (right < size && nums[right] == nums[left]) {
                right++;
            }
            
            if (right < size && nums[left] != nums[right]) {
                left++;
                nums[left] = nums[right];
            }
            
            right += (right < size) ? 1 : 0;
        }
        
        return left + 1;
    }
}

//version-2: two pointers
public class Solution {
    /*
     * @param nums: An ineger array
     * @return: An integer
     */
    public int removeDuplicates(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length <= 1) {
            return nums.length;
        }
        
        // normal case
        int size = nums.length;
        int left = 0;
        int right;
        for (right = 0; right < size; right++) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
        }
        
        return left + 1;
    }
}
