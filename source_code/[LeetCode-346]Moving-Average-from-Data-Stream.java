/***
* LeetCode 346. Moving Average from Data Stream
* Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

Implement the MovingAverage class:
  MovingAverage(int size) Initializes the object with the size of the window size.
  double next(int val) Returns the moving average of the last size values of the stream.

Example 1
  Input
    ["MovingAverage", "next", "next", "next", "next"]
    [[3], [1], [10], [3], [5]]
    Output
    [null, 1.0, 5.5, 4.66667, 6.0]
  
  Explanation
    MovingAverage movingAverage = new MovingAverage(3);
    movingAverage.next(1); // return 1.0 = 1 / 1
    movingAverage.next(10); // return 5.5 = (1 + 10) / 2
    movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
    movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3

Constraints:
  1 <= size <= 1000
  -10^5 <= val <= 10^5
  At most 10^4 calls will be made to next.

* Link(LeetCode): https://leetcode.com/problems/moving-average-from-data-stream
* Link(LintCode): https://www.lintcode.com/problem/642/
***/

//version-1: queue
class MovingAverage {

    private int size;
    private int count;
    private Queue<Integer> queue;
    private double total;

    public MovingAverage(int size) {
        this.size = size;
        this.count = 0;
        queue = new LinkedList<>();
        total = 0.0;
    }
    
    public double next(int val) {
        queue.offer(val);
        this.count += 1;
        this.total += val;

        if (queue.size() > size) {
            int deleteVal = queue.poll();
            this.total -= deleteVal;
            this.count -= 1;
        }

        return getAverage();
    }

    private double getAverage() {
        return this.total / this.count;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
