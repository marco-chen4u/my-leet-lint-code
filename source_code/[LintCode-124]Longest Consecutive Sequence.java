/***
 * LintCode 124. Longest Consecutive Sequence
Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

Example
    Given [100, 4, 200, 1, 3, 2],
    The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Clarification
    Your algorithm should run in O(n) complexity.
***/
//version-1: iteration + HashSet
public class Solution {
    /**
     * @param num: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        int result = 0; // default value
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        // initialize
        Set<Integer> set = new HashSet<Integer>();
        for (int num : nums) {
            set.add(num);
        }
        
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            
            if (set.contains(num)) {
                
                set.remove(num);
                
                // two pointers from the middle-line
                int left = num - 1;
                int right = num + 1;
                
                while (set.contains(left)) {
                    set.remove(left);
                    left--;
                }
                
                while (set.contains(right)) {
                    set.remove(right);
                    right++;
                }
                
                result = Math.max(result, right - left - 1);
            }
        }
        
        return result;
    }
}

//version-2: iteration + HashSet
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        int max = 1;
        for (int num : nums) {
            if (!set.contains(num -1)) {
                continue;
            }

            int start = num - 1;
            int current = num;
            int length = 1;
            while (set.contains(current)) {
                current++;
                length++;
            }

            max = Math.max(max, current - start);
        }

        return max;
    }
}

//version-3: sort, then iteration with calculate. time complexity: O(nlogn)
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);
        int size = nums.length;

        int max = 1;
        int currentLength = 1;
        for (int i = 0; i < size; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] + 1) {
                currentLength++;
                max = Math.max(max, currentLength);
            }
            else {
                currentLength = 1;// reset for the next calculation
            }

        }

        return max;
    }
}

//version-4: iteraion with minHeap + HashSet to remove duplciates, time complexity: O(nlogn), space complexity: O(logn)
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // corner case
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Queue<Integer> minHeap = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (set.contains(num)) {
                continue;
            }

            set.add(num);
            minHeap.offer(num);
        }

        int size = nums.length;

        int max = 1;
        int currentLength = 1;
        int pre = minHeap.poll();
        
        while (!minHeap.isEmpty()) {
            int current = minHeap.poll();

            if (current == pre + 1) {
                currentLength ++;
                max = Math.max(max, currentLength);
            }
            else {
                currentLength = 1; //reset it for the next calculation
            }

            pre = current;
        }

        return max;
    }
}

//version-5: minHeap, handle the duplicates inside the iteration of minHeap
public class Solution {
    
    /**
     * @param nums: A list of integers
     * @return: An integer
     */
    public int longestConsecutive(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Queue<Integer> minHeap = new PriorityQueue();
        for (int num : nums) {
            minHeap.offer(num);
        }

        int pre = minHeap.poll();
        int result = 1;
        int currentSize = 1;
        while (!minHeap.isEmpty()) {
            int current = minHeap.poll();

            if (pre == current) {
                continue;
            }

            if (pre + 1 == current) {
                currentSize += 1;
                result = Math.max(result, currentSize);
            }
            else {
                currentSize = 1;
            }

            pre = current;
        }

        return result;
    }
}
