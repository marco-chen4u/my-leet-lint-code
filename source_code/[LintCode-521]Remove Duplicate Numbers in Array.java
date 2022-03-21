/***
* LintCode 521. Remove Duplicate Numbers in Array
Given an array of integers, remove the duplicate numbers in it.

You should:
    Do it in place in the array.
    Move the unique numbers to the front of the array.
    Return the total number of the unique numbers.

Example
    Example 1:
        Input:
        nums = [1,3,1,4,4,2]
        Output:
            [1,3,4,2,?,?]
            4
        Explanation:
            Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
            Return the number of unique integers in nums => 4.
            Actually we don't care about what you place in ?, 
            we only care about the part which has no duplicate integers.

    Example 2:
        Input:
        nums = [1,2,3]
        Output:
            [1,2,3]
            3

Challenge
    Do it in O(n) time complexity.
    Do it in O(nlogn) time without extra space.
Notice
    You don't need to keep the original order of the integers.
***/
//version-1: slow-fast pointers, without set/map, fair solution
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        // check conrer case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        
        int i = 0, j = 1;
        while (j < nums.length) {
            if (nums[i] != nums[j]) {
                i += 1;
                nums[i] = nums[j];
            }
            
            j++;
        }
        
        return i + 1;
    }
}

// version-2: two pointers, without set/map, better solution
public class Solution {
    /**
     * @param nums: an array of integers
     * @return: the number of unique integers
     */
    public int deduplication(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return 1;
        }

        Arrays.sort(nums);

        int left = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[left]) {
                nums[++left] = nums[i];
            }
        }

        return left + 1;// it's 0 based index, so we need to add 1 for the actual size
    }
}
