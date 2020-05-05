/***
* LintCode 5. Kth Largest Element

Find K-th largest element in an array.

Example
	Example 1:
		Input:
			n = 1, nums = [1,3,4,2]
		Output:
			4

	Example 2:
		Input:
			n = 3, nums = [9,3,2,4,8]
		Output:
			4

Challenge
	O(n) time, O(1) extra memory.

Notice
	You can swap elements in the array
***/
//version-1
public class Solution {
    
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        int result = 0; // default value
        // check corner case
        if (nums == null || nums.length == 0 || n < 0 || n > nums.length) {
            return result;
        }
        
        result = partition(nums, 0, nums.length - 1, nums.length - n);
        
        return result;
    }
	
	// helper method
    private int partition(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        
        int left = start;
        int right = end;
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        
        //(1) doing partion by this pivot above
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            
            while (left <= right && nums[right] > pivot) {
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
        
        // (2) after done with partion by pivot above, index right <= left.
        if (k <= right) {
            return partition(nums, start, right, k);
        }
        else if (k >= left) {
            return partition(nums, left, end, k);
        }
        else {
            return nums[k];
        }
    }
}

//version-2
public class Solution {
    /**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public int kthLargestElement(int n, int[] nums) {
        int result = 0; // default value
        // check corner case
        if (nums == null || nums.length == 0 || n < 0 || n > nums.length) {
            return result;
        }
        
        result = quickSelect(nums, 0, nums.length - 1, n - 1);
        
        return result;
    }
    
    // helper method
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        
        int left = start;
        int right = end;
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];
        
        //(1) doing partion by this pivot above
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
        
        // (2) after done with partion by pivot above, index right <= left.
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
}