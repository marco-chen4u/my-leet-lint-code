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

//solution-1: using stack, time complexity: O(n)
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

// solution-2: using stack, time complexity:O(n)
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
        Stack<Integer> stack = new Stack<>();
        int value = 0;

        char[] charArray = s.toCharArray();
        int size = charArray.length;
        int lastPos = size - 1;

        for (int i = 0; i < size; i++) {
            char ch = charArray[i];

            if (Character.isDigit(ch)) {
                value = value * 10 + (ch - '0');
            }

            if (!isOperator(ch) && i != lastPos) {
                continue;
            }

            switch(preOperator) {
                case '+':
                    stack.push(value);
                    break;
                case '-':
                    stack.push(-value);
                    break;
                case '*':
                    stack.push(stack.pop() * value);
                    break;
                case '/':
                    stack.push(stack.pop() / value);
                    break;
            }

            preOperator = ch;
            value = 0;// reset
        }

        while (!stack.isEmpty()) {
            result += stack.pop();
        }

        return result;
    }

    // helper method
    private boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch =='*' || ch == '/';
    }
}
