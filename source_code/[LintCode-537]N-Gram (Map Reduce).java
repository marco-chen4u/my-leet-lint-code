/***
* LintCode 537. N-Gram (Map Reduce)
Give a number of strings and the number N. 
Use the Map Reduce method to count all N-Grams and their occurrences. 
The letter is granular.

Example
	Example 1:
		Input: N = 3
			doc_1: "abcabc"
			doc_2: "abcabc"
			doc_3: "bbcabc"
		Output:
			[
			  "abc": ５,
			  "bbc": 1, 
			  "bca": 3,
			  "cab": 3
			]

	Example 2:
		Input: N=3
			doc_1: "abcabc"
		Output:
			[
			  "abc": 2, 
			  "bca": 1,
			  "cab": 1
			]
***/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class NGram {

    public static class Map {
        public void map(String _, int n, String str,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, Integer value);
			int size = str.length();
			n = Math.min(n, size);
			for (int i = 0; i < size - n + 1; i++) {
				String word = str.substring(i, i + n);
				output.collect(word, 1);
			}
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
			String word = key;
			int count = 0;
			while (values.hasNext()) {
				count += values.next();
			}
			
			output.collect(word, count);
        }
    }
}