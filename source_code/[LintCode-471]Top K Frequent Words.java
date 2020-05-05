/***
* LintCode 471. Top K Frequent Words
Given a list of words and an integer k, return the top k frequent words in the list.

Example
	Example 1:
		Input:
		  [
			"yes", "lint", "code",
			"yes", "code", "baby",
			"you", "baby", "chrome",
			"safari", "lint", "code",
			"body", "lint", "code"
		  ]
		  k = 3
		Output: ["code", "lint", "baby"]

	Example 2:
		Input:
		  [
			"yes", "lint", "code",
			"yes", "code", "baby",
			"you", "baby", "chrome",
			"safari", "lint", "code",
			"body", "lint", "code"
		  ]
		  k = 4
		Output: ["code", "lint", "baby", "yes"]

Challenge
	Do it in O(nlogk) time and O(n) extra space.

Notice
	You should order the words by the frequency of them in the return list, the most frequent one comes first. 
	If two words has the same frequency, the one with lower alphabetical order come first.
***/
public class Solution {
    // inner class
    class KeyWord{
        // fields
        String word;
        int frequency;
        // constructor
        public KeyWord(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }
    }
    /**
     * @param words: an array of string
     * @param k: An integer
     * @return: an array of string
     */
    public String[] topKFrequentWords(String[] words, int k) {
        // check corner case
        if (words == null || words.length == 0 || k < 1) {
            return new String[0];
        }
        
        int size = words.length;  // row size
        k = Math.min(k, size);
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        for(String item : words) {
            if (item == null || item.length() == 0) {
                continue;
            }
            
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        
        Comparator<KeyWord> comparator = new Comparator<KeyWord>() {
            @Override
            public int compare(KeyWord a, KeyWord b) {
                if (a.frequency != b.frequency) {
                    return a.frequency - b.frequency;
                }
                else {
                    return b.word.compareTo(a.word);
                }
            }
        };
        
        Queue<KeyWord> minHeap = new PriorityQueue<KeyWord>(comparator);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            KeyWord keyword = new KeyWord(entry.getKey(), entry.getValue()); 
            minHeap.offer(keyword);
            
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        
        String[] result = new String[k];
        int index = k - 1;
        while (!minHeap.isEmpty()) {
            KeyWord keyword = minHeap.poll();
            result[index--] = keyword.word;
        }
        
        return result;
    }
}