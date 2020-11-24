/***
Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

Follow-up: Could you solve the problem in linear time and in O(1) space?

Example 1:
    Input: nums = [3,2,3]
    Output: [3]

Example 2:
    Input: nums = [1]
    Output: [1]

Example 3:
    Input: nums = [1,2]
    Output: [1,2]
 

Constraints:
    1 <= nums.length <= 5 * 104
    -109 <= nums[i] <= 109
***/
//soultion-1: HashMap + Set
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        Set<Integer> set = new HashSet<>();
        
        if (size <= 2) {
            for (int num : nums) {
                set.add(num);
            }
            
            result.addAll(set);
            return result;
        }
        
        // regular case
        int majorityCount = size / 3;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
        }
        
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            int num = entry.getKey();
            int count = entry.getValue();
            
            if (count > majorityCount) {
                set.add(num);
            }
        }
        
        result.addAll(set);
        
        return result;
    }
}

//solution-2: HashMap + Java8 streaming
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        
        // regular case
        
        int majorityCount = size / 3;
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);
            
            if (count > majorityCount) {
                result.add(num);
            }
        }
        
        return result.stream()
                        .distinct()
                        .collect(Collectors.toList());
    }
}
