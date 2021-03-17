/***
* LintCode 1790. Rotate String II
Given a string(Given in the way of char array), a right offset and a left offset, rotate the string by offset in place.(left offset represents the offset of a string to the left,right offset represents the offset of a string to the right,the total offset is calculated from the left offset and the right offset,split two strings at the total offset and swap positions)。

Example
    Example 1:
        Input：str ="abcdefg", left = 3, right = 1
        Output："cdefgab"
        Explanation：
            The left offset is 3, the right offset is 1, and the total offset is left 2. Therefore, the original string moves to the left and becomes "cdefg"+ "ab".

    Example 2:
        Input：str="abcdefg", left = 0, right = 0
        Output："abcdefg"
        Explanation：The left offset is 0, the right offset is 0, and the total offset is 0. So the string remains unchanged.

    Example 3:
        Input：str = "abcdefg",left = 1, right = 2
        Output："gabcdef"
        Explanation：
            The left offset is 1, the right offset is 2, and the total offset is right 1. Therefore, the original string moves to the left and becomes "g" + "abcdef".
***/
public class Solution {
    /**
    * @param str: Input string to rotate
    * @param left: left offset
    * @param right: right offset
    * @return : return a string after rotated by the offset
    */
    public String RotateString2(String str, int left, int right) {
        String result = str;
        // check corner case
        if (str == null || str.length() == 0 || left == right) {
            return result;
        }
		
        int size = str.length();
        int offset = (left - right) % size;
        offset += (offset > 0) ? 0 : size;
		
        String leftPart = str.substring(0, offset);
        String rightPart = str.substring(offset, size);
		
        result = rightPart.concat(leftPart);//rotate
		
        return result;
    }
}
