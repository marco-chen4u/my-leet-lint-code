/***
* LintCode 499. Word Count (Map Reduce)
Using map reduce to count word frequency.
(link: https://hadoop.apache.org/docs/r1.2.1/mapred_tutorial.html#Example%3A+WordCount+v1.0)

Example 1
    Input:
        chunk1: "Google Bye GoodBye Hadoop code"
        chunk2: "lintcode code Bye"
    Output:
        Bye: 2
        GoodBye: 1
        Google: 1
        Hadoop: 1
        code: 2
        lintcode: 1
Example 2
    Input:
        chunk1: "Lintcode is so so good"
    Output:
        Lintcode: 1
        good: 1
        is: 1
        so: 2
***/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 */
public class WordCount {
    // constants
    private static final String SPERATOR = " ";

    public static class Map {
        public void map(String key, String value, OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            String[] tokens = value.split(SPERATOR);
            for (String token : tokens) {
                output.collect(token, 1);
            }

        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int count = 0;
            while (values.hasNext()) {
                count += values.next();
            }

            output.collect(key, count);
        }
    }
}
