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
    
LintCode link: https://www.lintcode.com/problem/65/
LeetCode link: https://leetcode.com/problems/median-of-two-sorted-arrays/
***/
//version-1: merge-sort(two pointers), works perfectly in leetcode but not lintcode
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;

        int[] nums = new int[n];
        int index = 0;

        // two pointers
        int i = 0;
        int j = 0;
        while (i < n1 && j < n2) {
            if(nums1[i] <= nums2[j]) {
                nums[index++] = nums1[i++];
            }
            else {
                nums[index++] = nums2[j++];
            }
        }

        while (i < n1) {
            nums[index++] = nums1[i++];
        }

        while (j < n2) {
            nums[index++] = nums2[j++];
        }

        double result = 0.0;

        if (n % 2 == 1) {
            result = (float) nums[n / 2];
        }
        else {
            result = (float)(nums[n / 2 - 1] + nums[ n / 2]) / 2;
        }

        return result;
    }
}

//version-2: recursion
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
