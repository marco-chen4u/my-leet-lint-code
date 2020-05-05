/***
* LeetCode 244. Shortest Word Distance II
Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 

Example:

Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
	Input: word1 = “coding”, word2 = “practice”
	Output: 3

	Input: word1 = "makes", word2 = "coding"
	Output: 1

Note:
	You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
***/
class WordDistance {
    // fields
    private final int MAX = Integer.MAX_VALUE;
    private Map<String, List<Integer>> map; // <word, [index1, index2, ....]> key value pair

    public WordDistance(String[] words) {
        int size = words.length;
        map = new HashMap<String, List<Integer>>();
        for (int i = 0; i < size; i++) {
            String word = words[i];
            
            map.putIfAbsent(word, new ArrayList<Integer>());
            
            map.get(word).add(i);// ascending order of indexes
        }
    }
    
    public int shortest(String word1, String word2) {
        int result = MAX;
        if (map.isEmpty() || !map.containsKey(word1) || !map.containsKey(word2)) {
            return result;
        }
        
        List<Integer> list1 = map.get(word1);
        List<Integer> list2 = map.get(word2);
        int size1 = list1.size();
        int size2 = list2.size();
                
        // check corner case
        if (size1 == 0 || size2 == 0) {
            return result;
        }
        
        // two pointers
        int i = 0;
        int j = 0;
        while (i < size1 && j < size2) {
            result = Math.min(result, Math.abs(list1.get(i) - list2.get(j)));
            
            if (list1.get(i) < list2.get(j)) {
                i++;
            }
            else {
                j++;
            }
        }
        
        return result;
    }
}

/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */