/***
* LeetCode 560. Subarray Sum Equals K 
Given an array of integers nums and an integer k, return the total number of continuous subarrays whose sum equals to k.

Example 1:
    Input: nums = [1,1,1], k = 2
    Output: 2

Example 2:
    Input: nums = [1,2,3], k = 3
    Output: 2

Constraints:
    1 <= nums.length <= 2 * 10^4
    -1000 <= nums[i] <= 1000
    -10^7 <= k <= 10^7

Link: https://leetcode.com/problems/subarray-sum-equals-k/submissions/
***/
class Solution {
    public int subarraySum(int[] nums, int k) {
        int result= 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        if (nums.length == 1 && nums[0] == k) {
            return 1;
        }
        
        // normal case
        //initialization
        int size = nums.length;
        int[] prefixSum = new int[size + 1];
        Arrays.fill(prefixSum, 0);
        for (int i = 0; i < size; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j <= size; j++) {
                int sum = prefixSum[j] - prefixSum[i];
                
                if (sum != k) {
                    continue;
                }
                
                result +=1;
            }
        }
        
        return result;
        
    }
}
