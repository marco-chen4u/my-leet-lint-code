/**
* LintCode 547. Intersection of Two Arrays
* Given two arrays, write a function to compute their intersection.
Example 1:
    Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2], 
    Output: [2].

Example 2:
    Input: nums1 = [1, 2], nums2 = [2], 
    Output: [2].

Challenage:
    Can you implement it in three different algorithms?

Link: https://www.lintcode.com/problem/547/    

Related problem:
    LintCode 548. Intersection of Two Arrays II 
    LintCode 248. Count of Smaller Number

Link: 
    LintCode: https://www.lintcode.com/problem/547
    LeetCode: https://leetcode.com/problems/intersection-of-two-arrays/
**/

//version-1: sort & binary search
public class Solution {
    /**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     *          we will sort your return value in output
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // corner case
        if (nums1 == null || nums1.length == 0) {
            return nums1;
        }

        if (nums2 == null || nums2.length == 0) {
            return nums2;
        }

        // normal case
        this.sort(nums1);
        this.sort(nums2);

        Set<Integer> set = new HashSet<>();
        for (int num : nums1) {
            if (!find(nums2, num)) {
                continue;
            }

            set.add(num);
        }

        int size = set.size();
        int index = 0;
        int[] result = new int[size];
        for (int value : set) {
            result[index++] = value;
        }

        return result;
    }

    // helper method
    private boolean find(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        if (nums[left] == target) {
            return true;
        }

        if (nums[right] == target) {
            return true;
        }

        return false;// not found
    }

    private void sort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
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

//version-2: sort & merge
public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0, j = 0;
        int[] temp = new int[nums1.length];
        int index = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] == nums2[j]) {
                if (index == 0 || temp[index - 1] != nums1[i]) {
                    temp[index++] = nums1[i];
                }
                i++;
                j++;
                continue;
            } 
            
            if (nums1[i] < nums2[j]) {
                i++;
                continue;
            } 
            if (nums1[i] > nums2[j]){
                j++;
            }
        }
        
        int[] result = new int[index];
        for (int k = 0; k < index; k++) {
            result[k] = temp[k];
        }
        
        return result;
    }
}

//version-3: hashmap
public class Solution {
    /**
     * @param nums1 an integer array
     * @param nums2 an integer array
     * @return an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        
        HashSet<Integer> hash = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            hash.add(nums1[i]);
        }
        
        HashSet<Integer> resultHash = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (hash.contains(nums2[i]) && !resultHash.contains(nums2[i])) {
                resultHash.add(nums2[i]);
            }
        }
        
        int size = resultHash.size();
        int[] result = new int[size];
        int index = 0;
        for (Integer num : resultHash) {
            result[index++] = num;
        }
        
        return result;
    }
}
