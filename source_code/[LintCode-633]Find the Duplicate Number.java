/***
* LintCode 633. Find the Duplicate Number
Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), gaurentee that at least one duplicate number must exist. 
Assume that there is only one duplicate number, find the duplicate one.
	-You must not modify the array (assume the array is read only).
	-You must use only constant, O(1) extra space.
	-Your runtime complexity should be less than O(n^2).
	-There is only one duplicate number in the array, but it could be repeated more than once.
Example
	Example 1:
		Input:
			[5,5,4,3,2,1]
		Output:
			5
	Example 2:
		Input:
			[5,4,4,3,2,1]
		Output:
			4
***/
/****
* [1, 2, 3, 4, 5, ..., P, P, P, P, P + 1, P + 2, ... n],  totaly n + 1 items
* the count that value is<= P, count >= (n + 1) - (n - P) = P + 1
* if x is in [P + 1, P + 2, ... n] inside these (n - P) values, the count that is <= x should be >= x + 1
* if y is in [1, 2, 3, 4, 5,..., P - 1], inside this values, the count that is <= y should be <= y
* the the threshold value is P, before the P, the count that <= itself(y) is <= y,  but at the poit of P, is >= P + 1, ever after that.
* so, P is something like the first point that make the bad verison, which simillar to badfirst version question
***/
public class Solution {
    /**
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
	// check corner case
	if (nums == null || nums.length == 0) {
		return 0;
	}

	int size = nums.length;
	int n = size - 1;

	int start = 1;
	int end = n;

	while (start + 1 < end) {
		int mid = start + (end - start) / 2;

		if (getCount(nums, mid) > mid) {
			end = mid;
		}
		else {
			start = mid;
		}//enf if
	}// end while

	if (getCount(nums, start) > start) {
		return start;
	}
	else {
		return end;
	}// end if
    }
	
	/***
	* get the count of item that is <= target
	**/
	private int getCount(int[] nums, int target) {
		int count = 0;

		for (int num : nums) {
			count += (num <= target) ? 1 : 0;
		}// end for

		return count;
	}
}
