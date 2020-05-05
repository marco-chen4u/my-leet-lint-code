/***
* LintCode 368. Expression Evaluation
Given an expression string array, return the final result of this expression
The expression contains only integer, +, -, *, /, (, ).

Example
	Example 1:
		For the expression `2*6-(23+7)/(1+2)`,
		Input:
			["2", "*", "6", "-", "(","23", "+", "7", ")", "/", "(", "1", "+", "2", ")"]
		Output:
			2

	Example 2:
		For the expression `4-(2-3)*2+5/5`,
		Input:
			["4", "-", "(", "2","-", "3", ")", "*", "2", "+", "5", "/", "5"]
		Output:
			7
***/
//version-1: 把表达式转为反波兰表达式，然后对逆波兰式表达式进行计算.
public class Solution {
    /**
     * @param expression: a list of strings
     * @return: an integer
     */
    public int evaluateExpression(String[] expression) {
        // write your code here
        if (isEmpty(expression)) {
            return 0;
        }
        
        String[] reversePolishNotations = getRPNExpression(expression);
        
        return getComputeRPN(reversePolishNotations);
    }
    
    // helper methods
    private String[] getRPNExpression(String[] expression) {
        String[] defaultValue = new String[0];
        if (isEmpty(expression)) {
            return defaultValue;
        }
        
        int size = expression.length;
        String[] result = new String[size];
        int index = 0;
        
        Stack<String> stack = new Stack<String>();// hold all ther operator base on decreamental priority
        int priority = 0;
        
        for (String current : expression) {
            if (isLeftBracket(current)) {
                stack.push(current);
                continue;
            }
            
            if (isRightBracket(current)) {
                while (!stack.isEmpty() && !isLeftBracket(stack.peek())) {
                    result[index++] = stack.pop();
                }
                
                stack.pop();// pop out the left bracket
                continue;
            }
            
            if (isNumeric(current)) {
                result[index++] = current;
                continue;
            }
            
            if (isOperator(current)) {
                priority = getPriority(current);
                while (!stack.isEmpty() && //先存入(递减)单调栈，然后根据优先级，弹出并进行计算
                        priority <= getPriority(stack.peek())) {
                    result[index++] = stack.pop();
                }
                
                stack.push(current);
            }
        }// for
        
        while (!stack.isEmpty()) {
            result[index++] = stack.pop();
        }
        
        return result;
    }
    
    private int getComputeRPN(String[] reversePolishNotations) {
        int result = 0;
        if (isEmpty(reversePolishNotations)) {
            return result;
        }
        
        Stack<Integer> stack = new Stack<Integer>();// to hold all operands(number)
        
        for (String current : reversePolishNotations) {
            if (isEmptyString(current)) {
                continue;
            }
            
            if (isNumeric(current)) {
                stack.push(Integer.valueOf(current));
                continue;
            }
            
            int b = stack.pop();// second operand(操作数)
            int a = stack.pop();// first operand(操作数)
            
            switch (current) {//根据(operator操作符)优先级，弹出并进行计算
                case "*" :
                    stack.push(a * b);
                    break;
                case "/":
                    stack.push(a / b);
                    break;
                case "+":
                    stack.push(a + b);
                    break;
                case "-":
                    stack.push(a - b);
                    break;
            } // switch case
        } // for
        
        
        if (!stack.isEmpty()) {
            result = Integer.valueOf(stack.pop());
        }
        
        return result;
    }
    
    private boolean isLeftBracket(String str) {
        return "(".equals(str);
    }
    
    private boolean isRightBracket(String str) {
        return ")".equals(str);
    }
    
    private boolean isOperator(String str) {
        return "*".equals(str) || "/".equals(str) || 
                "+".equals(str) || "-".equals(str);
    }
    
    private boolean isNumeric(String str) {
        if (isEmptyString(str)) {
            return false;
        }
        
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        
        return true;
    }
    
    private int getPriority(String str) {
        int priority = 0;
        
        switch (str) {
            case "*":
            case "/":
                priority = 3;
                break;
            case "+":
            case "-":
                priority = 2;
                break;
            case "(":
            case ")":
                priority = 1;
                break;
            default: 
                priority = 0;
        }
        
        return priority;
    }
    
    private boolean isEmptyString(String str) {
        return str == null || str.length() == 0;
    }
    
    private boolean isEmpty(String[] strs) {
        return strs == null || strs.length == 0;
    }
}