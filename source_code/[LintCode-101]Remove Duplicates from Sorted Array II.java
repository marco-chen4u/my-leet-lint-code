/***
* LintCode 101. Remove Duplicates from Sorted Array II
Given a sorted array, remove the duplicates in place such that each element appear at most twice and return the new length.
If a number appears more than two times, then keep the number appears twice in array after remove.

Example
	Example 1:
		Input: []
		Output: 0

	Example 2:
		Input:  [1,1,1,2,2,3]
		Output: 5	
		Explanation: 
			the length is 5: [1,1,2,2,3]

Notice
	Need to operate in the original array
***/
public class Solution {
    /**
     * @param A: a array of integers
     * @return : return an integer
     */
    public int removeDuplicates(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = nums.length;
        int index = 0;
        int count = 1;
        for (int i = 1; i < size; i++) {
            if (nums[i] == nums[index]) {
                if (count < 2) {
                    nums[++index] = nums[i];
                    count++;
                }
                
                continue;
            }
            
            nums[++index] = nums[i];
            count = 1;
        }
        
        return index + 1;
    }
}