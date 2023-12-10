/***
* LintCode 190. Next Permutation II
Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.

If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).

Example 1
    Input:1,2,3
    Output:1,3,2

Example 2
    Input:3,2,1
    Output:1,2,3

Example 3
    Input:1,1,5
    Output:1,5,1

Challenge
    The replacement must be in-place, do not allocate extra memory.
***/

//solution-1: inplace + iteration + sort
public class Solution {
    // field
    private final int DEFAULT_VALUE = -1;

    /**
     * @param nums: An array of integers
     * @return: nothing
     */
    public void nextPermutation(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }

        int size= nums.length;

        //[1] find the exact index position which is to be replace for the good candidate
        int indexToReplace = DEFAULT_VALUE;
        int pivot = DEFAULT_VALUE;
        for (int i = size - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                indexToReplace = i;
                pivot = nums[indexToReplace];

                break;
            }
        }

        if (pivot == DEFAULT_VALUE) {// the last index of permutation
            Arrays.sort(nums);
            return;
        }

        //[2] find the perfect candidate wich is just a little bit (>=) larger than the item-value where to replace done with step-[1]
        int i = indexToReplace + 1;
        while (i < size && nums[i] > pivot) {
            i++;
        }

        //[3] swap this candidates to the index position where found in step-[1]
        swap(nums, indexToReplace, i - 1);

        //[4] sort the rest part of [replaceIndex + 1, lastIndex] inside the array
        int startPos = indexToReplace + 1;
        int endPos = size;
        Arrays.sort(nums, startPos, endPos);
    }
    
    // helper method
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
//solution-2: same idea as solution-1 with different sort(quick sort implementation with 2-pointer strategy) 
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
