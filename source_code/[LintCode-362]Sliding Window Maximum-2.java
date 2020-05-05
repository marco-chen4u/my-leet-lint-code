/***
* LintCode 362. Sliding Window Maximum
Given an array of n integer with duplicate number, and a moving window(size k), 
move the window at each iteration from the start of the array, 
find the maximum number inside the window at each moving.

Example
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
			 *  此双端队列Deque，存储的数值是递减序列. 例如deque[3,2,1]，
			 *  那么 deque.peekFirst()==3,deque.peekLast()==1. 
			 *  如果deque.pollFirst()那么deque[2,1].如果deque.pollLast()那么deque[3,2].
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