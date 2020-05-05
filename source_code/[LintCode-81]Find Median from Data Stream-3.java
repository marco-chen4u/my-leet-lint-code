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
//version-3 Median class
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int[] result;
        int[] defaultValue = new int[0];
        // check corner case
        if (nums == null || nums.length == 0) {
            return defaultValue;
        }
        
        int size = nums.length;
        result = new int[size];
        
        Median median = new Median(size);
        int index = 0;
        for (int num : nums) {
            median.add(num);
            result[index++] = median.getValue();
        }
        
        return result;
    }
}

class Median {
    // fields
    Queue<Integer> maxHeap;//holding the smaller part of the data
    Queue<Integer> minHeap;// holding the bigger part of teh data
    
    // constructor
    public Median(int size) {
        maxHeap = new PriorityQueue<Integer>(size, Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>(size);
    }
    
    // innernal methods
    private void keepBalance() {
        while(maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
            minHeap.peek() < maxHeap.peek()) {
            exChangeRoot(minHeap, maxHeap);
        }
    }
    
    private void exChangeRoot(Queue<Integer> heapA, Queue<Integer> heapB) {
        if (heapA.isEmpty() || heapB.isEmpty()) {
            return;
        }
        
        int rootA = heapA.poll();
        int rootB = heapB.poll();
        
        heapA.offer(rootB);
        heapB.offer(rootA);
    }
    
    // public methods
    public int getValue() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        
        return maxHeap.peek();
    }
    
    public void add(int num) {
        if (num <= getValue()) {
            maxHeap.offer(num);
        }
        else {
            minHeap.offer(num);
        }
        
        keepBalance();
    }
}