/***
* LintCode 587. Two Sum - Unique Pairs
Given an array of integers, find how many unique pairs in the array such that their sum is equal to a specific target number. 
Please return the number of pairs.

Example 1
    Input: nums = [1,1,2,45,46,46], target = 47 
    Output: 2
    Explanation:    
        1 + 46 = 47
        2 + 45 = 47
        
Example 2
    Input: nums = [1,1], target = 2 
    Output: 1
    Explanation:
    1 + 1 = 2

Link:
    https://www.lintcode.com/problem/587/

***/
//solution-1: two pointers, time-complexity: O(nlogn)
public class Solution {
    /**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum6(int[] nums, int target) {
        // write your code here
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Arrays.sort(nums);

        int count = 0;
        int size = nums.length;
        int left = 0;
        int right = size - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum < target) {
                left ++;
                continue;
            }

            if (sum > target) {
                right--;
                continue;
            }

            if (sum == target) {
                count +=1;

                left++;
                while (left < right && nums[left] == nums[left - 1]) {
                    left++;
                }

                right--;
                while (left < right && nums[right] == nums[right + 1]) {
                    right--;
                }
            }
        }

        return count;
    }
}
