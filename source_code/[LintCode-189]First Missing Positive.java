/***
* LintCode 189. First Missing Positive
Given an unsorted integer array, find the first missing positive integer.

Example
	Example 1:
		Input:[1,2,0]
		Output:3
	Example 2:
		Input:[3,4,-1,1]
		Output:2

Challenge
	Your algorithm should run in O(n) time and uses constant space.
***/
public class Solution {
    /**
     * @param num: An array of integers
     * @return: An integer
     */
    public int firstMissingPositive(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 1;
        }
        
        if (nums.length == 1) {
            return (nums[0] != 1) ? 1 : 2;
        } 
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            getRangeValue(num, map);
            max = Math.max(max, num);
        }
        
        //System.out.println("max = " + max);
        
        if (max <= 0) {
            return 1;
        }
        
        int result = (max != Integer.MAX_VALUE) ? max + 1 : max;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            
            //System.out.println("key = " + entry.getKey() + ", value = " + entry.getValue());
            
            if (entry.getValue() != 0 || entry.getKey() <= 0) {
                continue;
            }
            
            result = Math.min(result, entry.getKey());
        }
        
        return result;
    }
    
    // helper method
    private void getRangeValue(int num, Map<Integer, Integer> map) {
        // check corner case
        if (num < 0) {
            if (!map.containsKey(1)) {
                map.put(1, 0);
            }
            return;
        }
        
        int upperBound = num + 1;
        int lowerBound = num - 1;
        
        if (upperBound >= 0) {
            if (!map.containsKey(upperBound)) {
                map.put(upperBound, 0);
            }
        }
        
        if (lowerBound >= 0) {
            if (!map.containsKey(lowerBound)) {
                map.put(lowerBound,0);
            }
        }
        
        
        map.put(num, map.getOrDefault(num, 0) + 1);
    }
}