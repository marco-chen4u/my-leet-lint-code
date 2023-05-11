/***
* LintCode 59. 3Sum Closest
Given an array S of n integers, 
find three integers in S such that the sum is closest to a given number, target. 
Return the sum of the three integers.

You may assume that each input would have exactly one solution.

Example 1:
    Input:
        numbers = [2,7,11,15]
        target = 3
    Output:
        20
    Explanation:
        2+7+11=20

Example 2:
    Input:
        numbers = [-1,2,1,-4]
        target = 1
    Output:
        2
    Explanation:
        -1+2+1=2

Challenge
    O(n^2)time, O(1) extra space
***/
//version-1
public class Solution {
    /**
     * @param nums: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = 0;
        // check corner cases
        if (nums == null || nums.length < 3) {
            return result;
        }

        // normal case
        int size = nums.length;
        Arrays.sort(nums);

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < size - 2; i++) {
            int left = i + 1;
            int targetValue = target - nums[i];
            int right = size - 1;

            while (left < right) {
                int sum = nums[left] + nums[right];
                
                // corner case
                if (sum == targetValue) {
                    return sum + nums[i];
                }

                // regular case
                int diff = Math.abs(targetValue - sum);
                if (diff < minDiff) {
                    minDiff = diff;
                    result = sum + nums[i];
                }

                if (sum < targetValue) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param numbers: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] nums, int target) {
        int result = -1;
        if (nums == null || nums.length < 3) {
            return result;
        }
        
        Arrays.sort(nums);
        
        // initialize
        //result = nums[0] + nums[1] + nums[2];
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1;
            int right = nums.length - 1;
            
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum - target);
                if (diff < minDiff) {
                    minDiff = diff;
                    result = sum;
                }
                
                if (sum > target) {
                    right--;
                }
                else {
                    left++;
                }
            }
        }
        
        return result;
    }
}
