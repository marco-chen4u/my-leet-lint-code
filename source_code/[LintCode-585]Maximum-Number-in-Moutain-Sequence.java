/***
* LintCode 585. Maximum Number in Mountain Sequence
Given a mountain sequence of n integers which increase firstly and then decrease, find the mountain top(Maximum).

Example 1:
    Input: nums = [1, 2, 4, 8, 6, 3] 
    Output: 8
    
Example 2:
    Input: nums = [10, 9, 8, 7]
    Output: 10
***/
//version-1: binary search
public class Solution {
    /**
     * @param nums: a mountain sequence which increase firstly and then decrease
     * @return: then mountain top
     */
    public int mountainSequence(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        if (nums.length == 1) {
            return nums[0];
        }
        
        int defaultPeak = nums[0];
        
        int start = 1;// start from the 2nd element instead of the deault(1st) one
        int end = nums.length - 2;// end to the last 2nd element of the array, instead of the last one.
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid;
            }
            else if (nums[mid] < nums[mid - 1]) {
                end = mid;
            }
            else {
                end = mid;
            }
        }
        
        if (nums[start] > nums[end]) {
            return Math.max(defaultPeak, nums[start]);
        }
        else {
            return Math.max(defaultPeak, nums[end]);
        }
    }
}
