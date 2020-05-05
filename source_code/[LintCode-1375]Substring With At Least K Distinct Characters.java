/***
* LintCode 1375. Substring With At Least K Distinct Characters
Given a string s, with only lowercase characters,  Return the number of substrings that at least k distinct characters.
Example
	Example 1:
		Input: S = "abcabcabca", k = 4
		Output: 0
		Explanation: There are only three distinct characters in the string.
	Example 2:
		Input: S = "abcabcabcabc", k = 3
		Output: 55
		Explanation: Any substring whose length is not smaller than 3 contains a, b, c.
			For example, there are 10 substrings whose length are 3, "abc", "bca", "cab" ... "abc"
			There are 9 substrings whose length are 4, "abca", "bcab", "cabc" ... "cabc"
			...
			There is 1 substring whose length is 12, "abcabcabcabc"
			So the answer is 1 + 2 + ... + 10 = 55.
Notice
	1.10 ≤ length(S) ≤ 1,000,000
	2.1 ≤ k ≤ 26
***/
public class Solution {
	/**
	* @param s : input string
	* @param k : a number, substring has contains at least k distinct characters
	* @return : the number of all subtrings that contains at least k distinct characters
	*/
	public long kDistinctCharacters(String s, int k) {
		long result = 0;
		// check corner case
		if (s == null || s.length() == 0 || k > s.length()) {
			return result;
		}
		
		int size = s.length();
		int distinctCharCount = 0;
		char[] letters = new char[26];// all lowercase character
		
		// two pointers
		int left = 0;
		int right = 0;		
		while (left < size) {
			while (distinctCharCount < k && right < size) {
				int index = s.charAt(right) - 'a';
				letters[index]++;
				distinctCharCount += (letters[index] == 1) ? 1: 0;
				
				right++;
			}
			
			result += (distinctCharCount == k) ? size - right + 1 : 0;
			
			int preWindowIndex = s.charAt(left) - 'a';
			if (letters[preWindowIndex] == 1) {
				distinctCharCount--;
			}
			letters[preWindowIndex]--;
			
			left++;
		}
		
		return result;
	}
}