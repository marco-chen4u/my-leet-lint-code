/**
* LeetCode 1539. Kth Missing Positive Number
Given an array nums of positive integers sorted in a strictly increasing order, and an integer k.

Find the kth positive integer that is missing from this array.

Example 1:
    Input: nums = [2,3,4,7,11], k = 5
    Output: 9
    Explanation: The missing positive integers are [1,5,6,8,9,10,12,13,...]. The 5th missing positive integer is 9.

Example 2:
    Input: nums = [1,2,3,4], k = 2
    Output: 6
    Explanation: The missing positive integers are [5,6,7,...]. The 2nd missing positive integer is 6.
    
Constraints:
    1 <= nums.length <= 1000
    1 <= nums[i] <= 1000
    1 <= k <= 1000
    nums[i] < nums[j] for 1 <= i < j <= nums.length
    
Related topics:
    Array, Binary Search
**/
//version-1: brute force, time complexity: O(n)
class Solution {
    public int findKthPositive(int[] nums, int k) {
        
        // corner case -1 
        // check if the nums[0] is missing
        if (nums[0] >= k + 1) {
            return k;
        }
        
        // normal case
        k -= nums[0] - 1;
        
        int size = nums.length;
        int lastPos = size - 1;
        
        for (int i = 0; i < size - 1; i++) {
            int missing = nums[i + 1] - (nums[i] + 1);
            
            if (missing >= k) {
                return nums[i] + k;
            }
            
            k -= missing;
        }
        
        return nums[lastPos] + k;
    }
}

//version-2: Binary Search, time complexity: O(log(n))
class Solution {
    public int findKthPositive(int[] nums, int k) {
      
        int size = nums.length;
        int left = 0;
        int right = size - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] - mid <= k) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        //System.out.println("left= " + left);
        
        return left + k;
    }
}
