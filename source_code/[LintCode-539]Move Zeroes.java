/***
* LintCode 539. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example
	Example 1:
		Input: nums = [0, 1, 0, 3, 12],
		Output: [1, 3, 12, 0, 0].

	Example 2:
		Input: nums = [0, 0, 0, 3, 1],
		Output: [3, 1, 0, 0, 0].

Notice
	You must do this in-place without making a copy of the array.
	Minimize the total number of operations.
***/
//version-1
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int index = 0;
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] != 0) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                index += 1;
            }
        }
    }
}
//version-2
public class Solution {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}