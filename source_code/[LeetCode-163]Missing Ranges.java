/***
* LeetCode 163. Missing Ranges
Given a sorted integer array nums, where the range of elements are in the inclusive range [lower, upper], return its missing ranges.

Example:
	Input: nums = [0, 1, 3, 50, 75], lower = 0 and upper = 99,
	Output: ["2", "4->49", "51->74", "76->99"]
***/
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;

        long firstItemValue = nums[0];        
        addRange(result, lower, firstItemValue - 1);
        
        for (int i = 1; i < size; i++) {
            long start = (long)nums[i - 1] + 1;
            long end = (long)nums[i] - 1;
            addRange(result, start, end);
        }

        long lastItemValue = nums[size - 1];
        addRange(result, lastItemValue + 1, upper);
        
        return result;
    }
    
    // helper method
    private void addRange(List<String> result, long start, long end) {
        // check corner case
        if (start > end) {
            return;
        }
        
        if (start == end) {
            result.add(String.valueOf(start));
            return;
        }
        
        result.add(start + "->" + end);
    }
}
