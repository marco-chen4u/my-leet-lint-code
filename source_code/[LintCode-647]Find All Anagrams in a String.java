/***
* LintCode 647. Find All Anagrams in a String
Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 40,000.
The order of output does not matter.

Example
	Example 1:
		Input : s =  "cbaebabacd", p = "abc"
		Output : [0, 6]
		Explanation : 
			The substring with start index = 0 is "cba", which is an anagram of "abc".
			The substring with start index = 6 is "bac", which is an anagram of "abc".
***/

//version-1: time complexity : O((|s| - |p|) * |p|) = O(n^2), when in worse case like |s| = 2 * |p|
public class Solution {
    /**
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        
        // check corner case
        if (p == null || p.length() == 0) {
            return result;
        }
        
        int m = p.length();
        int n = s.length();
        
        for (int i = 0; i <= n - m; i++) {
            String subString = s.substring(i, i + m);
            
            if (isAnagram(subString, p)) {
                result.add(i);
            }
        }
        
        return result;
    }
    
    // helper method
    private boolean isAnagram(String a, String b) {
        int[] values = new int[26];
        for (char ch : a.toCharArray()) {
            values[ch - 'a']++;
        }
        
        for (char ch : b.toCharArray()) {
            values[ch - 'a']--;
        }
        
        for (int i = 0; i < values.length; i++) {
            if (values[i] != 0) {
                return false;
            }
        }
        
        return true;
    }
}

//version-2 : O(n), Sliding Window
public class Solution {
    private final int SIZE = 256;
    /**
     * @param s: a string
     * @param p: a string
     * @return: a list of index
     */
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<Integer>();
        
        // check corner cases
        if (isEmptyStr(s) || isEmptyStr(p)) {
            return result;
        }
        
        int[] hash = new int[SIZE];
        int[] values = new int[SIZE];
        
        // initialize hash value of target window
        for (char ch : p.toCharArray()) {
            hash[ch]++;
        }
        
        int n = s.length();
        int m = p.length();
        
        char[] charArray = s.toCharArray();
        
        for (int i = 0; i < n; i++) {
            //System.out.println("i = " + i + "charArray[" + i + "] = " + charArray[i]);
            char current = charArray[i];
            values[current]++;
            
            if (i > m - 1) {
                char lastWindowIndexPos = charArray[i - m];
                //System.out.println("lastWindowIndexPos = " + lastWindowIndexPos);
                values[lastWindowIndexPos]--;
            }
            
            if (i >= m - 1) {
                if (isAnagram(values, hash)) {
                    result.add(i - (m - 1));
                }
            }
            
        }
        
        return result;
    }
    
    // helper methods
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
    
    private boolean isAnagram(int[] values, int[] hash) {
        if (values.length != hash.length) {
            return false;
        }
        
        int size = values.length;
        
        for (int i = 0; i < size; i++) {
            if (values[i] != hash[i]) {
                return false;
            }
        }
        
        return true;
    }
}