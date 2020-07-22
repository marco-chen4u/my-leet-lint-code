/***
* LintCode 1325. Bitwise AND of Numbers Range
Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4.

Example
    Example1
        Input: m=5, n=7
        Output: 4

    Example2
        Input: m=14, n=15
        Output: 14
***/
public class Solution {
    /**
     * @param m: an Integer
     * @param n: an Integer
     * @return: the bitwise AND of all numbers in [m,n]
     */
    public int rangeBitwiseAnd(int m, int n) {
        int posIndex = 0;// from right to left
        
        while (m != n) {
            m = m >> 1;// right shit by 1 which means being divided by 2,original number will remove last 1 digit
            n = n >> 1;
            
            posIndex++;// the lower digit position will move forward(from right to left) by 1, until to find the common higher 1s
        }
        
        return m << posIndex;// n << posIndex
    }
}
