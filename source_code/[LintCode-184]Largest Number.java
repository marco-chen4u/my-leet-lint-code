/***
* LintCode 184. Largest Number
Given a list of non negative integers, arrange them such that they form the largest number.

Example
    Example 1:
        Input:[1, 20, 23, 4, 8]
        Output:"8423201"

    Example 2:
        Input:[4, 6, 65]
        Output:"6654"

Challenge
    Do it in O(nlogn) time complexity.

Notice
    The result may be very large, so you need to return a string instead of an integer.
***/

public class Solution {
    /**
     * @param nums: A list of non negative integers
     * @return: A string
     */
    public String largestNumber(int[] nums) {
        String result = null;
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = String.valueOf(nums[i]);
        }
        
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                String ab = a + b;
		String ba = b + a;
		return ba.compareTo(ab);
            }
        };
        
        Arrays.sort(values, comparator);
        
        if ("0".equals(values[0])) {
	    return "0";
	}
        
        result = String.join("", values);
        
        return result;
    }
}
