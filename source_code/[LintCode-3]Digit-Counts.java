/***
LintCode 3. Digit Counts
Count the number of k's between 0 and n. k can be 0 - 9.

Example 1:
    Input:
        k = 1, n = 1
    Output:
        1
    Explanation:
        In [0, 1], we found that 1 appeared once (1).

Example 2:
    Input:
        k = 1, n = 12
    Output:
        5
    Explanation:
        In [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12], we found that one appeared five times (1, 10, 11, 12)(Note that there are two 1 in 11).

Link: https://www.lintcode.com/problem/digit-counts/
***/
public class Solution {
    /**
     * @param k: An integer
     * @param n: An integer
     * @return: An integer denote the count of digit k in 1..n
     */
    public int digitCounts(int k, int n) {
        int result = 0;
        
        for (int i = 0; i <= n; i++) {
            result += getDigitCount(i, k);
        }
        
        return result;
    }
    
    private int getDigitCount(int num, int k) {
        int count = 0;
        
        if (num == k) {
            return 1;
        }
        
        while (num != 0) {
            int digitValue = num - (num / 10)*10;
            count += (digitValue == k) ? 1 : 0;
            
            num = num / 10;
        }
        
        return count;
    }
}
