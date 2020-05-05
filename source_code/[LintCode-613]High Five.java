/***
* LintCode 613. High Five
There are two properties in the node student id and scores, to ensure that each student will have at least 5 points, find the average of 5 highest scores for each person.

Example
	Example 1:
		Input: 
		[[1,91],[1,92],[2,93],[2,99],[2,98],[2,97],[1,60],[1,58],[2,100],[1,61]]
		Output:
		1: 72.40
		2: 97.40

	Example 2:
		Input:
		[[1,90],[1,90],[1,90],[1,90],[1,90],[1,90]]
		Output: 
		1: 90.00
***/
/**
 * Definition for a Record
 * class Record {
 *     public int id, score;
 *     public Record(int id, int score){
 *         this.id = id;
 *         this.score = score;
 *     }
 * }
 */
public class Solution {
    /**
     * @param results a list of <student_id, score>
     * @return find the average of 5 highest scores for each person
     * Map<Integer, Double> (student_id, average_score)
     */
    public Map<Integer, Double> highFive(Record[] results) {
        Map<Integer, Double> result = new HashMap<Integer, Double>();
        // check corner case
        if (results == null || results.length == 0) {
            return result;
        }
        
        
        Map<Integer, Queue<Integer>> map = new HashMap<Integer, Queue<Integer>>();
        for (Record record : results) {
            map.putIfAbsent(record.id, new PriorityQueue<Integer>(5));
            
            Queue<Integer> minHeap = map.get(record.id);
            
            minHeap.offer(record.score);
            if (minHeap.size() > 5) {
                minHeap.poll();
            }
        }
        
        for (Map.Entry<Integer, Queue<Integer>> entry : map.entrySet()) {
            int key = entry.getKey();
            Queue<Integer> queue = entry.getValue();
            double value = 0;
            while (!queue.isEmpty()) {
                value += queue.poll();
            }
            
            value /= 5;
            
            result.put(key, value);
        }
        
        return result;
    }
}