/***
* LintCode 158. Valid Anagram
Write a method anagram(s,t) to decide if two strings are anagrams or not.

Example
	Example 1:
		Input: s = "ab", t = "ab"
		Output: true

	Example 2:
		Input:  s = "abcd", t = "dcba"
		Output: true

	Example 3:
		Input:  s = "ac", t = "ab"
		Output: false

Challenge
	O(n) time, O(1) extra space

Clarification
	What is Anagram?

	Two strings are anagram if they can be the same after change the order of characters.
***/
public class Solution {
    /**
     * @param s: The first string
     * @param t: The second string
     * @return: true or false
     */
    public boolean anagram(String s, String t) {
        // check corner case
        if (s == null && t == null || 
            s.length() == 0 && s.length() == 0) {
            return true;
        }
        
        if (s.length() != t.length()) {
            return false;
        }
        
        int size = s.length();
        int[] values = new int[256];
        
        for (char ch : s.toCharArray()) {
            values[ch]++;
        }
        
        for (char ch : t.toCharArray()) {
            values[ch]--;
        }
        
        for (int value : values) {
            if (value != 0) {
                return false;
            }
        }
        
        return true;
    }
}