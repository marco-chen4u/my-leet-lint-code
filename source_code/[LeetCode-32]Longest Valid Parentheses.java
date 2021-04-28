/***
* LeetCode 32. Longest Valid Parentheses
Given a string containing just the characters '(' and ')', 
find the length of the longest valid (well-formed) parentheses substring.
Example 1:
    Input: "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()"
Example 2:
    Input: ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()"
***/
/*
* (1) stack to keep track all left bracket position in the char array
* (2) initialize variable-leftMost = -1
* (3) when encunter left barcket, just push it's index position value into the stack
* (4) when it's right bracket, we need to keep track the following variables
     index     leftmost   stack    pop-value   max(result)
     test case = "(()())())()"
	 ch    index     leftmost   stack    pop-value   max(result)
	 (      0           -1       [0]                   0
	 (      1           -1       [0,1]      1          0
	 )      2           -1       [0]                   2-0 = 2
	 (      3           -1       [0,3]                 2
	 )      4           -1       [0]        3          4 - 0 = 4
	 )      5           -1       empty      0          5 - (-1) = 6
	 (      6           -1       [6]        
	 )      7           -1       empty      6          7 - (-1) = 8
	 )      8           -1->8    empty
	 (      9           8        [9]
	 )      10          8        empty      9          10 - 8  = 2, max(8, 2) = 8
	 
* (5) return max(result)

*/
class Solution {
	
    public int longestValidParentheses(String s) {
        int result = 0;

        // check cornrer case
        if (s == null || s.length() == 0) {
            return result;
        }

        char[] charArray = s.toCharArray();
        int size = charArray.length;
        Stack<Integer> stack = new Stack<Integer>();
        int leftMost = -1;

        for (int i = 0; i < size; i++) {
            char ch = charArray[i];

            if (isLeftBracket(ch)) {
                stack.push(i);
            }
            else {// is right barcket
                if (stack.isEmpty()) {
                    leftMost = i;
                }
                else {
                    int j = stack.pop();

                    int currentSize =  (!stack.isEmpty()) ? i - stack.peek() : i - leftMost;
                    result = Math.max(result, currentSize);
                }
            }
        }

        return result;
    }

    // helper method
    private boolean isLeftBracket(char ch) {
        return ch == '(';
    }
}

/*
* test case = "(()())())"
*/

//Version-2:DP
public class Solution {
    /**
     * @param s: a string
     * @return: return a integer
     */
    public int longestValidParentheses(String s) {
        int result = 0;
        
        // check corner case
        if (s == null || s.length() <= 1) {
            return result;
        }
        
        int size = s.length();
        char[] charArray = s.toCharArray();
        // state
        int[] dp = new int[size];
        
        // initialize
        Arrays.fill(dp, 0);
        
        // function
        for (int i = 1; i < size; i++) {
            if (isCloseBracket(charArray[i])) {// left bracket
                if (isOpenBracket(charArray[i - 1])) {//"()"
                    dp[i] = (i - 2 >= 0) ? dp[i - 2] + 2 : 2;
                    result = Math.max(result, dp[i]);
                    continue;
                }
                
                if (isCloseBracket(charArray[i - 1])) {//"))"
                    int openBracketPos = i - dp[i - 1] - 1 ;
                    if (openBracketPos < 0) {// no such index position, out of boundary
                        continue;
                    }
                    
                    if (isOpenBracket(charArray[openBracketPos])) {
                        dp[i] = dp[i - 1] + 2 + ((openBracketPos - 1 >= 0) ? dp[openBracketPos - 1] : 0);
                    }
                }
            }
            
            result = Math.max(result, dp[i]);
        }
        
        return result;
    }
    
    // helper method
    private boolean isOpenBracket(char ch) {
        return ch == '(';
    }
    
    private boolean isCloseBracket(char ch) {
        return ch == ')';
    }
}
