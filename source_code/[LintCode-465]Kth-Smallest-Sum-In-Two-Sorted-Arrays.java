/***
* LintCode 465 · Kth Smallest Sum In Two Sorted Arrays
Given two integer arrays sorted in ascending order and an integer k. 
Define sum = a + b, where a is an element from the first array and b is an element from the second one. 
Find the kth smallest sum out of all possible sums.

Example 1
    Input:
        a = [1, 7, 11]
        b = [2, 4, 6]
        k = 3
    Output: 7
    Explanation: The sums are [3, 5, 7, 9, 11, 13, 13, 15, 17] and the 3th is 7.

Example 2
    Input:
        a = [1, 7, 11]
        b = [2, 4, 6]
        k = 4
    Output: 9
    Explanation: The sums are [3, 5, 7, 9, 11, 13, 13, 15, 17] and the 4th is 9.

Example 3
    Input:
        a = [1, 7, 11]
        b = [2, 4, 6]
        k = 8
    Output: 15
    Explanation: The sums are [3, 5, 7, 9, 11, 13, 13, 15, 17] and the 8th is 15.
    
Constraints:
    Do it in either of the following time complexity:
        1. O(k log min(n, m, k)). where n is the size of A, and m is the size of B.
        2. O( (m + n) log maxValue). where maxValue is the max number in A and B.

LintCode link: https://www.lintcode.com/problem/465/
LeetCode link: https://leetcode.com/problems/kth-smallest-product-of-two-sorted-arrays/
***/
/*
*类似于 Kth smallest in sorted matrix 一题。
*使用 minHeap
*我们把行和列分别定为两个数组，就可以形成一个排好序的矩阵
*/
//version-1: BFS
public class Solution {

    private static final int[] directionX = new int[] {1, 0};
    private static final int[] directionY = new int[] {0, 1}; 

    class Pair implements Comparable<Pair>{
        int x;
        int y;
        int sum;

        public Pair(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair other) {
            return this.sum - other.sum;
        }
    }

    /**
     * @param a: an integer arrays sorted in ascending order
     * @param b: an integer arrays sorted in ascending order
     * @param k: An integer
     * @return: An integer
     */
    public int kthSmallestSum(int[] a, int[] b, int k) {
        // write your code here
        if ((a == null || a.length == 0) && (b == null || b.length == 0)) {
            return 0;
        }

        if (a == null || a.length == 0) {
            return b[k];
        }

        if (b == null || b.length == 0) {
            return a[k];
        }

        int n1 = a.length;
        int n2 = b.length;

        Queue<Pair> minHeap = new PriorityQueue<>();

        boolean[][] visited = new boolean[n1][n2];
        
        int x = 0;
        int y = 0;

        Pair current = new Pair(x, y, a[x] + b[y]);
        minHeap.offer(current);
        visited[x][y] = true;

        for (int count = 0; count < k - 1; count++) {
            current = minHeap.poll();
            for (int i = 0; i < 2; i++) {
                int nextX = current.x + directionX[i];
                int nextY = current.y + directionY[i];
                Pair next = new Pair(nextX, nextY, 0);

                if (nextX >= n1 || nextY >= n2 || 
                    visited[nextX][nextY]) {
                    continue;
                }

                next.sum = a[nextX] + b[nextY];

                visited[nextX][nextY] = true;
                minHeap.offer(next);
            }
        }

        return minHeap.peek().sum;
    }
}

//version-2: BFS
public class Solution {

    private static final int[] directionX = new int[] {1, 0};
    private static final int[] directionY = new int[] {0, 1}; 

    class Pair implements Comparable<Pair>{
        int x;
        int y;
        int sum;

        public Pair(int x, int y, int sum) {
            this.x = x;
            this.y = y;
            this.sum = sum;
        }

        @Override
        public int compareTo(Pair other) {
            return this.sum - other.sum;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof Pair)) {
                return false;
            }

            Pair other = (Pair) obj;

            return this.x == other.x && this.y == other.y;
        }

        @Override
        public int hashCode() {
            return this.x * 1237 + this.y;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("x = " + this.x + ", y = " + this.y + ", sum = " + this.sum);
            sb.append("]");

            return sb.toString();
        }
    }

    /**
     * @param a: an integer arrays sorted in ascending order
     * @param b: an integer arrays sorted in ascending order
     * @param k: An integer
     * @return: An integer
     */
    public int kthSmallestSum(int[] a, int[] b, int k) {
        // write your code here
        if ((a == null || a.length == 0) && (b == null || b.length == 0)) {
            return 0;
        }

        if (a == null || a.length == 0) {
            return b[k];
        }

        if (b == null || b.length == 0) {
            return a[k];
        }

        int n1 = a.length;
        int n2 = b.length;

        Queue<Pair> minHeap = new PriorityQueue<>();

        Set<Pair> visited = new HashSet<>();
        
        int x = 0;
        int y = 0;

        Pair current = new Pair(x, y, a[x] + b[y]);
        minHeap.offer(current);
        visited.add(current);

        for (int count = 0; count < k - 1; count++) {
            current = minHeap.poll();
            for (int i = 0; i < 2; i++) {
                int nextX = current.x + directionX[i];
                int nextY = current.y + directionY[i];
                Pair next = new Pair(nextX, nextY, 0);

                if (nextX >= n1 || nextY >= n2 || 
                    visited.contains(next)) {
                    continue;
                }

                next.sum = a[nextX] + b[nextY];

                visited.add(next);
                minHeap.offer(next);
            }
        }

        return minHeap.peek().sum;
    }
}
