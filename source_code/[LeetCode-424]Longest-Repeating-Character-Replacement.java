/***
* LeetCode 424. Longest Repeating Character Replacement
You are given a string s and an integer k. 
You can choose any character of the string and change it to any other uppercase English character. 
You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1
    Input: s = "ABAB", k = 2
    Output: 4
    Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2
    Input: s = "AABABBA", k = 1
    Output: 4
    Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
    The substring "BBBB" has the longest repeating letters, which is 4.
    There may exists other ways to achive this answer too.

Constraints:
    1 <= s.length <= 105
    s consists of only uppercase English letters.
    0 <= k <= s.length

LintCode link: https://www.lintcode.com/problem/1246/
LeetCode link:https://leetcode.com/problems/longest-repeating-character-replacement/
***/
//version-1: two pointers, sliding window
public class Solution {
    /**
     * @param s: a string
     * @param k: a integer
     * @return: return a integer
     */
    public int characterReplacement(String s, int k) {
      
        if (s == null || s.isEmpty()) {
            return 0;
        }      
        int n = s.length();
        char[] chars = s.toCharArray();

        int[] charFreqCount = new int[26];

        int max = 0; // max repeating char count

        int left = 0;
        int right = 0;

        while (right < n) {
            int current = chars[right] - 'A';
            charFreqCount[current]++;

            max = Math.max(max, charFreqCount[current]);

            while (right - left + 1 > k + max) {// if the distance is > than k + max, means we have byond the scope of possible character change, we need to shrink the window

                int leftIndex = chars[left] - 'A';
                charFreqCount[leftIndex]--;

                left++;
            }


            right++;
        }

        return right - left;
    }
}
