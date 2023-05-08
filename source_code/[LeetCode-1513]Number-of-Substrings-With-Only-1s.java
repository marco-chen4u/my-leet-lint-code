/***
* LeetCode 1513. Number of Substrings With Only 1s
Given a binary string s, return the number of substrings with all characters 1's. 
Since the answer may be too large, return it modulo 109 + 7.

Example 1
    Input: s = "0110111"
    Output: 9
    Explanation: There are 9 substring in total with only 1's characters.
    "1" -> 5 times.
    "11" -> 3 times.
    "111" -> 1 time.
    
Example 2
    Input: s = "101"
    Output: 2
    Explanation: Substring "1" is shown 2 times in s.
    
Example 3
    Input: s = "111111"
    Output: 21
    Explanation: Each substring contains only 1's characters.
    
Constraints:
    1 <= s.length <= 105
    s[i] is either '0' or '1'.

LintCode link: https://www.lintcode.com/problem/1870/
LeetCode link: https://leetcode.com/problems/number-of-substrings-with-only-1s/
***/
//version-1: two pointers
class Solution {
    public int numSub(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }

        long result = 0;

        char[] chars = s.toCharArray();
        int mod = 1_000_000_007;

        int size = chars.length;

        int i = 0;
        int j = 0;

        while (i < size) {
            if (chars[i] == '1') {
                j = i;

                while (j < size) {
                    if (chars[j] == '1') {
                        j++;
                    }
                    else {
                        break;
                    }
                }
                j--;

                int len = j - i + 1;

                result = result + (1l *len * (len + 1) / 2) % mod;
                i = j;
            }
            i++;
        }

        return (int)result;
    }
}
