/***
* LintCode 610. Two Sum - Difference equals to target
Given an array of integers, find two numbers that their difference equals to a target value.
where index1 must be less than index2. Please note that your returned answers (both index1 and index2) are NOT zero-based.

Example
    Example 1:
        Input: nums = [2, 7, 15, 24], target = 5 
        Output: [1, 2] 
        Explanation:
            (7 - 2 = 5)

    Example 2:
        Input: nums = [1, 1], target = 0
        Output: [1, 2] 
        Explanation:
            (1 - 1 = 0)

Notice
    It's guaranteed there is only one available solution
***/
//version-1: 2 pointers, sliding window, time complexity: O(nlogn)[/w sorting], process /w O(n)
public class Solution {
    // inner class
    class Pair {
        //fields
        int index;
        int val;
        //constructor
        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
    
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        int[] result = new int[2];
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        if (target < 0) {
            target = - target;
        }
        
        // intialize
        int size = nums.length;
        Pair[] pairs = new Pair[size];
        for (int i = 0; i < size; i++) {
            pairs[i] = new Pair(i + 1, nums[i]);
        }
        
        Arrays.sort(pairs, (a, b)-> a.val - b.val);
        
        int j = 0;
        for (int i = 0; i < size; i++) {
            
            while ((j == i) || (j < size && pairs[j].val - pairs[i].val < target)) {
                j++;
            }
            
            if (j < size && pairs[j].val - pairs[i].val == target) {
                result[0] = Math.min(pairs[i].index, pairs[j].index);
                result[1] = Math.max(pairs[i].index, pairs[j].index);
                break;
            }
        }
        
        return result;
    }
}

// version-2: map, space complexity O(n)
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [index1 + 1, index2 + 1] (index1 < index2)
     */
    public int[] twoSum7(int[] nums, int target) {
        int[] result = new int[2];
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target + nums[i]) && i != map.get(target + nums[i])) {
                result[0] = Math.min(map.get(target + nums[i]), i) + 1;
                result[1] = Math.max(map.get(target + nums[i]), i) + 1;
            }
        }
        
        return result;
    }
}

updated:
/***
* LintCode 610. Two Sum - Difference equals to target
Given an sorted array of integers, find two numbers that their difference equals to a target value.
Return a list with two number like [num1, num2] that the difference of num1 and num2 equals to target value, and num1 is less than num2.

Example 1:
    Input: nums = [2, 7, 15, 24], target = 5 
     Output: [2, 7] 
    Explanation:
        (7 - 2 = 5)
Example 2:
    Input: nums = [1, 1], target = 0
     Output: 1, 1] 
    Explanation:
        (1 - 1 = 0)
        
Notice
    It's guaranteed there is only one available solution
    Note: Requires O(1) space complexity to comple
***/

//version-3:two pointers(slow + fast pointers), sliding window
public class Solution {
    /**
     * @param nums: an array of Integer
     * @param target: an integer
     * @return: [num1, num2] (num1 < num2)
     */
    public int[] twoSum7(int[] nums, int target) {
        int DEFAULT_VALUE = -1;
        int[] DEFAULT_RESULT = new int[] {DEFAULT_VALUE, DEFAULT_VALUE};
        // check corner cases
        if (nums == null || nums.length <=1) {
            return DEFAULT_RESULT;
        }

        // normal case
        int size = nums.length;
        target = Math.abs(target);
        
        int i;// slow pointer
        int j;// fast pointer

        for (i = 0, j = i + 1; i < size; i++) {
            j = (j <= i) ? i + 1 : j;// make sure fast pointer is right ahead of the slow on

            while (j < size && nums[j] - nums[i] < target) {
                j++;
            }

            if (j < size && nums[j] - nums[i] == target) {
                return new int[]{nums[i], nums[j]};
            }
        }

        return DEFAULT_RESULT;
    }
}
