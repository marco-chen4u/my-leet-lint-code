/***
* LintCode 462. Total Occurrence of Target
Given a target number and an integer array sorted in ascending order. Find the total number of occurrences of target in the array.

Example
    Example1:
        Input: [1, 3, 3, 4, 5] and target = 3, 
        Output: 2.
    Example2:
        Input: [2, 2, 3, 4, 6] and target = 4, 
        Output: 1.
    Example3:
        Input: [1, 2, 3, 4, 5] and target = 6, 
        Output: 0.
Challenge
    Time complexity in O(logn)
***/
public class Solution {
    /**
     * @param A: A an integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int totalOccurrence(int[] A, int target) {
        // check corner case
        if (A == null || A.length == 0) {
            return 0;
        }

        int firstPos = findFirst(A, target);
        int lastPos = findLast(A, target, firstPos);

        //System.out.println("firstPos = " + firstPos + ", lastPos = " + lastPos);
        if (firstPos == -1 && lastPos == -1) {
            return 0;
        }

        return lastPos - firstPos + 1;
    }

    // helper methods
    private int findFirst(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;

        while (start  + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        return -1;
    }

    private int findLast(int[] nums, int target, int start) {
        // check corner case
        if (start == -1) {
            return -1;
        }

        // regular case
        int end = nums.length - 1;

        while (start + 1 < end) {
            int mid = start + (end - start) / 2;

            if (nums[mid] > target) {
                end = mid;
            }
            else {
                start = mid;
            }
        }

        if (nums[end] == target) {
            return end;
        }

        if (nums[start] == target) {
            return start;
        }

        return -1;
    }
}
