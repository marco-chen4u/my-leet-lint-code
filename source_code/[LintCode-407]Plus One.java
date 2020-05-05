/***
* LintCode 407. Plus One
Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.

Example
	Example 1:
		Input: [1,2,3]
		Output: [1,2,4]
	Example 2:
		Input: [9,9,9]
		Output: [1,0,0,0]
***/
//version-1
public class Solution {
    /**
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        long value = getValue(digits);
        
        value +=1;
        
        int[] result = getDigits(value);
        
        return result;
    }
    
    // helper methods
    private long getValue(int[] digits) {
        long result = 0;
        for (int digit : digits) {
            result = result * 10 + digit;
        }
        
        return result;
    }
    
    private int[] getDigits(long value) {
        String valueStr = String.valueOf(value);
        int size = valueStr.length();
        int[] result = new int[size];
        
        int index = 0;
        for (char ch : valueStr.toCharArray()) {
            result[index++] = ch - '0';
        }
        
        return result;
    }
    
}

//version-2
public class Solution {
    /**
     * @param digits: a number represented as an array of digits
     * @return: the result
     */
    public int[] plusOne(int[] digits) {
        int[] result = new int[0];
        int size = digits.length;
        
        int carry = 1;
        for (int i = size - 1; i >= 0; i--) {
 
            digits[i] = digits[i] + carry;
            carry = digits[i] / 10;
            
            digits[i] %= 10;
        }
        
        if (carry == 0) {
            return digits;
        }
        
        result = new int[size + 1];
        result[0] = carry;
        for (int i = 1; i < result.length; i++) {
            result[i] = digits[i - 1];
        }
        
        return result;
    }
}