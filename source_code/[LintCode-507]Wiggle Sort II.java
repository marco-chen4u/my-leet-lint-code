/***
* LintCode 507. Wiggle Sort II
Given an unsorted array nums, reorder it such that
nums[0] < nums[1] > nums[2] < nums[3]....

Example
	Example 1
		Input: nums = [1, 5, 1, 1, 6, 4]
		Output: [1, 4, 1, 5, 1, 6]
	Example 2
		Input: nums = [1, 3, 2, 2, 3, 1]
		Output: [2, 3, 1, 3, 1, 2]
Challenge
	Can you do it in O(n) time and/or in-place with O(1) extra space?
Notice
	You may assume all input has valid answer.
	You just need to give one vaild answer.
***/
public class Solution {
    /*
     * @param nums: A list of integers
     * @return: nothing
     */
    public void wiggleSort(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }
        
        int size = nums.length;
        int[] values = new int[size];
        values = Arrays.copyOf(nums, size);
        
        int median = getMedian(values);
        int[] result = new int[size];
        Arrays.fill(result, median);
        
        int left = 1;
        int right = (size % 2 == 0) ? size - 2 : size - 1;
        for (int i = 0; i < size; i++) {
            if (nums[i] < median) {
                result[right] = nums[i];
                right -= 2;
            } else if (nums[i] > median) {
                result[left] = nums[i];
                left += 2;
            }
        }
        
        for (int i = 0; i < size; i++) {
            nums[i] = result[i];
        }
    }
    
    // helper methods
    private int getMedian(int[] values) {
        int size = values.length;
        int start = 0;
        int end = size - 1;
        int k = size / 2;
        
        return partition(values, start, end, k);
    }

    private int partition(int[] nums, int start, int end, int k) {
        int left = start, right = end;
        int now = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= now) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= now) {
                left++;
            }
            nums[right] = nums[left];
        }
        if (left - start == k) {
            return now;
        } else if (left - start < k) {
            return partition(nums, left + 1, end, k - (left - start + 1));
        } else {
            return partition(nums, start, right - 1, k);
        }
    }
    
    // private int partition(int[] values, int start, int end, int k) {
    //     int left = start;
    //     int right = end;
        
    //     int mid = left + (right - left) / 2;
    //     int pivot = values[mid];
        
    //     //(1) doing partion by this pivot above
    //     while (left <= right) {
    //         while (left <= right && values[left] < pivot) {
    //             left++;
    //         }
            
    //         while (left <= right && values[right] > pivot) {
    //             right--;
    //         }
            
    //         if (left <= right) {
    //             int tmp = values[left];
    //             values[left] = values[right];
    //             values[right] = tmp;
                
    //             left++;
    //             right--;
    //         }
    //     }
        
    //     //(2) after done with partion by pivot above, index right <= left.
    //     if (k < right) {
    //         return partition(values, start, right, k);
    //     }
    //     else if (k > left) {
    //         return partition(values, left, end, k);
    //     }
    //     else {
    //         return values[k];
    //     }
    // }
}