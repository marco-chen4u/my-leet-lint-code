/**
* LintCode 548. Intersection of Two Arrays II
* Given two arrays, write a function to compute their intersection.
    -Each element in the result should appear as many times as it shows in both arrays.
    -The result can be in any order.
    
Example1
    Input: 
        nums1 = [1, 2, 2, 1], nums2 = [2, 2]
    Output: 
        [2, 2]

Example2
    Input: 
        nums1 = [1, 1, 2], nums2 = [1]
    Output: 
        [1]

Challenge
    -What if the given array is already sorted? How would you optimize your algorithm?
    -What if nums1's size is small compared to num2's size? Which algorithm is better?
    -What if elements of nums2 are stored on disk, 
     and the memory is limited such that you cannot load all elements into the memory at once?
    
Link: https://www.lintcode.com/problem/548/

Tags:
    Hash Table, Two Pointers, Same Direction of Two Pointers
**/
//version-1: HashMap
public class Solution {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (isEmpty(nums1)) {
            return nums1;
        }

        if (isEmpty(nums2)) {
            return nums2;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int value : nums1) {
            map.put(value, map.getOrDefault(value, 0) + 1);
        }

        List<Integer> values = new ArrayList<>();

        for (int value : nums2) {
            if (map.containsKey(value) && map.get(value) > 0) {
        	map.put(value, map.get(value) - 1);
        	values.add(value);
            }
        }

        int[] result = new int[values.size()];
        int index = 0;
        for (int value : values) {
            result[index++] = value;
        }

        return result;
    }

    // helper method
    private boolean isEmpty(int[] nums) {
	    return nums == null || nums.length == 0;
    }	
    
}

//version-2: sort and merge with two pointers
public class Solution {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (isEmpty(nums1)) {
           return nums1;
        }

        if (isEmpty(nums2)) {
            return nums2;
        }

        quickSort(nums1, 0, nums1.length - 1);
        quickSort(nums2, 0, nums2.length - 1);

        int i = 0;
        int j = 0;

        List<Integer> values = new ArrayList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
                continue;
            }

            if (nums1[i] > nums2[j]) {
                j++;
                continue;
            }

            values.add(nums1[i]);
            i++;
            j++;
        }

        int[] result = new int[values.size()];
        int index = 0;
        for (int value : values) {
            result[index++] = value;
        }
        
        return result;
    }

    // helper methods
    private boolean isEmpty(int[] nums) {
        return nums == null || nums.length == 0;
    }

    private void quickSort(int[] nums, int start, int end) {
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

        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}
