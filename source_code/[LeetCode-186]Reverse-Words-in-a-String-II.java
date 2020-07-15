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
        if (s == null || s.length <= 1) {
            return;
        }
        
        int size = s.length;
        reverse(s, 0, size - 1);
        
        int start = 0;
        for (int i = 0; i < size; i++) {
            if (s[i] == ' ') {
                reverse(s, start, i - 1);
                start = i + 1;
            }
            
            if (i == size -1) {
                reverse(s, start, i);
            }
        }
    }
    
    // helper
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
