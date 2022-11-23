/***
* LintCode 415. Valid Palindrome
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

    Example 1:
        Input: "A man, a plan, a canal: Panama"
        Output: true
        Explanation: "amanaplanacanalpanama"

    Example 2:
        Input: "race a car"
        Output: false
        Explanation: "raceacar"

Challenge
    O(n) time without extra memory.
Notice
    Have you consider that the string might be empty? This is a good question to ask during an interview.
    For the purpose of this problem, we define empty string as valid palindrome.
***/
public class Solution {    
    // helper method
    private boolean isValid(char c) {
        return Character.isLetter(c) || Character.isDigit(c);
    }
    
    /**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        int size = s.length();
        int left = 0;
        int right = size - 1;
        while (left < right) {
            while (left < size && !isValid(s.charAt(left))) {
                left++;
            }
            if (left == size) {
                return true; // empty valid character string
            }
            
            while (right >= 0 && !isValid(s.charAt(right))) {
                right--;
            }
            
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return left >= right;
    }
}
