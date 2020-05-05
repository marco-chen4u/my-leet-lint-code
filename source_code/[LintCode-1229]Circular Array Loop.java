/***
* LintCode 1229. Circular Array Loop
You are given an array of positive and negative integers. If a number n at an index is positive, then move forward n steps. Conversely, if it's negative (-n), move backward n steps. Assume the first element of the array is forward next to the last element, and the last element is backward next to the first element. Determine if there is a loop in this array. A loop starts and ends at a particular index with more than 1 element along the loop. The loop must be "forward" or "backward'.

Example
	Example 1:
		Input: [2, -1, 1, 2, 2]
		OUtput: true
		Explanation: there is a loop, from index 0 -> 2 -> 3 -> 0.

	Example 2:
		Input: [-1, 2]
		Output: false

Challenge
	Can you do it in O(n) time complexity and O(1) space complexity?

Notice
	The given array is guaranteed to contain no element "0".
***/
public class Solution {
    // field
    private final int MARKED = Integer.MIN_VALUE;
    
    /**
     * @param nums: an array of positive and negative integers
     * @return: if there is a loop in this array
     */
    public boolean circularArrayLoop(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        int size = nums.length;
		int[] values = new int[size];
		for (int i = 0; i < size; i++) {
			values[i] = nums[i];
		}
		
        for (int i = 0; i < size; i++) {
            int current = nums[i];
            nums[i] = MARKED;// makred it as currently visited
            
            if (current == MARKED) {// that means this position has already been visited
                continue;
            }
            
            int next = ((i + current) + size) % size;
            if (i == next) {// move back with only 1 step, whic dosen't satisfy the loop condition
                continue;
            }
            
            // moving with one direction to try to get a loop
            while (current * nums[next] > 0) {
                current = nums[next];
                nums[next] = MARKED;// marked it visited
                next = (current + next + size) % size;// moving forward/backward with it's consistant direction
            }
            
            if (next == i) {
                return true;
            }
        }
        
        return false;
    }
}

public class Solution {
    // field
    private final int MARKED = 0;
    private int size;
    
    /**
     * @param nums: an array of positive and negative integers
     * @return: if there is a loop in this array
     */
    public boolean circularArrayLoop(int[] nums) {
        // check corner case
        if (nums == null || nums.length <= 1) {
            return false;
        }
        
        size = nums.length;
        
        for (int i = 0; i < size; i++) {
            if (nums[i] == MARKED) {// already visited
                continue;
            }
            
            // two pointers
            int j = i;// slow pointer
            int k = getIndex(i, nums);// fast pointer
            
            while (nums[k] * nums[i] > 0 && nums[getIndex(k, nums)] * nums[i] > 0) {
                if (j == k) {
                    if (j == getIndex(j, nums)) {// loop back with only one element 
                        break;
                    }
                    
                    return true;
                }
                
                j = getIndex(j, nums);
                k = getIndex(getIndex(k, nums), nums);
            }
            
            // loop not found, mark all element along the the way to MARKED
            j = i;
            int value = nums[i];
            while (value * nums[j] > 0) {
                int next = getIndex(j, nums);
                nums[j] = MARKED;
                j = next;
            }
        }
        
        return false;
    }
    
    // helper method
    private int getIndex(int i, int[] nums) {
        int value = i + nums[i];
        
        while (value < 0) {
            value += size;
        }
        
        return value % size;
    }
}