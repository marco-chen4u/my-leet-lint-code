/***
* LintCode 81. Find Median from Data Stream
Numbers keep coming, return the median of numbers at every time a new number added.
Example
	For numbers coming list: [1, 2, 3, 4, 5], return [1, 1, 2, 2, 3].
	For numbers coming list: [4, 5, 1, 3, 2, 6, 0], return [4, 4, 4, 3, 3, 3, 3].
	For numbers coming list: [2, 20, 100], return [2, 2, 20].
Challenge
	Total run time in O(nlogn).
Clarification
	What's the definition of Median?
	Median is the number that in the middle of a sorted array. 
	If there are n numbers in a sorted array A, the median is A[(n - 1) / 2]. 
	For example, if A=[1,2,3], median is 2. If A=[1,19], median is 1.
***/
//version-2
public class Solution {    
    // fields
    private Queue<Integer> maxHeap;
    private Queue<Integer> minHeap;
    
    // methods
    private void initializeHeaps() {
        maxHeap = new PriorityQueue<Integer>((a, b)->(b - a));
        minHeap = new PriorityQueue<Integer>();
    }
    
    private void balance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }        
        if (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    private void add(int num) {
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        }
        else {
            minHeap.offer(num);
        }        
        balance();
    }
    
    private int getMedian() {
        return maxHeap.peek();
    }
    
    public int[] medianII(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        initializeHeaps();
        int size = nums.length;
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            add(nums[i]);
            result[i] = getMedian();
        }
        
        return result;
    }
}