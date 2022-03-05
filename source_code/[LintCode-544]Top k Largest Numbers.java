/***
* LintCode 544. Top k Largest Numbers
Given an integer array, find the top k largest numbers in it.
Example
Example1
Input: [3, 10, 1000, -99, 4, 100] and k = 3
Output: [1000, 100, 10]

Example2
Input: [8, 7, 6, 5, 4, 3, 2, 1] and k = 5
Output: [8, 7, 6, 5, 4]
***/

//version-1: Heap, time complexity: O(nlogk)
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }
        
        k = Math.min(k, nums.length);
        
        Queue<Integer> minHeap = new PriorityQueue<Integer>();
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        int[] result = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }
        
        return result;
    }
}

// version-2: quick sort, time complexity: O(n)
/*
* The time complexity for the average case for quick select is O(n) (reduced from O(nlogn) — quick sort). 
*The worst case time complexity is still O(n²) but by using a random pivot, 
*the worst case can be avoided in most cases.
* T(n) = T(n/2) + T(n/4) + T(n/8) + ....
*      = n(1 + 1/2 + 1/4 + 1/8 + ...)
*      = 2n
*/
public class Solution {
    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // check corner cases
        if (nums == null || nums.length == 0 || k < 1) {
            return new int[0];
        }
        
        k = Math.min(k, nums.length);
        
        quickSort(nums, 0, nums.length - 1, k);
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = nums[i];
        }
        
        return result;
    }
    
    private void quickSort(int[] nums, int start, int end, int k) {
        // check corner case
        if (start >= end) {
            return;
        }
        
        int left = start;
        int right = end;
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            
            if (left <= right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                
                left++;
                right--;
            }
        }
        
        quickSort(nums, start, right, k);
        quickSort(nums, left, end, k);
    }   
}
