/***
* LeetCode 523. Continuous Subarray Sum
Given a list of non-negative numbers and a target integer k, 
write a function to check if the array has a continuous subarray of size at least 2 that sums up to a multiple of k, 
that is, sums up to n*k where n is also an integer.

Example 1
    Input: [23, 2, 4, 6, 7],  k=6
    Output: True
    Explanation: Because [2, 4] is a continuous subarray of size 2 and sums up to 6.
Example 2
    Input: [23, 2, 6, 4, 7],  k=6
    Output: True
    Explanation: Because [23, 2, 6, 4, 7] is an continuous subarray of size 5 and sums up to 42.
Note:
    1.The length of the array won't exceed 10,000.
    2.You may assume the sum of all the numbers is in the range of a signed 32-bit integer.
***/
// version-: brute force(realy bad version,O(n^3))
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return false;
        }

        int size = nums.length;
        for (int left = 0; left < size - 1; left++) {
            for (int right = left + 1; right < size; right++) {
                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }

                if ((sum == k) || (k != 0 && sum % k == 0)) {
                    return true;
                }
            }// for i
        }// for i
		
        return false;
    }
}

// version-2: brute force(better version,O(n^2))
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return false;
        }

        // normal case
        int size = nums.length;
        int[] preSum = new int[size];
        preSum[0] = nums[0];
        for (int i = 1; i < size; i++) {
            preSum[i] = preSum[i - 1] + nums[i];
        }

        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size; j++) {
                int sum = preSum[j] - preSum[i] + nums[i];

                if ((sum == k) ||(k != 0 && sum % k == 0)) {
                    return true;
                }
            }// for j
        }// for j

        return false;
    }
}

//version-3: Map(best, O(n))
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return false;
        }		

        int size = nums.length;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(sum, -1);

        for (int i = 0; i < size; i++) {
            sum += nums[i];

            if (k != 0) {
                sum %= k;
            }

            if (map.containsKey(sum)) {
                if (i > map.get(sum) + 1) {
                    return true;
                }
            }
            else {
                map.put(sum, i);
            }
        }

        return false;
    }
}
