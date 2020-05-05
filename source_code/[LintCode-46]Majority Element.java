/***
* LintCode 46. Majority Element
Given an array of integers, the majority number is the number that occurs more than half of the size of the array. Find it.

Example
	Example 1:
		Input: [1, 1, 1, 1, 2, 2, 2]
		Output: 1
	Example 2:
		Input: [1, 1, 1, 2, 2, 2, 2]
		Output: 2

Challenge
	O(n) time and O(1) extra space

Notice
	You may assume that the array is non-empty and the majority number always exist in the array.
***/
//version-1: brute force, time complexity : O(n^2)
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(List<Integer> nums) {
        int result = -1;//default
        
        int size = nums.size();
        int majorityCount = size / 2;
        
        for (int num : nums) {
            int count = 0;
            
            for (int value : nums) {
                count += (value == num) ? 1 : 0;
            }
            
            if (count >= majorityCount + 1) {
                result = num;
                return result;
            }
        }
        
        return result;
    }
}

//version-2: divide and conquar, time complexity: O(nlogn)
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(List<Integer> nums) {
        int[] values = new int[nums.size()];
        int index = 0;
        for (int num : nums) {
            values[index++] = num;
        }
        
        int size = nums.size();
		int value = helper(values, 0, size - 1);
		
		int count = getOccurence(values, 0, size - 1, value);
		if ((count == (size /2)) && 
	            getCategoriesOfNum(values) == 2) {
		    return -1;
		}
		
		return value;
	}
	
	// helper methods
	private int helper(int[] nums, int start, int end) {
		// check corner case
		if (start == end) {
			return nums[start];
		}
		
		int mid = start + (end - start) / 2;
		
		int leftNum = helper(nums, start, mid);
		int rightNum = helper(nums, mid + 1, end);
		
		int leftCount = getOccurence(nums, start, end, leftNum);
		int rightCount = getOccurence(nums, start, end, rightNum);
		
		// check corner case
		/*if (leftCount == rightCount) {
		    return -1;
		}*/
		
		return (leftCount > rightCount) ? leftNum : rightNum;
	}
	
	private int getOccurence(int[] nums, int start, int end, int value) {
		int count = 0;
		
		for (int num : nums) {
			count += (num == value) ? 1 : 0;
		}
		
		return count;
	}
	
	private int getCategoriesOfNum(int[] nums) {
	    Set<Integer> categorySet = new HashSet<Integer>();
	    
	    for (int num : nums) {
	        categorySet.add(num);
	    }
	    
	    return categorySet.size();
	}
}

//version-3: Boyer-Moore Voting Algorithm, time complexity: O(n)
public class Solution {
    /*
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(List<Integer> nums) {
        int count = 0;
        int candidate = -1;// initialized
        
        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            
            count += (num == candidate) ? 1 : -1;
        }
        
        return (count > 0) ? candidate : -1;
    }
}