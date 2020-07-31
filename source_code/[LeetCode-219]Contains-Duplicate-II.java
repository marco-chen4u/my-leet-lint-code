/***
* LeetCode 219. Contains Duplicate II
Given an array of integers and an integer k, 
find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

Example 1:
    Input: nums = [1,2,3,1], k = 3
    Output: true

Example 2:
    Input: nums = [1,0,1,1], k = 1
    Output: true

Example 3:
    Input: nums = [1,2,3,1,2,3], k = 2
    Output: false
***/
//solution-1:brute-force, HashMap
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length == 0 || k == 0) {
            return false;
        }
        
        // regualr case
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            int value = i;
            
            map.putIfAbsent(key, new ArrayList<Integer>());
            map.get(key).add(value);
        }
        
        // check diff
        for (List<Integer> list : map.values()) {
            if (list == null || list.isEmpty() || list.size() == 1) {
                continue;
            }
            
            for (int i = 1; i < list.size(); i++) {
                int diff = Math.abs(list.get(i) - list.get(i - 1));
                if (diff <= k) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

//solution-2: sliding window
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length == 0 || k == 0) {
            return false;
        }
        
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                set.remove(nums[i - k - 1]);
            }
            
            if (set.contains(nums[i])) {
                return true;
            }
            
            set.add(nums[i]);
        }
        
        return false;
    }
}
