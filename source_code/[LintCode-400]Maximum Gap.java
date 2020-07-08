/***
* LintCode 400. Maximum Gap
Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
Return 0 if the array contains less than 2 elements.

Example
    Example 1:
        Input: [1, 9, 2, 5]
        Output: 4
        Explanation: The sorted form is [1, 2, 5, 9], and the maximum gap is between 5 and 9.
    Example 2:
        Input: [1]
        Output: 0
        Explanation: The array contains less than 2 elements.

Challenge
    Sort is easy but will cost O(nlogn) time. Try to solve it in linear time and space.

Notice
    You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
***/
//version-1: sort and cost O(nlogn) time
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the maximun difference
     */
    public int maximumGap(int[] nums) {
        int maxGap = 0;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return maxGap;
        }
        
        Arrays.sort(nums);
        int size = nums.length;
        for (int i = 1; i < size; i++) {
            int diff = nums[i] - nums[i - 1];
            
            maxGap = Math.max(maxGap, diff);
        }
        
        return maxGap;
    }
}

//version-2: O(n)
class Solution {
    
    //inn class
    class Bucket {
        // fields
        int min;
        int max;
        // constructor
        public Bucket() {
            max = 0;
            min = Integer.MAX_VALUE;
        }
        
        // methods
        public void update(int value) {
            max = Math.max(max, value);
            min = Math.min(min, value);
        }
    }
    
    
    public int maximumGap(int[] nums) {
        // check corner cases
        if (nums == null || nums.length < 2) {
            return 0;
        }
        
        if (nums.length == 2) {
            return Math.abs(nums[0] - nums[1]);
        }
        
        // regular case
        int size = nums.length;
        Bucket[] buckets = new Bucket[size - 1];
        
        for (int i = 0; i < size - 1; i++) {
            buckets[i] = new Bucket();
        }
        
        int min = nums[0];
        int max = nums[0];
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        if (max == min) {
            return 0;
        }
        
        for (int i = 0; i < size; i++) {
            int index = findIndex(nums[i], min, max, size - 1);
            buckets[index].update(nums[i]);
        }
        
        int result = 0;
        int currentMin = min;
        for (int i = 0; i < size - 1; i++) {
            if (buckets[i].min == Integer.MAX_VALUE) {
                continue;
            }
            
            result = Math.max(result, buckets[i].min - currentMin);
            currentMin = Math.max(currentMin, buckets[i].max);
        }
        
        return result;
    }
    
    // helper method
    private int findIndex(int value, int min, int max, int n) {
        float size = (float)(max - min) / n;
        return (int)Math.min(n - 1, (value - min) / size);
    }
}
