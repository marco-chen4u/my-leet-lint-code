/***
* LintCode 1281. Top K Frequent Elements
Given a non-empty array of integers, return the k most frequent elements.
Example
	Example 1:
		Input: nums = [1,1,1,2,2,3], k = 2
		Output: [1,2]

	Example 2:
		Input: nums = [1], k = 1
		Output: [1]
Notice
	You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
	Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
***/
public class Solution {
    /**
     * @param nums: the given array
     * @param k: the given k
     * @return: the k most frequent elements
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0 || k <= 0) {
            return result;
        }
        
        int size = nums.length;
        
        // Map<num, frequency> key - value pair
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int num : nums) {
            map.putIfAbsent(num, 0);
            
            map.put(num, map.get(num) + 1);
        }
        
        k = Math.min(k, map.size());
        
        Comparator<Map.Entry<Integer, Integer>> comparator = new Comparator<Map.Entry<Integer, Integer>>() {
            
            @Override
            public int compare(Map.Entry<Integer, Integer> a, 
                                Map.Entry<Integer, Integer> b) {
                return a.getValue() - b.getValue();
            }
        };
        
        Queue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(size, comparator);
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            minHeap.offer(entry);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        while (!minHeap.isEmpty()) {
            Map.Entry<Integer, Integer> current = minHeap.poll();
            result.add(current.getKey());
        }
        
        Collections.reverse(result);
        
        return result;
    }
}