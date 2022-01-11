/***
* LintCode 31. Partition Array
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:
    -All elements < k are moved to the left
    -All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.

note:
    You should do really partition in array nums instead of just counting the numbers of integers smaller than k.
    If all elements in nums are smaller than k, then return nums.length
    0<=nums.length<=2000

Example 1:
    Input:
        nums = []
        k = 9
    Output:
        0

Example 2:
    Input:
        nums = [3,2,2,1]
        k = 2
    Output:
        1
    Explaination:
        the real array is [1,2,2,3]. So return 1

Challenge
    Can you partition the array in-place and in O(n)O(n)?
***/
//version-1: two pointers, partition the array in-place, time complexity: O(n)
public class Solution {
    /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        int result = 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        // normal case
        int left = 0;
        int right = nums.length - 1;

        int pivot = k;

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] >= pivot) {
                right--;
            }

            // check corner case
            if (left > right) {
                break;
            }

            // swap values in index-left and inedex-right
            int tmp = nums[left];
            nums[left] = nums[right];
            nums[right] = tmp;

            left++;
            right--;
        }

        return left;
    }
}


// class Solution:
//     """
//     @param nums: The integer array you should partition
//     @param k: An integer
//     @return: The index after partition
//     """
//     def partitionArray(self, nums, k):
//         print("input array = " , nums)

//         left, right = 0, len(nums) - 1
//         while left <= right:
//             while left <= right and nums[left] < k:
//                 left += 1
//             while left <= right and nums[right] >= k:
//                 right -= 1
//             if left <= right:
//                 nums[left], nums[right] = nums[right], nums[left]
//                 left += 1
//                 right -= 1

//         print("after partition, left pointer has move to :" , left)
//         print("after partition, right pointer has move to :" , right)
//         print("after partition, the array output = " , nums)

//         return left
        

