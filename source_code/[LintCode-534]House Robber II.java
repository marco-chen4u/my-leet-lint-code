/***
* LintCode 534. House Robber II
After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. This time, all houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, the security system for these houses remain the same as for those in the previous street.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example
	Example1
		Input:  nums = [3,6,4]
		Output: 6
	Example2
		Input:  nums = [2,3,2,3]
		Output: 6

Notice
	This is an extension of House Robber.
***/
public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
		// check corner cases
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		int size = nums.length;
		return Math.max(getHouseRobed(nums, 0, size - 2), 
						getHouseRobed(nums, 1, size - 1));
	}
	
	// helper method
	private int getHouseRobed(int[] nums, int startIndex, int endIndex) {
		// check corner cases
		if (startIndex >= endIndex) {
			return nums[startIndex];
		}
		
		if (startIndex + 1 == endIndex) {
			return Math.max(nums[startIndex], nums[endIndex]);
		}
		
		int[] f = new int[2];
		int i = startIndex;
		f[i % 2] = nums[i];
		f[(i + 1) % 2] = Math.max(nums[i], nums[i + 1]);
		
		for (i = startIndex + 2; i <= endIndex; i++) {
			f[(i % 2)] = Math.max(f[(i - 1) % 2], f[(i - 2) % 2] + nums[i]);
		}
		
		return f[endIndex % 2];
	}
}

//version-2: DP-2 pointers
public class Solution {
    /**
     * @param nums: An array of non-negative integers.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber2(int[] nums) {
		// check corner cases
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		if (nums.length == 1) {
			return nums[0];
		}
		
		int size = nums.length;
		return Math.max(getHouseRobed(nums, 0, size - 2), 
						getHouseRobed(nums, 1, size - 1));
	}
	
	// helper method
	private int getHouseRobed(int[] nums, int startIndex, int endIndex) {
		// check corner cases
		if (startIndex >= endIndex) {
			return nums[startIndex];
		}
		
		if (startIndex + 1 == endIndex) {
			return Math.max(nums[startIndex], nums[endIndex]);
		}
		
        int robed = 0;
        int notRobed = 0;
        
        for (int i = startIndex; i <= endIndex; i++) {
            int pre = Math.max(robed, notRobed);
            robed = notRobed + nums[i]; 
            notRobed = pre;
        }
        
        return Math.max(robed, notRobed);
	}
}