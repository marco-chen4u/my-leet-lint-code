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
        
        if (dict == null || dict.isEmpty()) {
            return false;
        }
        
        int n = s.length();
        int maxLength = getMaxLength(dict);
        
        boolean[] f = new boolean[n + 1];
        
        f[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = i - 1; j >= 0 && i - j <= maxLength; j--) {
                if (f[j] && isWordBreak(s, j, i, dict)) {
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[n];
    }
    
    // helper methods
    private int getMaxLength(Set<String> dict) {
        int result = Integer.MIN_VALUE;
        for (String keyWord : dict) {
            result = Math.max(result, keyWord.length());
        }
        
        return result;
    }
    
    private boolean isWordBreak(String s, int startIndex, int endIndex, Set<String> dict) {
        if (startIndex >= endIndex) {
            return false;
        }
        
        return dict.contains(s.substring(startIndex, endIndex));
    }
}