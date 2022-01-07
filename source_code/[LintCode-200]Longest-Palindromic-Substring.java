/***
* LintCode 200. Longest Palindromic Substring
Given a string S, find the longest palindromic substring in S. 
You may assume that the maximum length of S is 1000, 
and there exists one unique longest palindromic substring.

Example 1:
    Input:"abcdzdcab"
    Output:"cdzdc"

Example 2:
    Input:"aba"
    Output:"aba"

Challenge:
    O(n2) time is acceptable. Can you do it in O(n) time.

***/
//version-1: DP
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // check corner cases
        if (isEmpty(s) || s.length() <= 1) {
            return s;
        }
        
        int n = s.length();
        int left = 0; 
        int right = 0;
        
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }
        
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = (s.charAt(i) == s.charAt(i + 1));
        }
        
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                boolean isInnerPalindrome = isPalindrome[i + 1][j - 1] ||  // regular inner palindromic
                                            j - i < 2;// single characater
                isPalindrome[i][j] = s.charAt(i) == s.charAt(j) && isInnerPalindrome;
                if (isPalindrome[i][j]) {
                    if ((right - left) < (j - i)) {
                        left = i;
                        right = j;
                    }
                }
            }
        }
        
        return s.substring(left, right + 1);
    }
    
    // helper methods
    private boolean isEmpty(String s) {
        return s == null || s.length() == 0;
    }
}
