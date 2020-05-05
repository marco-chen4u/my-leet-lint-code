/***
 * LintCode 606. Kth Largest Element II
Find K-th largest element in an array. and N is much larger than k.

Example
    Example 1:
        Input:[9,3,2,4,8],3
        Output:4
    
    Example 2:
        Input:[1,2,3,4,5,6,8,9,10,7],10
        Output:1

Notice
    You can swap elements in the array
***/
public class Solution {
    /**
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length <= 0 || k < 1) {
            return 0;
        }
        
        Queue<Integer> minHeap = new PriorityQueue<Integer>();
        
        for (int num : nums) {
            minHeap.offer(num);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        return minHeap.peek();
    }
}

public class Solution {
    // helper methods
    private void swapItem(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private int quickSelect(int[] nums, int start, int end, int k) {
        int left = start;
        int right = end;
        
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            
            if (left <= right) {
                swapItem(nums, left, right);
                
                left++;
                right--;
            }
        }
        
        if (k <= right) {
            return quickSelect(nums, start, right, k);
        }
        
        if (k >= left) {
            return quickSelect(nums, left, end, k);
        }
        
        return nums[k];
    }
    
    /**
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (k <= 0) {
            return 0;
        }
        
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }
}