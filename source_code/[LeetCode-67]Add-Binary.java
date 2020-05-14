/***
*LeetCode 67. Add Binary
Given two binary strings, return their sum (also a binary string).

The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
    Input: a = "11", b = "1"
    Output: "100"

Example 2:
    Input: a = "1010", b = "1011"
    Output: "10101"

Constraints:
    Each string consists only of '0' or '1' characters.
    1 <= a.length, b.length <= 10^4
    Each string is either "0" or doesn't contain any leading zero.
***/
//version-1
class Solution {
    public String addBinary(String a, String b) {
        
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        
        for (int i = a.length() -1, j = b.length() - 1;
            i >= 0 || j >= 0;
            i--, j--) {
            
            int num1 = (i >= 0) ? a.charAt(i) - '0' : 0;
            int num2 = (j >= 0) ? b.charAt(j) - '0' : 0;
            
            int sum = num1 + num2 + carry;
            int value = sum % 2;
            carry = sum / 2;
            
            sb.insert(0, value);
            
        }
        
        //System.out.println("carry = " + carry);
        
        sb.insert(0, carry == 1 ? "1" : "");
        
        return sb.toString();
    }
}
