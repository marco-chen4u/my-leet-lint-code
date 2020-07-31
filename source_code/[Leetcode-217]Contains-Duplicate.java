/***
* LeetCode 217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:
    Input: [1,2,3,1]
    Output: true

Example 2:
    Input: [1,2,3,4]
    Output: false

Example 3:
    Input: [1,1,1,3,3,4,3,2,4,2]
    Output: true
***/
//solution-1: sort
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        Arrays.sort(nums);
        
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                continue;
            }
            
            return true;
        }
        
        return false;
    }
}

//solution-2: HashSet
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // check corner case 
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        int size = nums.length;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        return set.size() != size;
    }
}

