/**
* LintCode 413. Reverse Integer
Reverse digits of an integer. Returns 0 when the reversed integer overflows 32-bit integer.

Example 1:
    Input: 123
    Output: 321

Example 2:
    Input: -123
    Output: -321
**/
//version-1: simulation, calculation
public class Solution {
    /**
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        // check corner case- single digit number
        if (n > -10 && n < 10) {
            return n;
        }

        boolean isLimit = n > Integer.MAX_VALUE;
        System.out.println("isLimit=" + isLimit);
        
        int result = 0;
        
        while (n != 0) {
            int temp = result * 10 + n % 10;
            
            n = n / 10;
            
            if (temp / 10 != result) {
                result = 0;
                break;
            }
            
            result = temp;
        }
        
        return result;
    }
}
//version-2: use long to avoid interger type max/min value over stack issue, simulation
public class Solution {
    /**
     * @param n: the integer to be reversed
     * @return: the reversed integer
     */
    public int reverseInteger(int n) {
        long result = 0;
        while(n != 0) {
            result = result * 10 + n % 10;
            n = n / 10;
        }
      
        if (result < Integer.MIN_VALUE || 
            result > Integer.MAX_VALUE) {
            return 0;
        }
        
        return (int)result;
    }
}
