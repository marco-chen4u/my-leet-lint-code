/***
* LintCode.8 Given a string(Given in the way of char array) and an offset, rotate the string by offset in place.(rotate from left to right).
Example
    Example 1:
        Input: str="abcdefg", offset = 3
        Output: str = "efgabcd"	
        Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".

    Example 2:
        Input: str="abcdefg", offset = 0
        Output: str = "abcdefg"	
        Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "abcdefg".

    Example 3:
        Input: str="abcdefg", offset = 1
        Output: str = "gabcdef"	
        Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "gabcdef".

    Example 4:
        Input: str="abcdefg", offset =2
        Output: str = "fgabcde"	
        Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "fgabcde".

    Example 5:
        Input: str="abcdefg", offset = 10
        Output: str = "efgabcd"	
        Explanation: Note that it is rotated in place, that is, after str is rotated, it becomes "efgabcd".

Challenge
    Rotate in-place with O(1) extra memory.

Clarification
    in place means you should change string s in the function. You don't return anything.

Notice
    offset >= 0
    the length of str >= 0
    Make changes on the original input data
***/

// solution: 2 pointers
/*
* 三步反转法
*/
public class Solution {
    /**
     * @param str: An array of char
     * @param offset: An integer
     * @return: nothing
     */
    public void rotateString(char[] str, int offset) {
        // check corner case
        if (str == null || str.length == 0) {
            return;
        }

        int size = str.length;
        int last = size - 1;
        offset %= size;

        if (offset == 0) {
            return;
        }

        reverse(str, 0, size - offset - 1);
        reverse(str, size - offset, last);
        reverse(str, 0, last);
    }

    // helper method
    private void reverse(char[] charArray, int i, int j) {
        // check corner case
        if (charArray == null || charArray.length == 0) {
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
