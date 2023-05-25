/***
* LeetCode 1175. Prime Arrangements
Return the number of permutations of 1 to n so that prime numbers are at prime indices (1-indexed.)

(Recall that an integer is prime if and only if it is greater than 1, 
and cannot be written as a product of two positive integers both smaller than it.)

Since the answer may be large, return the answer modulo 10^9 + 7.

Example 1:
    Input: n = 5
    Output: 12
    Explanation: For example [1,2,5,4,3] is a valid permutation, but [5,2,3,4,1] is not because the prime number 5 is at index 1.

Example 2:
    Input: n = 100
    Output: 682289015

Constraints:
    1 <= n <= 100

* LeetCode link: https://leetcode.com/problems/prime-arrangements/
 ***/
/*
* main idea:
  in number n, if there m prime in this number scope,
  the the count of this permutation would be (m)! * (n - m) !
  
  (m)! : these m prime numbers could do what they could do the position arrangement in each valid position
  
  (n - m)! : this none prime numbers could do they could place, it's the total count arrangment of permutation
  
  there 2 step to finish those permutation, so it's (m)! * (n - m) !
*/

// this problem is actually the variance of the counting prime numbers
class Solution {
    private static int MOD = 1_000_000_007;
    public int numPrimeArrangements(int n) {

        if (n == 1) {
            return 1;
        }

        int count = 0;
        for (int i = 2; i <=n; i++) {
            if (!isPrime(i)) {
                continue;
            }

            count += 1;
        }

        System.out.println("count = " + count);

        long x = getFactoria(count);
        long y = getFactoria(n - count);

        long result = x * y % MOD;

        return (int)result;
    }

    private boolean isPrime(int num) {
        if (num == 2) {
            return true;
        }

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private long getFactoria(long num) {
        if (num <= 1) {
            return num;
        }

        long result = 1;
        for (long i = 1; i <= num; i++) {
            result *= i;
            result %= MOD;
        }

        return result % MOD;
    }
}
