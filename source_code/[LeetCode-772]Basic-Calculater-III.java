/***
* LeetCode 772. Basic Calculator III
Implement a basic calculator to evaluate a simple expression string.
The expression string contains only non-negative integers, '+', '-', '*', '/' operators, and open '(' and closing parentheses ')'. 
The integer division should truncate toward zero.
You may assume that the given expression is always valid. All intermediate results will be in the range of [-2^31, 2^31 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

Example 1:
    Input: s = "1+1"
    Output: 2

Example 2:
    Input: s = "6-4/2"
    Output: 4

Example 3:
    Input: s = "2*(5+5*2)/3+(6/2+8)"
    Output: 21

Example 4:
    Input: s = "(2+6*3+5-(3*14/7+2)*5)+3"
    Output: -12

Example 5:
    Input: s = "0"
    Output: 0

Constraints:
    1 <= s <= 10^4
    s consists of digits, '+', '-', '*', '/', '(', and ')'.
    s is a valid expression.
***/
//solution-1: using stack and recursion
public class Solution {
    /**
     * @param s: the given expression
     * @return: the result of expression
     */
    public int calculate(String s) {
        int result = 0;
        // check corner case
        if (s == null || s.isEmpty()) {
            return result;
        }

        // regular case
        char preOperator = '+';
        int value = 0;

        char[] charArray = s.toCharArray();
        int size = charArray.length;
        int lastPos = size - 1;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < size; i++) {
            char ch = charArray[i];            

            if (isLeftBracket(ch)) {
                int j = findPair(i, charArray);
                String str = String.valueOf(Arrays.copyOfRange(charArray, i, j));
                value = calculate(str);
                i = j;
            }

            if (isDigit(ch)) {
                value = value * 10 + (ch - '0');
            }

            if (!isOperator(ch) && i != lastPos) {
                continue;
            }

            if (preOperator == '+') {
                stack.push(value);
            }

            if (preOperator == '-') {
                stack.push(-value);
            }

            if (preOperator == '*') {
                stack.push(stack.pop() * value);
            }

            if (preOperator == '/') {
                stack.push(stack.pop() / value);
            }

            preOperator = ch;
            value = 0;//reset
        }
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // helper methods
    private boolean isValid(char ch) {
        return isDigit(ch) || isBracket(ch) || isOperator(ch);
    }
    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isBracket(char ch) {
        return isLeftBracket(ch) || isRightBracket(ch);
    }

    private boolean isLeftBracket(char ch) {
        return ch == '(';
    }

    private boolean isRightBracket(char ch) {
        return ch == ')';
    }

    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    private int findPair(int startPos, char[] charArray) {
        int result = -1;
        // check corner case
        int size = charArray.length;
        if (startPos < 0 || startPos >= size) {
            return -1;
        }

        result = startPos;
        if (!isLeftBracket(charArray[startPos])) {
            return result;
        }

        // regular case
        int count = 0;
        int index = startPos;
        for (; index < size; index++) {
            char ch = charArray[index];
            if (isLeftBracket(ch)) {
                count ++;
                continue;
            }

            if (isRightBracket(ch)) {
                count--;
                if (count == 0) {
                    result = index;
                    break;
                }
            }
        }

        return result;
    }
}
