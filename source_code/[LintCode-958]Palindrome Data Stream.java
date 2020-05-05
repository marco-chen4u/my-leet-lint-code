/***
* LintCode 958. Palindrome Data Stream
There is a data stream, one letter at a time, and determine whether the current stream's arrangement can form a palindrome.

Example
	Example 1:
		input:s = ['a','a','a','a','a']
		outut:[1,1,1,1,1]
		Explanation:
			“a” can form a palindrome
			“aa” can form a palindrome
			“aaa” can form a palindrome
			“aaaa” can form a palindrome
			“aaaaa” can form a palindrome

	Example 2:
		input:s = ['a','b','a']
		outut:[1,0,1]
		Explanation:
			“a” can form a palindrome
			“ab” can't form a palindrome
			“aba” can form a palindrome
			Notice
			1 <= |s| <= 10^5

link: https://www.lintcode.com/problem/palindrome-data-stream/
***/
public class Solution {
    /**
     * @param s: The data stream
     * @return: Return the judgement stream
     */
    public int[] getStream(String s) {
        int[] result = new int[0];
        
        // check corner case
        if (s == null || s.length() == 0) {
            return result;
        }
        
        char[] charArray = s.toCharArray();
        int size = charArray.length;
        
        result = new int[size];
        
        int[] values = new int[256];
        
        int indexPos = 0;
        for (char ch : charArray) {
            values[ch] += (values[ch] == 0) ? 1 : -1;
            
            result[indexPos++] = (getSum(values) > 1) ? 0 : 1;
        }
        
        return result;
    }
    
    private int getSum(int[] values) {
        int result = 0;
        
        for (int value : values) {
            result += value;
        }
        
        return result;
    }
    
    /*private boolean canFormPalindrome(String s) {
        boolean result = false;
        
        // check corner case
        int n = s.length();
        
        if (n <= 1) {
            return true;
        }
        
        boolean isOdd = (n % 2 == 1) ? true : false;
        
        int[] values = new int[256];
        for (char ch : s.toCharArray()) {
            values[ch] += (values[ch] == 0) ? 1 : -1;
        }
        
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        
        result = isOdd ? (sum == 1) : (sum == 0);
        
        return result;
    }*/
}