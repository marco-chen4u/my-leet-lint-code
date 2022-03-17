/***
* LintCode 1787. Google Suggestion (Map Reduce)
Use MapReduce framework to build a key-value index for Google Suggestion, 
where the key is the prefix of a query and the value is the top 10 searched queries.

You don't need go through all queries and calculate the number of searches, 
assume you are given a list of queries and their number of searches, 
which is the output of another map reduce problem - Word Count.

The key of the map function is the document id which you can ignore it. 
The value of the map function is a document instance which contains two member variables, word and count. 
e.g. "hello 100", that means the query "hello" has been searched 10 times. 
The output the the map function depending on your algorithm, 
we won't check it so you can output anything you want as key-value pairs.

The key, value of the reduce function is depending on what you output in the map function. 
The output of the reduce function is key-value pairs where the key is the prefix, 
the value is top 10 queries and their counts. Use the Document class to wrap them.

Example1
    Input:
        [("apple",100), ("app",1200), ("app store",1200)]
    Output: 
        "a": [("app", 1200), ("app store", 1200), ("apple", 100)]
        "ap": [("app", 1200), ("app store", 1200), ("apple", 100)]
        "app": [("app", 1200), ("app store", 1200), ("apple", 100)]
        "app ": [("app store", 1200)]
        "app s": [("app store", 1200)]
        "app st": [("app store", 1200)]
        "app sto": [("app store", 1200)]
        "app stor": [("app store", 1200)]
        "app store": [("app store", 1200)]
        "appl": [("apple", 100)]
        "apple": [("apple", 100)]

**Example2**
    Input:
        [("apple",1200), ("app",1200), ("app store",1200)]
    Output: 
        "a": [("app", 1200), ("app store", 1200), ("apple", 1200)]
        "ap": [("app", 1200), ("app store", 1200), ("apple", 1200)]
        "app": [("app", 1200), ("app store", 1200), ("apple", 1200)]
        "app ": [("app store", 1200)]
        "app s": [("app store", 1200)]
        "app st": [("app store", 1200)]
        "app sto": [("app store", 1200)]
        "app stor": [("app store", 1200)]
        "app store": [("app store", 1200)]
        "appl": [("apple", 1200)]
        "apple": [("apple", 1200)]
***/
/**
 * Definition of OutputCollector:
 * class OutputCollector<K, V> {
 *     public void collect(K key, V value);
 *         // Adds a key/value pair to the output buffer
 * }
 * Definition of Document:
 * class Document {
 *     public int count;
 *     public String content;
 * }
 *
 *class Pair {
 *   private String content;
 *   private int count;
 *   
 *   Pair(String content, int count) {
 *       this.content = content;
 *       this.count = count;
 *   }
 *
 *   public String getContent(){
 *	 	 return this.content;
 *	 }
 *
 *	public int getCount(){
 *   	 return this.count;
 *   }
 *
 *}
 */
public class GoogleSuggestion {

    public static class Map {
        public void map(Document value,
                        OutputCollector<String, Pair> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, Pair value);
            String wordContent = value.content;
            int count = value.count;
            
            String prefix = "";
            int size = wordContent.length();
            Pair currentPair = new Pair(wordContent, count);
            
            for (int i = 0; i < size; i++) {
                prefix += wordContent.charAt(i);
                output.collect(prefix, currentPair);
            }
        }
    }

    public static class Reduce {
        // field
        private Queue<Pair> minHeap;
        private Comparator<Pair> comparator;
        
        public void setup() {
            // initialize your data structure here
            this.comparator = new Comparator<Pair>() {
                @Override
                public int compare(Pair a, Pair b) {
                    if (a.getCount() != b.getCount()) {
                        return a.getCount() - b.getCount();
                    }
                    
                    return b.getContent().compareTo(a.getContent());
                }
                
            };
            
            this.minHeap = new PriorityQueue<Pair>(comparator);
        }
		
        public void reduce(String key, Iterator<Pair> values, OutputCollector<String, Pair> output) {
    		// Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, Pair value);
            this.setup();
            
            while (values.hasNext()) {
                Pair current = values.next();
                
                minHeap.offer(current);
                if (minHeap.size() > 10) {
                    minHeap.poll();
                }
            }
            
            List<Pair> result = new ArrayList<Pair>();
            while (!minHeap.isEmpty()) {
                result.add(0, minHeap.poll());//reverse order
            }
            
            for (Pair current : result) {
                output.collect(key, current);
            }
        }
    }
}
