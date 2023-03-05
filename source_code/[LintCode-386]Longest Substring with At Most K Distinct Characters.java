/***
* LintCode 386.Longest Substring with At Most K Distinct Characters
Given a string S, find the length of the longest substring T that contains at most k distinct characters.
Example
    Example 1:
        Input: S = "eceba" and k = 3
        Output: 4
        Explanation: T = "eceb"
    
    Example 2:
        Input: S = "WORLD" and k = 4
        Output: 4
        Explanation: T = "WORL" or "ORLD"
Challenge
    O(n) time
***/
//version-1: time-complexity[O(n)]
public class Solution {
    /**
     * @param s: A string
     * @param k: An integer
     * @return: An integer
     */
public int lengthOfLongestSubstringKDistinct(String s, int k) {
        int result = 0;
        // check corner cases
        if (s == null || s.length() == 0 || k < 1) {
            return result;
        }		

        int size = s.length();
        if (k > size) {
            return size;
        }

        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int left = 0;
        int right = 0;
        for (; left < size; left++) {
            while (right < size && (map.containsKey(s.charAt(right)) || map.size() < k)) {
                char current = s.charAt(right);

                map.put(current, map.getOrDefault(current, 0) + 1);

                right++;
            }

            result = Math.max(result, right - left);

            char key = s.charAt(left);
            int count = map.get(key);
            count -=1;
            map.put(key, count);
            if (count == 0) {
                map.remove(key);
            }
        }

        return result;
    }
}
