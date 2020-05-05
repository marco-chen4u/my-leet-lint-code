/***
* LintCode 54. String to Integer (atoi)
Implement function atoi to convert a string to an integer.
If no valid conversion could be performed, a zero value is returned.
If the correct value is out of the range of representable values, 
INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.
Example
    Example 1
        Input: "10"
        Output: 10
    
    Example 2
        Input: "1"
        Output: 1
    
    Example 3
        Input: "123123123123123"
        Output: 2147483647
        Explanation: 123123123123123 > INT_MAX, so we return INT_MAX
    
    Example 4
        Input: "1.0"
        Output: 1
        Explanation: We just need to change the first vaild number
***/
public class Solution {
    public int atoi(String str) {
        double result = 0;
        int maxValue = Integer.MAX_VALUE;
        int minValue = Integer.MIN_VALUE;
        // check corner case
        str = str.trim();
        if (str == null || str.length() == 0) {
            return 0;
        }
        
        int startIndex = 0;
        int i = 0;
        boolean isNegative = false;
        if (str.charAt(i) == '-' || str.charAt(i) == '+') {
            startIndex++;
            isNegative = (str.charAt(i) == '-') ? true : false;
        }
        
        for (i = startIndex; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                break;
            }
            
            result = result * 10 + (str.charAt(i) - '0');
        }
        
        if (result > maxValue && isNegative) {
            return minValue;
        }        
        if (result > maxValue) {
            return maxValue;
        }
        
        if (isNegative) {
            result = result * -1;
        }
        
        return (int) result;
    }
}