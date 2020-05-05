/***
* LintCode 1443. Longest AB Substring
Given a string s of only 'A' and 'B', find the longest substring that satisfies the number of 'A' and 'B' are the same. Please output the length of this substring.

Example
	Example1
		Input: s = "ABAAABBBA"
		Output: 8
		Explanation: 
		Substring s[0,7] and s[1,8] meet the conditions, their length is 8.
	Example2
		Input: s = "AAAAAA"
		Output: 0
		Explanation: 
		No substring satisfies the condition except empty substring.

Notice
	This substring can be empty.
	n is the length of s, 2<=n<=1000000.
***/
//version-1(bad, O(n^3))
public class Solution {
    /**
     * @param s: a String consists of a and b
     * @return: the longest of the longest string that meets the condition
     */
    public int getAns(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int size = s.length();
        int result = result = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j <= size; j++) {
                String substring = s.substring(i, j);
                if (isEvenAB(substring)) {
                    result = Math.max(result, j - i);
                }
            }
        }
        
        return result;
    }
    
    private boolean isEvenAB(String str) {
        int countOfA = 0, countOfB = 0;
        int size = str.length();
        for (char c : str.toCharArray()) {
            switch(c) {
                case 'A': 
                    countOfA++;
                    break;
                case 'B': 
                    countOfB++;
                    break;
            }
        }
        
        return (countOfA == countOfB && 
                size == countOfA + countOfB);
    }
}

//version-2(still bad, but better)
public class Solution {
    /**
     * @param S: a String consists of a and b
     * @return: the longest of the longest string that meets the condition
     */
    public int getAns(String S) {
        int result = 0;
        // check corner case
        if (isEmpty(S)) {
            return result;
        }
        
        int n = S.length();
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int value = (S.charAt(i) == 'A') ? -1 : 1;
            preSum[i + 1] = preSum[i] + value;
        }
        
        int index = 0;
        for (int length = 2; length <= n; length++) {
            for (int i = 0; i <= n - length ; i++) {
                int j = i + length - 1;
                
                if (preSum[j + 1] - preSum[i] == 0) {
                    result = Math.max(result, length);
                }
            }
        }
        
        return result;
    }
    
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}