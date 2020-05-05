/***
* LintCode 422. Length of Last Word
Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

If the last word does not exist, return 0.

Example
	Example 1:
		Input: "Hello World"
		Output: 5

	Example 2:
		Input: "Hello LintCode"
		Output: 8

Notice
	A word is defined as a character sequence consists of non-space characters only.
***/
//Version-1 : Array and String.split() method
public class Solution {
    // field
    private final String SEPARATOR = " ";
    /**
     * @param s: A string
     * @return: the length of last word
     */
    public int lengthOfLastWord(String s) {
        if (isEmptyStr(s)) {
            return 0;
        }
        
        String[] tokens = s.split(SEPARATOR);

        int size = tokens.length;
        int lastPos = size -1;
        String lastToken = tokens[lastPos];
        
        if (isEmptyStr(lastToken)) {
            return 0;
        }
        else {
            return lastToken.length();
        }
    }
    
    // helper method
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
}

//Version-2 : String.lastIndexOf() method
public class Solution {
    // field
    private final String SEPARATOR = " ";
    
    /**
     * @param s: A string
     * @return: the length of last word
     */
    public int lengthOfLastWord(String s) {
        // check corner case
        if (isEmptyStr(s)) {
            return 0;
        }
        
        s = s.trim();
        int size = s.length();
        int index = s.lastIndexOf(SEPARATOR);
        if (index != -1) {
            String lastToken = s.substring(index + 1, size);
            
            if (!isEmptyStr(lastToken)) {
                return lastToken.length();
            }
            else {
                return 0;
            }
        }
        
        return size;
    }
    
    // helper method
    private boolean isEmptyStr(String str) {
        return str == null || str.length() == 0;
    }
}