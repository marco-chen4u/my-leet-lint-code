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
