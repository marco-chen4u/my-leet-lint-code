/***
* LintCode 1790. Rotate String II
Given a string(Given in the way of char array), a right offset and a left offset, rotate the string by offset in place.
(left offset represents the offset of a string to the left,
 right offset represents the offset of a string to the right,
 the total offset is calculated from the left offset and the right offset,
 split two strings at the total offset and swap positions).

Example
    Example 1:
        Input：str ="abcdefg", left = 3, right = 1
        Output："cdefgab"
        Explanation：
            The left offset is 3, the right offset is 1, and the total offset is left 2. 
	    Therefore, the original string moves to the left and becomes "cdefg"+ "ab".

    Example 2:
        Input：str="abcdefg", left = 0, right = 0
        Output："abcdefg"
        Explanation：The left offset is 0, the right offset is 0, and the total offset is 0. So the string remains unchanged.

    Example 3:
        Input：str = "abcdefg",left = 1, right = 2
        Output："gabcdef"
        Explanation：
            The left offset is 1, the right offset is 2, and the total offset is right 1. 
	    Therefore, the original string moves to the left and becomes "g" + "abcdef".
***/
//solution-1: substring
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

//solution-2: 2-pointers, 三步反转法
public class Solution {
    /**
     * @param str: An array of char
     * @param left: a left offset
     * @param right: a right offset
     * @return: return a rotate string
     */
    public String RotateString2(String str, int left, int right) {
        // check corner case
        if (str == null || str.isEmpty()) {
            return str;
        }

        int size = str.length();
        int offset = (size - left + right) % size;
        offset += (offset >= 0) ? 0 : size;

        //System.out.println("offset = " + offset);

        char[] charArray = str.toCharArray();
        int lastPos = size - 1;
        
        reverse(charArray, 0, size - offset - 1);
        reverse(charArray, size - offset, lastPos);
        reverse(charArray, 0, lastPos);

        return String.valueOf(charArray);
    }

    // helper methods
    private void reverse(char[] charArray, int i, int j) {
        // check corner case
        if (charArray == null || charArray.length == 0) {
            return;
        }

        if (i == j) {
            return;
        }

        while (i < j) {
            swap(charArray, i, j);
            i++;
            j--;
        }
    }

    private void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
