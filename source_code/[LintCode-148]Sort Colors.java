/***
* LintCode 148. Sort Colors (3 pointers)
Given an array with n objects colored red, white or blue, 
sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Example
    Given [1, 0, 1, 2], sort it in-place to [0, 1, 1, 2].

Challenge
    A rather straight forward solution is a two-pass algorithm using counting sort.
    First, iterate the array counting number of 0's, 1's, and 2's, 
        then overwrite array with total number of 0's, then 1's and followed by 2's.

    Could you come up with an one-pass algorithm using only constant space?

Notice
    You are not suppose to use the library's sort function for this problem.
    You should do it in-place (sort numbers in the original array).
***/
//version-1: 3 pointers + partition
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int left = 0;        
        int right = nums.length - 1;
        
        int pivot = 1;//the midle value of the color value[0, 1, 2]
        
        int i = 0;
        while (i <= right) {
            if (nums[i] < pivot) {
                swap(nums, left, i);
                left++;
                i++;
            }
            else if (nums[i] == pivot) {
                i++;
            }
            else {
                swap(nums, i, right);
                //这里为什么不i++，因为缓过来后nums[i]的值可能是0或1或2，如果是是2则需要继续交换
                right--;
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

//version-2: 3 pointers
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // check corner cases
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int size = nums.length;

        // normal case
        int zeroPointer = -1;
        int twoPointer = size;
        // i < twoPointer 是个很重要的条件
        // 一旦 i指针跟twoPointer指针重叠，就退出循环
        for (int i = 0; i < size && i < twoPointer; i++) {
            if (nums[i] == 0) {
                swap(nums, ++zeroPointer, i);
                // 为什么这里没有i--
                // 因为换过来的只可能是1， 不需要交换了
                // 当前指针的左边不可能有2
                // 所有的0也都小于zeroPointer增1的位置
            }
            else if (nums[i] == 2) {
                swap(nums, --twoPointer, i);
                i--;
                //为什么这里有i--
                // 因为换过来的可能是0或1或2，如果是0或2，需要继续交换
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

//version3: accounting即计数排序
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        if (nums == null || nums.length == 0) {
            return;
        }

        int[] colorCounts = new int[3];// for red[0], white[1], blue[2]
        for (int num : nums) {
            colorCounts[num]++;
        }

        int index = 0;
        int currentColor = 0;// start from red, then white, then blue
        for (int colorCount : colorCounts) {
            while (colorCount > 0) {
                nums[index++] = currentColor;
                
                colorCount--;
            }

            currentColor++;
        }
    }
}

//version-4: partitions
public class Solution {
    /**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        partition(nums, 1);
        partition(nums, 2);
    }

    // helper method
    private void partition(int[] nums, int pivot) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] >= pivot) {
                right--;
            }

            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
