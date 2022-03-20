/***
* LintCode 544. Top k Largest Numbers
Given an integer array, find the top k largest numbers in it.

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
        if (start >= end || start >= k) {
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

//version-3: heapify
public class Solution {
    
    // fields
    private int heapSize; //heap size

    /**
     * @param nums: an integer array
     * @param k: An integer
     * @return: the top k largest numbers in array
     */
    public int[] topk(int[] nums, int k) {
        // corner cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        k = Math.min(k, n);

        heapify(nums);
        
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = pop(nums);
        }

        return result;
    }

    // helper methods
    private void heapify(int[] nums) {
        heapSize = nums.length;

        for (int i = heapSize / 2; i >= 0; i--) {
            siftDown(nums,i);
        }

    }

    private void siftDown(int[] nums, int index) {
        while (index < heapSize) {
            int left = index * 2 + 1;
            int right = index * 2 + 2;

            int max = index;

            if (left < heapSize && nums[left] > nums[max]) {
                max = left;
            }

            if (right < heapSize && nums[right] > nums[max]) {
                max = right;
            }

            if (max == index) {
                break;
            }

            int tmp = nums[index];
            nums[index] = nums[max];
            nums[max] = tmp;

            index = max;
        }
    }

    private int pop(int[] nums) {
        int result = nums[0];

        int lastPos = heapSize - 1;
        nums[0] = nums[lastPos];

        heapSize -= 1;

        siftDown(nums, 0);

        return result;
    }
}
