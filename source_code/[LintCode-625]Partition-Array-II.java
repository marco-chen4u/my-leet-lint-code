/***
* LintCode 625. Partition Array II
Partition an unsorted integer array into three parts:
    1.The front part < low
    2.The middle part >= low & <= high
    3.The tail part > high
Return any of the possible solutions.
(low <= high in all testcases)

Example 1:
    Input:
        [4,3,4,1,2,3,1,2]
        2
        3
    Output:
        [1,1,2,3,2,3,4,4]
    Explanation:
        [1,1,2,2,3,3,4,4] is also a correct answer, but [1,2,1,2,3,3,4,4] is not

Example 2:
    Input:
        [3,2,1]
        2
        3
    Output:
        [1,2,3]

Challenge
    1.Do it in place.
    2.Do it in one pass (one loop).
***/
//version-1: two[three] pointers, in-place swap, time-complexity: O(n)
public class Solution {
    /**
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: nothing
     */
    public void partition2(int[] nums, int low, int high) {
        // check corner cases
        if (nums == null || nums.length <= 1) {
            return;
        }

        // regular case
        int size = nums.length;
        int index = 0;
        int left = 0;
        int right = size - 1;

        while (index <= right) {
            if (nums[index] < low) {
                swap(nums, left, index);
                index++;
                left++;
            }
            else if (nums[index] > high) {
                swap(nums, index, right);
                right--;
            }
            else {
                index++;
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

//version-2: two pointers, quick-select(partition), time-complexity: O(n)
public class Solution {
    /**
     * @param nums: an integer array
     * @param low: An integer
     * @param high: An integer
     * @return: nothing
     */
    public void partition2(int[] nums, int low, int high) {
        // check corner cases
        if (nums == null || nums.length <= 1) {
            return;
        }

        int size = nums.length;
        int cut = partition(nums, 0, size - 1, low);

        if (cut < size - 1) {
            partition(nums, cut, size - 1, high + 1);
        }
    }

    // helper methods
    private int partition(int[] nums, int start, int end, int pivot) {
        // check corner case
        if (start == end) {
            return end;
        }

        // regular case
        int left = start;
        int right = end;

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] >= pivot) {
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

        if (left < nums.length && nums[left] >= pivot) {
            return left;
        }

        return right;
    }


}
