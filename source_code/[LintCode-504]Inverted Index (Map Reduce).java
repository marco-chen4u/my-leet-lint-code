/***
* LintCode 504. Inverted Index (Map Reduce)
Use map reduce to build inverted index for given documents.

Example
    Look at the program.

    Input
        [{"id":1,"content":"This is  the content of document1"}, 
         {"id":2,"content":"This is the       content of document3"}
        ]
    Output
        {"This":[1,2],
         "content":[1,2],
         "document1":[1],
         "document3":[2],
         "is":[1,2],
         "of":[1,2],
         "the":[1,2]
        }
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
public class InvertedIndex {
    private static final String SEPERATOR =" ";
    
    // helper method
    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static class Map {
        public void map(String _, Document value,
                        OutputCollector<String, Integer> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, int value);
            int id = value.id;
            String[] tokens = value.content.split(SEPERATOR);
            for (String token : tokens) {
                // check corner case
                if (SEPERATOR.equals(token) || isEmpty(token)) {
                    continue;
                }
                
                output.collect(token, id);
            }
        }
    }

    public static class Reduce {
        public void reduce(String key, Iterator<Integer> values,
                           OutputCollector<String, List<Integer>> output) {
            // Write your code here
            // Output the results into output buffer.
            // Ps. output.collect(String key, List<Integer> value);

            if (key == null || key.isEmpty()) {
                return;
            }

            Set<Integer> documentIdSet = new HashSet<>();
            // remove the duplicates
            while (values.hasNext()) {
                int documentId = values.next();
                documentIdSet.add(documentId);
            }

            List<Integer> documentIdList = new ArrayList<>(documentIdSet);
            // sort the document id list
            Collections.sort(documentIdList);

            output.collect(key, documentIdList);
        }
}
