/***
* LintCode 406. Minimum Size Subarray Sum
Given an array of n positive integers and a positive integer s, 
find the minimal length of a subarray of which the sum ≥ s. 
If there isn't one, return -1 instead.

Example
    Example 1:
        Input: [2,3,1,2,4,3], s = 7
        Output: 2
        Explanation: The subarray [4,3] has the minimal length under the problem constraint.
    Example 2:
        Input: [1, 2, 3, 4, 5], s = 100
        Output: -1

Challenge
    If you have figured out the O(nlog n) solution, try coding another solution of which the time complexity is O(n).
***/
//solution-1: O(n), Two pointers, Silding Window
public class Solution {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     */
    public int minimumSize(int[] nums, int s) {
        int result = -1; 
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        result = Integer.MAX_VALUE;

        int i = 0, j = 0;

        int size = nums.length;
        int sum = 0;

        for (; i < size; i++) {
            while (j < size && sum < s) {
                sum += nums[j];
                j++;
            }

            if (sum >= s) {
                result = Math.min(result, j - i);				
            }

            sum -= nums[i];
        }

        return (result == Integer.MAX_VALUE) ? -1 : result;
    }	
}

//solution-2: PrefixSum + BinarySearch
class Solution {
    public int minimumSize(int[] nums, int target) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int result = Integer.MAX_VALUE;
        int n = nums.length;
        
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int current = nums[i - 1];
            prefixSum[i] = prefixSum[i - 1] + current;
        }
        
        for (int left = 0; left < n + 1; left++) {
            int right = searchRightPos(prefixSum, left + 1, n, prefixSum[left] + target);
            
            if (right == n + 1) {
                break;
            }
            
            result = Math.min(result, right - left);
        }
        
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }
    
    // helper method
    private int searchRightPos(int[] prefixSum, int leftNext, int n, int value) {
        int start = leftNext;
        int end = n;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (prefixSum[mid] >= value) {
                end = mid - 1;
            }
            else {
                start = mid + 1;
            }
        }
        
        return start;
    }
}
