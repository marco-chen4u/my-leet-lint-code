/***
* LintCode 144. Interleaving Positive and Negative Numbers
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

Example
	Given [-1, -2, -3, 4, 5, 6], after re-range, 
	it will be [-1, 5, -2, 4, -3, 6] or any other reasonable answer.

Challenge
	Do it in-place and without extra memory.

Notice
	You are not necessary to keep the original order of positive integers or negative integers.
***/
public class Solution {
    /*
     * @param A: An integer array.
     * @return: nothing
     */
    public void rerange(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        // sort
        Arrays.sort(nums);
        
        // initialize the two pointers
        int i = 0;
        int j = nums.length - 1;
        
        // find out how many positive and negative numbers first
        int count = 0;
        for (int num : nums) {
            count += (num > 0) ? 1 : -1;
        }
        // adjust the starting of two pointer, so as to make the interleaving positive and negative even 
        if (count < 0) {
            i++;
        }
        else if (count > 0 ){
            j--;
        }
        
        while (i <= j) {
            if (nums[i] < 0 && nums[j] > 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            
            i += 2;
            j -= 2;
        }
    }
}