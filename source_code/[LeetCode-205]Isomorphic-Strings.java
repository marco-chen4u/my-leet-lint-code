/***
* LeetCode 205. Isomorphic String
Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. 
No two characters may map to the same character but a character may map to itself.

Example 1:
    Input: s = "egg", t = "add"
    Output: true

Example 2:
    Input: s = "foo", t = "bar"
    Output: false

Example 3:
    Input: s = "paper", t = "title"
    Output: true

Note:
    You may assume both s and t have the same length.
***/
//solution1: HashMap
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if the characters in s can be replaced to get t or false
     */
    public boolean isIsomorphic(String s, String t) {
        // check corner cases
        if (s == null && t == null) {
            return true;
        }
        
        if (s == null || t == null) {
            return false;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        // regular case
        Map<Character, Character> map = new HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            if (map.containsKey(sChar)) {
                
                if (!map.containsValue(tChar) ||
                    map.get(sChar) != tChar) {
                    return false;
                }
                
                continue;
            }
            
            if (map.containsValue(tChar)) {
                return false;
            } 
            
            map.put(sChar, tChar);
        }
        
        return true;
    }
}
//solution2: BitArray
class Solution {
    public boolean isIsomorphic(String s, String t) {
        // check corner case
        if (s == null && t == null) {
            return true;
        }
        
        if (s == null || t == null) {
            return false;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        // regular case
        int[] map = new int[256];
        int size = s.length();
        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Arrays.fill(map, 0);
        
        for (int i = 0; i < size; i++) {
            int sIndex = sCharArray[i];
            int tValue = tCharArray[i];
            
            map[sIndex] += (map[sIndex] == 0) ? tValue : 0;
            
            if (map[sIndex] != tValue) {
                return false;
            }
            
        }
        
        Arrays.fill(map, 0);
        for (int i = 0; i < size; i++) {
            int tIndex = tCharArray[i];
            int sValue = sCharArray[i];
            
            map[tIndex] += (map[tIndex] == 0) ? sValue : 0;
            
            if (map[tIndex] != sValue) {
                return false;
            }
        }
        
        return true;
        
    }
}
