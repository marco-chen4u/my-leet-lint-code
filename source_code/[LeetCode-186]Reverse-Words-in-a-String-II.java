/***
* LeetCode 186. Reverse Words in a String II
Given an input string , reverse the string word by word. 

Example:
    Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
    Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]

Note: 
    A word is defined as a sequence of non-space characters.
    The input string does not contain leading or trailing spaces.
    The words are always separated by a single space.

Follow up: Could you do it in-place without allocating extra space?

Link: https://leetcode.com/problems/reverse-words-in-a-string-ii/
***/
//solution-1: O(n) time and O(1) space: In place
/*
* Reverse the whole String, then reverse each word
*/
class Solution {
    public void reverseWords(char[] s) {
        // check corner case
        if (s == null || s.length <= 1) {
            return;
        }
        
        int lastPos = s.length - 1;
        // reverse the whole string
        reverse(s, 0, lastPos);
        
        // reverse each word
        int start = 0;
        int end = -1;
        for (int i = 0; i <= lastPos; i++) {
            if (s[i] == ' ' || i == lastPos) {
                end = (s[i] == ' ') ? i - 1 : i;
                reverse(s, start, end);
                
                start = i + 1;// process next word
            }
        }
    }
    
    // helper method
    private void reverse(char[] chars, int i, int j) {
        if (i >= j) {
            return;
        }
        
        while (i < j) {
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            
            i++;
            j--;
        }
    }
}
