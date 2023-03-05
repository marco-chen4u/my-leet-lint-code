/***
* LintCode 387. The Smallest Difference
Given two array of integers(the first array is array A, the second array is array B), now we are going to find a element in array A which is A[i], and another element in array B which is B[j], so that the difference between A[i] and B[j] (|A[i] - B[j]|) is as small as possible, return their smallest difference.
Example
    Example 1:
        Input: A = [3, 6, 7, 4], B = [2, 8, 9, 3]
        Output: 0
        Explanation: A[0] - B[3] = 0
    Example 2:
        Input: A = [1, 2, 3, 4], B = [7, 6, 5]
        Output: 1
        Explanation: B[2] - A[3] = 1
Challenge
    O(n log n) time
***/
//version-1: binary search
public class Solution {
    /**
     * @param A: An integer array
     * @param B: An integer array
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        int result = Integer.MAX_VALUE;
        // check corner cases
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        Arrays.sort(B);
        for (int num : A) {
            int value = getClosestTarget(B, num);
            result = Math.min(result, Math.abs(num - value));
        }

        return result;
    }
	
    // helper method
    private int getClosestTarget(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }	

        if (nums[start] == target) {
            return target;
        }		
        if (nums[end] == target) {
            return target;
        }

        return (Math.abs(target - nums[start]) < Math.abs(nums[end] - target)) ? 
                nums[start] : nums[end];
	}
}


//version-2: two pointers
public class Solution {
    /**
     * @param A: An integer array
     * @param B: An integer array
     * @return: Their smallest difference.
     */
    public int smallestDifference(int[] A, int[] B) {
        int result = Integer.MAX_VALUE;
        // check corner cases
        if (A == null || A.length == 0 || B == null || B.length == 0) {
            return 0;
        }

        Arrays.sort(A);
        Arrays.sort(B):

        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            result = Math.min(result, Math.abs(A[i] - B[j]));
            if (A[i] < B[j]) {
                i++;
            }
            else {
                j++;
            }
        }

        return result;
    }
}
