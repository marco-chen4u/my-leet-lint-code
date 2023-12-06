/***
* LintCode 829. Word Pattern II
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, 
such that there is a bijection between a letter in pattern and a non-empty substring in str.
(i.e if a corresponds to s, then b cannot correspond to s.
 For example, given pattern = "ab", str = "ss", return false.)

Example
    Given pattern = "abab", str = "redblueredblue", return true.
    Given pattern = "aaaa", str = "asdasdasdasd", return true.
    Given pattern = "aabb", str = "xyzabcxzyabc", return false.

Notice
    You may assume both pattern and str contains only lowercase letters.

Link:
    (1)LintCode: https://www.lintcode.com/problem/829
    (2)LeetCode: https://leetcode.com/problems/word-pattern-ii/
***/
//solution-1: HashSet<String> + HashMap<Character, String>
public class Solution {
	
    /**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // check corner cases
        if (pattern == null && str == null) {
            return true;
        }

        if ((pattern != null && pattern.length() == 0) && 
                str != null && str.length() == 0) {
            return true;
        }

        if (pattern == null || pattern.length() == 0) {
            return false;
        }

        if (str == null || str.length() == 0) {
            return false;
        }

        Map<Character, String> map = new HashMap<Character, String>();
        Set<String> valueSet = new HashSet<String>();

        return find(pattern, 0, str, 0, map, valueSet);
    }
	
    // helper method
    private boolean find(String pattern, int patternIndex,
                          String testString, int strIndex,
                          Map<Character, String> map,
                          Set<String> valueSet) {
        if (patternIndex == pattern.length()) {
            return strIndex == testString.length();
        }

        char key = pattern.charAt(patternIndex);

        // check key has already existed
        if (map.containsKey(key)) {
            String value = map.get(key);

            if (testString.startsWith(value, strIndex)) {
                return find(pattern, patternIndex + 1, 
                             testString, strIndex + value.length(),
                             map, 
                             valueSet);// check the remaining
            }
            else {
                return false;
            }
        }

        // check values
        for (int i = strIndex; i < testString.length(); i++) {
            String value = testString.substring(strIndex, i + 1);

            if (valueSet.contains(value)) {
                continue;
            }

            map.put(key, value);

            valueSet.add(value);

            if (find(pattern, patternIndex + 1, 
                      testString, i + 1, 
                      map, valueSet)) {
                 return true;
            }

            valueSet.remove(value);

            map.remove(key);			
        }

        return false;// default value
    }
}


// solution-2: HashMap<Character, String> only
class Solution {
    public boolean wordPatternMatch(String pattern, String s) {
        if (isNull(pattern) && isNull(s)) {
            return true;
        }

        if (isEmpty(pattern) && isEmpty(s)) {
            return true;
        }

        return find(pattern, 0, s, 0, new HashMap<Character, String> ());
    }

    // helper methods
    private boolean isNull(String str) {
        return str == null;
    }

    private boolean isEmpty(String str) {
        return str == "";
    }

    private boolean find(String pattern, int patternIndex, String valueStr, int valueIndex, Map<Character, String> map) {
        if (patternIndex == pattern.length()) {
            return valueIndex == valueStr.length();
        }

        char currentKey = pattern.charAt(patternIndex);

        if (map.containsKey(currentKey)) {
            String currentValue = map.get(currentKey);

            if (valueStr.startsWith(currentValue, valueIndex)) {
                return find(pattern, patternIndex + 1, valueStr, valueIndex + currentValue.length(), map);
            }
            else {
                return false;
            }
        }

        for (int i = valueIndex; i < valueStr.length(); i++) {
            String value = valueStr.substring(valueIndex, i + 1);//potential value
            
            if (map.containsValue(value)) {
                continue;
            }

            map.put(currentKey, value);

            if (find(pattern, patternIndex + 1, valueStr, valueIndex + value.length(), map)) {
                return true;
            }

            map.remove(currentKey);

        }

        return false;
    }
}
