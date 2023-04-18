/***
* LintCode 1324. Count Primes
Count the number of prime numbers less than a non-negative number, n.

Example 1：
    Input: n = 2
    Output: 0

Example 2：
    Input: n = 4
    Output: 2
    Explanation：2, 3 are prime number
        
LintCode link: https://www.lintcode.com/problem/1324/
LeetCode link: https://leetcode.com/problems/count-primes/
***/

//version-1: prime definition to calculation, but the time space is O(nlogn)
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
        
        // regualur case
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

//version-2: ieve of Eratosthenes(https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes),time complexity O(nloglogn)=> O(n), space complexity: O(n)
class Solution {
    public int countPrimes(int n) {
        // check corner case
        if (n <= 2) {
            return 0;
        }
        
        // regular case
        boolean[] dp = new boolean[n];
        Arrays.fill(dp, true);

        dp[0] = false;
        dp[1] = false;

        int count = 0;
        for (long i = 2; i < n; i++) {
            if (!dp[(int)i]) {
                continue;
            }

            count+=1;// if current dp[i] is a prime
                     // then it's times * i should not be prime
            for (long j = i * i; j < n; j+= i) {/* mark the times of i value is not prime */
                dp[(int)j] = false;
            }

        }

        return count;
    }
    
}
