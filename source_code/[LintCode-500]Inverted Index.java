/***
* LintCode 500. Inverted Index
Create an inverted index with given documents.

Given a list of documents with id and content. (class Document)
Return an inverted index (HashMap with key is the word and value is a list of document ids).
	
Example 1:
    Input:
        [
          {
            "id": 1,
            "content": "This is the content of document 1 it is very short"
          },
          {
            "id": 2,
            "content": "This is the content of document 2 it is very long bilabial bilabial heheh hahaha ..."
          },
        ]
    Output:
        {
           "This": [1, 2],
           "is": [1, 2],
           ...
        }

Example 2:
    Input:
        [
          {
            "id": 1,
            "content": "you are young"
          },
          {
            "id": 2,
            "content": "you are handsome"
          },
        ]
    Output:
        {
           "are": [1, 2],
           ...
        }
Notice
    Ensure that data does not include punctuation.
***/
/**
 * Definition of Document:
 * class Document {
 *     public int id;
 *     public String content;
 * }
 */
public class Solution {
    // fields
    private final String SEPERATOR = " ";
    
    // helper method
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
    
    /**
     * @param docs a list of documents
     * @return an inverted index
     */
    public Map<String, List<Integer>> invertedIndex(List<Document> docs) {
        Map<String, List<Integer>> result = new HashMap<String, List<Integer>>();
        Map<String, Set<Integer>> wordMap = new HashMap<String, Set<Integer>>();
        
        for (Document doc : docs) {
            int index = doc.id;
            String[] tokens = doc.content.split(SEPERATOR);
            for (String token : tokens) {
                if (isEmptyStr(token)) {
                    continue;
                }
                
                wordMap.putIfAbsent(token, new HashSet<Integer>());
                wordMap.get(token).add(index);
            }
        }
        
        for (Map.Entry<String, Set<Integer>> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            List<Integer> indexList = new ArrayList<Integer>(entry.getValue());
			
            Collections.sort(indexList);
            
            result.put(word, indexList);
        }
        
        return result;
    }
}
