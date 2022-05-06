/***
* LintCode 539. Move Zeroes
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.


Example 1:
    Input: nums = [0, 1, 0, 3, 12],
    Output: [1, 3, 12, 0, 0].

Example 2:
    Input: nums = [0, 0, 0, 3, 1],
    Output: [3, 1, 0, 0, 0].

Notice
    You must do this in-place without making a copy of the array.
    Minimize the total number of operations.
***/

/**
* 给定一个数组，写一个方法将0移动到数组的最后面，非零元素保持原数组的顺序。
* 1. 必须在原数组操作
* 2. 最少写操作（写做少次）
**/

//version-1
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return;
        }
        
        int index = 0;
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            if (nums[i] != 0) {
                int temp = nums[index];
                nums[index] = nums[i];
                nums[i] = temp;
                index += 1;
            }
        }
    }
}

//version-2
public class Solution {
    /**
     * @param nums an integer array
     * @return nothing, do this in-place
     */
    public void moveZeroes(int[] nums) {
        // Write your code here
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}

//version-3: best for more 0 in a array
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return;
        }

        // normal case
        int size = nums.length;
        int left = 0;
        int right = 0;

        while (left < size) {

            if (nums[left] != 0){
                left++;
                right++;
                continue;
            }
            
            while (right < size && nums[right] == 0) {
                right++;
            }

            if (right < size && nums[left] == 0) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }

            // next iteration
            left++;
        }
    }
}

//version-4:two pointers 同向双指针
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // fillPointer代表被填充的指针，指向将被非0数填充的位置
        int fillPointer = 0;
        // movePointer代表将被前移的指针，指向被前移的非0位置
        int movePointer = 0;
        int size = nums.length;

        // 如果前移指针没有越界，一直循环
        while (movePointer < size) {

            if (nums[movePointer] != 0) {
                // 只有填充指针 ！= 前移指针，两指针交换
                // 如果两指针相同，同时移动
                if (fillPointer != movePointer) {
                    swap(nums, fillPointer, movePointer);
                }

                fillPointer ++;
            }

            // 每次循环都要移动前移指针
            movePointer++;
        }
    }

    // helper methods
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

//version-5:同向双指针， 边界清0，这是最少的写操作，最佳答案
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        // fillPointer代表被填充的指针，指向将被非0数填充的位置
        int fillPointer = 0;
        // movePointer代表将被前移的指针，指向被前移的非0位置
        int movePointer = 0;
        int size = nums.length;

        // 如果前移指针没有越界，一直循环
        while (movePointer < size) {

            if (nums[movePointer] != 0) {
                // 只有填充指针 ！= 前移指针，两指针交换
                // 如果两指针相同，同时移动
                if (fillPointer != movePointer) {
                    nums[fillPointer] = nums[movePointer];//不需要交换，只需把有效数据填充(移动)到这个fillPointer位置的元素即可，减少了写操作，因为交换需要2个位置都要写，不划算
                }

                fillPointer ++;
            }

            // 每次循环都要移动前移指针
            movePointer++;
        }

        //这个时候fillPointer前的都是有效数据，后面的都是dirty data都得清零
        //把后续所有数据全部清空
        while (fillPointer < size) {
            if (nums[fillPointer] != 0) {
                nums[fillPointer] = 0;
            }
            
            fillPointer++;
        }
    }

    // helper methods
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

//version-6: two pointers, same-direciton, more elegant.
public class Solution {
    /**
     * @param nums: an integer array
     * @return: nothing
     */
    public void moveZeroes(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int index = 0;
        int left = 0;

        for (; index < nums.length; index++) {
            int current = nums[index];
            if (current == 0){
                continue;
            }

            swap(nums, left, index);
            left++;
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        if (nums[i] == nums[j]) {
            return;
        }

        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
