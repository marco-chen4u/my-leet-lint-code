/***
* LintCode 554. Sort Integers (Map Reduce)
Sort integers by Map Reduce framework.
In the mapper, key is the document id which can be ignored, value is the integers.
In the reducer, your can specify what the key / value is (this depends on how you implement your mapper class). 
For the output of the reducer class, the key can be anything you want, the value should be ordered. 
(the order is depending on when you output it)

Example
Example 1:
	Input:
		1: [14,7,9]
		2: [10,1]
		3: [2,5,6,3]
		4: []
	Output:
		[1,2,3,5,6,7,9,10,14]
Example 2:
	Input:
		1: [14,7]
	Output:
		[7,14]
***/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class SortIntegers {
    private static final String IGNORE_KEY = "IGNORE KEY";

    public static class Map {
        public void map(int _, List<Integer> value,
                        OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);
            Collections.sort(value);
            output.collect(IGNORE_KEY, value);
        }
    }
        
    public static class Reduce {
        public void reduce(String key, List<List<Integer>> values,
                           OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);
            int size = values.size();
            List<Integer> result = mergeSort(values, 0 , size - 1);
            
            output.collect(key, result);
        }
        
        // helper methods
        private List<Integer> mergeSort(List<List<Integer>> values, 
                                            int start , int end) {
            // check corner case
            if (start == end) {
                return values.get(start);
            }
            
            int mid = start + (end - start) / 2;
            
            List<Integer> leftResult = mergeSort(values, start, mid);
            List<Integer> rightResult = mergeSort(values, mid + 1, end);
            
            List<Integer> result = merge(leftResult, rightResult);
            
            return result;
        }
        
        private List<Integer> merge(List<Integer> left, List<Integer> right) {
            List<Integer> result = new ArrayList<Integer>();
            
            int i = 0;
            int j = 0;
            
            while (i < left.size() && j < right.size()) {
                if (left.get(i) <= right.get(j)) {
                    result.add(left.get(i++));
                }
                else {
                    result.add(right.get(j++));
                }
            }
            
            while (i < left.size()) {
                result.add(left.get(i++));
            }
            
            while (j < right.size()) {
                result.add(right.get(j++));
            }
            
            return result;
        }
    }
}