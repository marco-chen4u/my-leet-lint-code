/**
* LintCode 460. Find K Closest Elements
Given target, a non-negative integer k and an integer array A sorted in ascending order, 
find the k closest numbers to target in A, 
sorted in ascending order by the difference between the number and target. 
Otherwise, sorted in ascending order by number if the difference is same.

Note:
    1.The value k is a non-negative integer and will always be smaller than the length of the sorted array.
    2.Length of the given array is positive and will not exceed 10^4​​ 
    3.Absolute value of elements in the array will not exceed 10^4

Example 1:
    Input: A = [1, 2, 3], target = 2, k = 3
    Output: [2, 1, 3]
Example 2:
    Input: A = [1, 4, 6, 8], target = 3, k = 3
    Output: [4, 1, 6]
**/
//solution-1
public class Solution {
    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        int[] defaultValue = new int[0];
        // check corner cases
        if (nums == null || nums.length == 0) {
            return defaultValue;
        }

        int size = nums.length;
        if (k <= 0) {
            return defaultValue;
        }

        if (k > size) {
            return nums;
        }

        // regular case
        int left = 0;
        int right = size - 1;

        while (left + 1 < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid;
            }
            else {
                right = mid;
            }
        }

        int[] result = new int[k];
        Arrays.fill(result, 0);
        int index = 0;
        while (left >= 0 && right < size  && index < k) {
            result[index++] = Math.abs(nums[left] - target) <= Math.abs(nums[right] - target) ? nums[left--] : nums[right++];
        }

        while (left >= 0 && index < k) {
            result[index++] = nums[left--];
        }

        while (right < size && index < k) {
            result[index++] = nums[right++];
        }

        return result;
    }
}

//solution-2: MinHeap
public class Solution {

    // inner class
    class Item implements Comparable<Item> {
        // fields
        int value;
        int target;
        int diffVal;

        // constructor
        public Item(int value, int target) {
            this.value = value;
            this.target = target;
            this.diffVal = Math.abs(target - value);
        }

        @Override
        public int compareTo(Item other) {
            if (this.diffVal != other.diffVal) {
                return this.diffVal - other.diffVal;
            }

            return this.value - other.value;
        }        
    }

    /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public int[] kClosestNumbers(int[] nums, int target, int k) {
        int[] defaultValue = new int[0];
        // check corner case
        if (nums == null || nums.length == 0) {
            return defaultValue;
        }
        
        int size = nums.length;

        if (k <= 0) {
            return defaultValue;
        }

        if (k > size) {
            return nums;
        }
        
        k = (k > size) ? size : k;

        // regular case
        Queue<Item> minHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int num : nums) {
            minHeap.offer(new Item(num, target));

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];
        Arrays.fill(result, 0);

        int index = k -1;

        while (!minHeap.isEmpty()) {
            Item item = minHeap.poll();
            result[index--] = item.value;
        }

        return result;
    }
}
