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
    O(n^2) time is acceptable. Can you do it in O(n) time.

***/
//version-1: DP, time complexity: O(n^2)
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

//version-2: Two Pointer, time complexity: O(n^3)
public class Solution {
    /**
     * @param s: input string
     * @return: the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return s;
        }
        
        int longest = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (isPalindrome(s, i, j)) {
                    if (longest < j - i + 1) {
                        longest = j - i + 1;
                        start = i;
                    }
                }
            }
        }
        
        return s.substring(start, start + longest);
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

// version-3: Two Pointers, Time Complexity: O(n^2)
public class Solution {
    /**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        String longest = "";
        int size = s.length();
        for (int i = 0; i < size; i++) {
            String oddPalindrome = getPalindromeFrom(s, i, i);
            if (longest.length() < oddPalindrome.length()) {
                longest = oddPalindrome;
            }

            String evenPalindrome = getPalindromeFrom(s, i, i + 1);
            if (longest.length() < evenPalindrome.length()) {
                longest = evenPalindrome;
            }
        }

        return longest;
    }

    // helper method
    private String getPalindromeFrom(String s, int left, int right) {
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            left--;
            right++;
        }

        return s.substring(left + 1, right);
    }
}
