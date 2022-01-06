/***
* LintCode 130. Heapify
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Example
Example 1
Input : [3,2,1,4,5]
Output : [1,2,3,4,5]
Explanation : return any one of the legitimate heap arrays
Challenge
    O(n) time complexity

Clarification
What is heap? What is heapify? What if there is a lot of solutions?
    -Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
    -Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
    -Return any of them.
***/
// version-1: sift-down
public class Solution {	
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    public void heapify(int[] A) {
        // check corner case
        if (A == null || A.length == 0) {
            return;
        }

        for (int i = A.length / 2; i >= 0; i--) {
            siftDown(A, i);
        }
    }

    // helper methods
    private void siftDown(int[] nums, int index) {
        // check corner case
        if (nums == null || nums.length == 0 || 
            index < 0 || index > nums.length - 1) {
            return;
        }

        int size = nums.length;

        while (index < size) {
            int smallest = index;
            int leftChildIndex = index * 2 + 1;
            int rightChildIndex = index * 2 + 2;

            if (leftChildIndex < size && nums[leftChildIndex] < nums[smallest]) {
                smallest = leftChildIndex;
            }
			
            if (rightChildIndex < size && nums[rightChildIndex] < nums[smallest]) {
                smallest = rightChildIndex;
            }
			
            if (smallest == index) {
                break;
            }

            swap(nums, index, smallest);
			
            index = smallest;
        }
    }
	
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// version-2: shif-up
public class Solution {
    /*
     * @param A: Given an integer array
     * @return: nothing
     */
    public void heapify(int[] A) {
        if (A == null || A.length == 0) {
            return;
        }

        for (int i = 0; i < A.length; i++) {
            siftUp(A, i);
        }
    }

    private void siftUp(int[] nums, int index) {
        while (index != 0) {
            int parentIndex = (index - 1) / 2;
			
            if (nums[index] > nums[parentIndex]) {
                break;
            }

            swap(nums, index, parentIndex);

            index = parentIndex;
        }
    }
	
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
