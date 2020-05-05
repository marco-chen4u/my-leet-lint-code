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
		int i, j = 0;
		for (i = 0; i < size; i++) {
			while (j < size) {
				char current = s.charAt(j);
				if (map.containsKey(current)) {
					map.put(current, map.get(current) + 1);
				}
				else {
					if (map.size() == k) {
						break;
					}
					
					map.put(current, 1);
				}
				
				j++;
			}
			
			result = Math.max(result, j - i);
			
			char key = s.charAt(i);
			int count = map.get(key);
			if (count > 1) {
				map.put(key, count - 1);
			}
			else {
				map.remove(key);
			}
		}
		
		return result;
	}
}