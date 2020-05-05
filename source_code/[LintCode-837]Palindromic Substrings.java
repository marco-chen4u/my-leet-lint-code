/***
* LintCode 837. Palindromic Substrings
Given a string, your task is to count how many palindromic substrings in this string.
The substrings with different start indexes or end indexes are counted as different substrings even they consist of same characters.

Example
Example1
	Input: "abc"
	Output: 3
	Explanation:
		3 palindromic strings: "a", "b", "c".

Example2
	Input: "aba"
	Output: 4
	Explanation:
		4 palindromic strings: "a", "b", "a", "aba".

Notice
	The input string length won't exceed 1000
***/
//version-1: two pointers
public class Solution {
    /**
     * @param str: s string
     * @return: return an integer, denote the number of the palindromic substrings
     */
    public int countPalindromicSubstrings(String str) {
        int result = 0;
        
        // check corner case
        if (str == null || str.length() == 0) {
            return result;
        }
        
        int n = str.length();
        for (int i = 0; i < n; i++) {
            // two pointers
            
            //odd positions processing
            int left = i;
            int right = i;
            while (left >= 0 && right < n && 
                str.charAt(left) == str.charAt(right)) {
                result += 1;
                
                left--;
                right++;
            }
            
            // even positions processing
            left = i;
            right = i + 1;
            while (left >= 0 && right < n &&
                str.charAt(left) == str.charAt(right)) {
                result += 1;
                
                left--;
                right++;
            }
        }
        
        return result;
    }
}

//version-2: two pointers + matrix
public class Solution {
    /**
     * @param str: s string
     * @return: return an integer, denote the number of the palindromic substrings
     */
    public int countPalindromicSubstrings(String str) {
		int result = 0;
		
		// check corner case
		if (str == null || str.length() == 0) {
			return result;
		}
		
		int n = str.length();
		int[][] dp = new int[n][n];
		for (int right = 0; right < n; right++) {
			for (int left = 0; left <= right; left++) {
				if (str.charAt(left) == str.charAt(right) && 
					(right - left <= 2 || dp[left + 1][right - 1] == 1)) {
					dp[left][right] = 1;
				}
				
				result += dp[left][right];
			}
		}
		
		return result;
	}
}