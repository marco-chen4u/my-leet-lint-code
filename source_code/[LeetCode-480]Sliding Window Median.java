/***
* LeetCode 480. Sliding Window Median
Median is the middle value in an ordered integer list. 
If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

Examples: 
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. 
You can only see the k numbers in the window. 
Each time the sliding window moves right by one position. 
Your job is to output the median array for each window in the original array.

For example,
Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
Window position                Median
---------------               -----
[1  3  -1] -3  5  3  6  7       1
 1 [3  -1  -3] 5  3  6  7       -1
 1  3 [-1  -3  5] 3  6  7       -1
 1  3  -1 [-3  5  3] 6  7       3
 1  3  -1  -3 [5  3  6] 7       5
 1  3  -1  -3  5 [3  6  7]      6
 Therefore, return the median sliding window as [1,-1,-1,3,5,6].
 
Note:
You may assume k is always valid, ie: k is always smaller than input array's size for non-empty array.
***/
class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] defaultValue = new double[0];
        if (nums == null || nums.length == 0 || k <= 0) {
            return defaultValue;
        }
        
        int size = nums.length;
        k = Math.min(k, size);
        Median median = new Median(size);
        
        double[] result = new double[size - k + 1];
        
        //loading the window
        for (int i = 0; i < k - 1; i++) {
            median.add(nums[i]);
        }        
        
        for (int i = k - 1; i < size; i++) {
            median.add(nums[i]);
            result[i - k + 1] = median.getValue();
            
            int lastValue = nums[i - k + 1];
            median.remove(lastValue);
        }
        
        return result;
    }
}

// helper class
class Median {
    // fields
    Queue<Integer> maxHeap; // holding the smaller part of data
    Queue<Integer> minHeap; // holding the larger part of data
    
    // constructor
    public Median(int size) {
        maxHeap = new PriorityQueue<Integer>(size,Collections.reverseOrder());
        minHeap = new PriorityQueue<Integer>(size);
    }
    
    // internal methods
    private void keepBalance() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
            maxHeap.peek() > minHeap.peek()) {
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
    public void add(int num) {
        if (num > getValue()) {
            minHeap.offer(num);
        }
        else {
            maxHeap.offer(num);
        }
        
        keepBalance();
    }
    
    public void remove(int num) {
        if (num > getValue()) {
            minHeap.remove(num);
        }
        else {
            maxHeap.remove(num);
        }
        
        keepBalance();
    }
    
    public double getValue() {
        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }
        
        if (maxHeap.size() == minHeap.size()) {
            return maxHeap.peek() / 2.0 + minHeap.peek() / 2.0;
        }
        
        return maxHeap.peek();
    }
}
