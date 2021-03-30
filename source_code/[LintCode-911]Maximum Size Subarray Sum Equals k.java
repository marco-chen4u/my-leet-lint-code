/***
* LintCode 911. Maximum Size Subarray Sum Equals k
Given an array nums and a target value k, find the maximum length of a subarray that sums to k. If there isn't one, return 0 instead.

Example
    Example1
        Input:  nums = [1, -1, 5, -2, 3], k = 3
        Output: 4
        Explanation:
            because the subarray [1, -1, 5, -2] sums to 3 and is the longest.

    Example2
        Input: nums = [-2, -1, 2, 1], k = 1
        Output: 2
        Explanation:
            because the subarray [-1, 2] sums to 1 and is the longest.

Challenge
    Can you do it in O(n) time?

Notice
    The sum of the entire nums array is guaranteed to fit within the 32-bit signed integer range.
***/
//version-1
public class Solution {
    /**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int result = 0;
	
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();// <sum, index> key-value pair
        int sum = 0;
        map.put(sum, 0);
		
        int size = nums.length;
		
        for (int i = 1; i <= size; i++) {
            sum += nums[i - 1];
			
            if (map.containsKey(sum - k)) {
                result = Math.max(result, i - map.get(sum - k));
            }
			
            if (!map.containsKey(sum)) {// to get the longest length, so keep the oldest index
                map.put(sum, i);
            }
        }
		
        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param nums: an array
     * @param k: a target value
     * @return: the maximum length of a subarray that sums to k
     */
    public int maxSubArrayLen(int[] nums, int k) {
        int result = 0;
        
        int size = nums.length;
        int[] prefixSum = new int[size + 1];
        prefixSum[0] = 0;
        for (int i = 1; i <= size; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i -1];
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>(); // <sum, indexPos> key value pair
        map.put(k, 0);
        for (int i = 1; i <= size; i++) {
            if (map.containsKey(prefixSum[i])) {
                result = Math.max(result, i - map.get(prefixSum[i]));
            }
            if (!map.containsKey(prefixSum[i] + k)) {
                map.put(prefixSum[i] + k, i);
            }
        }
        
        return result;
    }
}
