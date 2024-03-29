/***
* LeetCode 395. Longest Substring with At Least K Repeating Characters
Given a string s and an integer k, 
return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

Example 1
    Input: s = "aaabb", k = 3
    Output: 3
    Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.
    
Example 2
    Input: s = "ababbc", k = 2
    Output: 5
    Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
    
Constraints:
    1 <= s.length <= 104
    s consists of only lowercase English letters.
    1 <= k <= 105

LintCode link: https://www.lintcode.com/problem/1261/
LeetCode link: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/ 
***/
/*
* idea: https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/solutions/403337/summary-of-3-approaches-from-brute-force-to-optimal/
*/

//version-1: brute force, time complexity: O(n^2), space compexity: O(1)   ***[int[26]]
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }

        int n = s.length();
        int[] freqCount = new int[26];

        char[] chars = s.toCharArray();

        int max = 0;

        for (int i = 0; i < n; i++) {

            Arrays.fill(freqCount, 0);

            for (int j = i; j < n; j++) {

                int current = chars[j] - 'a';
                freqCount[current]++;

                if (!isValid(k, freqCount)) {
                    continue;
                }

                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }

    // helper method
    private boolean isValid(int k, int[] freqCount) {
        int distinctCharCount = 0;
        int uniqueAtLeastKCount = 0;

        for (int value : freqCount) {
            distinctCharCount += value > 0 ? 1 : 0;
            uniqueAtLeastKCount += value >= k ? 1 : 0;
        }

        return distinctCharCount == uniqueAtLeastKCount;
    }
}

//version-2: O(n) brute force
/* Instead of looping over all possible substrings, which takes O(n^2), 
we can loop over all possible number of unique letters in substring, from 1 to 26.*/
// time O(n), space O(1)
class Solution {
    public int longestSubstring(String s, int k) {
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }

        int max = 0;
        for (int uniqueCharCount = 1; uniqueCharCount <= 26; uniqueCharCount++) {
            max = Math.max(max, longestSubstringWithUniqueChar(s, k, uniqueCharCount));
        }

        return max;
    }

    private int longestSubstringWithUniqueChar(String str, int k, int uniqueCharCount) {
        int n = str.length();

        char[] chars = str.toCharArray();

        int[] charFreqCount = new int[26];
        int distinctCharCount = 0;
        int atLeastKCharCount = 0;

        int max = 0;

        // two pointers
        int left = 0;
        int right = 0;
        while (right < n) {
            int current = chars[right] - 'a';// new right most char
            
            charFreqCount[current]++;
            distinctCharCount += charFreqCount[current] == 1 ? 1 : 0;
            
            atLeastKCharCount += charFreqCount[current] == k ? 1 : 0;

            while (distinctCharCount > uniqueCharCount) {// moving the sliding window
                char leftChar = chars[left];
                int leftIndex = leftChar - 'a';

                if (charFreqCount[leftIndex] == 1) {
                    distinctCharCount--;
                }

                if (charFreqCount[leftIndex] == k) {
                    atLeastKCharCount--;
                }

                charFreqCount[leftIndex]--;

                left++;// moving the sliding window forward
            }

            if (distinctCharCount == uniqueCharCount && 
                distinctCharCount == atLeastKCharCount) {
                max = Math.max(max, right - left + 1);
            }
            
            
            right++;
        }

        return max;
    }
}

//version-3: recursion, devide and conquer, two pointers
// time complexity: O(n), space complexity: O(1)
/*find the delimiters that is not satisfied with at-least-k char condtion, and divide by this delimiters(char) and find max substring length*/
public class Solution {
    /**
     * @param s: a string
     * @param k: an integer
     * @return: return an integer
     */
    public int longestSubstring(String s, int k) {
        // corner case
        if (s == null || s.isEmpty() || k > s.length()) {
            return 0;
        }

        return longestSubstring(s, k, 0, s.length() - 1);
    }

    // helper method
    private int longestSubstring(String s, int k, int start, int end) {
        // corner case
        if (end - start + 1 < k) {// no at least k repeat character conditions satisfied
            return 0;
        }

        char[] chars = s.toCharArray();

        int[] charFreqCount = new int[26];
        for (int i = start; i <= end; i++) {
            int current = chars[i] - 'a';
            charFreqCount[current]++;
        }

        Set<Character> delimiters = new HashSet<>();
        for (int i = start; i <= end; i++) {
            char ch = chars[i];
            int current = ch - 'a';
            int value = charFreqCount[current];
            if (value > 0 && value < k) {
                delimiters.add(ch);
            }
        }

        // don't forget this corner case condition to get return
        if (delimiters.isEmpty()) {
            return end -start + 1;
        }

        int max = 0;

        // two pointers
        int left = start;
        int right = start;
        for (; right <= end; right++) {
            char ch = chars[right];
            if (delimiters.contains(ch)) {// devide and conquer
                max = Math.max(max, longestSubstring(s, k, left, right - 1));

                left = right + 1;
            }
        }

        // don't forget the last part of split by delimiters to check
        max = Math.max(max, longestSubstring(s, k, left, end));

        return max;
    }
}

