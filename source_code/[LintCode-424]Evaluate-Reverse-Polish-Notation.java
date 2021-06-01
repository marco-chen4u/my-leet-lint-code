/***
* LintCode 424. Evaluate Reverse Polish Notation
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, /. 
Each operand may be an integer or another expression. 

Example 1:
    Input: ["2", "1", "+", "3", "*"] 
    Output: 9
    Explanation: ["2", "1", "+", "3", "*"] -> (2 + 1) * 3 -> 9

Example 2:
    Input: ["4", "13", "5", "/", "+"]
    Output: 6
    Explanation: ["4", "13", "5", "/", "+"] -> 4 + 13 / 5 -> 6

Link:
***/
//version-1
public class Solution {

    /**
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        

        int result = 0;
        // check corner case
        if (tokens == null || tokens.length == 0) {
            return result;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (isDigit(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            if (isOperator(token)) {
                int right = (Integer)stack.pop();
                int left = (Integer)stack.pop();
                String operator = token.trim();
                int value = calculate(left, operator, right);
                stack.push(value);
            }
        }

        result = stack.peek();
        return result;
    }

    // helper methods
    private boolean isDigit(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        char[] chars = str.toCharArray();
        int size = chars.length;
        int startIndex = (chars[0] == '+' || chars[0] == '-') ? 1 : 0;

        if (size <= startIndex) {
            return false;
        }
        
        for (int i = startIndex; i < size; i++) {
            char ch = chars[i];
            if (!Character.isDigit(ch)) {
                return false;
            }
        }

        return true;
    }

    private boolean isOperator(String token) {
        String str = token.trim();
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }

    private int calculate(int left, String operator, int right) {
        int result = 0;

        switch(operator) {
            case "+":
                result = left + right;
                break;
            case "-":
                result = left - right;
                break;
            case "*":
                result = left * right;
                break;
            case "/":
                result = left / right;
                break;
        }

        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        // check corner case
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (isDigit(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            if (!isOperator(token)) {
                continue;
            }

            int right = (Integer)stack.pop();
            int left = (Integer)stack.pop();

            switch (token) {
                case "+":
                    stack.push(left + right);
                    break;
                case "-":
                    stack.push(left - right);
                    break;
                case "*":
                    stack.push(left * right);
                    break;
                case "/":
                    stack.push(left / right);
                    break;
            }
        }

        result = stack.peek();
        return result;
    }

    // helper method
    private boolean isDigit(String token) {
        // check corner case
        if (token == null || token.isEmpty()) {
            return false;
        }

        char[] chars = token.toCharArray();
        int size = chars.length;
        int startIndex = (chars[0] == '+' || chars[0] == '-') ? 1 : 0;

        if (size <= startIndex) {
            return false;
        }
        
        for (int i = startIndex; i < size; i++) {
            if (!Character.isDigit(chars[i])) {
                return false;
            }
        }

        return true;
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }
}

//version-3
public class Solution {
    /**
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        // check corner case
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        int result = 0;
        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {
            if (!isOperator(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int right = stack.pop();
            int left = stack.pop();
            switch (token) {
                case "+":
                    stack.push(left + right);
                    break;
                case "-":
                    stack.push(left - right);
                    break;
                case "*":
                    stack.push(left * right);
                    break;
                case "/":
                    stack.push(left / right);
                    break;
            }
        }

        return stack.peek();
    }

    // helper method
    private boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
}

//version-4
public class Solution {
    /**
     * @param tokens: The Reverse Polish Notation
     * @return: the value
     */
    public int evalRPN(String[] tokens) {
        int result = 0;
        // check corner case
        if (tokens == null || tokens.length == 0) {
            return result;
        }

        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) {
            if (!"+-*/".contains(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int right = stack.pop();
            int left = stack.pop();
            switch(token) {
                case "+":
                    stack.push(left + right);
                    break;
                case "-":
                    stack.push(left - right);
                    break;
                case "*":
                    stack.push(left * right);
                    break;
                case "/":
                    stack.push(left / right);
                    break;
            }
        }

        result = stack.peek();

        return result;
    }
}
