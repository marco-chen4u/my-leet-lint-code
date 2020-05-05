/***
* LintCode 637. Valid Word Abbreviation
Given a non-empty string word and an abbreviation abbr, return whether the string matches with the given abbreviation.

A string such as "word" contains only the following valid abbreviations:

["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

Example
	Example 1:
		Input : s = "internationalization", abbr = "i12iz4n"
		Output : true
	Example 2:
		Input : s = "apple", abbr = "a2e"
		Output : false

Notice
	Notice that only the above abbreviations are valid abbreviations of the string word. 
	Any other string is not a valid abbreviation of word.
***/
public class Solution {
    /**
     * @param word: a non-empty string
     * @param abbr: an abbreviation
     * @return: true if string matches with the given abbr or false
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        if (word == null && abbr == null) {
            return true;
        }
        
        if (word.length() == 0 && abbr.length() == 0) {
            return true;
        }
        
        if (word == null || word.length() == 0 ||
            abbr == null || abbr.length() == 0) {
            return false;
        }
        
        int i = 0; 
        int j = 0;
        char[] s = word.toCharArray();
        char[] t = abbr.toCharArray();
        
        while (i < s.length && j < t.length) {
            if (Character.isDigit(t[j])) {
                if (t[j] == '0') {// no '0' beginning
                    return false;
                }
                
                int value = 0;
                while (j < t.length && Character.isDigit(t[j])) {
                    value = value * 10 + (t[j] - '0');
                    j++;
                }
                
                i += value;
            }
            else {
                if (s[i] != t[j]) {
                    return false;
                }
                
                i++;
                j++;
            }
        }
        
        return i == s.length && j == t.length;
    }
}