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

        int i = indexToReplace + 1;
        while (i < size && nums[i] > pivot) {
            i++;
        }

        swap(nums, indexToReplace, i - 1);

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
