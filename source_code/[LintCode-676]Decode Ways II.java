/***
* LintCode 676. Decode Ways II
A message containing letters from A-Z is being encoded to numbers using the following mapping way:
'A' -> 1
'B' -> 2
...
'Z' -> 26
Beyond that, now the encoded string can also contain the character *, which can be treated as one of the numbers from 1 to 9.
Given the encoded message containing digits and the character *, return the total number of ways to decode it.
Also, since the answer may be very large, you should return the output mod 10^9 + 7.

Example
	Example 1
		Input: "*"
		Output: 9
		Explanation: You can change it to "A", "B", "C", "D", "E", "F", "G", "H", "I".
	Example 2
		Input: "1*"
		Output: 18
	Example 3
		Input: "**1**"
		Output: 18720

Notice
	1.The length of the input string will fit in range [1, 10^5].
	2.The input string will only contain the character * and digits 0 - 9.
***/
public class Solution {
    // fields
    private final long MOD_VALUE = 1000000007;
	
    /**
     * @param s: a message being encoded
     * @return: an integer
     */
    public int numDecodings(String s) {
        // check cornor case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int n = s.length();
        char[] chars = s.toCharArray();
        
        long[] dp = new long[n + 1];
        
        // initialize
        dp[0] = 1;
        dp[1] = dp[0] * getCount1(chars[0]);
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] * getCount1(chars[i - 1])
                    +
                    dp[i - 2] * getCount2(chars[i - 2], chars[i - 1]);
            
            dp[i] = dp[i] % MOD_VALUE;
        }
        
        return (int)dp[n];
    }
	
    // helper method
    private int getCount1(char ch) {
	int count = 0;
	switch (ch) {
	    case '0' :
	        count = 0;
	        break;
	    case '*' :
	        count = 9;
	        break;
	    default : 
	        count = 1;
	        break;
	}

	return count;
    }
	
    private int getCount2(char ch2, char ch1) {
        int count = 0;
        switch (ch2) {
	    case '0' :
	        count = 0;
	        break;

	    case '1' : // ch2 = '1'
	        if (ch1 == '*') {
		    count = 9;// ch1 = '11' - '19'
		    break;
	        }

	        count = 1;
	        break;
			
	    case '2' : // ch2 = '2'
	        if (ch1 == '*') {
		    count = 6; // '20' - '26'
		    break;
	        }

	        if (ch1 >= '0' && ch1 <= '6') {
		    count = 1;
		    break;
	        }

	        count = 0;
	        break;
	    case '*' : //ch2 = '*'
	        if (ch1 >= '0' && ch1 <= '6') {
		    count = 2;
		    break;
	        }

	        if (ch1 >= '7' && ch1 <= '9') {
		    count = 1;
		    break;
	        }

	        count = 15;
	        break;	
	    default : // ch2 ='3' - '9'
	        count = 0;
	        break;
            }
		
        return count;
    }
}
