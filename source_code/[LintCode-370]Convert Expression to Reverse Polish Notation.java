/***
* LintCode 370. Convert Expression to Reverse Polish Notation
Given a string array representing an expression, and return the Reverse Polish notation of this expression. (remove the parentheses)

Example
    Example 1:
        Input: ["3", "-", "4", "+", "5"]
        Output: ["3", "4", "-", "5", "+"]
        Explanation: 3 - 4 + 5 = -1 + 5 = 4
            3 4 - 5 + = -1 5 + = 4

    Example 2:
        Input: ["(", "5", "-", "6", ")", "*", "7"]
        Output: ["5","6","-","7","*"]
        Explanation: (5 - 6) * 7 = -1 * 7 = -7
            5 6 - 7 * = -1 7 * = -7

    Example 3:
        Input: ["2", "*", "6", "-", "(", "23", "+", "7", ")", "/", "(", "1", "+", "2", ")"]
        Output:["2","6","*","23","7","+","1","2","+","/","-"]
        Explanation: 2*6-(23+7)/(1+2)
            2 6 * 23 7 + 1 2 + / -

Clarification
    Definition of Reverse Polish Notation:
    -https://en.wikipedia.org/wiki/Reverse_Polish_notation
***/
// version-1: Mono Stack<Operator+Brackets> 
public class Solution {
    /**
     * @param expression: A string array
     * @return: The Reverse Polish notation of this expression
     */
    public List<String> convertToRPN(String[] expression) {
        List<String> result = new ArrayList<String>();
        // check corner cases
        if (expression == null || expression.length == 0) {
            return result;
        }
        
        Stack<String> stack = new Stack<String>();// to store all operators and brackets
        int currentPriority = 0;
        
        for (String current : expression) {
            if (isLeftBracket(current)) {
                stack.push(current);
                continue;
            }
            
            if (isRightBracket(current)) {
                while (!stack.isEmpty() && !isLeftBracket(stack.peek())) {
                    result.add(stack.pop());
                }
                
                stack.pop(); // pop out the left bracket from the stack
                continue;
            }
            
            if (isOperator(current)) {
                currentPriority = getPriority(current);
                while (!stack.isEmpty() &&
                        currentPriority <= getPriority(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(current);
                continue;
            }
            
            if (isNumeric(current)) {
                result.add(current);
            }
        }
        
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        
        return result;
    }
    
    // helper methods
    private boolean isNumeric(String str) {
        for (char ch : str.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isOperator(String str) {
        return "+".equals(str) || "-".equals(str) ||
                "*".equals(str) || "/".equals(str);
    }
    
    private boolean isLeftBracket(String str) {
        return "(".equals(str);
    }
    
    private boolean isRightBracket(String str) {
        return ")".equals(str);
    }
    
    private int getPriority(String operator) {
        switch (operator) {
            case "*":
            case "/":
                return 3;
            case "+":
            case "-":
                return 2;
            case "(":
                return 1;
            default:
                return 0;
        }
    }
}
