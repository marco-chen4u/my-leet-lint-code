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

Link: 
    (1)LintCode: https://www.lintcode.com/problem/828/
    (2)LeetCode: https://leetcode.com/problems/word-pattern/
***/
//solution-1: Array, HashSet, HashMap
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
        Map<Character, String> patternMap = new HashMap<Character, String>();
        Set<Character> keySet = new HashSet<Character>();
        for (int index = 0; index < patternSize; index++) {
            char key = pattern.charAt(index);
            String value = testStrings[index];

            if (!keySet.contains(key) && 
                    !patternMap.containsKey(key) &&
                    !patternMap.containsValue(value)) {
                keySet.add(key);
                patternMap.put(key, value);

                continue;
            }

            if (value.equals(patternMap.get(key))) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }
}

//solution-2: Array, HashSet, HashMap
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

        Set<Character> keySet = new HashSet<Character>();
        Set<String> valueSet = new HashSet<String>();
        Map<Character, String> patternMap = new HashMap<Character, String>();

        // calculate the mapping
        for (int index = 0; index < patternSize; index++) {
            char key = pattern.charAt(index);
            String value = testStrings[index];

            if (!keySet.contains(key) && !valueSet.contains(value)) {
                keySet.add(key);
                valueSet.add(value);

                patternMap.put(key, value);

                continue;
            }

            if (value.equals(patternMap.get(key))) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }
}

// solution-3: Array, HashSet, and HashMap
public class Solution {
    /**
     * @param pattern: a string, denote pattern string
     * @param teststr: a string, denote matching string
     * @return: an boolean, denote whether the pattern string and the matching string match or not
     */
    public boolean wordPattern(String pattern, String teststr) {
        // skip corner cases

	// regular case
        String[] values = teststr.split(" ");
        int length = values.length;
        if (pattern.length() != length) {
            return false;
        }


        Map<Character, String> patternMap = new HashMap<>();

        int index = 0;
        for (String value : values) {
            
            char key = pattern.charAt(index);
            index ++;// for the next iteration to get the next key to check

            if (!patternMap.containsKey(key) && !patternMap.containsValue(value)) {

                patternMap.put(key, value);
                
                continue;
            }

            if (!patternMap.containsKey(key)) {
                return false;
            }

            if (value.equals(patternMap.get(key))) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }
}

// solution-4: HashMap and Array
class Solution {
    public boolean wordPattern(String pattern, String s) {
        
	// skip the corner cases check

	// regular case
        String[] values = s.split(" ");
        
        if (pattern.length() != values.length) {
            return false;
        }
        
        Map<Character, String> patternMap = new HashMap<>();

        int index = 0;
        for (String value : values) {
            char key = pattern.charAt(index++);

            if (!patternMap.containsKey(key) && !patternMap.containsValue(value)) {
                patternMap.put(key, value);
                continue;
            }

            if (value.equals(patternMap.get(key))) {
                continue;
            }
            else {
                return false;
            }
        }

        return true;
    }
}
