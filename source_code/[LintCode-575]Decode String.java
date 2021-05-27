/***
* LintCode 575. Decode String
Given an expression s contains numbers, letters and brackets. 
Number represents the number of repetitions inside the brackets
(can be a string or another expression)．Please expand expression to be a string.

Example
    Example1
        Input: S = abc3[a]
        Output: "abcaaa"
    Example2
        Input: S = 3[2[ad]3[pf]]xyz
        Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"
Challenge
    Can you do it without recursion?
***/
//solution-1: stack<Object>
public class Solution {
    // helper method
    private String popStack(Stack<Object> stack) {
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty() && stack.peek() instanceof String) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return s;
        }

        Stack<Object> stack = new Stack<Object>();
        int number = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                number = number * 10 + (c - '0');
                continue;
            }
            
            if (c == '[') {
                stack.push(Integer.valueOf(number));
                number = 0; // reset
                continue;
            }
            
            if (c == ']') {
                String content = popStack(stack);
                int count = (Integer)(stack.pop());
                for (int i = 1; i <= count; i++) {
                    stack.push(content);
                }
                
                continue;
            }
            
            // it's character
            stack.push(String.valueOf(c));
        }

        return popStack(stack);
    }
}

//solution-2: no stack but with recursion
public class Solution {
    private final String EMPTY = "";
    private final int DEFAULT_VALUE = -1;

    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // check corner case
        if (s == null || s.isEmpty()) {
            return s;
        }

        Stack<Integer> stack = new Stack<>();
        char[] charArray = s.toCharArray();
        int size = charArray.length;
        int index = 0;
        int currentValue = 0;

        StringBuilder sb = new StringBuilder();

        for (;index < size; index++) {
            char ch = charArray[index];

            if (isLetter(ch)) {
                sb.append(ch);
                continue;
            }

            if (isDigit(ch)) {
                currentValue = currentValue * 10 + (ch - '0');
                continue;
            }

            if (isLeftBracket(ch)) {
                int rightBracketPos = findPair(index, charArray);
                String str = s.substring(index + 1, rightBracketPos);
                String stringValue = expressionExpand(str);
                sb.append(getRepeat(currentValue, stringValue));
                currentValue = 0;
                index = rightBracketPos;
            }
        }

        return sb.toString();
    }

    // helper methods
    private boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isBracket(char ch) {
        return isLeftBracket(ch) || isRightBracket(ch);
    }

    private boolean isLeftBracket(char ch) {
        return '[' == ch;
    }

    private boolean isRightBracket(char ch) {
        return ']' == ch;
    }

    private String getRepeat(int num, String str) {
        if (str == null) {
            return str;
        }

        if (num == 0 || str.isEmpty()) {
            return EMPTY;
        }

        if (num == 1) {
            return str;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(str);
        }

        return sb.toString();
    }

    private int findPair(int currentPos, char[] charArray) {
        if (currentPos < 0 || currentPos >= charArray.length) {
            return DEFAULT_VALUE;
        }

        int index = currentPos;

        if (!isBracket(charArray[index])) {
            return index;
        }

        int bracketCount = 0;
        for (; index < charArray.length; index++) {
            char ch = charArray[index];

            if (isLeftBracket(ch)) {
                bracketCount ++;
                continue;
            }

            if (isRightBracket(ch)) {
                bracketCount --;

                if (bracketCount == 0) {
                    break;
                }
                
            }
        }

        return index;
    }
}
