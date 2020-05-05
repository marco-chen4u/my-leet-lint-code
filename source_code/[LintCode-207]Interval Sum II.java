/***
* LintCode 207. Interval Sum II
Given an integer array in the construct method, implement two methods query(start, end) and modify(index, value):
For query(start, end), return the sum from index start to index end in the given array.
For modify(index, value), modify the number in the given index to value

Example
	Example 1
		Input:
			[1,2,7,8,5]
			[query(0,2),modify(0,4),query(0,1),modify(2,1),query(2,4)]
		Output: [10,6,14]
		Explanation:
			Given array A = [1,2,7,8,5].
			After query(0, 2), 1 + 2 + 7 = 10,
			After modify(0, 4), change A[0] from 1 to 4, A = [4,2,7,8,5].
			After query(0, 1), 4 + 2 = 6.
			After modify(2, 1), change A[2] from 7 to 1，A = [4,2,1,8,5].
			After query(2, 4), 1 + 8 + 5 = 14.

	Example 2
		Input:
			[1,2,3,4,5]
			[query(0,0),query(1,2),quert(3,4)]
		Output: [1,5,9]
		Explantion:
			1 = 1
			2 + 3 = 5
			4 + 5 = 9

Challenge
	O(logN) time for query and modify.

Notice
	We suggest you finish problem Segment Tree Build, Segment Tree Query and Segment Tree Modify first.
***/
public class Solution {
    // inner class
    class SegmentTreeNode {
        // fields
        int start;
        int end;
        int sum;
        SegmentTreeNode left;
        SegmentTreeNode right;
        
        // constructor
        public SegmentTreeNode(int start, int end, int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    
    /* you may need to use some attributes here */
    private SegmentTreeNode root;
    
    // helper methods
    private SegmentTreeNode build(int[] nums, int start, int end) {
        // check corner cases
        if (start > end) {
            return null;
        }
        
        if (start == end) {
            return new SegmentTreeNode(start, end, nums[start]);
        }
        
        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        
        int mid = (start + end) / 2;
        
        node.left = build(nums, start, mid);
        node.right = build(nums, mid + 1, end);
        
        int leftSum = (node.left != null) ? node.left.sum : 0;
        int rightSum = (node.right != null) ? node.right.sum : 0;
        
        node.sum = leftSum + rightSum;
        
        return node;
    }
    
    private int query(SegmentTreeNode node, int start, int end) {
        // check corner case
        if (node == null) {
            return 0;
        }
        
        if (node.start == start && node.end == end) {
            return node.sum;
        }
        
        int mid = (node.start + node.end) / 2;
        
        int leftSum = 0, rightSum = 0;
        
        // leftSum
        if (start <= mid) {
            if (end < mid) {
                leftSum = query(node.left, start, end);
            }
            else {
                leftSum = query(node.left, start, mid);
            }
        }
        
        // rightSum
        if (end > mid) {
            if (start > mid) {
                rightSum = query(node.right, start, end);
            }
            else {
                rightSum = query(node.right, mid + 1, end);
            }
        }
        
        return leftSum + rightSum;
    }
    
    private void modify(SegmentTreeNode node, int index, int value) {
        // check corner cases
        if (node == null) {
            return;
        }
        
        if (index < node.start || index > node.end) {
            return;
        }
        
        if (node.start == index && node.end == index) {
            node.sum = value;
            return;
        }
        
        // normal case
        int mid = (node.start + node.end) / 2;
        if (index <= mid) {
            modify(node.left, index, value);
        }
        else {
            modify(node.right, index, value);
        }
        
        int leftSum = (node.left != null)  ? node.left.sum : 0;
        int rightSum = (node.right != null) ?  node.right.sum : 0;
        
        node.sum = leftSum + rightSum;
    }

    /*
    * @param A: An integer array
    */
    public Solution(int[] A) {
        root = build(A, 0, A.length - 1);
    }

    /*
     * @param start: An integer
     * @param end: An integer
     * @return: The sum from start to end
     */
    public long query(int start, int end) {
        return query(root, start, end);
    }

    /*
     * @param index: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void modify(int index, int value) {
        modify(root, index, value);
    }
}