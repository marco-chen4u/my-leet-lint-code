/***
* LintCode 4. Ugly Number II
Ugly number is a number that only have prime factors 2, 3 and 5.
Design an algorithm to find the nth ugly number. 
The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

Example
    Example 1:
        Input: 9
        Output: 10

    Example 2:
        Input: 1
        Output: 1

Challenge
    O(n log n) or O(n) time.

Notice
    Note that 1 is typically treated as an ugly number.
***/
//version-1: itration with MinHeap + HashSet(avoid duplicates), time complexity: O(nlogn) [3n*log3n], space complexity: O(n)
public class Solution {
    /**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    public int nthUglyNumber(int n) {
        // check corner case
        if (n < 1) {
            return 0;
        }
        
        long[] primeFactors = new long[] {2, 3, 5};
        Queue<Long> minHeap = new PriorityQueue<Long>();
        Set<Long> visited = new HashSet<Long>();
        for (long primeFactor : primeFactors) {
            minHeap.offer(primeFactor);
            visited.add(primeFactor);
        }
        
        Long result = Long.valueOf(1);
        for (int i = 2; i <= n; i++) {
            result = minHeap.poll();
            
            for (long primeFactor : primeFactors) {
                long value = result * primeFactor;
                if (!visited.contains(value)) {
                    minHeap.offer(value);
                    visited.add(value);
                }
            }
        }
        
        return result.intValue();
    }
}

//version-2: dp with 3 pointers
public class Solution {
    /**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
        if (n <= 0) {
            return -1;
        }

        // state
        int[] dp = new int[n];
        
        // initialized
        dp[0] = 1;
        // three pointers, pointing to the next candicate number which produce by the multiplying with primes[2, 3, or 5]
        int l2 = 0, l3 = 0, l5 = 0;

        for (int i = 1; i < n; i++) {
            dp[i] = Math.min(Math.min(dp[l2] * 2, dp[l3] * 3), dp[l5] * 5);

            if (dp[i] == dp[l2] * 2) {
                l2++;
            }

            if (dp[i] == dp[l3] * 3) {
                l3++;
            }

            if (dp[i] == dp[l5] * 5) {
                l5++;
            }
        }// for i

        // return the n-th ugly number
        return dp[n - 1];
    }
}
