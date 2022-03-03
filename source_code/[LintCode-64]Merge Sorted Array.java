/***
* LintCode 64. Merge Sorted Array
Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example 1:
    Input：[1, 2, 3] 3  [4,5]  2
    Output：[1,2,3,4,5]
    Explanation:
        After merge, A will be filled as [1, 2, 3, 4, 5]

Example 2:
    Input：[1,2,5] 3 [3,4] 2
    Output：[1,2,3,4,5]
    Explanation:
        After merge, A will be filled as [1, 2, 3, 4, 5]

Notice
    You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. 
    The number of elements initialized in A and B are m and n respectively.
***/
public class Solution {
    /*
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // check corner case
        if (A == null || B == null) {
            return;
        }
        
        int i = m - 1;
        int j = n - 1;
        
        int index = m + n - 1;
        
        // merge elements from the back end to the front
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            }
            else {
                A[index--] = B[j--];
            }
        }
        
        while (i >= 0) {
            A[index--] = A[i--];
        }
        
        while (j >= 0) {
            A[index--] = B[j--];
        }
    }
}
