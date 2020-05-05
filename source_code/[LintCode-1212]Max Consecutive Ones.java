/***
* LintCode 1212. Max Consecutive Ones
Given a binary array, find the maximum number of consecutive 1s in this array.

Example
Example 1:

Input: [1,1,0,1,1,1]
Output: 3
Explanation: The first two digits or the last three digits are consecutive 1s.
    The maximum number of consecutive 1s is 3.
Example 2:

Input: [1]
Output: 1
Notice
The input array will only contain 0 and 1.
The length of input array is a positive integer and will not exceed 10,000
***/
//version-1
public class Solution {
    /**
     * @param nums: a binary array
     * @return:  the maximum number of consecutive 1s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        int count = 0;
        
        for (int num : nums) {
            if (num == 1) {
                count++;
                
                result = Math.max(result, count);
                continue;
            }
            
            count = 0;
        }
        
        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param nums: a binary array
     * @return:  the maximum number of consecutive 1s
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int result = 0;
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        String numContent = "";
        for (int num : nums) {
            numContent += String.valueOf(num);
        }
        
        String[] strs = numContent.split("0");
        for (String str : strs) {
            if (str == null || str.length() == 0) {
                continue;
            }
            
            int count = str.length();
            
            result = Math.max(result, count);
        }
        
        return result;
    }
}