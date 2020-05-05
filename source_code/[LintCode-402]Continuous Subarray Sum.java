/***
* LintCode 402. Continuous Subarray Sum
Given an integer array, find a continuous subarray where the sum of numbers is the biggest. 
Your code should return the index of the first number and the index of the last number. 
(If their are duplicate answer, return the minimum one in lexicographical order)

Example
	Example 1:
		Input: [-3,1,3,-3,4]
		Output: [1, 4]
	Example 2:
		Input: [0,1,0,1]
		Output: [0, 3]
		Explanation: The minimum one in lexicographical order.
***/
public class Solution {
    /*
     * @param A: An integer array
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> continuousSubarraySum(int[] A) {
		List<Integer> result = new ArrayList<Integer>();
		
		if (isEmpty(A)) {
			return result;
		}
		
		int size = A.length;
		int sum = 0;
		int maxSum = Integer.MIN_VALUE;
		
		int left, right;
		left = right = 0;
		
		// initialize
		int start, end;
		start = end = 0;
		
		for (int i = 0; i < size; i++) {
			if (sum < 0) {
				sum = 0;
				left = i;
			}
			
			sum += A[i];
			
			if (sum > maxSum) {
				maxSum = sum;
				right = i;
				
				start = left;
				end = right;
			}
		}

		result.add(start);
		result.add(end);
		
		return result;
	}
	
	// helper method
	private boolean isEmpty(int[] nums) {
	    return (nums == null || nums.length == 0);
	}
}	