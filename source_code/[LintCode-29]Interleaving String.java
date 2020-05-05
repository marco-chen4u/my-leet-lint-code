/***
* LintCode 29. Interleaving String
Given three strings: s1, s2, s3, determine whether s3 is formed by the interleaving of s1 and s2.
Example
	Example 1:
		Input:
			"aabcc"
			"dbbca"
			"aadbbcbcac"
		Output:
			true
	Example 2:
		Input:
			""
			""
			"1"
		Output:
			false
	Example 3:
		Input:
			"aabcc"
			"dbbca"
			"aadbbbaccc"
		Output:
			false
Challenge
	O(n2) time or better
***/
//version-1
public class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        // check corner case
        if ((s1 == null && s2 == null) && s3 == null) {
            return true;
        }        
        if ((s1 == null && s2 == null) && s3 != null) {
            return false;
        }        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }        
        // state
        int n = s1.length();
        int m = s2.length();
        boolean[][] f = new boolean[n + 1][m + 1];        
        // initialize
        f[0][0] = true;
        for (int i = 1; i <= n; i++) {
            f[i][0] = (f[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1));
        }
        
        for (int j = 1; j <= m; j++) {
            f[0][j] = (f[0][j -1] && s2.charAt(j - 1) == s3.charAt(j - 1));
        }        
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = (f[i - 1][j] && 
							s1.charAt(i - 1) == s3.charAt(i + j - 1)) ||
                            (f[i][j - 1]) &&
								s2.charAt(j - 1) == s3.charAt(i + j - 1);
            }
        }        
        // answer
        return f[n][m];
    }
}

//version-2
public class Solution {
    /**
     * @param s1: A string
     * @param s2: A string
     * @param s3: A string
     * @return: Determine whether s3 is formed by interleaving of s1 and s2
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        // check corner case
       if ((isEmpty(s1) && isEmpty(s2)) && isEmpty(s3)) {
            return true;
        }        
        if ((isEmpty(s1) && isEmpty(s2)) && !isEmpty(s3)) {
            return false;
        }        
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        
        int n = s1.length();
        int m = s2.length();
        
        char[] charArray1 = s1.toCharArray();
        char[] charArray2 = s2.toCharArray();
        char[] charArray3 = s3.toCharArray();
        
        // state
        boolean[][] dp = new boolean[n + 1][m + 1];
        
        // intitialize
        dp[0][0] = true;
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                int currentPos = i + j - 1;// s3's last character position;
                
                // initialize
                if (i == 0) {
                    if (j == 0) {
                        continue;// already initialized
                    }
                    
                    dp[i][j] = dp[i][j - 1] && charArray2[j - 1] == charArray3[currentPos];
                    continue;
                }
                
                if (j == 0) {
                    if (i == 0) {
                        continue;// already initialized
                    }
                    dp[i][j] = dp[i - 1][j] && charArray1[i - 1] == charArray3[currentPos];
                    continue;
                }
                
                dp[i][j] = (dp[i - 1][j] &&
                            charArray1[i - 1] == charArray3[currentPos]) ||
                            (dp[i][j - 1] && charArray2[j - 1] == charArray3[currentPos]);
            }
        }
        
        return dp[n][m];
    }
    
    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}