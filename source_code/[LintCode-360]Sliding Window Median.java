/***
* LintCode 360. Sliding Window Median
Given an array of n integer, and a moving window(size k), 
move the window at each iteration from the start of the array, 
find the median of the element inside the window at each moving. 
(If there are even numbers in the array, return the N/2-th number after sorting the element in the window. )

Example 1
    Input:
        [1,2,7,8,5]
        3
    Output:
        [2,7,7]
    Explanation:
        At first the window is at the start of the array like this'[ | 1,2,7 | ,8,5]', return the median'2';
        then the window move one step forward.'[1, | 2,7,8 | ,5]', return the median'7';
        then the window move one step forward again.'[1,2, | 7,8,5 | ]', return the median'7';

Example 2
    Input:
        [1,2,3,4,5,6,7]
        4
    Output:
        [2,3,4,5]
    Explanation:
        At first the window is at the start of the array like this'[ | 1,2,3,4, | 5,6,7]', return the median'2';
        then the window move one step forward.'[1,| 2,3,4,5 | 6,7]', return the median'3';
        then the window move one step forward again.'[1,2, | 3,4,5,6 | 7 ]', return the median'4';
        then the window move one step forward again.'[1,2,3,| 4,5,6,7 ]', return the median'5';

Challenge
    O(nlog(n)) time
***/
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k < 1) {
            return result;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((a,b)->(b - a));
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
        k = Math.min(k, nums.length);
        int currentMedian;

        // initial loading to the window
        if (k > 1) {
            maxHeap.offer(nums[0]);
            for (int i = 1; i < k - 1; i++) {
                int maxRoot = maxHeap.peek();
                if (nums[i] <= maxRoot) {
                    maxHeap.offer(nums[i]);
                }
                else {
                    minHeap.offer(nums[i]);
                }
            }
        }

        currentMedian = (k > 1) ? maxHeap.peek() : 0; //default value

        // moving the window
        for (int i = k - 1; i < nums.length; i++) {
            // insert
            if (nums[i] <= currentMedian) {
                maxHeap.offer(nums[i]);
            }
            else {
                minHeap.offer(nums[i]);
            }

            // keep balanced
            while (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
            while (maxHeap.size() < minHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }

            // get the current median of this window
            currentMedian = maxHeap.peek();			
            result.add(currentMedian);

            // remove the start item of last window
            int item = nums[i - (k - 1)];
            if (item <= maxHeap.peek()) {
                maxHeap.remove(item);
            }
            else {
                minHeap.remove(item);
            }
        }

        return result;
    }
}

//version-2, Median helper class
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        int size = nums.length;
        k = Math.min(k, size);
        Median median = new Median();
        
        // loading the first window items
        for (int i = 0; i <= k - 1; i++) {
            median.accept(nums[i]);
        }
        
        median.setLoadingWindow(false);
        result.add(median.getValue());
        
        for (int i = k; i < size; i++) {
            int lastValue = nums[i - k];
            
            median.remove(lastValue);
            
            median.accept(nums[i]);
            result.add(median.getValue());
        }
        
        return result;
    }
}
// helper class
class Median{
    // fields
    private Queue<Integer> minHeap;//holding the larger items
    private Queue<Integer> maxHeap;//hloding the smaller items
    private int median;
    private boolean isLoadingWindow;
    
    // constuctor
    public Median() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        this.isLoadingWindow = true;
    }
    
    private void keepBalance() {
        
        while (maxHeap.size() >= minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
    
    public void accept(int num) {
        if (maxHeap.isEmpty() || isLoadingWindow || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        }
        else {
            maxHeap.offer(num);
        }
        
        keepBalance();
    }
    
    public int getValue() {
        return maxHeap.peek();
    }
    
    public void setLoadingWindow(boolean flag) {
        this.isLoadingWindow = flag;
    }
    
    public void remove(int num) {
        if (num <= maxHeap.peek()) {
            maxHeap.remove(num);
        }
        else {
            minHeap.remove(num);
        }
        
        keepBalance();
    }
}

//version-3 better solution(median class)
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int size = nums.length;
        k = Math.min(k, size);
        Median median = new Median(size);
        
        for (int i = 0; i <= size; i++) {
            if (i >= k) {
                result.add(median.getValue());
                
                int lastValue = nums[i - k];
                median.remove(lastValue);
            }
            
            if (i < size) {
                median.accept(nums[i]);
            }
        }
        
        return result;
    }
}

