/***
* LeetCode 5. Longest Palindromic Substring
Given a string s, find the longest palindromic substring in s. 
You may assume that the maximum length of s is 1000.


Example 1:
    Input: "babad"
    Output: "bab"
    Note: "aba" is also a valid answer.
Example 2:
    Input: "cbbd"
    Output: "bb"
***/

//solution-1: dynamic programming
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

// solution-2: tow pointers
class Solution {
    public String longestPalindrome(String s) {
        // check corner cases
        if (s == null || s.isEmpty() || s.length() <= 1) {
            return s;
        }

        // regular case
        int size = s.length();
        int start = 0;
        int end = 0;
        int maxSize = 0;

        char[] chars = s.toCharArray();

        for (int i = 0; i < size; i++) {
            int sizeLeft = getExpand(chars, i, i);
            int sizeRight = getExpand(chars, i, i + 1);

            int currentSize = Math.max(sizeLeft, sizeRight);

            if (maxSize < currentSize) {
                start = i - (currentSize - 1)/2;
                end = i + (currentSize)/2;

                maxSize = currentSize;
            }
        }

        return s.substring(start, end + 1);
    }

    // helper methods
    private int getExpand(char[] chars, int left, int right) {
        int size = chars.length;

        while (left >= 0 && right < size && chars[left] == chars[right]) {
            left--;
            right++;
        }

        return right - left  - 1;
    }
}
