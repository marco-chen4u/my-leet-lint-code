/***
* LintCode 231. Typeahead
Implement typeahead. Given a string and a dictionary, return all words that contains the string as a substring. 
The dictionary will give at the initialize method and wont be changed. 
The method to find all words with given substring would be called multiple times.


Example 1
    Input:
        dict=["Jason Zhang", "James Yu", "Lee Zhang", "Yanny Li"]
        search("Zhang")
        search("James")
    Output:
        ["Jason Zhang","Lee Zhang"]
        ["James Yu"]

Example 2
    Input:
        dict=["San Zhang","Lisi","Li Ma","Jimmy Wang"]
        search("Li")
    Output:
        ["Li Ma","Lisi"]
***/
public class Typeahead {
    // fields
    private final String SEPERATOR = " ";

    private Set<String> dict;
    private Map<String, Set<String>> prefixMap;

    //public methods
    /*
    * @param dict: A dictionary of words dict
    */
    public Typeahead(Set<String> dict) {
        // do intialization if necessary
        this.dict = dict;
        this.prefixMap = new HashMap<String, Set<String>>();

        for (String word : dict) {
            generatePrefixMap(word, prefixMap);
        }
    }

    /*
     * @param str: a string
     * @return: a list of words
     */
    public List<String> search(String str) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (isEmptyStr(str) || !prefixMap.containsKey(str)) {
            return result;
        }

        Set<String> wordSet = prefixMap.get(str);
        for (String word : wordSet) {
            result.add(word);
        }

        return result;
    }

    // helper methods
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }

    private void generatePrefixMap(String word, Map<String, Set<String>> prefixMap) {
        // check corner case
        if (isEmptyStr(word)) {
            return;
        }
	
        // String[] tokens = word.split(SEPERATOR);
        // String prefix = null;
        // for (String token : tokens) {
        //    int size = token.length();
        //    for (int i = 0; i < size; i++) {
        //        for (int j = i + 1; j <= size; j++) {
        //            prefix = token.substring(i, j);
        //            prefixMap.putIfAbsent(prefix, new HashSet<String>());
        //            prefixMap.get(prefix).add(word); 
        //        }
        //    }
        // }
       
        String prefix = null;
        int size = word.length();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j <= size; j++) {
                    prefix = word.substring(i, j);
                    prefixMap.putIfAbsent(prefix, new HashSet<String>());
                    prefixMap.get(prefix).add(word);
            }
        }
	    
    }
	
    private List<String> getAllValues() {
        List<String> result = new ArrayList(prefixMap.values());
        return result;
    }
}
