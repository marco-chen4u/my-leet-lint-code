/***
* LintCode 249. Count of Smaller Number before itself
Give you an integer array (index from 0 to n-1, 
where n is the size of this array, data value from 0 to 10000) . 
For each element Ai in the array, 
count the number of element before this element Ai is smaller than it and return count number array.

Example
	Example 1:
	Input:
		[1,2,7,8,5]
	Output:
		[0,1,2,3,2]

	Example 2:
	Input:
		[7,8,2,1,3]
	Output:
		[0,1,0,0,2]

Clarification
	Before you do this, you'd better complete the following three questions： 
		(1)Segment Tree Build， 
		(2)Segment Tree Query II，

Notice
	We suggest you finish problem Segment Tree Build, Segment Tree Query II and Count of Smaller Number first.
***/
//version-1: segment tree
// hleper class
class SegmentTreeNode {
	// fields
	int start;
	int end;
	int count;
	SegmentTreeNode left;
	SegmentTreeNode right;
	// constructor
	public SegmentTreeNode(int start, int end, int count) {
		this.start = start;
		this.end = end;
		this.count = count;
		
		this.left = null;
		this.right = null;
	}
}

public class Solution {    
    // class fields
    private SegmentTreeNode root;
    
    // helper methods
    private SegmentTreeNode buildSegmentTree(int start, int end) {
        // check corner case
        if (start > end) {
            return null;
        }
        
        SegmentTreeNode node = new SegmentTreeNode(start, end, 0);
        
        if (start == end) {
            return node;
        }
        
        int mid = (start + end) / 2;
        
        node.left = buildSegmentTree(start, mid);
        node.right = buildSegmentTree(mid + 1, end);
        
        return node;
    }
    
    private int query(SegmentTreeNode node, int start, int end) {
        // check corner case
        if (node == null) {
            return 0;
        }
        
        if (node.start == start && node.end == end) {
            return node.count;
        }
        
        // normal case
        // divide
        int mid = (node.start + node.end) / 2;
        int leftCount = 0, rightCount = 0;
        // ------[1]left part result
        if (mid >= start) {
            if (mid < end) {
                leftCount = query(node.left, start, mid);
            }
            else {
                leftCount = query(node.left, start, end);// edge case
            }
        }
        // ------[2]right part result
        if (mid < end) {
            if (mid >= start) {
                rightCount = query(node.right, mid + 1, end);
            }
            else {
                rightCount = query(node.right, start, end);//edge case
            }
        }
        
        // conquer
        return leftCount + rightCount;
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
            node.count += value;
            return;
        }
        
        // normal case
        // divide
        int mid = (node.start + node.end) / 2;
        
        if (index <= mid) {
            modify(node.left, index, value);
        }
        
        if (index > mid) {
            modify(node.right, index, value);
        }
        
        int leftCount = (node.left != null) ? node.left.count : 0;
        int rightCount = (node.right != null) ? node.right.count : 0;
        
        // conquer
        node.count = leftCount + rightCount;
    }
    
    /**
     * @param A: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] A) {
        List<Integer> result = new ArrayList<Integer>();
        
        // check corner case
        if (A == null || A.length == 0) {
            return result;
        }
        
        root = buildSegmentTree(0, 1000);
        
        int count = 0;
        for (int i = 0; i < A.length; i++) {
            count = 0;
            
            if (A[i] > 0) {
                count = query(root, 0, A[i] - 1);
            }
            
            result.add(count);
            
            modify(root, A[i], 1);
        }
        
        return result;
    }
}

//version-2: binary indexed tree
// helper class
class FenwickTree {
    // fields
    public int[] prefixSum;
    
    // constructor
    public FenwickTree(int n) {
        prefixSum = new int[n + 1];
    }
    
    // helper method
    public int lowbit(int x) {
        return x & (-x);
    }
    
    // public methods
    public int query(int index) {
        int sum = 0;
        index += 1;
        
        while (index > 0) {
            sum += prefixSum[index];
            index -= lowbit(index);
        }
        
        return sum;
    }
    
    public void update(int index, int delta) {
        index += 1;
        
        while (index < prefixSum.length) {
            prefixSum[index] += delta; 
            index += lowbit(index);
        }
    }
}

public class Solution {
    // fields
    private final int MAX_SIZE = 1000;
    /**
     * @param nums: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        FenwickTree tree = new FenwickTree(MAX_SIZE);
        for (int num : nums) {
            int value = tree.query(num - 1);
            tree.update(num, 1);
            
            result.add(value);
        }
        
        return result;
    }
}

// version-3 : FenwickTree
// helper class
class FenwickTree {
    // fields
    public int[] prefixSum;
    
    // constructor
    public FenwickTree(int n) {
        prefixSum = new int[n + 1];
    }
    
    // helper method
    public int lowbit(int x) {
        return x & (-x);
    }
    
    // public methods
    public int query(int index) {
        int sum = 0;
        
        for (int i = index + 1; i > 0; i -= lowbit(i)) {
            sum += prefixSum[i];
        }
        
        return sum;
    }
    
    public void update(int index, int delta) {
        for (int i = index + 1; i < prefixSum.length; i += lowbit(i)) {
            prefixSum[i] += delta;
        }
    }
}

public class Solution {
    // fields
    private final int MAX_SIZE = 10000;
    /**
     * @param nums: an integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> countOfSmallerNumberII(int[] nums) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        FenwickTree tree = new FenwickTree(MAX_SIZE);
        for (int num : nums) {
            int value = tree.query(num - 1);
            tree.update(num, 1);
            
            result.add(value);
        }
        
        return result;
    }
}