/***
* LeetCode 372. Super Pow
Your task is to calculate a^b mod 1337 where a is a positive integer 
and b is an extremely large positive integer given in the form of an array.

Example 1
    Input: a = 2, b = [3]
    Output: 8

Example 2
    Input: a = 2, b = [1,0]
    Output: 1024

Example 3
    Input: a = 1, b = [4,3,3,8,5,2]
    Output: 1

Constraints:
    1 <= a <= 231 - 1
    1 <= b.length <= 2000
    0 <= b[i] <= 9
    b does not contain leading zeros.

LintCode link: https://www.lintcode.com/problem/1275/
LeetCode link: https://leetcode.com/problems/super-pow/
related: LeetCode 5. Pow(x, n) https://leetcode.com/problems/powx-n/
***/
/*
* explanation: https://www.youtube.com/watch?v=BR2Gyr9EvH8&ab_channel=KacyCodes
*/

//version-1: math, time complexity: O(n*logn), *note: n is the length of array-b
class Solution {

    private static final int MOD = 1337;

    public int superPow(int a, int[] b) {

        long value = 1;

        for (int n : b) {
            value = (myPow(value, 10) * myPow((long)a, n) % MOD);
        }
        
        return (int) value;
    }

    // helper method
    private long myPow(long a, int n) {
        long result = 1;
        long product = a;

        while (n != 0) {
            if (n % 2 == 1) {
                result = result * product % MOD;
            }

            product = product * product % MOD;
            n = n / 2;
        }

        return result;
    }
}
