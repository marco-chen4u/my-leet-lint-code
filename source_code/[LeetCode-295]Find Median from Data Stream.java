/***
* LeetCode 295. Find Median from Data Stream
Median is the middle value in an ordered integer list. If the size of the list is even, 
there is no middle value. So the median is the mean of the two middle value.

For example,
	[2,3,4], the median is 3
	[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:
	-void addNum(int num) - Add a integer number from the data stream to the data structure.
	-double findMedian() - Return the median of all elements so far.

Example:
	addNum(1)
	addNum(2)
	findMedian() -> 1.5
	addNum(3) 
	findMedian() -> 2

Follow Up:
(1)If all integer numbers from the stream are between 0 and 100, how would you optimize it?
(2)If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it
***/
class MedianFinder {
    // fields
    private Queue<Integer> maxHeap;// holding the smaller part of data
    private Queue<Integer> minHeap;// holding the larger part of data

    /** initialize your data structure here. */
    public MedianFinder() {
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (num > findMedian()) {
            minHeap.offer(num);
        }
        else {
            maxHeap.offer(num);
        }
        
        keepBalance();
    }
    
    public double findMedian() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        
        if (maxHeap.size() == minHeap.size()) {
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        
        return maxHeap.peek();
    }
    
    // helper methods
    private void keepBalance() {
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
            maxHeap.peek() > minHeap.peek()) {
            exChangeRoot(maxHeap, minHeap);
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
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */