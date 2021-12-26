/***
* LintCode 1779. Shortest Duplicate Subarray
Given an array, return the shortest subarray length which has duplicate elements.

Returns -1 if the array has no subarray with duplicate elements.

note:
    0 <= nums.length <= 10^6
    
Example 1:
    Input: [1,2,3,1,4,5,4,6,8]
    Output: 3
    Explanation: The the shortest subarray which has duplicate elements is [4,5,4].

Example 2:
    Input: [1,1]
    Output: 2
    Explanation: The the shortest subarray which has duplicate elements is [1,1].
***/
//version-1: Map<item-value, posIndex>
public class Solution {

    /**
     * @param nums: The array you should find shortest subarray length which has duplicate elements.
     * @return: Return the shortest subarray length which has duplicate elements.
     */
    public int getLength(int[] nums) {
        int result = -1;
        // check corner cases
        if (nums == null || nums.length <= 1) {
            return result;
        }

        // initialize
        result = Integer.MAX_VALUE;
        int size = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < size; i++) {
            if (map.containsKey(nums[i])) {
                int left = map.get(nums[i]);
                int right = i;

                int currentLength = right - left + 1;
                result = Math.min(result, currentLength);
            }

            map.put(nums[i], i);
        }

        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
