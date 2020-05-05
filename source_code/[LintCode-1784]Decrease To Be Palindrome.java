/***
* LintCode 1784. Decrease To Be Palindrome
Given a string s with a-z. We want to change s into a palindrome with following operations:
	1. change 'z' to 'y';
	2. change 'y' to 'x';
	3. change 'x' to 'w';
	................
	24. change 'c' to 'b';
	25. change 'b' to 'a';
Returns the number of operations needed to change s to a palindrome at least.

Example
	Example 1:
		Input: "abc"
		Output: 2
		Explanation: 
		  1. Change 'c' to 'b': "abb"
		  2. Change the last 'b' to 'a': "aba"
	  
	Example 2:
		Input: "abcd"
		Output: 4
***/
public class Solution {
    /**
     * @param s: the string s
     * @return: the number of operations at least
     */
    public int numberOfOperations(String s) {
        int result = 0;
		// check corner case
		if (s == null || s.length() == 0) {
			return result;
		}
		
		int n = s.length();
		// two pointers
		int left = 0;
		int right = n - 1;
		
		while (left < right) {
			result += Math.abs(s.charAt(left) - s.charAt(right));
			
			left++;
			right--;
		}
		
		return result;
    }
}