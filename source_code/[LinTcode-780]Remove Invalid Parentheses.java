/***
* LinTcode 780. Remove Invalid Parentheses
Remove the minimum number of invalid parentheses in order to make the input string valid. 
Return all possible results.

Example 1:
    Input:
        "()())()"
    Ouput:
        ["(())()","()()()"]

Example 2:
    Input:
        "(a)())()"
    Output:
        ["(a)()()", "(a())()"]

Example 3:
    Input:
        ")(" 
    Output:
        [""] 
Notice
    The input string may contain letters other than the parentheses ( and ).

Link
    LintCode: https://www.lintcode.com/problem/780/
    LeetCode: https://leetcode.com/problems/remove-invalid-parentheses/
***/
solution-1: dfs
public class Solution {
    // field
    private final String EMPTY = "";// empty value string
    private final char DEFAULT_VALUE = '#';
    
    /**
     * @param s: The input string
     * @return: Return all possible results
     */
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (s == null || s.length() == 0) {
            result.add(EMPTY);
            return result;
        }
        
        //size = s.length();
        
        int leftBracketCount = 0;
        int rightBracketCount = 0;
        for (char ch : s.toCharArray()) {
            if (isLeftBracket(ch)) {
                leftBracketCount++;
            }
            
            if (isRightBracket(ch)) {
                if (leftBracketCount > 0) {
                    leftBracketCount--;
                }
                else {
                    rightBracketCount++;
                }
            }
        }
        
        dfs(result, s, 0, leftBracketCount, rightBracketCount);
        
        return result;
    }
    
    // helper methods
    private boolean isLeftBracket(char ch) {
        return '(' == ch;
    }

    private boolean isRightBracket(char ch) {
        return ')' == ch;
    }

    private boolean isValidParenthese(String str) {
        int count = 0;
        for (char ch : str.toCharArray()) {
            if (isLeftBracket(ch)) {
                count++;
            }
            
            if (isRightBracket(ch)) {
                count--;
            }
            
            if (count < 0) {
                return false;
            }
        }
        
        return count == 0;
    }
    
    private void dfs(List<String> result, 
                        String str, int startIndex, 
                        int leftBracketCount, int rightBracketCount) {
        // check corner case
        if (leftBracketCount == 0 && rightBracketCount == 0) {
            if (isValidParenthese(str)) {
                result.add(str);
            }
            
            return;
        }
        
        // normal case
        for (int i = startIndex; i < str.length(); i++) {
            char currentChar = str.charAt(i);
            char preChar = (i > 0) ? str.charAt(i - 1) : DEFAULT_VALUE;
            // check if more left brackets
            if (leftBracketCount > 0 && 
                isLeftBracket(currentChar) &&
                (i == 0 || currentChar != preChar)) {
                String newStr = str.substring(0, i) + str.substring(i + 1);// delete the current char
                dfs(result, newStr, i, leftBracketCount - 1, rightBracketCount);
            }
            
            // check if more right brackets
            if (rightBracketCount > 0 && 
                isRightBracket(currentChar) &&
                (i == 0 || currentChar != preChar)) {
                String newStr = str.substring(0, i) + str.substring(i + 1);// delete the current char
                dfs(result, newStr, i, leftBracketCount, rightBracketCount - 1);
            }
        }
    }
   
}

// solution-2: dfs
class Solution {
    private final String EMPTY_STR = "";
    private final char DEFAULT_CHAR = '$';

    public List<String> removeInvalidParentheses(String s) {
        List<String> results = new ArrayList<>();

        if (s == null || s.isEmpty()) {
            results.add(EMPTY_STR);
            return results;
        }

        int leftCount = 0;
        int rightCount = 0;

        for (char ch : s.toCharArray()) {
            if (isLeft(ch)) {
                leftCount += 1;
                continue;
            }

            if (isRight(ch)) {
                if (leftCount > 0) {
                    leftCount -= 1;
                }
                else {
                    rightCount += 1;
                }
            }
        }

        find(s, 0, leftCount, rightCount, results);

        return results;
    }

    private void find(String str, int currentIndex, int leftCount, int rightCount, List<String> results) {
        if (leftCount == 0 && rightCount == 0) {
            if (isValid(str)) {
                results.add(str);
            }

            return;
        }

        for (int i = currentIndex; i < str.length(); i++) {
            char current = str.charAt(i);
            char pre = i > 0 ? str.charAt(i - 1) : DEFAULT_CHAR;

            // [1] check if more left brackets
            if (leftCount > 0 && 
                isLeft(current) && 
                (pre != current)) {
                
                String newStr = str.substring(0, i) + str.substring(i + 1);// remove the current character
                find(newStr, i, leftCount - 1, rightCount, results);
            }

            // [2] check if more right brackets
            if (rightCount > 0 &&
                isRight(current) && 
                (pre != current)) {
                
                String newStr = str.substring(0, i) + str.substring(i + 1);
                find(newStr, i, leftCount, rightCount - 1, results);
            }

        }
    }

    private boolean isLeft(char ch) {
        return '(' == ch;
    }

    private boolean isRight(char ch) {
        return ')' == ch;
    }

    private boolean isPair(char left, char right) {
        return isLeft(left) && isRight(right);
    }

    private boolean isValid(String str) {
        if (str == null || str.isEmpty()) {
            return true;
        }

        char[] chars = str.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char ch : str.toCharArray()) {
            if (isLeft(ch)) {
                stack.push(ch);
                continue;
            }

            if (isRight(ch) && !stack.isEmpty() && isPair(stack.peek(), ch)) {
                stack.pop();
                continue;
            }
        }

        return stack.isEmpty();

    }
}
