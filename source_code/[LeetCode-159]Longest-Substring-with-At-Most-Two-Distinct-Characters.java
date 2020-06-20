/***
* LeetCode 159. Longest Substring with At Most Two Distinct Characters
Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

Example 1:
    Input: "eceba"
    Output: 3
    Explanation: t is "ece" which its length is 3.

Exampe 2:
    Input: "ccaabbb"
    Output: 5
    Explanation: t is "aabbb" which its length is 5.
***/
//version-1: sliding window
class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        // check corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int result = 0;

        int size = s.length();
        int left = 0;
        int right;

        Map<Character, Integer> map = new HashMap<>();
        
        for (right = 0; right < size; right++) {
            char ch = s.charAt(right);
            map.put(ch, map.getOrDefault(ch, 0) + 1);

            while (left <= right && map.size() > 2) {
                result = Math.max(result, right - left);

                ch = s.charAt(left);
                if (map.get(ch) == 1) {
                    map.remove(ch);
                }
                else {
                    map.put(ch, map.get(ch) - 1);
                }

                left++;
            }
        }

        if (map.size() <= 2) {
            result = Math.max(result, right - left);
        }

        return result;
    }

}
