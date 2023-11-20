/***
* LeetCode 857. Minimum Cost to Hire K Workers
There are n workers. 
You are given two integer arrays quality and wage 
where quality[i] is the quality of the ith worker and wage[i] is the minimum wage expectation for the ith worker.

We want to hire exactly k workers to form a paid group. 
To hire a group of k workers, we must pay them according to the following rules:
  1.Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
  2.Every worker in the paid group must be paid at least their minimum wage expectation.
  
Given the integer k, 
return the least amount of money needed to form a paid group satisfying the above conditions. 
Answers within 10-5 of the actual answer will be accepted.

Link: https://leetcode.com/problems/minimum-cost-to-hire-k-workers/
Link: https://www.lintcode.com/problem/1512/
***/

//version-1 : sort by wage ratio(wage-income / quality[which is more like work-hour or work-quantity here]) time-complexity: O(nlogn)
public class Solution {

    class Worker implements Comparable<Worker> {
        int quality;
        int wage;
        double unitWage;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.unitWage = wage * 1.0 / quality;
        }

        @Override
        public int compareTo(Worker other) {
            return Double.compare(this.unitWage, other.unitWage);
        }
    }

    /**
     * @param quality: an array
     * @param wage: an array
     * @param k: an integer
     * @return: the least amount of money needed to form a paid group
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        // Write your code here
        int size = quality.length;
        Worker[] workers = new Worker[size];

        for (int i = 0; i < size; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }

        Arrays.sort(workers);

        int total = 0;
        double result = 1e9;

        Queue<Integer> maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
        for (Worker worker : workers) {
            
            maxHeap.offer(worker.quality);

            total += worker.quality;

            if (maxHeap.size() > k) {
                total -= maxHeap.peek();
                maxHeap.poll();
            }

            if (maxHeap.size() == k) {
                double cost = total * worker.unitWage;
                result = Math.min(result, cost);
            }

        }

        return result;
    }
}
