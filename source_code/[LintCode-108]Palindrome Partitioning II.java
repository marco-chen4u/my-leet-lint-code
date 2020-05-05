/***
* LintCode 108. Palindrome Partitioning II
Given a string s, cut s into some substrings such that every substring is a palindrome.
Return the minimum cuts needed for a palindrome partitioning of s.
Example
	Example 1:
		Input: "a"
		Output: 0
		Explanation: "a" is already a palindrome, no need to split.
	Example 2:
		Input: "aab"
		Output: 1
		Explanation: Split "aab" once, into "aa" and "b", both palindrome.
***/
public class Solution {
    public int minCut(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return -1;
        }
        
        // state
        int n = s.length();
        boolean[][] isPalinedrome = getIsPalindrome(s);
        int[] f = new int[n + 1];        
        // initialize
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
        }        
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (isPalinedrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }        
        // answer
        return f[n] - 1;
    }
    
    // helper method
    private boolean[][] getIsPalindrome(String s) {
        int n = s.length();
        // state
        boolean[][] isPalinedrome = new boolean[n][n];        
        // initialize
        for (int i = 0; i < n; i++) {
            isPalinedrome[i][i] = true;
        }        
        for (int i = 0; i < n - 1; i++) {
            isPalinedrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }        
        // function
        for (int length = 2; length < n; length++) {
            for (int start = 0; start + length < n; start++) {
                isPalinedrome[start][start + length] = 
					isPalinedrome[start + 1][start + length - 1] &&
						s.charAt(start) == s.charAt(start + length);
            }
        }        
        // answer
        return isPalinedrome;
    }
	
	private boolean[][] calculatePalindrome(String s) {
		int n = s.length();
		boolean[][] palindrome = new boolean[n][n];
		
		int i, j, mid;
		for (boolean[] row : palindrome) {
			Arrays.fill(row, false);
		}
		
		for (mid = 0; mid < n; mid++) {
			// odd-length palindrome processing
			i = j = mid;
			while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
				palindrome[i][j] = true;
				
				i--;
				j++;
			}
			
			// even-length palindrome processing
			i = mid - 1; 
			j = mid;
			while (i >= 0 && j < n && s.charAt(i) == s.charAt(j)) {
				palindrome[i][j] = true;
				
				i--;
				j++;
			}
			
		}
		
		return palindrome;
	}
}