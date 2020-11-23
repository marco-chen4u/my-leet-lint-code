/***
LeetCode 224. Basic Calculator
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:
   Input: "1 + 1"
   Output: 2

Example 2:
   Input: " 2-1 + 2 "
   Output: 3

Example 3:
    Input: "(1+(4+5+2)-3)+(6+8)"
    Output: 23

Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
***/
//solution-1: using stack and recursion
class Solution {
    public int calculate(String s) {
        //initialization
        int result = 0;
        int currentValue = 0;
        char operator = '+';
        
        // check corner case
        if (s == null || s.isEmpty()) {
            return result;
        }
        
        // regular case
        int size = s.length();
        int lastPos = size - 1;
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < size; i++) {
            char ch = s.charAt(i);
            
            if (!isValid(ch)) {
                continue;
            }
            
            if (isLeftBracket(ch)) {
                int end = findPair(s, i);
                currentValue = calculate(s.substring(i + 1, end));
                i = end;//next loop will be i +=1;
                continue;
            }
            
            if (isDigit(ch)) {
                currentValue = currentValue * 10 + (ch - '0');
                continue;
            }
            
            pushValueIntoStack(currentValue, operator, stack);
            
            operator = ch;
            currentValue = 0;
        }
        
        // handle the last operator
        pushValueIntoStack(currentValue, operator, stack);
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    // helper methods
    private boolean isValid(char ch) {
        return isDigit(ch) || isOperator(ch) || isLeftBracket(ch) || isRightBracket(ch);
    }
    
    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
    
    private boolean isOperator(char ch) {
        return ch == '+' || 
                ch == '-';
    }
    
    private boolean isBlank(char ch) {
        return Character.isWhitespace(ch);
    }
    
    private boolean isLeftBracket(char ch) {
        return ch == '(';
    }
    
    private boolean isRightBracket(char ch) {
        return ch == ')';
    }
    
    private void pushValueIntoStack(int currentValue, char operator, Stack<Integer> stack) {
        switch (operator) {
            case '+':
                stack.push(currentValue);
                break;
            case '-':
                stack.push(-currentValue);
                break;
            case '*':
                stack.push(stack.pop() * currentValue);
                break;
            case '/':
                stack.push(stack.pop() / currentValue);
                break;
        }
    }
    
    private int findPair(String s, int i) {
        int count = 0;

        int index = i;
        while (index < s.length()) {
            char ch = s.charAt(index);
            if (isLeftBracket(ch)) {
                count++;
            }
            else if (isRightBracket(ch)) {
                count--;
                
                if (count == 0) {
                    break;
                }
            }
            
            index ++;
        }
        
        return index;
    }
}


