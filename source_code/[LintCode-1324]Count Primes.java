/***
* LintCode 1324. Count Primes
Count the number of prime numbers less than a non-negative number, n.

Example
	Example 1：
		Input: n = 2
		Output: 0

	Example 2：
		Input: n = 4
		Output: 2
		Explanation：2, 3 are prime number
***/
public class Solution {
    /**
     * @param n: a integer
     * @return: return a integer
     */
    public int countPrimes(int n) {
        int count = 0;
        // check corner case
        if (n <= 2) {
            return count;
        }
        
        for (int i = 2; i < n; i++) {
            count += isPrimeNumber(i) ? 1 : 0;
        }
        
        return count;
    }
    
    // helper method
    private boolean isPrimeNumber(int value) {
        int start = 2;
        int end = (int)(Math.sqrt(value));
        
        for (int i = start; i <= end; i++) {
            if (value % i == 0) {// shold always equals 1
                return false;
            }
        }
        
        return true;
    }
}