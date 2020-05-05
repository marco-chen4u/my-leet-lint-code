/***
* LeetCode 5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.

Example
	Example 1:
		Input: "babad"
		Output: "bab"
		Note: "aba" is also a valid answer.
	Example 2:
		Input: "cbbd"
		Output: "bb"
***/
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2){
            return s;
        }
        
        int length = s.length();
        
        boolean isPalindromic[][] = new boolean[length][length];
        for(int i = 0; i < length; i++){
            isPalindromic[i][i] = true;
        }
        
        int left = 0;
        int right = 0;
        
        for(int j = 1; j < length; j++){
            for(int i = 0; i < j; i++){
                boolean isInnerWordPalindromic = isPalindromic[i+1][j-1] || j - i < 2;
                
                if(s.charAt(i) == s.charAt(j) && isInnerWordPalindromic){
                    isPalindromic[i][j] = true;
                    
                    if(j - i > right -left){
                        left = i;
                        right = j;
                    }
                }
            }
        }
        
        return s.substring(left, right + 1);
    }
}