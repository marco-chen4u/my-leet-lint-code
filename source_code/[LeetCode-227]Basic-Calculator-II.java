/***
Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . 
The integer division should truncate toward zero.

Example 1:
    Input: "3+2*2"
    Output: 7

Example 2:
    Input: " 3/2 "
    Output: 1

Example 3:
    Input: " 3+5 / 2 "
    Output: 5

Note:
    You may assume that the given expression is always valid.
    Do not use the eval built-in library function.
***/

//solution-1: using stack
class Solution {
    public int calculate(String s) {
        
        // initialization
        char operator = '+';
        int currentValue = 0;
        int result = 0;
        
        if (s == null || s.isEmpty()) {
            return result;
        }
        
        Stack<Integer> stack = new Stack<>();
        
        for (char ch : s.toCharArray()) {
            if (!isValid(ch)) {
                continue;
            }
            
            if (isDigit(ch)) {
                currentValue = currentValue * 10 + (ch - '0');
                continue;
            }
            
            pushValueIntoStack(operator, currentValue, stack);
            
            operator = ch;
            currentValue = 0;
        }
        
        // handle the last operator
        pushValueIntoStack(operator, currentValue, stack);
        
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        
        return result;
    }
    
    
    // helper methods
    private boolean isValid(char ch) {
        return isDigit(ch) || isOperator(ch);
    }
    
    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }
    
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }
    
    private void pushValueIntoStack(char operator, int currentValue, Stack<Integer> stack) {
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
}
