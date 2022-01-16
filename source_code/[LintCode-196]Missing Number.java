/***
* LintCode 196. Missing Number
Given an array contains N numbers of 0 .. N, find which number doesn't exist in the array.

Example 1:
    Input:[0,1,3]
    Output:2
	
Example 2:
    Input:[1,2,3]
    Output:0
	
Challenge
    Do it in-place with O(1) extra memory and O(n) time.
***/

//version-1: enumeration
public class Solution {
    // constant
    private final int DEFUALT_VALUE = -1;

    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        int result = DEFUALT_VALUE;

        int size = nums.length;
        int index = 0;

        Arrays.sort(nums);

        for (int i = 0; i < size; i++) {
            if ((nums[i] ^ index) != 0) {
                result = index;
                break;
            }

            index++;
        }

        result = (result == DEFUALT_VALUE) ? size : result;

        return result;
    }
}

//version-2: 
public class Solution {
    /**
     * @param nums: An array of integers
     * @return: An integer
     */
    public int findMissing(int[] nums) {
        int size = nums.length;
        int sum = 0;
        int index = 0;

	for (int num : nums) {
            sum += index - num;

            index++;
        }

        int result = sum + index;

        return result;
    }
}
