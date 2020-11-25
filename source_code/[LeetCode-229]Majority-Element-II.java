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

Link: https://leetcode.com/problems/majority-element-ii/solution/
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

//solution-3: Boyer-Moore Voting Algorithm, time complexity: O(n), space complexity: O(1)
/***
To figure out a O(1)O(1) space requirement, we would need to get this simple intuition first. For an array of length n:
    -There can be at most one majority element which is more than ⌊n/2⌋ times.
    -There can be at most two majority elements which are more than ⌊n/3⌋ times.
    -There can be at most three majority elements which are more than ⌊n/4⌋ times.
and so on.

Let's try to get an intuition for the case where we would like to find a majority element which is more than ⌊n/2⌋ times in an array of length n.

The idea is to have two variables, one holding a potential candidate for majority element and a counter to keep track of whether to swap a potential candidate or not. Why can we get away with only two variables? Because there can be at most one majority element which is more than ⌊n/2⌋ times. Therefore, having only one variable to hold the only potential candidate and one counter is enough.

While scanning the array, the counter is incremented if you encounter an element which is exactly same as the potential candidate but decremented otherwise. When the counter reaches zero, the element which will be encountered next will become the potential candidate. Keep doing this procedure while scanning the array. 

However, when you have exhausted the array, you have to make sure that the element recorded in the potential candidate variable is the majority element by checking whether it occurs more than ⌊n/2⌋ times in the array. In the original Majority Element problem, it is guaranteed that there is a majority element in the array so your implementation can omit the second pass. However, in a general case, you need this second pass since your array can have no majority elements at all!
***/
/*The counter is initialized as 0 and the potential candidate as None at the start of the array.*/
class Solution {
    
    // filed
    private int N_BY_3_MAJORITY = 0;
    
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        N_BY_3_MAJORITY = size / 3;
        
        Integer candidate1 = null;
        Integer candidate2 = null;
        
        int counter1 = 0;
        int counter2 = 0;
        
        // 1st pass, to set up candidates
        for (int num : nums) {
            if (candidate1 != null && candidate1 == num) {
                counter1++;
            }
            else if (candidate2 != null && candidate2 == num) {
                counter2++;
            }
            else if (counter1 == 0) {
                candidate1 = num;
                counter1++;
            }
            else if (counter2 == 0) {
                candidate2 = num;
                counter2++;
            }
            else {
                counter1--;
                counter2--;
            }
        }
        
        // 2nd pass, to make sure by calculation on candidates
        counter1 = 0;
        counter2 = 0;
        for(int num : nums) {
            counter1 += (candidate1 != null && candidate1 == num) ? 1 : 0;
            counter2 += (candidate2 != null && candidate2 == num) ? 1 : 0;
        }
        
        if (counter1 > N_BY_3_MAJORITY) {
            result.add(candidate1);
        }
        
        if (counter2 > N_BY_3_MAJORITY) {
            result.add(candidate2);
        }
        
        return result;
        
    }
}

