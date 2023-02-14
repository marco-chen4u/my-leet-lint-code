/***
* LeetCode 859. Buddy Strings
Given two strings s and goal, return true if you can swap two letters in s so the result is equal to goal, otherwise, return false.
Swapping letters is defined as taking two indices i and j (0-indexed) such that i != j and swapping the characters at s[i] and s[j].
For example, swapping at indices 0 and 2 in "abcd" results in "cbad".
 
Example 1:
Input: s = "ab", goal = "ba"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'b' to get "ba", which is equal to goal.

Example 2:
Input: s = "ab", goal = "ab"
Output: false
Explanation: The only letters you can swap are s[0] = 'a' and s[1] = 'b', which results in "ba" != goal.

Example 3:
Input: s = "aa", goal = "aa"
Output: true
Explanation: You can swap s[0] = 'a' and s[1] = 'a' to get "aa", which is equal to goal.


Constraints:
    1 <= s.length, goal.length <= 2 * 104
    s and goal consist of lowercase letters.

url: https://leetcode.com/problems/buddy-strings/
***/
class Solution {

    /**
     * @param a: string A
     * @param b: string B
     * @return: bool
     */
    public boolean buddyStrings(String a, String b) {
        // check corner cases
        if (isIdentical(a, b)) {
            return true;
        }

        // regular case
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        int[] diffPos = getDiffPos(charsA, charsB);
        if (diffPos.length != 2) {
            return false;
        }

        int left = diffPos[0];
        int right = diffPos[1];

        charsA = getSwap(charsA, left, right);

        return Arrays.equals(charsA, charsB);
    }

    // helper method
    private boolean isIdentical(String a, String b) {
        if (a == null && b == null) {
            return true;
        }

        if (!a.equals(b)) {
            return false;
        }
        
        // same object content
        int[] values = new int[26];
        for (char ch : a.toCharArray()) {
            values[ch - 'a']++;
        }

        for (int value : values) {
            if (value > 1) {
                return true;
            }
        }

        return false;
    }

    private boolean isSameCharacter(String str) {
        Set<Character> set = new HashSet<>();
        
        for (char ch : str.toCharArray()) {
            set.add(ch);
        }

        return set.size() == 1;
    }

    private int[] getDiffPos(char[] a, char[] b) {
        int[] result = new int[0];
        int sizeA = a.length;
        int sizeB = b.length;
        
        if (sizeA != sizeB) {
            return result;
        }

        int size = sizeA;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (a[i] == b[i]) {
                continue;
            }

            list.add(i);
        }

        result = new int[list.size()];
        int index = 0;
        for (int pos : list) {
            result[index++] = pos;
        }

        return result;
    }

    private char[] getSwap(char[] chars, int left, int right) {
        int size = chars.length;
        if (left < 0  || left >= size) {
            return chars;
        }

        if (right < 0 || right >= size) {
            return chars;
        }

        char tmp = chars[left];
        chars[left] = chars[right];
        chars[right] = tmp;

        return chars;
    }
}
