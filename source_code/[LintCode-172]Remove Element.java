/***
* LintCode 172. Remove Element
Given an array and a value, remove all occurrences of that value in place and return the new length.
The order of elements can be changed, and the elements after the new length don't matter.

Example
Example 1:
	Input: [], value = 0
	Output: 0

Example 2:
	Input:  [0,4,4,0,0,2,4,4], value = 4
	Output: 4	
	Explanation: 
	the array after remove is [0,0,0,2]

***/
//version-1
public class Solution {
    /*
     * @param A: A list of integers
     * @param elem: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] nums, int value) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = nums.length;
        int left = 0; 
        int right = size - 1;
        
        while (left <= right) {
            if (nums[left] == value) {
                nums[left] = nums[right];
                right--;
            }
            else {
                left++;
            }
        }
        
        return right + 1;
    }
}

//version-2
public class Solution {
    /*
     * @param nums: A list of integers
     * @param value: An integer
     * @return: The new length after remove
     */
    public int removeElement(int[] nums, int value) {
        int result = 0;
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        
        while (left <= right) {
            if (nums[left] == value) {
                nums[left] = nums[right];
                right--;
                continue;
            }
            
            left++;
        }
        
        result = right + 1;
        
        return result;
    }
}