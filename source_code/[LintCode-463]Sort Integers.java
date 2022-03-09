/***
* LintCode 463. Sort Integers
Given an integer array, sort it in ascending order. 
Use selection sort, bubble sort, insertion sort or any O(n2) algorithm.

Example 1:
    Input:  [3, 2, 1, 4, 5]
    Output: [1, 2, 3, 4, 5]	
    Explanation: 
        return the sorted array.

Example 2:
    Input:  [1, 1, 2, 1, 1]
    Output: [1, 1, 1, 1, 2]	
    Explanation: 
        return the sorted array.
***/
//version-1: quick sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        quickSelect(nums, 0, nums.length - 1);
    }
	
    // helper method
    private void quickSelect(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

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
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
                right--;
            }
        }

        quickSelect(nums, start, right);
        quickSelect(nums, left, end);
    }
}

//version-2: selection sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int min = nums[i];

            int index = -1;
            for (int j = i + 1; j < size; j++) {
                if (min > nums[j]) {
                    min = nums[j];
                    index = j;
                }
            }

            if (index != -1) {
                swap(nums, i, index);
            }			
        }		
    }

    // helper method
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

//version-3: insert sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int value = nums[i];
                int index = i - 1;				
                while (index >= 0 && value < nums[index]) {
                    nums[index + 1] = nums[index];
                    index--;
                }

                nums[index + 1] = value;	
            }				
        }
    }
}

// version-4: bubble sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }

        int size = nums.length;
	int lastPos = size - 1;
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < lastPos - i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                }
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// version-5: merge sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        mergeSort(nums, 0, nums.length - 1);
    }

    // helper method
    private void mergeSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);

        merge(nums, start, end);
    }
	
    private void merge(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }

        int mid = start + (end - start) / 2;
        int[] left = new int[mid - start + 1];
        int[] right = new int[end - mid];
        for (int i = 0; i < left.length; i++) {
            left[i] = nums[start + i];
        }
        for (int j = 0; j < right.length; j++) {
            right[j] = nums[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int index = start;

        while (i < left.length && j < right.length) {
            nums[index++] = (left[i] <= right[j]) ? left[i++] : right[j++];
        }

        while (i < left.length) {
            nums[index++] = left[i++];
        }

        while (j < right.length) {
            nums[index++] = right[j++];
        }
    }
}

//version-6: Heap Sort
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void sortIntegers(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        heapSort(nums);
    }

    // helper methods
    private void heapSort(int[] nums) {
        int heapSize = nums.length;
        buildHeap(nums, heapSize);

        while(heapSize > 1) {
            swap(nums, 0, heapSize - 1);
            heapSize--;
            heapify(nums, heapSize, 0);
        }

    }

    private void buildHeap(int[] nums, int heapSize) {
        for (int i = nums.length / 2; i >= 0; i--) {
            heapify(nums, heapSize, i);
        }
    }

    private void heapify(int[] nums, int heapSize, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;		
        int largest = i;

        if (left < heapSize && nums[left] > nums[largest]) {
            largest = left;
        }

        if (right < heapSize && nums[right] > nums[largest]) {
            largest = right;
        }

        if (largest != i) {
            swap(nums, i, largest);
            heapify(nums, heapSize, largest);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
