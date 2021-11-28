/***
* LintCode 76. Longest Increasing Subsequence
Given a sequence of integers, find the longest increasing subsequence (LIS).
You code should return the length of the LIS.
Example
    Example 1:
        Input:  [5,4,1,2,3]
        Output:  3	
        Explanation:
            LIS is [1,2,3]
    Example 2:
        Input: [4,2,4,5,3,7]
        Output:  4	
        Explanation: 
            LIS is [2,4,5,7]
Challenge
    Time complexity O(n^2) or O(nlogn)

Clarification
    What's the definition of longest increasing subsequence?
    The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.
    https://en.wikipedia.org/wiki/Longest_increasing_subsequence
***/
//version-1: DP
public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // state
        int n = nums.length;
        int[] f = new int[n];
        
        // initialize
        for(int i = 0; i < n; i++) {
            f[i] = 1;
        }
        
        // function
        int result = 0; //Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            result = Math.max(result, f[i]);
        }
        
        return result;
    }
}

/*** LintCode 76. Longest Increasing Subsequence ***/
//version-2: O(nlogn) solution
public class Solution {
    /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        int result = 0;
        // check coner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int n = nums.length;
        int[] minLast = new int[n];
        Arrays.fill(minLast, Integer.MAX_VALUE);
        
        for (int num : nums) {
			// find the first number in the array-minLast that >= nums[i];
            int index = binarySearch(minLast, num);
            minLast[index] = num;
        }
        
        for (int i = n - 1; i >= 0; i--) {
            if (minLast[i] == Integer.MAX_VALUE) {
                continue;
            }
            
            result = i + 1;
            break;
        }
        
        return result;
    }
    
    // helper method
    /*
     * Getting the first index where the item value >= target in this sorted array.
     */
    private int binarySearch(int[] nums, int target) {
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
        
        if (nums[start] >= target) {
            return start;
        }
        
        return end;
    }
}