// helper class
class Median {
    // fields
    private Queue<Integer> minHeap;//holding the larger part of items
    private Queue<Integer> maxHeap;//holding the smaller part of items
    
    // constructor
    public Median(int size) {
        minHeap = new PriorityQueue<Integer>(size);
        maxHeap = new PriorityQueue<Integer>(size, Collections.reverseOrder());
    }
    
    // methods
    // innernal methods
    private void keepBalance() {
        while (maxHeap.size() >= minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
            minHeap.peek() < maxHeap.peek()) {
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
    
    // public methods
    public void accept(int num) {
        if (num > getValue()) {
            minHeap.offer(num);//holding the larger part of items
        }
        else {
            maxHeap.offer(num);//holding the smaller part of items
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
    
    /***
     * return the median value
    */ 
    public int getValue() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return 0;
        }
        
        return maxHeap.peek();
    }
 }
 
 //version-4
 public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int size = nums.length;
        k = Math.min(k, size);
        Median median = new Median(size);
        // loading the window
        for (int i = 0; i < k - 1; i++) {
            median.accept(nums[i]);
        }
        
        // getting the result
        for (int i = k - 1; i < size; i++) {
            median.accept(nums[i]);
            
            result.add(median.getValue());
            
            int lastValue = nums[i - k + 1];
            median.remove(lastValue);
        }
        
        return result;
    }
}

// helper class
class Median {
    // fields
    private Queue<Integer> minHeap;//holding the larger part of items
    private Queue<Integer> maxHeap;//holding the smaller part of items
    
    // constructor
    public Median(int size) {
        minHeap = new PriorityQueue<Integer>(size);
        maxHeap = new PriorityQueue<Integer>(size, Collections.reverseOrder());
    }
    
    // methods
    // innernal methods
    private void keepBalance() {
        while (maxHeap.size() >= minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        
        while (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
            minHeap.peek() < maxHeap.peek()) {
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
    
    // public methods
    public void accept(int num) {
        if (num > getValue()) {
            minHeap.offer(num);//holding the larger part of items
        }
        else {
            maxHeap.offer(num);//holding the smaller part of items
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
    
    /***
     * return the median value
    */ 
    public int getValue() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return 0;
        }
        
        return maxHeap.peek();
    }
 }

//version-5: quick select
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        int n = nums.length;
        k = Math.min(n, k);
        k = k;

        int i,j;
        for (i = 0; i < n - k + 1; i++) {
            j = i + k;
            int[] values = Arrays.copyOfRange(nums, i, j);
            int medianPos = (k - 1)/ 2;
            //System.out.println("values = " + Arrays.toString(values) + ", i = " + i + ", j = " + j + ", medianPos = " + medianPos);
            //System.out.println(String.format("values = %s, i = %d, j = %d, medianPos = %d", Arrays.toString(values), i, j, medianPos));
            int candidate = quickSelect(values, 0, values.length - 1, medianPos);
            result.add(candidate);
        }

        return result;
    }

    // helper method
    private int quickSelect(int[] nums, int start, int end, int k) {
        if (start >= end) {
            return nums[start];
        }

        int left = start;
        int right = end;

        int mid = left + (right - left) / 2;
        int pivot = nums[mid];

        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }

            while (left <= right && nums[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;

                left++;
                right--;
            }
        }

        if (k <= right) {
            return quickSelect(nums, start, right, k);
        }

        if (k >= left) {
            return quickSelect(nums, left, end, k);
        }

        return nums[k];
    }
}
