/***
* LintCode 138. Subarray Sum
Given an integer array, find a subarray where the sum of numbers is zero. 
Your code should return the index of the first number and the index of the last number.

-There is at least one subarray that it's sum equals to zero.

Example 1:
    Input:  [-3, 1, 2, -3, 4]
    Output: [0, 2] or [1, 3].
    Explanation: return anyone that the sum is 0.

Example 2:
    Input:  [-3, 1, -4, 2, -3, 4]
    Output: [1,5]	
***/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        List<Integer> result = new ArrayList<>();
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        if (nums.length == 1 && nums[0] == 0) {
            result.add(0);
            result.add(0);
            return result;
        }

        // normal case
        // initialization
        int size = nums.length;
        int[] prefixSum = new int[size + 1];
        Arrays.fill(prefixSum, 0);

        for (int i = 0; i < size; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        // calculate
        for (int i = 0; i < size; i++) {
            int sum = prefixSum[i + 1];

            if (!map.containsKey(sum)) {
                map.put(sum, i);
                continue;
            }

            result.add(map.get(sum) + 1);// start Index
            result.add(i);// end Index
        }

        // return
        return result;
    }
}
