/***
* LintCode 824. Single Number IV
Give an array, all the numbers appear twice except one number which appears once and all the numbers which appear twice are next to each other. Find the number which appears once.

Example
	Example 1:
		Input: [3,3,2,2,4,5,5]
		Output: 4
		Explanation: 4 appears only once.
	Example 2:
		Input: [2,1,1,3,3]
		Output: 2
		Explanation: 2 appears only once.

Notice
	1 <= nums.length < 10^4
	In order to limit the time complexity of the program, your program will run 10^5 times.
*/

//version-1: HashMap, time complexity-O(n), space complexity-O(n)
public class Solution {
    // field
    private final int DEFAULT_VALUE = 0;
    
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int result = DEFAULT_VALUE;
        
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

//version-2: Bit Operation(xor calculation), time complexity-O(n)
public class Solution {
    private final int DEFALUT_VALUE = 0;
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int result = DEFALUT_VALUE;
        
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int xorValue = DEFALUT_VALUE;
        for (int num : nums) {
            xorValue ^= num;
        }
        
        result = (xorValue == 0) ? DEFALUT_VALUE : xorValue;
        
        return result;
    }
}

//version-3: Binary Search
public class Solution {
    /**
     * @param nums: The number array
     * @return: Return the single number
     */
    public int getSingleNumber(int[] nums) {
        int size = nums.length;
        int start = 0;
        int end = size - 1;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (nums[mid] == nums[mid - 1]) {
                if ((mid - start + 1) % 2 == 1) {
                    end = mid - 2;
                }
                else {
                    start = mid + 1;
                }
            }
            else if (nums[mid] == nums[mid + 1]) {
                if ((end - mid + 1) % 2 == 1) {
                    start = mid + 2;
                }
                else {
                    end = mid - 1;
                }
            }
            else {
                return nums[mid];
            }
        }
        
        return nums[start];
    }
}