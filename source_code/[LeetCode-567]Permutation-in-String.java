/***
* LeetCode 567. Permutation in String
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.

Example 1 
    Input: s1 = "ab", s2 = "eidbaooo"
    Output: true
    Explanation: s2 contains one permutation of s1 ("ba").
    
Example 2
    Input: s1 = "ab", s2 = "eidboaoo"
    Output: false
    
Constraints:
    1 <= s1.length, s2.length <= 104
    s1 and s2 consist of lowercase English letters.

LintCode link: https://www.lintcode.com/problem/1169/
LeetCode link: https://leetcode.com/problems/permutation-in-string/
***/
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1 == null && s1 == null) {
            return true;
        }

        if (s1.isEmpty() && s2.isEmpty()) {
            return true;
        }

        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()) {
            return false;
        }

        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();

        int n1 = chars1.length;
        int n2 = chars2.length;

        if (n1 > n2) {
            return false;
        }

        Arrays.sort(chars1);
        //System.out.println("chars1 = " + Arrays.toString(chars1));

        for (int i = 0; i < n2 - n1 + 1; i++) {
            int left = i;
            char[] chars = Arrays.copyOfRange(chars2, left, left + n1);

            Arrays.sort(chars);
            //System.out.println("chars = " + Arrays.toString(chars));

            if (isEqual(chars1, chars)) {
                return true;
            }
        }

        return false;
    }

    private  boolean isEqual(char[] charsA, char[] charsB) {
        if (charsA == null && charsB == null) {
            return true;
        }

        if (charsA == null || charsB == null) {
            return false;
        }

        if (charsA.length != charsB.length) {
            return false;
        }

        int n = charsA.length;

        for (int i = 0; i < n; i++) {
            if (charsA[i] != charsB[i]) {
                return false;
            }
        }

        return true;
    }
}
