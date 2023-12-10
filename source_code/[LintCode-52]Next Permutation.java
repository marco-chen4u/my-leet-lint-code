/***
* LintCode 52. Next Permutation
Given a list of integers, which denote a permutation.
Find the next permutation in ascending order.


Example 1
    Input:[1]
    Output:[1]

Example 2
    Input:[1,3,2,3]
    Output:[1,3,3,2]

Example 3
    Input:[4,3,2,1]
    Output:[1,2,3,4]

Notice
    The list may contains duplicate integers.
***/
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        int[] result = nums;
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        // normal case
        int size = nums.length;
        int lastPos = size - 1;
        int index = lastPos;
        while (index > 0 && nums[index - 1] >=nums[index]) {
            index--;
        }
        
        reverse(nums, index, lastPos);
        
        if (index != 0) {
            int pivotIndex = index - 1;
            int pivot = nums[index - 1];
            
            int i = index;
            while (i < size && nums[i] <= pivot) {
                i++;
            }
            
            swapItem(nums, pivotIndex, i);
        }
        
        return result;
    }
    
    // helper methods
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swapItem(nums, left, right);
            
            left++;
            right--;
        }
    }
    
    private void swapItem(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }
        
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

// solution-2: inplace + sort[quick-sort]  note: quick-sort's time complexity is O(nlogn)
/*
 find the index to replace and the cadidate which is just a little bit larger value than this to-replace item, then do the swap, and sort the rest part after the index to replace
*/
class Solution {
    public void nextPermutation(int[] nums) {
        int size = nums.length;

        int lastIndex = size - 1;
        int indexToReplace = lastIndex - 1;

        while (indexToReplace >= 0) {
            if (nums[indexToReplace] < nums[indexToReplace + 1]) break;
            indexToReplace--;
        }

        //System.out.println("indexToReplace = " + indexToReplace);
        if (indexToReplace == -1) {
            quickSort(nums, 0, lastIndex);
            return;
        }

        int largerIndex = lastIndex;
        while (largerIndex > indexToReplace && nums[largerIndex] <= nums[indexToReplace]) {
            largerIndex--;
        }
        //System.out.println("largerIndex = " + largerIndex);

        swap(nums, indexToReplace, largerIndex);

        quickSort(nums, indexToReplace + 1, lastIndex);
    }

    // helper methods
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];

        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) {
            return;
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

        quickSort(nums, start, right);
        quickSort(nums, left, end);
    }
}
