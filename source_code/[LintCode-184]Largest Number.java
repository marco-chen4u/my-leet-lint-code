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
                int i = 0, j = 0;
                while (i < a.length() && j < b.length()) {
                    if (a.charAt(i) != b.charAt(j)) {
                        return b.charAt(j) - a.charAt(i);
                    }
                    else {
                        i++;
                        j++;
                        
                        if (i == a.length() && j == b.length()) {
                            break;
                        }
                        
                        if (i == a.length()) {
                            i--;
                        }
                        
                        if (j == b.length()) {
                            j--;
                        }
                    }
                }
                
                return 0;
            }
        };
        
        Arrays.sort(values, comparator);
        
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (/*value.equals("0") &&*/ (sb.toString().equals("0"))) {
                continue;
            }
            
            sb.append(value);
        }
        
        result = sb.toString();
        
        return result;
    }
}