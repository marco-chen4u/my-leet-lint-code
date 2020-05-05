/***
* LintCode 83. Single Number II
Given 3*n + 1 non-negative integer, every numbers occurs triple times except one, find it.

Example
	Example 1:
		Input:  [1,1,2,3,3,3,2,2,4,1]
		Output:  4
	Example 2:
		Input: [2,1,2,2]
		Output:  1	

Challenge
One-pass, constant extra space.
***/

//version-1: HashMap, time complexity-O(n), space complexity-O(n)
public class Solution {
    // field
    private final int DEFAULT_VALUE = Integer.MIN_VALUE;
    
    /**
     * @param nums: An integer array
     * @return: An integer
     */
    public int singleNumberII(int[] nums) {
        int result = DEFAULT_VALUE;
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 1) {
                continue;
            }
            
            result = entry.getKey();
        }
        
        return result;
    }
}

//version-2: Bit Operation with formular(int[32] array % 3)
/*
*统计二进制每个位上1的个数并对3取模。
*出现一次的数字对应位上的1会被统计出来。
*/
public class Solution {
    /**
     * @param nums: An integer array
     * @return: An integer
     */
    public int singleNumberII(int[] nums) {
        int result = 0;
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int[] values = new int[32];
        
        for (int i = 0; i < values.length; i++) {
            for (int index = 0; index < nums.length; index++) {
                values[i] += (nums[index] >> i) & 1;
                values[i] %= 3;
                
            }
            
            result = result | (values[i] << i);
        }
        
        return result;
    }
}