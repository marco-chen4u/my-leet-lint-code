/**
* LintCode 1725. Reverse Only Letters
Given a string S, return the "reversed" string where all characters that are not a letter stay in the same place, 
and all letters reverse their positions.

Example 1:
    Input："ab-cd"
    Output："dc-ba"
    Explanation：The '-' stays in customary position and other characters reverse.

Example 2:
    Input："Test1ng-Leet=code-Q!"
    Output："Qedo1ct-eeLg=ntse-T!"
    Explanation：The '-' ,'=','1', and '!'  stay in customary position and other characters reverse.
    
**/
//version-1: two pointers
public class Solution {
    /**
     * @param s: Customary string
     * @return: Reversed string
     */
    public String ReverseOnlyLetters(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        int size = s.length();
        int left = 0;
        int right = size - 1;

        char[] chars = s.toCharArray();

        while (left < right) {
            while (left < right && !Character.isLetter(chars[left])) {
                left++;
            }

            while (left < right && !Character.isLetter(chars[right])) {
                right--;
            }

            if (left < right) {
                char tmp = chars[left];
                chars[left] = chars[right];
                chars[right] = tmp;

                left++;
                right--;
            }
        }

        return String.valueOf(chars);
    }
}
