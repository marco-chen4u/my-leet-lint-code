/***
* LeetCode 43. Multiply Strings
Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.

Example:
	Example 1:
		Input: num1 = "2", num2 = "3"
		Output: "6"

	Example 2:
		Input: num1 = "123", num2 = "456"
		Output: "56088"

Note:
	1.The length of both num1 and num2 is < 110.
	2.Both num1 and num2 contain only digits 0-9.
	3.Both num1 and num2 do not contain any leading zero, except the number 0 itself.
	4.You must not use any built-in BigInteger library or convert the inputs to integer directly.
***/

class Solution {
    public String multiply(String num1, String num2) {
        int len1 = num1.length();
        int len2 = num2.length();
        
        int[] values = new int[len1 + len2];
        
        for (int i = len1 - 1; i >= 0; i--) {
            for (int j = len2 - 1; j >= 0; j--) {
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                
                int lowPos = i + j + 1;
                int highPos = i + j;
                
                product += values[lowPos];
                values[lowPos] = product % 10;
                values[highPos] += product / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int value : values) {
            if (sb.length() == 0 && value == 0) {
                continue;
            }
            
            sb.append(value);
        }
        
        String result = sb.length() > 0 ? sb.toString() : "0";
        
        return result;
    }
}
