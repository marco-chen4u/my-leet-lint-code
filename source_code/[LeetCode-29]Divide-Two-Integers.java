/***
* LeetCode 29. Divide Two Integers
Given two integers dividend and divisor, divide two integers without using multiplication, division, and mod operator.

The integer division should truncate toward zero, which means losing its fractional part. 
For example, 8.345 would be truncated to 8, and -2.7335 would be truncated to -2.

Return the quotient after dividing dividend by divisor.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−2^31, 2^31 − 1]. 
For this problem, if the quotient is strictly greater than 2^31 - 1, then return 2^31 - 1, 
and if the quotient is strictly less than -2^31, then return -2^31.

Example 1
    Input: dividend = 10, divisor = 3
    Output: 3
    Explanation: 10/3 = 3.33333.. which is truncated to 3.
    
Example 2
    Input: dividend = 7, divisor = -3
    Output: -2
    Explanation: 7/-3 = -2.33333.. which is truncated to -2.
    
Constraints:
    -2^31 <= dividend, divisor <= 2^31 - 1
    divisor != 0
    
* LintCode link: https://www.lintcode.com/problem/414/
* LeetCode link: https://leetcode.com/problems/divide-two-integers/
***/

class Solution {
    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 1) {
            return dividend;
        }
        
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        

        boolean isNegative = dividend > 0 && divisor < 0 || 
                                dividend < 0 && divisor > 0;
        
        long a = Math.abs((long)dividend);// note: this is conversion is really important, [a = (long)Math.abs(dividend)] it is not working, think diff.
        long b = Math.abs((long)divisor);

        int result = 0;
        while (a >= b) {
            int shiftPos = 1;
            while (a >= (b << shiftPos)) {
                shiftPos ++;
            }
            
            a -= b << (shiftPos - 1);
            result += 1 << (shiftPos - 1);
        }

        return isNegative ? - result : result;
    }
}
