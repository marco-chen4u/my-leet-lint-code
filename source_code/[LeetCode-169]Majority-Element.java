/***
Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:
    Input: [3,2,3]
    Output: 3

Example 2:
    Input: [2,2,1,1,1,2,2]
    Output: 2

Link: https://leetcode.com/problems/majority-element/
***/
//solution-1: HashMap
class Solution {
    public int majorityElement(int[] nums) {
        int result = -1;
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        int majority = size / 2;
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            
            if (count > majority) {
                result = num;
                break;
            }
        }
        
        return result;
    }
} 

//solution-2:Bit manipulation
class Solution {
    public int majorityElement(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // regular case
        int[] bit = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                bit[i] += ((num >> i & 1) == 1) ? 1 : 0;
            }
        }
        
        int result = 0;// majority candidate
        int majorityCount = nums.length / 2;
        
        for (int i = 0; i < 32; i++) {
            bit[i] = (bit[i] > majorityCount) ? 1 : 0;
            result += (bit[i] << i);
        }
        
        return result;
        
    }
}

//solution-3(recomended): Boyer-Moore voting algorithm, O(1) space complexity
class Solution {
    public int majorityElement(int[] nums) {
        int result = -1;
        // check coner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        int majority = size / 2;
        
        int counter = 0;
        Integer candidate = null;
        
        // 1st pass, find out potential candidate
        for (int num : nums) {
            if (candidate != null && candidate == num) {
                counter++;
            }
            else if (counter == 0) {
                candidate = num;
                counter++;
            }
            else {
                counter--;
            }
        }
        
        // 2nd pass, calculate the counter by this potential candidate if it is satisfied at the majority criteria
        counter = 0;
        for (int num : nums) {
            if (candidate != null && candidate == num) {
                counter++;
            }
        }
        
        result = (counter > majority) ? candidate : -1;
        
        return result;
    }
}

//solution-4: Boyer-Moore voting algorithm, simplified
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int candidate = -1;
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            
            count += (num == candidate) ? 1 : -1;
        }
        
        return (count > 0) ? candidate : -1;
    }
}
