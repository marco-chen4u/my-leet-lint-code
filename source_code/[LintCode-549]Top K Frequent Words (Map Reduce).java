/***
* LintCode 549. Top K Frequent Words (Map Reduce)
Find top k frequent words with map reduce framework.
The mapper's key is the document id, value is the content of the document, words in a document are split by spaces.
For reducer, the output should be at most k key-value pairs, which are the top k words and their frequencies in this reducer. 
The judge will take care about how to merge different reducers' results to get the global top k frequent words, so you don't need to care about that part.

The k is given in the constructor of TopK class.
For the words with same frequency, rank them with alphabet.

Example1
    Input:
        document A = "lintcode is the best online judge
        I love lintcode" and 
        document B = "lintcode is an online judge for coding interview
        you can test your code online at lintcode"
    Output: 
        "lintcode", 4
        "online", 3

Example2
    Input:
        document A = "a a a b b b" and
        document B = "a a a b b b"
    Output: 
        "a", 6
        "b", 6
Notice
    For the words with same frequency, rank them with alphabet.
***/
 
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
// helper class 
class Element implements Comparable<Element> {
    // fileds
    String word;
    int count;

    // constructor
    public Element(String word, int count) {
        this.word = word;
        this.count = count;
    }
	
    // methods
    @Override
    public int compareTo(Element other) {
        if (this.count != other.count) {
            return this.count - other.count;
        }

        return other.word.compareTo(this.word);
    }
}
public class TopKFrequentWords {
    // field
    private final static String SEPERATOR = " ";

    public static class Map {
        // helper method
        private boolean isEmptyStr(String str) {
            return str == null || str.length() == 0;
        }
        
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            String[] tokens = value.content.split(SEPERATOR);
            for (String word : tokens) {
                // check corner case
                if (isEmptyStr(word)) {
                    continue;
                }

                output.collect(word, 1);
            }
        }
    }

    public static class Reduce {
        // fields
        private int k;
        private Queue<Element> minHeap;		

        public void setup(int k) {
            // initialize your data structure here
            this.k = k;
            //Comparator<Element> comparator = new Comparator<Element>() {
            //	@Override
            //	public int compare(Element a, Element b) {
            //		if (a.count != b.count) {
            //			return a.count - b.count;
            //		}
            //		
            //		return b.word.compareTo(a.word);
            //	}
            //};
            //minHeap = new PriorityQueue<Element>(comparator);

            minHeap = new PriorityQueue<Element>(k);
        }   

        public void reduce(String key, Iterator<Integer> values) {
            int count = 0;

            while (values.hasNext()) {
                count += values.next();
            }

            Element current = new Element(key, count);

            minHeap.offer(current);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        public void cleanup(OutputCollector<String, Integer> output) {
            // Output the top k pairs <word, times> into output buffer.
            // Ps. output.collect(String key, Integer value);
            List<Element> result = new ArrayList<Element>();
            while (!minHeap.isEmpty()) {
                result.add(0, minHeap.poll());
            }

            for (Element current : result) {
                output.collect(current.word, current.count);
            }
        }
    }
}
