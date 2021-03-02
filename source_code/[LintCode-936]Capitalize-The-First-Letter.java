/***
* LintCode-936. Capitalizes The First Letter
936. Capitalizes The First Letter
中文English
Given a sentence of English, update the first letter of each word to uppercase.

Example
Example1

Input: s =  "i want to get an accepted"
Output: "I Want To Get An Accepted"
Example2

Input: s =  "i jidls    mdijf  i  lsidj  i p l   "
Output: "I Jidls    Mdijf  I  Lsidj  I P L   "
Notice
The given sentence may not be a grammatical sentence.
The length of the sentence does not exceed 100.

url: https://www.lintcode.com/problem/936/
***/
//solution-1: char array + corner case check
public class Solution {
    /**
     * @param s: a string
     * @return: a string after capitalizes the first letter
     */
    public String capitalizesFirst(String s) {
        int gapValue = 'A' - 'a';

        // check corner cases
        if (s == null || s.isEmpty()) {
            return s;
        }

        char[] charArray = s.toCharArray();
        
        // 1st charcacter process
        charArray[0] +=isLowerCaseCharacter(charArray[0]) ? gapValue : 0;

        int size = charArray.length;
        // regular case
        for (int i = 1; i < size; i++) {
            
            char currentChar = charArray[i];

            if (!isLowerCaseCharacter(currentChar)) {
                continue;
            }

            char preChar = charArray[i - 1];
            
            if (!isSpace(preChar)) {
                continue;
            }

            charArray[i] += gapValue;
        }

        return String.valueOf(charArray);
    }

    private boolean isLowerCaseCharacter(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    private boolean isSpace(char ch) {
        return ch == ' ';
    }
}
