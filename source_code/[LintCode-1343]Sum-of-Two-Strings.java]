/***
* LintCode 1343. Sum of Two Strings
Given you two strings which are only contain digit character. You need to return a string spliced by the sum of the bits.

Example 1
    Input:
        A = "99"
        B = "111"
    Output: "11010"
    Explanation: because 9 + 1 = 10, 9 + 1 = 10, 0 + 1 = 1,connect them，so answer is "11010"

Example 2
    Input:
        A = "2"
        B = "321"
    Output: "323"
    Explanation: because 2 + 1 = 3, 2 + 0 = 2, 3 + 0 = 3, connect them，so answer is "323"

LintCode link: https://www.lintcode.com/problem/1343/
**/
//version-1: two pointers
public class Solution {
    /**
     * @param a: a string
     * @param b: a string
     * @return: return the sum of two strings
     */
    public String sumofTwoStrings(String a, String b) {
        // write your code here
        if (isEmpty(a)) {
            return b;
        }

        if (isEmpty(b)) {
            return a;
        }

        int size1 = a.length();
        int size2 = b.length();

        int i = size1 - 1;
        int j = size2 - 1;

        String result = "";
        while (i >= 0 || j >= 0) {
            int val1 = i >= 0 ? (a.charAt(i) - '0') : 0;
            int val2 = j >= 0 ? (b.charAt(j) - '0') : 0;

            int value = val1 + val2;
            result = value + result;

            i--;
            j--;
        }

        return result;
    }

    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
