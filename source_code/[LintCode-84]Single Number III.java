/***
* LintCode 84. Single Number III
Given 2*n + 2 numbers, every numbers occurs twice except two, find them.

Example
	Example 1:
		Input:  [1,2,2,3,4,4,5,3]
		Output:  [1,5]
	Example 2:
		Input: [1,1,2,3,4,4]
		Output:  [2,3]
	
Challenge
	O(n) time, O(1) extra space.
***/
//version-1: HashMap for accounting, time complexity O(n), sapce complexity O(n)
public class Solution {
    /**
     * @param A: An integer array
     * @return: An integer array
     */
    public List<Integer> singleNumberIII(int[] A) {
        List<Integer> result = new ArrayList<Integer>();
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : A) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 2) {
                continue;
            }
            
            result.add(entry.getKey());
        }
        
        return result;
    }
}

//version-2: bit manipulation/operation, time complexity O(n), space complexity O(1)
public class Solution {
    /**
     * @param nums: An integer array
     * @return: An integer array
     */
    public List<Integer> singleNumberIII(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int xorValue = 0;
        for (int num : nums) {
            xorValue ^= num;
        }
        
        // make sure this position has the 1 value in binary
        int lastBit =xorValue & (-xorValue);//Integer.lowestOneBit(xorValue);
        
        int value1 = 0;
        int value2 = 0;
        for (int num : nums) {
            if ((num & lastBit) == 0) {
                value1 ^= num;
            }
            else {
                value2 ^= num;
            }
        }
        
        result.add(value1);
        result.add(value2);
        
        return result;
    }
}