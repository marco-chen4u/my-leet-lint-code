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
    /**
     * @param s: A string
     * @return: An integer
     */
    public int minCut(String s) {
        //check corner case
        if (s == null || s.length() <= 1) {
            return 0;
        }        
		// state
        int n = s.length();
		boolean[][] isPalindrome = getIsPalindrome(s);
        int[] f = new int[n + 1];
		// initailize
        f[0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i] = Integer.MAX_VALUE;
        }
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (isPalindrome[j][i - 1]) {
                    f[i] = Math.min(f[i], f[j] + 1);
                }
            }
        }
        // return
        return f[n] - 1;
    }
	
	// helper method
	private boolean[][] getIsPalindrome(String s) {
		int n = s.length();
        boolean[][] isPalindrome = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            isPalindrome[i][i] = true;
        }        
        for (int i = 0; i < n - 1; i++) {
            isPalindrome[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        }
        
        for (int i = n -1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                isPalindrome[i][j] = isPalindrome[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
            }
        }
		
		return isPalindrome;
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