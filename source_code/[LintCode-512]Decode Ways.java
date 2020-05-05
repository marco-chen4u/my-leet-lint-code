/***
* LintCode 512. Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:
	'A' -> 1
	'B' -> 2
	...
	'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.
Example
	Example 1:
		Input: "12"
		Output: 2
		Explanation: It could be decoded as AB (1 2) or L (12).

	Example 2:
		Input: "10"
		Output: 1
***/
// version -1
public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
		// check corner cases
		if (s == null || s.length() == 0) {
			return 0;
		}
		for (char c : s.toCharArray()) {
			if (!Character.isDigit(c)) {
				return 0;
			}
		}
		
		// state
		int n = s.length();
		int[] dp = new int[n + 1];
		// initialize
		dp[0] = 1;
		dp[1] = (s.charAt(0) == 0) ? 0 : 1;
		for (int i = 2; i <= n; i++) {
			char currentChar = s.charAt(i - 1);
			
			if (currentChar != '0') {
				dp[i] += dp[i - 1];
			}
			
			if (isTwoCharValidToConvertLetter(s.substring(i - 2, i))) {
				dp[i] += dp[i - 2];
			}
		}
		//return
		return dp[n];
	}
	
	// helper method
	private boolean isTwoCharValidToConvertLetter(String twoChars) {
		if (twoChars.length() != 2) {
			return false;
		}
		
		int value = (twoChars.charAt(0) - '0')*10 + (twoChars.charAt(1) - '0');
		return (value >= 10 && value <= 26);
	}
}

// version-2
public class Solution {
    /**
     * @param s: a string,  encoded message
     * @return: an integer, the number of ways decoding
     */
    public int numDecodings(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        
        // state
        int[] dp = new int[n + 1];
        
        // initialize
        dp[0] = 1; // empty content is 1 decode way
        dp[1] = (s.charAt(0) != '0') ? 1 : 0;
        
        for (int i = 2; i <= n; i++) {
            dp[i] += (s.charAt(i - 1) != '0') ? dp[i - 1] : 0;
            
            int towDigit = Integer.valueOf(s.substring(i - 2, i));
            dp[i] += (towDigit >= 10 && towDigit <= 26) ? dp[i - 2] : 0;
        }
        
        return dp[n];
    }
}