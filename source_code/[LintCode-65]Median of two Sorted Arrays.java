/***
* LintCode 65. Median of two Sorted Arrays
There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays.

Example 1
    Input:
        A = [1,2,3,4,5,6]
        B = [2,3,4,5]
    Output: 3.5

Example 2
    Input:
        A = [1,2,3]
        B = [4,5]
    Output: 3

Challenge
    The overall run time complexity should be O(log (m+n)).
***/
public class Solution {
    /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length + B.length;
        
        if (n % 2 == 0) {
            return (findKth(A, 0, B, 0, n / 2) + findKth(A, 0, B, 0, n / 2 + 1)) / 2.0;
        }
        else {
            return findKth(A, 0, B, 0, n / 2 + 1);
        }
    }
    
    private int findKth(int[] A, int startOfA, int[] B, int startOfB, int k) {
        if (startOfA >= A.length) {
            return B[startOfB + k - 1];
        }
        
        if (startOfB >= B.length) {
            return A[startOfA + k - 1];
        }
        
        if (k == 1) {
            return Math.min(A[startOfA], B[startOfB]);
        }
        
        int halfKthValueOfA = startOfA + k / 2 - 1 < A.length ? A[startOfA + k / 2 - 1] : Integer.MAX_VALUE;
        int halfKthValueOfB = startOfB + k / 2 - 1 < B.length ? B[startOfB + k / 2 - 1] : Integer.MAX_VALUE;
        
        if (halfKthValueOfA < halfKthValueOfB) {
            return findKth(A, startOfA + k / 2, B, startOfB, k - k / 2);
        }
        else {
            return findKth(A, startOfA, B, startOfB + k / 2, k - k / 2);
        }
    }
}
