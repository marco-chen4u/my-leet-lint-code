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
//version-1: dp,sequential type dynampic programming
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

//version-2: DP
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
                f[i] = f[j] && isWordBreak(s, j, i, dict);
                if (f[i]){
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

//version-3: DP
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

