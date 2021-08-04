/***
* LintCode 404. Subarray Sum II (DP & Three Pointers)
Given an positive integer array A and an interval. 
Return the number of subarrays whose sum is in the range of given interval.

Example
    Example 1:
        Input: A = [1, 2, 3, 4], start = 1, end = 3
        Output: 4
        Explanation: All possible subarrays: [1], [1, 2], [2], [3].

    Example 2:
        Input: A = [1, 2, 3, 4], start = 1, end = 100
        Output: 10
        Explanation: Any subarray is ok.

Notice
    Subarray is a part of origin array with continuous index.
***/
public class Solution {
    /**
     * @param A: An integer array
     * @param start: An integer
     * @param end: An integer
     * @return: the number of possible answer
     */
    public int subarraySumII(int[] A, int start, int end) {
        // check corner case
        if (isEmpty(A)) {
            return 0;
        }

        int n = A.length;
        int preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + A[i];
        }

        int result = 0;

        // two pointers
        int left = right = 0;
        for (int i = 1; i <= n; i++) {
            while (i > left && (preSum[i] - preSum[left]) > end) {
                left++;
            }

            while (i > right && (preSum[i] - preSum[right]) >= start) {
                right++;
            }

            result += (right - left);
        }

        return result;
    }
}
