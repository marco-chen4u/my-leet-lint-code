/***
LeetCode 228. Summary Ranges
You are given a sorted unique integer array nums.

Return the smallest sorted list of ranges that cover all the numbers in the array exactly. That is, each element of nums is covered by exactly one of the ranges, and there is no integer x such that x is in one of the ranges but not in nums.

Each range [a,b] in the list should be output as:
    "a->b" if a != b
    "a" if a == b
 

Example 1:
    Input: nums = [0,1,2,4,5,7]
    Output: ["0->2","4->5","7"]
    Explanation: The ranges are:
        [0,2] --> "0->2"
        [4,5] --> "4->5"
        [7,7] --> "7"

Example 2:
    Input: nums = [0,2,3,4,6,8,9]
    Output: ["0","2->4","6","8->9"]
    Explanation: The ranges are:
        [0,0] --> "0"
        [2,4] --> "2->4"
        [6,6] --> "6"
        [8,9] --> "8->9"

Example 3:
    Input: nums = []
    Output: []

Example 4:
    Input: nums = [-1]
    Output: ["-1"]

Example 5:
    Input: nums = [0]
    Output: ["0"]
 

Constraints:
    0 <= nums.length <= 20
    -231 <= nums[i] <= 231 - 1
    All the values of nums are unique.
    nums is sorted in ascending order.
***/
class Solution {
    private final int DEFAULT = Integer.MAX_VALUE;
    
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        // check corner case 
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        int size = nums.length;
        int lastPos = size -1;
        
        if (size == 1) {
            result.add(getRange(nums[0], nums[0]));
            return result;
        }
        
        int pre = nums[0];
        int start = nums[0];
        int current = DEFAULT;
        
        for (int i = 1; i < size; i++) {
            current = nums[i];
            
            if (current > pre + 1) {
                result.add(getRange(start, pre));
                
                start = current;
                pre = current;
            }
            
            if (current == pre + 1) {
                
                pre = current;
            }
            
            if (i == lastPos) {
                result.add(getRange(start, current));
            }
        }
        
        return result;
    }
    
    // helper methods
    private String getRange(int start, int end) {
        if (start == end) {
            return String.valueOf(start);
        }
        
        return String.valueOf(start) + "->" + String.valueOf(end);
    }
}

