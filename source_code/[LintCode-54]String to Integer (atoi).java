/***
* LintCode 54. String to Integer (atoi)
Implement function atoi to convert a string to an integer.
If no valid conversion could be performed, a zero value is returned.
If the correct value is out of the range of representable values, 
INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

Example 1
    Input: "10"
    Output: 10

Example 2
    Input: "1"
    Output: 1

Example 3
    Input: "123123123123123"
    Output: 2147483647
    Explanation: 123123123123123 > INT_MAX, so we return INT_MAX

Example 4
    Input: "1.0"
    Output: 1
    Explanation: We just need to change the first vaild number
***/

//version-1: String, enumeration, calculation by number postion
public class Solution {
    private static final int MAX = Integer.MAX_VALUE;
    private static final int MIN = Integer.MIN_VALUE;

    /**
     * @param s: A string
     * @return: An integer
     */
    public int atoi(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return 0;
        }

        s = s.trim();
        char[] chars = s.toCharArray();
        int size = chars.length;

        int startIndex = (chars[0] == '+' || chars[0] == '-') ? 1 : 0;
        boolean isNegative = chars[0] == '-';

        double value = 0;
        int result = 0;

        for (int i = startIndex; i < size; i++) {
            char ch = chars[i];

            if (!Character.isDigit(ch)) {
                break;
            }

            value = value * 10 + (ch - '0');
        }

        if (value > MAX) {
            result = isNegative ? MIN : MAX;
            return result;
        }

        result = isNegative ? -(int)value : (int)value;

        return result;
    }
}
