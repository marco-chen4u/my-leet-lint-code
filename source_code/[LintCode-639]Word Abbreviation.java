/***
* LintCode 639. Word Abbreviation
Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
	1.Begin with the first character and then the number of characters abbreviated, which followed by the last character.
	2.If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation cannot map to more than one original words.
	3.If the abbreviation doesn't make the word shorter, then keep it as original.

Example
	Example 1:
		Input:
			["like","god","internal","me","internet","interval","intension","face","intrusion"]
		Output:
			["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]

	Example 2:
		Input:
			["where","there","is","beautiful","way"]
		Output:
			["w3e","t3e","is","b7l","way"]

Notice
	1.Both n and the length of each word will not exceed 400.
	2.The length of each word is greater than 1.
	3.The words consist of lowercase English letters only.
	4.The return answers should be in the same order as the original array.
***/
public class Solution {
    /**
     * @param dict: an array of n distinct non-empty strings
     * @return: an array of minimal possible abbreviations for every word
     */
    public String[] wordsAbbreviation(String[] dict) {
        // check corner case
        if (dict == null || dict.length == 0) {
            return dict;
        }
        
        // normal case
        String[] result = new String[dict.length];
        Map<String, Integer> count = new HashMap<String, Integer>();
        
        //round-1
        int round = 1;
        for (int i = 0; i < dict.length; i++) {
            result[i] = getAbbreviation(dict[i], round);
            count.put(result[i], count.getOrDefault(result[i], 0) + 1);
        }
        
        // round-2
        // round-3
        // round-n....
        while (true) {
            round++;
            boolean unique = true;//flag to terminate the while loop
            // check if there exists duplicates
            for (int i = 0; i < dict.length; i++) {
                if (count.get(result[i]) > 1) {
                    result[i] = getAbbreviation(dict[i], round);
                    count.put(result[i], count.getOrDefault(result[i], 0) + 1);
                    unique = false;// still has some duplicates
                }
            }
            
            if (unique) {// all are unique without duplicates
                break;
            }
        }
        
        return result;
     }
     
    // helper method
    private String getAbbreviation(String s, int p) {// p : prefix round
        if (p + 2 >= s.length()) {// rule 3
            return s;
        }
        
        String result = null;
        result = s.substring(0, p) + (s.length() - 1 - p) + s.charAt(s.length() - 1);//rule 1&2
        return result;
    }
}