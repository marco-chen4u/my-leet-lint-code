/***
* LintCode 148. Sort Colors (3 pointers)
Given an array with n objects colored red, white or blue, 
sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Example
	Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].

Challenge
	A rather straight forward solution is a two-pass algorithm using counting sort.
	First, iterate the array counting number of 0's, 1's, and 2's, 
		then overwrite array with total number of 0's, then 1's and followed by 2's.

	Could you come up with an one-pass algorithm using only constant space?

Notice
	You are not suppose to use the library's sort function for this problem.
	You should do it in-place (sort numbers in the original array).
***/
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0;
        int i = 0;
        int right = nums.length - 1;
        int pivot = 1;//the midle value of the color value[0, 1, 2]
        while (i <= right) {
            if (nums[i] < pivot) {
                swap(nums, left, i);
                left++;
                i++;
            }
            else if (nums[i] == pivot) {
                i++;
            }
            else {
                swap(nums, i, right);
                right--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}