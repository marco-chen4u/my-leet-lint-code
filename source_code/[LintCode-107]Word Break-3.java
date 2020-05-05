/***
* LintCode 107. Word Break
Given a string s and a dictionary of words dict, 
determine if s can be break into a space-separated sequence of one or more dictionary words.

Example
	Example 1:
		Input:  "lintcode", ["lint", "code"]
		Output:  true

	Example 2:
		Input: "a", ["a"]
		Output:  true
	
***/
public class Solution {

	//helper mehtod to get the max length of the dictionary
    private int getMaxWordLength(Set<String> dict) {
        if (dict == null || dict.size() == 0) {
            return 0;
        }
        
        int max = Integer.MIN_VALUE;
        for (String word : dict) {
            max = Math.max(word.length(), max);
        }
        
        return max;
    }
    /*
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
    public boolean wordBreak(String s, Set<String> dict) {
        // check corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        if (dict == null || dict.size() == 0) {
            return false;
        }
        
        // state
        int n = s.length();
        boolean[] canWordBreak = new boolean[n + 1];
        
        // initialize
        canWordBreak[0] = true;
        int maxWordLength = getMaxWordLength(dict);
        
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= maxWordLength && j <= i; j++) {
                if (!canWordBreak[i - j]) {
                    continue;
                }
                
                String word = s.substring(i - j, i);
                if (dict.contains(word)) {
                    canWordBreak[i] = true;
                    break;
                }
            }
        }
        
        // return
        return canWordBreak[n];
        
    }
}