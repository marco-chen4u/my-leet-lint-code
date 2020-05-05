/***
* LintCode 840. Range Sum Query - Mutable
Given an integer array nums, and then you need to implement two functions:
	update(i, val) Modify the element whose index is i to val.
	sumRange(l, r) Return the sum of elements whose indexes are in range of [l, r][l,r].
	
Example
	Example 1:
		Input: 
		  nums = [1, 3, 5]
		  sumRange(0, 2)
		  update(1, 2)
		  sumRange(0, 2)
		Output:
		  9
		  8
	  
	Example 2:
		Input:
		  nums = [0, 9, 5, 7, 3]
		  sumRange(4, 4)
		  sumRange(2, 4)
		  update(4, 5)
		  update(1, 7)
		  update(0, 8)
		  sumRange(1, 2)
		Output:
		  3
		  15
		  12
	  
Notice
	The array is only modifiable by the update function.
	You may assume the number of calls to update and sumRange function is distributed evenly.
***/
//version-1
// helper class
class FenwickTree {
	// fileds
	int[] prefixSum;
	 
	// helper method
	private int lowbit(int x) {
		return x & (-x);
	}
	
	//public void print() {
	//    System.out.println(Arrays.toString(this.prefixSum));
	//}
	 
	// constructor
	public FenwickTree(int n) {
		prefixSum = new int[n + 1];
		 
		Arrays.fill(prefixSum, 0);
	}
	 
	// methods
	public void update(int i, int value) {
		while (i < prefixSum.length) {
			prefixSum[i] += value;
			i += lowbit(i);
		 }
	 }
	 
	public int query(int i) {
		int sum = 0;
		
		while (i > 0) {
			sum += prefixSum[i];
			i -= lowbit(i);
		}
		
		return sum;
	}
}

public class NumArray {
	// fields
	private FenwickTree tree;
	private int[] nums;
	
	// constructor
    public NumArray(int[] nums) {
		this.nums = nums;
		int size = nums.length;
		
		tree = new FenwickTree(size);
		
        for (int i = 0; i < size; i++) {
			tree.update(i + 1, nums[i]);
		}
		
		//tree.print();
    }
    
    public void update(int i, int val) {
        tree.update(i + 1, val - nums[i]);
		nums[i] = val;
    }
    
    public int sumRange(int i, int j) {
        return tree.query(j + 1) - tree.query(i);
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */
 
//version-2
class NumArray {
	
	// fields
	private int[] arrayValue;
	private int[] bit;
	
	// private methods
	private int getPrefixSum(int index) {
		int sum = 0;
		
		for (int i = index + 1; i > 0; i -= lowbit(i)) {
			sum += bit[i];
		}
		
		return sum;
	}
	
	private int lowbit(int x) {
		return x & (- x);
	}
	

	// constructor
    public NumArray(int[] nums) {
        arrayValue = new int[nums.length];
		bit = new int[nums.length + 1];
		
		for (int i = 0; i < nums.length; i++) {
			update(i, nums[i]);
		}
    }
    
    public void update(int index, int val) {
        int delta = val - arrayValue[index];
		arrayValue[index] = val;
		
		for (int i = index + 1; i <= arrayValue.length; i += lowbit(i)) {
			bit[i] += delta;
		}
    }
    
    public int sumRange(int left, int right) {
        return getPrefixSum(right) - getPrefixSum(left - 1);
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */