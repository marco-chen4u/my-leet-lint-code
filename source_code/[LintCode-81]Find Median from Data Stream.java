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
/***
 Analyzing as below:
 Two heaps
	-One Max Heap to keep the smaller half of tha data;
	-One Min Heap to keep the larger half of the data;
	-Balance
		0 <= [smaller.size()] - [larger.size()] <= 1
		     |<------------total size--------->|

    if (total-size-diff = 1) { 
        if (minHeap.peek() > maxHeap.peek()) {
            swap(minHeap.peek(), maxHeap.peek());
        }
    }
-return maxHeap.peek();

***/
// version-1
public class Solution {
    // fields
    private Queue<Integer> maxHeap;// keep the smaller part of the data
    private Queue<Integer> minHeap;// keep the larger part of the data
    private int numberOfElements = 0;    
    // methods
    private void addNum(int value) {
        maxHeap.offer(value); // smaller part of the data
        
        if (numberOfElements % 2 == 0) {
            if (minHeap.isEmpty()) {
                numberOfElements++;
                return;
            }
            else if (maxHeap.peek() > minHeap.peek()) {
                // swap
                Integer maxRoot = maxHeap.poll();
                Integer minRoot = minHeap.poll();
                
                maxHeap.offer(minRoot);
                minHeap.offer(maxRoot);
            }
        }
        else {
            // make the both[smaller, larger] parts balanced
            minHeap.offer(maxHeap.poll());
        }
        
        numberOfElements++;
    }
    
    private int getMedian() {
        return maxHeap.peek();
    }
    
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        // check corner case
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        // initialize
        int size = nums.length;
        int[] result = new int[size];
        maxHeap = new PriorityQueue<Integer>(size, new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b - a;
            }
        });// smaller half of the data
        minHeap = new PriorityQueue<Integer>(size); // larger half of the data
        
        // compute
        for (int i = 0; i < size; i++) {
            addNum(nums[i]);
            result[i] = getMedian();
        }
        
        return result;
    }
}

//version-2
public class Solution {

    private Queue<Integer> minHeap = new PriorityQueue<>();
    private Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

    private int numOfElements = 0;

    /**
     * @param val: a num from the data stream.
     * @return: nothing
     */
    public void add(int val) {
        // write your code here
        maxHeap.offer(val);

        numOfElements += 1;

        // re-balance
        if (numOfElements % 2 == 0) {
            minHeap.offer(maxHeap.poll());
        }
        else {
            if (minHeap.isEmpty()) {
                return;
            }

            if (maxHeap.peek() > minHeap.peek()) {
                exChangePeak(maxHeap, minHeap);
            }
        }

    }

    /**
     * @return: return the median of the all numbers
     */
    public int getMedian() {
        // write your code here
        return maxHeap.peek();
    }

    private void exChangePeak(Queue<Integer> maxHeap, Queue<Integer> minHeap) {
        int maxPeak = maxHeap.poll();
        int minPeak = minHeap.poll();

        maxHeap.offer(minPeak);
        minHeap.offer(maxPeak);
    }
}

//version-3
public class Solution {

    private Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private Queue<Integer> minHeap = new PriorityQueue<>();

    /**
     * @param val: a num from the data stream.
     * @return: nothing
     */
    public void add(int val) {

        if (val > getMedian()) {
            minHeap.offer(val);
        }
        else {
            maxHeap.offer(val);
        }

        rebalance();
    }

    /**
     * @return: return the median of the all numbers
     */
    public int getMedian() {

        if (maxHeap.isEmpty() && minHeap.isEmpty()) {
            return 0;
        }

        return maxHeap.peek();
    }

    // helper methods
    private void rebalance() {
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }

        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        if (maxHeap.isEmpty() || minHeap.isEmpty()) {
            return;
        }

        if (maxHeap.peek() > minHeap.peek()) {
            exChangePeak(maxHeap, minHeap);
        }
    }

    private void exChangePeak(Queue<Integer> maxHeap, Queue<Integer> minHeap) {
        int maxPeak = maxHeap.poll();
        int minPeak = minHeap.poll();

        maxHeap.offer(minPeak);
        minHeap.offer(maxPeak);
    }
}
