/***
* LeetCode 313. Super Ugly Number
A super ugly number is a positive integer whose prime factors are in the array primes.

Given an integer n and an array of integers primes, return the Nth super ugly number.

The nth super ugly number is guaranteed to fit in a 32-bit signed integer.

Example 1
    Input: n = 12, primes = [2,7,13,19]
    Output: 32
    Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first 12 super ugly numbers given primes = [2,7,13,19].
    
Example 2
    Input: n = 1, primes = [2,3,5]
    Output: 1
    Explanation: 1 has no prime factors, therefore all of its prime factors are in the array primes = [2,3,5].

Constraints:
    1 <= n <= 105
    1 <= primes.length <= 100
    2 <= primes[i] <= 1000
    primes[i] is guaranteed to be a prime number.
    All the values of primes are unique and sorted in ascending order.
***/
//version-1: only working for limit N count <= 5000(because of the HaseSet capacity issue)
class Solution {

    public int nthSuperUglyNumber(int n, int[] primes) {

        if (n < 1) {
            return 0;
        }

        Queue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        for (long primeFactor : primes) {
            minHeap.offer(primeFactor);
            visited.add(primeFactor);
        }

        Long current = Long.valueOf(1);
        for (int i = 2; i <= n; i++) {
            current = minHeap.poll();

            for (long primeFactor : primes) {
                long next = current * primeFactor;
                if (visited.contains(next)) {
                    continue;
                }

                minHeap.offer(next);
                visited.add(next);
            }
        }

        int result = current.intValue();

        return result;
        
    }
}

//version-2: minHeap with DP + inner class
class Solution {

    public int nthSuperUglyNumber(int n, int[] primes) {

        if (n < 1) {
            return 0;
        }

        Queue<Element> minHeap = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) {
            minHeap.offer(new Element(primes[i], 1, primes[i]));
        }

        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            dp[i] = minHeap.peek().val;

            int loopCount = 0;
            while (minHeap.peek().val == dp[i]) {
                Element current = minHeap.poll();
                System.out.println("currentIndex = " + current.index);
                int nextVal = current.prime * dp[current.index];
                int nextIndex = current.index + 1;
                int prime = current.prime;
                Element next = new Element(nextVal, nextIndex, prime);
                minHeap.offer(next);
                loopCount++;
            }
            System.out.println(minHeap.toString() + "loopCoutn = " + loopCount);

        }

        return dp[n - 1];
        
    }

    class Element implements Comparable<Element> {

        // fields
        int val;
        int index;
        int prime;

        // constructor
        public Element(int val, int index, int prime) {
            this.val = val;
            this.index = index;
            this.prime = prime;
        }

        @Override
        public int compareTo(Element other) {
            return this.val - other.val;
        }

        public String toString() {
            return String.valueOf(val);
        }
    }
}
