/***
Given an array of integers and a number k, the majority number is the number that occurs more than 1/k of the size of the array.
Find it.

Example 1:
    Input: [3,1,2,3,2,3,3,4,4,4] and k=3, 
    Output: 3.

Example 2:
    Input: [1,1,2] and k=3, 
    Output: 1.

Challenge
    O(n) time and O(k) extra space

Link: https://www.lintcode.com/problem/majority-number-iii/
***/
//solution-1: similar to Boyer-Moore voting algorithm, but using HashMap to store all k candidates with counters.
public class Solution {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The majority number
     */
    public int majorityNumber(List<Integer> nums, int k) {
        int result = -1;
        
        // check corner case
        if (nums == null || nums.isEmpty()) {
            return result;
        }
        
        
        int size = nums.size();
        int majority = size / k;
        Map<Integer, Integer> candidateMap = new HashMap<>();
        
        // (1) locate the candidates
        for (int num : nums) {
            candidateMap.put(num, candidateMap.getOrDefault(num, 0) + 1);
            
            if (candidateMap.size() >= k) {
                doEviction(candidateMap);
            }
        }
        
        // (2) calculate candidate counter if it is satisfied by majority
        for (int key : candidateMap.keySet()) {
            candidateMap.put(key, 0);
        }
        
        for (int num : nums) {
            if (candidateMap.containsKey(num)) {
                candidateMap.put(num, candidateMap.get(num) + 1);
            }
        }
        
        int maxCounter = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : candidateMap.entrySet()) {
            int candidate = entry.getKey();
            int counter = entry.getValue();
            
            if (counter > majority && counter > maxCounter) {
                maxCounter = counter;
                result = candidate;
            }
        }
        
        return result;
    }
    
    // helper method
    private void doEviction(Map<Integer, Integer> candidateMap) {
        List<Integer> evictionList = new ArrayList<>();
        for (int key : candidateMap.keySet()) {
            candidateMap.put(key, candidateMap.get(key) - 1);
            if (candidateMap.get(key) == 0) {
                evictionList.add(key);
            }
        }
        
        for (int key : evictionList) {
            candidateMap.remove(key);
        }
    }
}
