/***
* LintCode 943. Range Sum Query - Immutable
Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
Example
	Example1
		Input: nums = [-2, 0, 3, -5, 2, -1]
		sumRange(0, 2)
		sumRange(2, 5)
		sumRange(0, 5)
	Output:
		1
		-1
		-3
	Explanation: 
		sumRange(0, 2) -> (-2) + 0 + 3 = 1
		sumRange(2, 5) -> 3 + (-5) + 2 + (-1) = -1
		sumRange(0, 5) -> (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
Example2
	Input: 
		nums = [-4, -5]
		sumRange(0, 0)
		sumRange(1, 1)
		sumRange(0, 1)
		sumRange(0, 0)
	Output: 
		-4
		-5
		-9
		-4
	Explanation: 
		sumRange(0, 0) -> -4
		sumRange(1, 1) -> -5
		sumRange(0, 1) -> (-4) + (-5) = -9
		sumRange(0, 0) -> -4
Notice
	You may assume that the array does not change.
	There are many calls to sumRange function.
***/
class NumArray {
    private int size;
    private int[] prefixSum;
	
    public NumArray(int[] nums) {
        // initiate
        size = nums.length;
        prefixSum = new int[size + 1];
        // state
        prefixSum[0] = 0;
        for (int i = 1; i <= size; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }
    }
    
    public int sumRange(int i, int j) {
        // check corner cases
        if (i < 0 || j > size || i > j) {
            return -1;
        }
        
        return (prefixSum[j + 1] - prefixSum[i]);
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */