/***
* LintCode 503. Anagram (Map Reduce)
Use Map Reduce to find anagrams in a given list of words.
Example
	Example 1:
		Input: "lint lint lnit ln"
		Output: 
		  ["lint", "lint", "lnit"]
		  ["ln"]
	Example 2:
		Input: "ab ba cab"
		Output:
		  ["ab", "ba"]
		  ["cab"]
***/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class Anagram {
	// field
	private static final String SEPERATOR = " ";
	private static final String EMPTY = "";
	
	// helper method
	private static boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
	
	private static String getAnagramKey(String value) {
		if (isEmpty(value)) {
			return EMPTY;
		}
		
		char[] charArray = value.toCharArray();
		
		Arrays.sort(charArray);
		
		return new String(charArray);
	}

    public static class Map {
        public void map(String key, String value,
                        OutputCollector<String, String> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, String value);
			String[] tokens = value.split(SEPERATOR);
			for (String token : tokens) {
				String tokenKey = getAnagramKey(token);
				output.collect(tokenKey, token);
			}
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<String> values,
                           OutputCollector<String, List<String>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<String> value);
			List<String> result = new ArrayList<String>();
			while (values.hasNext()) {
				String value = values.next();
				result.add(value);
			}
			
			output.collect(key, result);
        }
    }
}
