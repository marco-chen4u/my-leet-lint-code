/***
* LintCode 461. Kth Smallest Numbers in Unsorted Array
Find the kth smallest number in an unsorted integer array.

Example 1:
    Input: [3, 4, 1, 2, 5], k = 3
    Output: 3

Example 2:
    Input: [1, 1, 1], k = 2
    Output: 1

Challenge
    An O(nlogn) algorithm is acceptable, if you can do it in O(n), that would be great.
***/
public class Solution {
    
    // helper methods
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[k];
        }
        
        int left = start;
        int right = end;
        int mid = start + (end - start) / 2;
        int pivot = nums[mid];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swap(nums, left, right);
                
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return quickSelect(nums, start, right, k);
        }
        else if (k >= left) {
            return quickSelect(nums, left, end, k);
        }
        else {
            return nums[k];
        }
    }
    
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0 || k < 1 || k > nums.length) {
            return 0;
        }
        
        return quickSelect(nums, 0, nums.length - 1, k - 1);
    }
}

public class Solution {
    /**
     * @param k: An integer
     * @param nums: An integer array
     * @return: kth smallest element
     */
    public int kthSmallest(int k, int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (k < 1 || k > nums.length) {
            return 0;
        }
        
        Queue<Integer> maxHeap = new PriorityQueue<Integer>(k, (a, b)-> b - a);
        
        for (int num : nums) {
            maxHeap.offer(num);
            
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        
        return maxHeap.peek();
    }
}
