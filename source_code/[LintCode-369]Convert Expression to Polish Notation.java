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
/*
conclusion:
    if it is polish notation processing, then scan the expression from the end index to the beginning index of elements. [backward traversing tokens]
    if it is reverse polish notation processing, then scan the expression from the beginning to the end index of elements.[forward traversing tokens]
*/
//version-1: Mono Stack to Store all operators including the brackets
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

//version-2: Mono Stack to store all operators
public class Solution {
    /**
     * @param expression: A string array
     * @return: The Polish notation of this expression
     */
    public List<String> convertToPN(String[] expression) {
        List<String> result = new ArrayList<>();
        // check corner case
        if (expression == null || expression.length == 0) {
            return result;
        }

        // regular case
        Stack<String> stack = new Stack<>();//mono stack to store operators
        int size = expression.length;
        for (int i = size - 1; i >= 0; i--) {
            String token = expression[i];

            if (isRightBracket(token)) {
                stack.push(token);
                continue;
            }

            if (isLeftBracket(token)) {
                while (!stack.isEmpty() && !isRightBracket(stack.peek())) {
                    result.add(0, stack.pop());
                }

                stack.pop();
                continue;
            }

            if (isNumeric(token)) {
                result.add(0, token);
                continue;
            }

            if (isOperator(token)) {
                int currentPriority = getPriority(token);
                while (!stack.isEmpty() && currentPriority < getPriority(stack.peek())) {
                    result.add(0, stack.pop());
                }

                stack.push(token);
                continue;
            }
        }

        while (!stack.isEmpty()) {
            result.add(0, stack.pop());
        }

        return result;
    }

    // helper method
    private boolean isNumeric(String token) {
        for (char ch : token.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    private boolean isOperator(String token) {
        return "+-*/".contains(token);
    }

    private boolean isLeftBracket(String token) {
        return "(".equals(token);
    }

    private boolean isRightBracket(String token) {
        return ")".equals(token);
    }

    private int getPriority(String token) {
        int result = 0;
        switch (token) {
            case "+":
            case "-":
                result = 1;
                break;
            case "*":
            case "/":
                result = 2;
                break;
            default:
                result = 0;
                break;
        }

        return result;
    }
}
