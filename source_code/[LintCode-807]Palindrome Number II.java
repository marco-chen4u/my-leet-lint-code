/***
* LintCode 807. Palindrome Number II
Determines whether a binary representation of a non-negative integer n is a palindrome

Example
	Example1
		Input: n = 0
		Output: True
		Explanation:
			The binary representation of 0 is: 0

	Example2
		Input: n = 3
		Output: True
		Explanation:
			The binary representation of 3 is: 11

	Example3
		Input: n = 4
		Output: False
		Explanation:
			The binary representation of 4 is: 100

	Example4
		Input: n = 6
		Output: False
		Explanation:
			The binary representation of 6 is: 110

Notice
	0 <= n <= 2^32 - 1
***/
public class Solution {
    /**
     * @param n: non-negative integer n.
     * @return: return whether a binary representation of a non-negative integer n is a palindrome.
     */
    public boolean isPalindrome(int n) {
        // check corner case
        if (n < 0) {
            return false;
        }
        
        String binaryStr = getBinaryStr(n);
        
        return checkPalindrome(binaryStr);
    }
    
    // helper method
    private String getBinaryStr(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n != 0) {
            sb.append(n % 2);
            n = n >> 1;
        }
        
        return sb.toString();
    }
    
    private boolean checkPalindrome(String str) {
        
        // check corner case
        if (str == null || str.length() <= 1) {
            return true;
        }
        
        int n = str.length();
        
        int left, right;
        
        left = 0;
        right = n - 1;
        
        while (left <= right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            
            left++;
            right--;
        }
        
        return true;
    }
}