/***
* LintCode 550. Top K Frequent Words II
Find top k frequent words in realtime data stream.

Implement three methods for Topk Class:
TopK(k). The constructor.
add(word). Add a new word.
topk(). Get the current top k frequent words.

Example
	Example 1:
		Input：
			TopK(2)
			add("lint")
			add("code")
			add("code")
			topk()
		Output：["code", "lint"]
		Explanation：
			"code" appears twice and "lint" appears once, they are the two most frequent words.

	Example 2:
		Input：
			TopK(1)
			add("aa")
			add("ab")
			topk()
		Output：["aa"]
		Explanation：
			"aa" and "ab" appear once , but aa's dictionary order is less than ab's.

Notice
	If two words have the same frequency, rank them by dictionary order.
***/
public class TopK {    
    // fields
    private Map<String, Integer> map;
    private NavigableSet<String> set;
    private int MAX_SIZE;
    private Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                if (a.equals(b)) {
                    return 0;
                }
                
                int frequencyA = map.get(a);
                int frequencyB = map.get(b);
                
                if (frequencyA != frequencyB) {
                    return frequencyB - frequencyA;
                }
                
                return a.compareTo(b);
            }
        };
    
    /*
    * @param k: An integer
    */public TopK(int k) {
        // do intialization if necessary
        MAX_SIZE = k;
        map = new HashMap<String, Integer>();
        set = new TreeSet<String>(comparator);
    }

    /*
     * @param word: A string
     * @return: nothing
     */
    public void add(String word) {
        int count = map.getOrDefault(word, 0);
        if (count >= 1) {
            set.remove(word);
        }
        
        count++;
        
        map.put(word, count);
         
        set.add(word);
        
        if (set.size() > MAX_SIZE) {
            set.pollLast();
        }
    }

    /*
     * @return: the current top k frequent words.
     */
    public List<String> topk() {
        List<String> result = new ArrayList<String>();
        
        for (String item : set) {
            result.add(item);
        }
        
        return result;
    }
}