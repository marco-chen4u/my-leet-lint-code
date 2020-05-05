/***
 * LintCode 124. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
    Given [100, 4, 200, 1, 3, 2],
    The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
    Your algorithm should run in O(n) complexity.
***/
public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        int result = 0; // default value
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // initialize
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            if (set.contains(num)) {
                
                set.remove(num);
                
                // two pointers from the middle-line
                int left = num - 1;
                int right = num + 1;
                
                while (set.contains(left)) {
                    set.remove(left);
                    left--;
                }
                
                while (set.contains(right)) {
                    set.remove(right);
                    right++;
                }
                
                result = Math.max(result, right - left - 1);
            }
        }
        
        return result;
    }
}