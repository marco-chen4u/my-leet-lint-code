/***
* LeetCode 220. Contains Duplicates III
Given an array of integers, 
find out whether there are two distinct indices i and j in the array 
such that the absolute difference between nums[i] and nums[j] is at most t and 
the absolute difference between i and j is at most k.

Example 1:
    Input: nums = [1,2,3,1], k = 3, t = 0
    Output: true

Example 2:
    Input: nums = [1,0,1,1], k = 1, t = 2
    Output: true

Example 3:
    Input: nums = [1,5,9,1,5,9], k = 2, t = 3
    Output: false
***/
//solution-1: two pointers
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // check corner case
        if (nums == null || nums.length == 0 ||
            k == 0 || t < 0) {
            return false;
        }
        
        int size = nums.length;
        int last = size - 1;
        
        // two pointers
        for (int i = 0; i < nums.length; i++) {// left pointer
            for (int j = (i + k >= size) ? last : i + k; j > i; j--) {// right pointer
                long diff = Math.abs((long)nums[i] - (long)nums[j]);
                if (diff <= t) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

//solution-2: two pointer with sliding window
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // check corner case
        if (nums == null || nums.length == 0 || 
            k == 0 || t < 0) {
            return false;
        }
        
        int size = nums.length;
        int last = size - 1;
        for (int i = 0; i < size - 1; i++) {
            int j = i + 1;
            while (j > i && j < size && j - i <= k) {
                long diff = Math.abs((long)nums[j] - (long)nums[i]);
                if (diff <= t) {
                    return true;
                }
                
                j++;
            }
        }
        
        return false;
    }
}

//solution-3: sliding window, with Map<ArrayElelementValue, ArrayIndexPos> key pair. feasible but with Time Limit Exceeded
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // check corener cases
        if (nums == null || nums.length == 0 ||
            k == 0 || t < 0) {
            return false;
        }
        
        Map<Long, Integer> map = new HashMap<>(); // map<value, indexPosOfArray> key pair
        
        // two pointer
        int i;     // fast pointer
        int j = 0; // slow pointer
        for (i = 0; i < nums.length; i++) {
            if (i - j > k) {
                map.remove((long)nums[j]);
                j++;
            }
            
            // check if their exists Math.abs(diff(nums[i] - nums[x])) <= t, if so then return true
            if (hasDiff(map, nums[i], t)) {
                return true;
            }
            
            map.put((long)nums[i], i);
        }
        
        return false;
    }
    
    // helper method
    private boolean hasDiff(Map<Long, Integer> map, long current, long maxDiff) {
        for (long value : map.keySet()) {
            long diff = Math.abs(current - value);
            if (diff <= maxDiff) {
                return true;
            }
        }
        
        return false;
    }
}

//solution-4: sliding windows + bucket selection
...
