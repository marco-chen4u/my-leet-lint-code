/***
* LeetCode 1346. Check If N and Its Double Exist
Given an array arr of integers, check if there exists two integers N and M such that N is the double of M ( i.e. N = 2 * M).
More formally check if there exists two indices i and j such that :
	i != j
	0 <= i, j < arr.length
	arr[i] == 2 * arr[j]

Example 1:
	Input: arr = [10,2,5,3]
	Output: true
	Explanation: N = 10 is the double of M = 5,that is, 10 = 2 * 5.
Example 2:
	Input: arr = [7,1,14,11]
	Output: true
	Explanation: N = 14 is the double of M = 7,that is, 14 = 2 * 7.
Example 3:
	Input: arr = [3,1,7,11]
	Output: false
	Explanation: In this case does not exist N and M, such that N = 2 * M.
	
Constraints:
	2 <= arr.length <= 500
	-10^3 <= arr[i] <= 10^3	
***/
/*
* 这是一道考察array操作和数学计算，思考全面性问题相关知识的题目
*/
//version-1
class Solution {
    public boolean checkIfExist(int[] arr) {
		// check corner case
		if (arr == null || arr.length == 0) {
			return false;
		}
		
        Set<Integer> set = new HashSet<>();
		
		// loading
		for (int value : arr) {
			
			// check corner case
			if (value == 0 && set.contains(value)) {
				return true;
			}
			
			set.add(value);
		}
		
		// check every item except value = 0;
		for (int value : arr) {
			if (value == 0) {
				continue;
			}
			
			if (set.contains(value * 2)) {
				return true;
			}
		}
		
		return false;// default
    }
}

//version-2
class Solution {
    public boolean checkIfExist(int[] arr) {
        // check corner case
		if (arr == null || arr.length == 0) {
			return false;
		}
		
		Set<Integer> set = new HashSet<>();
		for (int value : arr) {
			if (set.contains(value * 2) ||
				value % 2 = 0 && set.contains(value / 2)) {
				return true;
			}
		}
		
		return false;
    }
}