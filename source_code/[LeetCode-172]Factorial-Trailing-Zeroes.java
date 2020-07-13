/***
* LeetCode 172. Factorial Trailing Zeroes
Given an integer n, return the number of trailing zeroes in n!.

Example 1:
    Input: 3
    Output: 0
    Explanation: 3! = 6, no trailing zero.

Example 2:
    Input: 5
    Output: 1
    Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.
***/

/*
* 1! = 1, 2!=2, 3!=6, 4!=24, 5!=120, 6!=720...
* from 5, the factorial result(120) starts to have 1 tailing zero,
* so, this is the Counting Factors of 5 problem to solve.
*/
class Solution {
    public int trailingZeroes(int n) {
        // check corner case
        if (n < 5) {
            return 0;
        }
        
        // regular case
        int result = 0;
        while (n >= 5) {
            result += n / 5;
            n /= 5;
        }
        
        return result;
    }
}
