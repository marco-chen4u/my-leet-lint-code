/***
* LintCode 640. One Edit Distance
Given two strings S and T, determine if they are both one edit distance apart.

Example
    Example 1:
        Input: s = "aDb", t = "adb" 
        Output: true
    Example 2:
        Input: s = "ab", t = "ab" 
        Output: false

Explanation:
    s=t ,so they aren't one edit distance apart
***/
/*
* 若两个字符串长度相差大于1或相等，直接返回false。 
* 反之，顺序判断每一位是否相等，若不相等，执行修改操作。
* Corner cases[字符相等， 字符串长度相等但个别字符不同， 字符串长度差1且字符差异在长字符串首尾两端发生， 字符串长度差1且字符差异在长字符串中间]的处理问题
*/
//version-1
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // check corner cases
        if (s == null && t == null) {
            return false;
        }
        
        if (Math.abs(s.length() - t.length()) > 1) {
            return false;
        }
        
        if (s.equals(t)) {
            return false;
        }
        
        // normal case process
        int size = Math.min(s.length(), t.length());
        
        if (Math.abs(size - Math.max(s.length(), t.length())) ==0) {// same size
            int diffCount = 0;
            for (int i = 0; i < size; i++) {
                diffCount += (s.charAt(i) != t.charAt(i)) ? 1 : 0;
            }
            
            return (diffCount == 1);
        }
        else {// size diff by 1, but not sure which alphabet is to edit for difference
            String baseStr = (s.length() > t.length()) ? s : t;
            String partialStr = (s.length() < t.length()) ? s : t;
            int indexPos = baseStr.indexOf(partialStr);
            
            if (indexPos == 0 || indexPos == 1) {// diff at both ends
                return true;
            }
            
            for (int i = 0; i < baseStr.length(); i++) {// diff in the middle
                String subStr = baseStr.substring(0, i) + baseStr.substring(i + 1);
                
                if (partialStr.equals(subStr)) {
                    return true;
                }
            }
        }
        
        return false;
    }
}

//version-2
public class Solution {
    /**
     * @param s: a string
     * @param t: a string
     * @return: true if they are both one edit distance apart or false
     */
    public boolean isOneEditDistance(String s, String t) {
        // check corner cases
        if (s == null && t == null || 
            s.length() == 0 && t.length() == 0) {
            return false;
        }
        
        if (s.equals(t)) {
            return false;
        }
        
        int sizeDiff = Math.abs(s.length() - t.length());
        if (sizeDiff > 1) {
            return false;
        }
        
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        
        int m = s.length();
        int n = t.length();// larger length
        
        // check if diff at both ends of the longer string
        int indexPos = t.indexOf(s);
        if (indexPos == 0 || indexPos == 1) {
            return true;
        }
        
        // check if diff in the middle of the string
        for (int i = 1; i < n - 1; i++) {
            if (s.charAt(i) == t.charAt(i)) {
                continue;
            }
            
            if (sizeDiff == 0) {// same size
                // check if the remaining of the both string the same
                return s.substring(i + 1).equals(t.substring(i + 1));
            }
            
            if (sizeDiff == 1) {
                // check if the remaining of the longer string is the same
                return s.substring(i).equals(t.substring(i + 1));
            }
        }
        
        return false;
        
    }
}

//version-3: two pointers
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        // check corner case
        if (s == null || t == null) {
            return false;
        }
        
        if (Math.abs(s.length() -t.length()) > 1) {
            return false;
        }
        
        if (s.length() > t.length()) {
            return isOneEditDistance(t, s);
        }
        
        int size1 = s.length();
        int size2 = t.length();
        
        // two pointers
        int i = 0; // for s
        int j = 0; // for t
        
        while (i < size1 && j < size2) {
            
            if (s.charAt(i) != t.charAt(j)) {
                return s.substring(i + 1).equals(t.substring(j + 1)) ||
                        s.substring(i).equals(t.substring(j + 1));
            }
            
            
            i++;
            j++;
        }
        
        return i + 1 == size2;
    }
}
