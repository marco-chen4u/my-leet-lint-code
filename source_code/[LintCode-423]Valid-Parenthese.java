/***
* LintCode 423. Valid Parentheses

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

Example 1:
    Input: "([)]"
    Output: False

Example 2:
    Input: "()[]{}"
    Output: True

Note: Use O(n) time, n is the number of parentheses.
***/
//version-1: stack
public class Solution {
    // fields
    private Stack<Character> stack = new Stack<>();

    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        
        stack.clear();
        char[] charArray = s.toCharArray();
        for (char ch : charArray) {
            if (isLeftBracket(ch)) {
                stack.push(ch);
                continue;
            }

            if (!isRightBracket(ch)) {
                continue;
            }

            // process the right bracket
            // check corner case
            if (stack.isEmpty()) {
                return false;
            }

            // regular case
            if (!isValid(stack.pop(), ch)) {
                return false;
            }
        }

        return stack.isEmpty();
    }

    // helper methods
    private boolean isLeftBracket(char ch) {
        return isLeftRoundBracket(ch) || isLeftSquareBracket(ch) || isLeftCurlyBracket(ch);
    }

    private boolean isRightBracket(char ch) {
        return isRightRoundBracket(ch) || isRightSquareBracket(ch) || isRightCurlyBracket(ch);
    }

    private boolean isValid(char left, char right) {
        return isLeftRoundBracket(left) && isRightRoundBracket(right) ||
                isLeftSquareBracket(left) && isRightSquareBracket(right) ||
                isLeftCurlyBracket(left) && isRightCurlyBracket(right);
    }

    private boolean isLeftRoundBracket(char ch) {
        return ch == '(';
    }

    private boolean isRightRoundBracket(char ch) {
        return ch == ')';
    }

    private boolean isLeftSquareBracket(char ch) {
        return ch == '[';
    }

    private boolean isRightSquareBracket(char ch) {
        return ch == ']';
    }

    private boolean isLeftCurlyBracket(char ch) {
        return ch == '{';
    }

    private boolean isRightCurlyBracket(char ch) {
        return ch == '}';
    }
}

//version-2: stack
public class Solution {
    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (isLeft(c)) {
                stack.push(c);
                continue;
            }
            
            if (isRight(c) && !stack.isEmpty() && isValidPair(stack.peek(), c)) {
                stack.pop();
            }
            else {
                return false;
            }
        }
        
        return stack.isEmpty();
    }
    
    // helper methods
    private boolean isLeft(char character) {
        return character == '(' || character == '{' || character == '[';
    }
    
    private boolean isRight(char character) {
        return character == ')' || character == '}' || character == ']';
    }
    
    private boolean isValidPair(char left, char right) {
        if (left == '(' && right == ')') {
            return true;
        }
        
        if (left == '{' && right == '}') {
            return true;
        }
        
        if (left == '[' && right == ']') {
            return true;
        }
        
        return false;
    }
}

//version-3: stack
public class Solution {
    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        // check corner case
        if (s == null || s.length() == 0) {
            return true;
        }
        
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (isLeftParenthese(c)) {
                stack.push(c);
            }
            else {
                if (isRightParenthese(c) && !stack.isEmpty() && isValidParenthesePair(stack.peek(), c)) {
                    stack.pop();
                }
                else {
                    return false;
                }
            }
        }
        
        return stack.isEmpty();
    }
    
    // helper method
    private boolean isLeftParenthese(char c) {
        return c == '(' || c == '[' || c == '{';
    }
    
    private boolean isRightParenthese(char c) {
        return c == ')' || c == ']' || c == '}';
    }
    
    private boolean isValidParenthesePair(char left, char right) {
        return left == '(' && right == ')' ||
                left == '[' && right == ']' ||
                left == '{' && right == '}';
    }
}

//version-4:
public class Solution {
    /**
     * @param s: A string
     * @return: whether the string is a valid parentheses
     */
    public boolean isValidParentheses(String s) {
        if (s == null || s.isEmpty()) {
            return true;
        }

        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (isLeftBracket(ch)) {
                stack.push(ch);
                continue;
            }

            if (isRightBracket(ch) && 
                (stack.isEmpty() ||
                !isPair(stack.peek(), ch))) {
                return false;
            }

            stack.pop();
        }

        return stack.isEmpty();
    }

    // helper method
    private boolean isPair(char left, char right) {
        return left == '(' && right == ')' ||
            left == '[' && right == ']' ||
            left == '{' && right == '}';
    }

    private boolean isLeftBracket(char ch) {
        return ch == '(' || 
            ch == '[' ||
            ch == '{';
    }

    private boolean isRightBracket(char ch) {
        return ch == ')' ||
            ch == ']' ||
            ch == '}';
    }
}
