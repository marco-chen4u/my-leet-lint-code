/***
* LintCode 828. Word Pattern
Given a pattern and a string str, find if str follows the same pattern.
Here follow means a full match, 
such that there is a bijection between a letter in pattern and a non-empty word in str.

Example1
    Input:  pattern = "abba" and str = "dog cat cat dog"
    Output: true
    Explanation:
        The pattern of str is abba

Example2
    Input:  pattern = "abba" and str = "dog cat cat fish"
    Output: false
    Explanation:
        The pattern of str is abbc

Example3
    Input:  pattern = "aaaa" and str = "dog cat cat dog"
    Output: false
    Explanation:
        The pattern of str is abba

Example4
    Input:  pattern = "abba" and str = "dog cat cat fish"
    Output: false
    Explanation:
        The pattern of str is abbc
Notice
    You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
***/
public class Solution {
    /**
     * @param pattern: a string, denote pattern string
     * @param teststr: a string, denote matching string
     * @return: an boolean, denote whether the pattern string and the matching string match or not
     */
    public boolean wordPattern(String pattern, String teststr) {
        String SEPERATOR = " ";
		
        // check corner cases
        if ((pattern == null && teststr == null) ||
            (pattern.length() == 0 && teststr.length() == 0)) {
            return true;
        }

        if ((pattern == null || pattern.length() == 0) && 
            (teststr != null && teststr.length() > 0)) {
            return false;
        }

        if ((pattern != null && pattern.length() > 0) 
            && (teststr == null || teststr.length() == 0)) {
            return false;
        }

        // initialize
        int patternSize = pattern.length();		
        String[] testStrings = teststr.split(SEPERATOR);
        if (patternSize != testStrings.length) {
            return false;
        }

        // calculate the mapping
        Map<Character, String> patternMapping = new HashMap<Character, String>();
        Set<Character> keyRecord = new HashSet<Character>();
        for (int index = 0; index < patternSize; index++) {
            char key = pattern.charAt(index);
            String value = testStrings[index];

            if (!keyRecord.contains(key) && 
                    !patternMapping.containsKey(key) &&
                    !patternMapping.containsValue(value)) {
                keyRecord.add(key);
                patternMapping.put(key, value);

                continue;
            }

            if (value.equals(patternMapping.get(key))) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }
}
