/***
* LintCode 582. Word Break II
Given a string s and a dictionary of words dict, 
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
Example
    Gieve s = lintcode,
	dict = ["de", "ding", "co", "code", "lint"].
	A solution is ["lint code", "lint co de"].
***/
//version-1: recursion
public class Solution {
    // field
    private static final String EMPTY = "";
    private static final String SEPERATOR = " ";    
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        map.put(EMPTY, new ArrayList<String>());
        map.get(EMPTY).add(EMPTY);
        
        return wordBreakHelper(s, wordDict, map);
    }
	
    // helper method
    private List<String> wordBreakHelper(String s, Set<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        
        List<String> result = new ArrayList<String>();        
        for (int len = 1; len <= s.length(); len++) {// divide s into s1 and s2, the size of s1 is len
            String s1 = s.substring(0, len);
            String s2 = s.substring(len);
            
            if (wordDict.contains(s1)) {
                List<String> s2WordBreakResult = wordBreakHelper(s2, wordDict, map);
                for (String s2Word : s2WordBreakResult) {
                    if (s2Word == EMPTY) {
                        result.add(s1);
                    }
                    else{
                        result.add(s1 + SEPERATOR + s2Word);
                    }
                }
            }
        }
        
        map.put(s, result);        
        return result;
    }
}

//version-2: recursion
class Solution {
    // fields
    private int n; // string size
    private int maxLength; // word's max length
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (s == null || s.isEmpty() ||
            wordDict == null || wordDict.isEmpty()) {
            return result;
        }
        
        n = s.length();
        Set<String> dict = new HashSet<String>(wordDict);
        if (!isWordBreak(s, dict)) {
            return result;
        }
        
        search(result, dict, new StringBuilder(), s, 0);
        
        return result;
    }
    
    // helper methods
    private boolean isWordBreak(String s, Set<String> dict) {
        boolean[] dp = new boolean[n + 1];
        
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
    
    private void search(List<String> result, 
                        Set<String> dict, 
                        StringBuilder sb, 
                        String s, 
                        int startIndex) {
        if (startIndex == n) {
            sb.setLength(sb.length() - 1);
            result.add(new String(sb));
            return;
        }
        
        for (int i = startIndex; i < n; i++) {
            if (!dict.contains(s.substring(startIndex, i + 1))) {
                continue;
            }
            
            int length = sb.length();
            sb.append(s.substring(startIndex, i + 1));
            sb.append(" ");
            search(result, dict, sb, s, i + 1);
            sb.setLength(length);
        }
    }
}
