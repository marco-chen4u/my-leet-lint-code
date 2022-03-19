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

//solution-2: stack(Object)
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // check corner case
        if (s == null || s.isEmpty()) {
            return s;
        }

        // regular case
        Stack<Object> stack = new Stack<>();
        int countValue = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                countValue = countValue * 10 + (ch - '0');
                continue;
            }

            if (Character.isLetter(ch)) {
                stack.push(String.valueOf(ch));
                continue;
            }

            if (ch == '[') {
                stack.push(Integer.valueOf(countValue));
                countValue = 0; //reset
                continue;
            }

            if (ch == ']') {
                String str = "";
                while (!stack.isEmpty() && stack.peek() instanceof String) {
                    str = (String)stack.pop() + str;
                }

                int count = (Integer) stack.pop();
                for (int i = 0; i < count; i++) {
                    stack.push(str);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, (String)stack.pop());
        }

        return sb.toString();
    }
}

//solution-3: no stack but with recursion
public class Solution {
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // corner case
        if (s == null || s.isEmpty()) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        
        int num = 0;
        char[] chars = s.toCharArray();
        int size = chars.length;
        for (int index = 0; index < size; index++) {
            char ch = chars[index];
            if(Character.isLetter(ch)) {
                sb.append(ch);
                continue;
            }

            if (Character.isDigit(ch)) {
                num = num * 10 + ch - '0';
                continue;
            }

            if (isLeftBracket(ch)) {
                int rightBracketPos = findPair(index, chars);
                String str = s.substring(index + 1, rightBracketPos);
                String stringValue = expressionExpand(str);
                sb.append(repeat(stringValue, num));

                num = 0;//reset
                index = rightBracketPos;
            }
        }

        return sb.toString();
    }

    // helper methods
    private int findPair(int currentPos, char[] chars) {
        if (currentPos < 0 || currentPos > chars.length) {
            return -1; //default
        }

        int index = currentPos;
        if (!isBracket(chars[index])) {
            return index;
        }

        int bracketCount = 0;
        for (;index < chars.length; index++) {
            char ch = chars[index];
            if (isLeftBracket(ch)) {
                bracketCount += 1;
                continue;
            }

            if (isRightBracket(ch)) {
                bracketCount -= 1;

                if (bracketCount == 0) {
                    // found the pair
                    break;
                }
                continue;
            }
        }

        return index;
    }

    private String repeat(String str, int count) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= count; i++) {
            sb.append(str);
        }

        return sb.toString();
    }

    private boolean isLeftBracket(char ch) {
        return '[' == ch;
    }

    private boolean isRightBracket(char ch) {
        return ']' == ch;
    }

    private boolean isBracket(char ch) {
        return isLeftBracket(ch) || isRightBracket(ch);
    }
}
