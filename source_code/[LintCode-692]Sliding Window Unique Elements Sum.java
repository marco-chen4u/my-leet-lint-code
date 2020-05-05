/***
* LintCode 692. Sliding Window Unique Elements Sum
Given an array and a window size that is sliding along the array, 
find the sum of the count of unique elements in each window.

Example
	Example1
		Input:
			[1, 2, 1, 3, 3]
			3
		Output: 5
		Explanation:
			First window [1, 2, 1], only 2 is unique, count is 1.
			Second window [2, 1, 3], all elements unique, count is 3.
			Third window [1, 3, 3], only 1 is unique, count is 1.
			sum of count = 1 + 3 + 1 = 5

	Example1
		Input:
			[1, 2, 1, 2, 1]
			3
		Output: 3

Notice
	If the window size is larger than the length of array, 
	just regard it as the length of the array (i.e., the window won't slide).
***/

public class Solution {
    /**
     * @param nums: the given array
     * @param k: the window size
     * @return: the sum of the count of unique elements in each window
     */
    public int slidingWindowUniqueElementsSum(int[] nums, int k) {
        int result = 0;
		// check corner case
		if (nums == null || nums.length == 0 || k <= 0) {
			return result;
		}
		
		k = Math.min(k, nums.length);
		
		int size = nums.length;
		int count = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		
		for (int i = 0; i < size; i++) {
			int item = nums[i];
			
			if (!map.containsKey(item)) {
				count++;
				map.put(item, 1);
			}
			else {
				map.put(item, map.get(item) + 1);
				
				if (map.get(item) == 2) {
					count--;
				}
			}
			
			if (i >= k - 1) { // 0..k -1
				result += count;
				
				int index = i - (k - 1); // start index of previous window
				int startItem = nums[index];
				map.put(startItem, map.get(startItem) - 1);
				
				// after removing left,
				if (map.get(startItem) == 0) {					
					count--;// (1)the unique was just removed: 2 [1 1 3], 2 was unique
					map.remove(startItem);
				}
				else if (map.get(startItem) == 1) {					
					//(2)the duplicate can become unique: 1 [2 1 3], 1was duplicate and now unique
					count++;
				}
			}
		}
		
		return result;
    }
}