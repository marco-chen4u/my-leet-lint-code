/***
* LintCode 994. Contiguous Array
Given a binary array, find the maximum length of a contiguous subarray with equal number of 0 and 1.

Example
	Example 1:
		Input: [0,1]
		Output: 2
		Explanation: [0, 1] is the longest contiguous subarray with equal number of 0 and 1.

	Example 2:
		Input: [0,1,0]
		Output: 2
		Explanation: [0, 1] (or [1, 0]) is a longest contiguous subarray with equal number of 0 and 1.

Notice
	The length of the given binary array will not exceed 50,000.
***/
//version-1
public class Solution {
    /**
     * @param nums: a binary array
     * @return: the maximum length of a contiguous subarray
     */
    public int findMaxLength(int[] nums) {
        int result = 0;
        
        int count = 0;
		int size = nums.length;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();// <sum,index> key-value pair
		map.put(count, -1);


		for (int i = 0; i < size; i++) {
			count += (nums[i] == 1) ? 1 : -1;
			
			if (map.containsKey(count)) {
				result = Math.max(result, i - map.get(count));
				continue;
			}
			
			if (!map.containsKey(count)) {// keey the oldest index to get the longest length
				map.put(count, i);
			}
		}

		return result;
    }
}

//version-2:
public class Solution {
    /**
     * @param nums: a binary array
     * @return: the maximum length of a contiguous subarray
     */
    public int findMaxLength(int[] nums) {
        int result = 0;
        
        int count = 0;
		int size = nums.length;
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();// <sum,index> key-value pair

		for (int i = 0; i < size; i++) {
			count += (nums[i] == 1) ? 1 : -1;
			
			if (count == 0) {
			    result = Math.max(result, i + 1);
			    continue;
			}
			
			if (map.containsKey(count)) {
				result = Math.max(result, i - map.get(count));
				continue;
			}
			
			if (!map.containsKey(count)) {
			    map.put(count, i);
			}
		}
		
		return result;
    }
}

//version-3: Use array instead of HashMap to track the sum
// ref: https://www.youtube.com/watch?v=uAGt1QoAoMU
public class Solution {
    /**
     * @param nums: a binary array
     * @return: the maximum length of a contiguous subarray
     */
    public int findMaxLength(int[] nums) {
        int result = 0;
		int n = nums.length;
		
		int[] sums = new int[n * 2 + 1];// to track the indexs of calculated sum
		Arrays.fill(sums, Integer.MIN_VALUE);
		
		int count = 0;
		
		for (int i = 0; i < n; i++) {
			count += (nums[i] == 0) ? -1 : 1;
			
			if (count == 0) {
				result = Math.max(result, i + 1);
				continue;
			}
			
			if (sums[count + n] != Integer.MIN_VALUE) {// has ever track before
				result = Math.max(result, i - sums[count + n]);// n here is the offset value
			}
			else {
				sums[count + n] = i;
			}
		}
		
		return result;
	}
}