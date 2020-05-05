/***
* LintCode 479. Second Max of Array
Find the second max number in a given array.
Example
	Example1:
		Input: [1, 3, 2, 4], 
		Output: 3.
	Example2:
		Input: [1, 2], 
		Output: 1.
Notice
	You can assume the array contains at least two numbers.
***/
public class Solution {
    /**
     * @param nums: An integer array
     * @return: The second max number in the array.
     */
    public int secondMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > max) {
                secondMax = max;
                max = num;
                continue;
            }
            
            if (num > secondMax) {
                secondMax = num;
            }
        }
        
        return secondMax;
    }
}