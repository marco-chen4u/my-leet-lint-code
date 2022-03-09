/**
* LintCode 45. Maximum Subarray Difference
Given an array with integers.

Find two non-overlapping subarrays A and B, which |SUM(A) - SUM(B)|∣SUM(A)−SUM(B)∣ is the largest.

Return the largest difference.

Example 1
    Input:
        array = [1, 2, -3, 1]
    Output:
        6
    Explanation:
        The subarray are [1,2] and [-3].So the answer is 6.
        
Example 2
    Input:
        array = [0,-1]
    Output:
        1
    Explanation:
        The subarray are [0] and [-1].So the answer is 1.

Challenge
    O(n) time and O(n) space.
**/
//version-1: prefix subarray
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two substrings
     */
    public int maxDiffSubArrays(int[] nums) {
        int size = nums.length;

        int[] leftMax = new int[size];
        int[] leftMin = new int[size];

        int[] rightMax = new int[size];
        int[] rightMin = new int[size];

        int[] copy = new int[size];
        for (int i = 0; i < size; i++) {
            copy[i] = -1 * nums[i];
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        int minSum = 0;
        // Forward: get max subarray
        for (int i = 0; i < size; i++) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMax[i] = max;
        }

        // Backward: get max subarray
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = size - 1; i >= 0; i--) {
            sum += nums[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMax[i] = max;
        }

        // Forward: get min subarray
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = 0; i < size; i++) {
            sum += copy[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            leftMin[i] = -1 * max;
        }

        // Backward: get min subarray
        max = Integer.MIN_VALUE;
        sum = 0;
        minSum = 0;
        for (int i = size - 1; i >= 0; i--) {
            sum += copy[i];
            max = Math.max(max, sum - minSum);
            minSum = Math.min(minSum, sum);
            rightMin[i] = -1 * max;
        }

        // get the max diff
        int diff = 0;
        for (int i = 0; i < size - 1; i++) {
            diff = Math.max(diff, Math.abs(leftMax[i] - rightMin[i + 1]));
            diff = Math.max(diff, Math.abs(leftMin[i] - rightMax[i + 1]));
        }

        return diff;
    }
}

//vesion-2: prefix subarray(better solution), time complexity: O(n), space complexity: O(n)
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer indicate the value of maximum difference between two substrings
     */
    public int maxDiffSubArrays(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        return Math.max(getMaxDiffSubArray(nums), getMaxDiffSubArray(reverse(nums)));
    }

    // helper methods
    private int getMaxDiffSubArray(int[] nums) {
        int size = nums.length;
        
        int[] preLeftMax = new int[size];
        int sum = 0;
        int minSum = 0;
        for (int i = 0; i < size; i++) {
            sum += nums[i];
            preLeftMax[i] = sum - minSum;
            minSum = Math.min(minSum, sum);
        }

        int[] preRightMin = new int[size];
        sum = 0;
        int maxSum = 0;
        for (int i = size - 1; i >= 0; i--) {
            sum += nums[i];
            preRightMin[i] = sum - maxSum;
            maxSum = Math.max(maxSum, sum);
        }

        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < size - 1; i++) {
            maxDiff = Math.max(maxDiff, preLeftMax[i] - preRightMin[i + 1]);
        }

        return maxDiff;
    }

    private int[] reverse(int[] nums) {
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        
        while (left < right) {
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left++;
            right--;
        }

        return nums;
    }
}
