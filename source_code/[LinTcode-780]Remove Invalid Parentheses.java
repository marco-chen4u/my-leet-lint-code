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
        
        helper(result, s, 0, leftBracketCount, rightBracketCount);
        
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
    
    private void helper(List<String> result, 
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
                helper(result, newStr, i, leftBracketCount - 1, rightBracketCount);
            }
            
            // check if more right brackets
            if (rightBracketCount > 0 && 
                isRightBracket(currentChar) &&
                (i == 0 || currentChar != preChar)) {
                String newStr = str.substring(0, i) + str.substring(i + 1);// delete the current char
                helper(result, newStr, i, leftBracketCount, rightBracketCount - 1);
            }
        }
    }
   
}
