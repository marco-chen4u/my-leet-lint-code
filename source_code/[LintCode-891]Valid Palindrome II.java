/***
* LintCode 891. Valid Palindrome II
Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.

Example
	Example 1:
		Input: s = "aba"
		Output: true
		Explanation: Originally a palindrome.

	Example 2:
		Input: s = "abca"
		Output: true
		Explanation: Delete 'b' or 'c'.

	Example 3:
		Input: s = "abc"
		Output: false
		Explanation: Deleting any letter can not make it a palindrome.

Notice
	The string will only contain lowercase characters.
	The maximum length of the string is 50000.
***/
public class Solution {
    /**
     * @param s: a string
     * @return: nothing
     */
    public boolean validPalindrome(String s) {
        boolean result = false;
        // check palindrom
        if (s == null || s.length() <= 2) {
            return true;
        }
        
        int size = s.length();
        
        // two pointers
        int left = 0;
        int right = size - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            
            left++;
            right--;
        }
        
        if (left >= right) {
            return true;
        }
        
        result = isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
        
        return result;
    }
    
    // helper method
    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}