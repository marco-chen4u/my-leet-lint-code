/***
 * LintCode 75. Find Peak Element
There is an integer array which has the following features:
    -The numbers in adjacent positions are different.
    -A[0] < A[1] && A[A.length - 2] > A[A.length - 1].
We define a position P is a peak if:
    A[P] > A[P-1] && A[P] > A[P+1]
Find a peak element in this array. Return the index of the peak.


Example 1:
    Input:  [1, 2, 1, 3, 4, 5, 7, 6]
    Output:  1 or 6		
    Explanation:
        return the index of peek.

Example 2:
    Input: [1,2,3,4,1]
    Output:  3
***/
//version-1: Binary Search
public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] nums) {
        // check corner case
        if (nums == null || nums.length < 3) {
            return -1;
        }
        
        int start = 1;
        int end = nums.length - 2;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            }
            else if (nums[mid] < nums[mid - 1]) {
                end = mid - 1;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] > nums[end]) {
            return start;
        }
        else {
            return end;
        }
    }
}

//version-2: Binary Search
public class Solution {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] nums) {
        // check corner case
        if (nums == null || nums.length < 3) {
            return -1;
        }
        
        int start = 1;
        int end = nums.length - 2;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] < nums[mid - 1]) {
                end = mid;
            }
            else if (nums[mid] < nums[mid + 1]) {
                start = mid;
            }
            else {
                return mid;
            }
        }
        
        return nums[start] > nums[end] ? start : end;
    }
}
