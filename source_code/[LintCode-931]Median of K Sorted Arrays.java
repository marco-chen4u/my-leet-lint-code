/***
 * LintCode 931. Median of K Sorted Arrays
There are k sorted arrays nums. Find the median of the given k sorted arrays.

Example
    Example 1:
        Input:
            [[1],[2],[3]]
        Output:
            2.00
    
    Example 2:
        Input:
            [[1,1,2],[2,4,8],[3,7,10,20]]
        Output:
            3.50

Notice
    The length of the given arrays may not equal to each other.
    The elements of the given arrays are all positive number.
    Return 0 if there are no elements in the array.
    In order to ensure time complexity, the program will be executed repeatedly.
***/
public class Solution {
    // helper methods
    private int getTotalSize(int[][] nums) {
        int totalSize = 0; 
        
        int rowSize = nums.length;
        for (int i = 0; i < rowSize; i++) {
            totalSize += nums[i].length;
        }
        
        return totalSize;
    }
    
    private int getCountGTE(int[] nums, int target) {
        int count = 0;// default value
        // check corner case
        if (nums == null || nums.length == 0) {
            return count;
        }
        
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] >= target) {
            return nums.length - start;//including the start position
        }
        
        if (nums[end] >= target) {
            return nums.length - end;//including the end position
        }
        
        return count;// return default
    }
    
    private int getCountGTE(int[][] nums, int target) {
        int count = 0;
        
        int rowSize = nums.length;
        for (int i = 0; i < rowSize; i++) {
            count += getCountGTE(nums[i], target);
        }
        
        return count;
    }
    
    private int findKth(int[][] nums, int k) {
        int start = 0;
        int end = Integer.MAX_VALUE;
        
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            
            if (getCountGTE(nums, mid) >= k) {
                start = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (getCountGTE(nums, start) >= k) {
            return start;
        }
        
        return end;
    }
    
    /**
     * @param nums: the given k sorted arrays
     * @return: the median of the given k sorted arrays
     */
    public double findMedian(int[][] nums) {
        //check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int size = getTotalSize(nums);
        
        if (size == 0) {
            return 0;
        }
        
        if (size % 2 == 1) {
            return findKth(nums, size / 2 + 1);
        }
        else {
            return (findKth(nums, size / 2) / 2.0) + (findKth(nums, size / 2 + 1) / 2.0);
        }
    }
}