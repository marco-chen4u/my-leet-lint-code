/***
* LintCode 362. Sliding Window Maximum
Given an array of n integer with duplicate number, and a moving window(size k), 
move the window at each iteration from the start of the array, 
find the maximum number inside the window at each moving.

Example 1:
    Input:
        [1,2,7,7,8]
        3
    Output:
        [7,7,8]
    Explanation:
        At first the window is at the start of the array like this '[|1, 2, 7| ,7, 8]' , return the maximum '7';
        then the window move one step forward.'[1, |2, 7 ,7|, 8]', return the maximum '7';
        then the window move one step forward again.'[1, 2, |7, 7, 8|]', return the maximum '8';

Example 2:
    Input:
        [1,2,3,1,2,3]
        5
    Output:
        [3,3]
    Explanation:
        At first, the state of the window is as follows: '[,2,3,1,2,1 | , 3]', a maximum of '3';
        And then the window to the right one. '[1, | 2,3,1,2,3 |]', a maximum of '3';

Challenge
    O(n) time and O(k) memory
***/

// version-1: segment tree
public class Solution {    
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int size = nums.length;
        k = Math.min(k, size);
        
        SegmentTreeNode root = build(nums, 0, size - 1);
        
        int windowCount = size - k;
        for (int i = 0; i <= windowCount; i++) {
            int start = i;
            int end = i + (k - 1);
            int value = query(root, start, end);
            
            result.add(value);
        }
        
        return result;
    }

    // helper methods
    private SegmentTreeNode build(int[] nums, int start, int end) {
        // check corner case
        if (start > end) {
            return null;
        }
        
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start]);
        }
        
        SegmentTreeNode node = new SegmentTreeNode(start, end, Integer.MIN_VALUE);
        int mid = (start + end) / 2;
        node.left = build(nums, start, mid);
        node.right = build(nums, mid + 1, end);
        
        if (node.left != null) {
            node.maxValue = Math.max(node.maxValue, node.left.maxValue);
        }
        
        if (node.right != null) {
            node.maxValue = Math.max(node.maxValue, node.right.maxValue);
        }
        
        return node;
    }
    
    private int query(SegmentTreeNode node, int start, int end) {
        // check corner case
        if (node == null) {
            return 0;
        }
        
        if (node.start == start && node.end == end) {
            return node.maxValue;
        }
        
        int mid = (node.start + node.end) / 2;
        int leftMax = 0, rightMax = 0;
        
        if (mid >= start) {
            if (mid >= end) {
                leftMax = query(node.left, start, end);
            }
            else {
                leftMax = query(node.left, start, mid);
            }
        }
        
        if (mid < end) {
            if (mid >= start) {
                rightMax = query(node.right, mid + 1, end);
            }
            else {
                rightMax = query(node.right, start, end);
            }
        }
        
        return Math.max(leftMax, rightMax);
    }
}
// Helper class
class SegmentTreeNode {
    // fields
    int start;
    int end;
    int maxValue;
    SegmentTreeNode left;
    SegmentTreeNode right;

    // constructor
    public SegmentTreeNode(int start, int end, int maxValue) {
        this.start = start;
        this.end = end;
        this.maxValue = maxValue;
    }
}

// version-2: Deque
public class Solution {
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner cases
        if (nums == null || nums.length == 0 || k < 1) {
            return result;
        }

        int size = nums.length;
        k = Math.min(size, k);

        int index = 0;
        for (int num : nums) {
            index++;
	
            /**
            *  this Deque，keeping the elements as descending order. for instance: deque[3,2,1]，
            *  so deque.peekFirst()==3, deque.peekLast()==1. 
            *  if wo do such operation based on above elements, deque.pollFirst(), 
            *            then, it would be: deque[2,1]. 
            *  if deque.pollLast, 
            *            then, it would be: deque[3,2].
            **/
            while (!deque.isEmpty() && deque.peekLast() < num) {
                deque.pollLast();
            }

            deque.offer(num);
	
            // remove the head-item of last silding window 
            if (index > k && deque.peekFirst() == nums[index - (k + 1)]) {
                deque.pollFirst();
            }

            if (index >= k) {
                result.add(deque.peekFirst());
            }
        }

        return result;
    }	
}

//version-3 (Deque)
public class Solution {
    // field
    int[] values;
	
    /**
     * @param nums: A list of integers.
     * @param k: An integer
     * @return: The maximum number inside the window at each moving.
     */
    public List<Integer> maxSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner cases
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }

        int size = nums.length;
        k = Math.min(k, size);
        this.values = nums;
        Deque<Integer> deque = new ArrayDeque<Integer>();
        // loading the window
        for (int i = 0; i < k - 1; i++) {
            inQueue(deque, i);
        }

        for (int i = k - 1; i < size; i++) {
            inQueue(deque, i);
            result.add(nums[deque.peekFirst()]);

            outQueue(deque, i - k + 1);
        }

        return result;
    }

    // helper method
    private void inQueue(Deque<Integer> deque, int pos) {
        while (!deque.isEmpty() && values[pos] > values[deque.peekLast()]) {
            deque.pollLast();
        }

        deque.offer(pos);
    }

    private void outQueue(Deque<Integer> deque, int pos) {
        if (deque.peekFirst() == pos) {
            deque.pollFirst();
        }
    }
}

//version-4: quick select
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[0];
        
        if (nums == null || nums.length == 0) {
            return result;
        }

        int n = nums.length;
        k = Math.min(k, n);

        result = new int[n - k + 1];
        int index = 0;
        for (int i =0; i < n - k + 1; i++) {
            int j = i + k;
            int[] values = Arrays.copyOfRange(nums, i, j);
            //System.out.println(String.format("values = %s, i = %d, j = %d", Arrays.toString(values), i, j));
            int candidate = quickSelect(values, 0, values.length - 1, values.length - 1);
            result[index++] = candidate;
        }

        return result;
    }

    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }

        int left = start;
        int right = end;
        int mid = left + (right - left) / 2;
        int pivot = nums[mid];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }

        if (k <= right) {
            return quickSelect(nums, start, right, k);
        }

        if (k >= left) {
            return quickSelect(nums, left, end, k);
        }

        return nums[k];
    }
}
