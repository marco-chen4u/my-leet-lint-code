/***
* LintCode 6. Merge Two Sorted Array
Merge two given sorted ascending integer array A and B into a new sorted integer array.

Example
	Example 1:
		Input:  A=[1], B=[1]
		Output: [1,1]	
		Explanation:  return array merged.
	Example 2:
		Input:  A=[1,2,3,4], B=[2,4,5,6]
		Output: [1,2,2,3,4,4,5,6]	
		Explanation: return array merged.
Challenge
	How can you optimize your algorithm if one array is very large and the other is very small?
***/
public class Solution {
    /**
     * @param A: sorted integer array A
     * @param B: sorted integer array B
     * @return: A new sorted integer array
     */
    public int[] mergeSortedArray(int[] A, int[] B) {
        // check corner case
        if ((A == null || A.length == 0) && (B == null || B.length == 0)) {
            return null;
        }
        
        if (A == null || A.length == 0) {
            return B;
        }
        
        if (B == null || B.length == 0) {
            return A;
        }
        
        int length = A.length + B.length;
        int[] result = new int[length];
        int i = 0;
        int j = 0;
        int index = 0;
        
        while (i < A.length && j < B.length) {
            if (A[i] < B[j]) {
                result[index ++] = A[i ++];
            }
            else {
                result[index ++] = B[j ++];
            }
        }
        
        while (i < A.length) {
            result[index ++] = A[i ++];
        }
        
        while (j < B.length) {
            result[index ++] = B[j ++];
        }
        
        return result;
    }
}