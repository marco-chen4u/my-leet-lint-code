/***
* LintCode 369. Convert Expression to Polish Notation
Given a string array representing an expression, and return the Polish notation of this expression. (remove the parentheses)

Example
    Example 1:
        Input: ["(", "5", "-", "6", ")", "*", "7"]
        Output: ["*", "-", "5", "6", "7"]
        Explanation: (5 - 6) * 7 = -1 * 7 = -7
            * - 5 6 7 = * -1 7 = -7
    Example 2:
        Input: ["3", "+", "(", "1", "-", "2", ")"]
        Output:["+", "3", "-", "1", "2"] 
        Explanation: 3 + (1 - 2) = 3 + -1 = 2
            + 3 - 1 2 = + 3 -1 = 2

Clarification
    Definition of Polish Notation:
    -http://en.wikipedia.org/wiki/Polish_notation
    -http://baike.baidu.com/view/7857952.htm
***/
public class Solution {
    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public List<String> convertToPN(String[] expression) {
        List<String> result = new ArrayList<String>();
        if (expression == null || expression.length == 0) {
            return result;
        }

        Stack<String> stack = new Stack<String>();
        int size = expression.length;
        int currentPriority = 0;

        for (int i = size - 1; i >= 0; i--) {
            String current = expression[i];
            if (isRightBacket(current)) {
                stack.push(current);
            }
            else if (isLeftBracket(current)) {
                while (!stack.isEmpty() &&
                        !isRightBacket(stack.peek())) {
                    result.add(stack.pop());
                }

                stack.pop();// pop out the right barcket
            }
            else if (isNumeric(current)) {
                result.add(current);
            }
            else if (isOperator(current)){
                currentPriority = getPriority(current);
                while (!stack.isEmpty() && 
                        currentPriority < getPriority(stack.peek())) {
                    result.add(stack.pop());
                }

                stack.push(current);
            }
        }// for current

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        Collections.reverse(result);

        return result;
    }
	
    // helper methods
    private boolean isRightBacket(String str) {
        return ")".equals(str);
    }

    private boolean isLeftBracket(String str) {
        return "(".equals(str);
    }

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
	
    private int getPriority(String operator) {
        int priority = 0;
        switch (operator) {
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
            default :
                priority = 0;				
        }

        return priority;
    }
}
