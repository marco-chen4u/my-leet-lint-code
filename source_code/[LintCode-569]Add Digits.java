/***
* LintCode 569. Add Digits
Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example
	Example 1:
		Input:
			num=38
		Output:
			2
		Explanation:
			The process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return 2.

	Example 2:
		Input:
			num=9
		Output:
			9
		Explanation:
			9<10,return 9.

Challenge
	Could you do it without any loop/recursion in O(1) runtime?
***/
//version-1: Recursion
public class Solution {
    /**
     * @param num: a non-negative integer
     * @return: one digit
     */
    public int addDigits(int num) {
        // check corner case
        if (num <= 9 && num >= 0) {
            return num;
        }
        
        int value = 0;
        for (char ch : Integer.toString(num).toCharArray()) {
            value += Character.getNumericValue(ch);
        }
        
        return addDigits(value);
    }
}

//version-2: loop
public class Solution {
    /**
     * @param num: a non-negative integer
     * @return: one digit
     */
    public int addDigits(int num) {
        // check corner case
        if (num <= 9 && num >= 1) {
            return num;
        }
        
        while (num >= 10) {
            int value = 0;
            
            while (num > 0) {
                value += num %10;
                num /= 10;
            }
            
            num = value;
        }
        
        return num;
    }
}