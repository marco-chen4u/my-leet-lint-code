/***
* LintCode 427. Generate Parentheses
Given n, and there are n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example 1
    Input: 3
    Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]

Example 2
    Input: 2
    Output: ["()()", "(())"]

Link: 
    LintCode: https://www.lintcode.com/problem/427
    LeetCode: https://leetcode.com/problems/generate-parentheses/
***/
//version-1: stringbuilder and dfs
public class Solution {
    private final String LEFT_PARENTHESE = "(";
    private final String RIGHT_PARENTHESE = ")";
    
    private int n;// total size
    /**
     * @param n: n pairs
     * @return: All combinations of well-formed parentheses
     */
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        this.n = n;
        // check corner case
        if (n <= 0) {
            return result;
        }

        int left = 0;
        int right = 0;
        StringBuilder parentheses = new StringBuilder();
        findValidPair(result,  parentheses, left, right);

        return result;
    }
	
    // helper method
    private void findValidPair(List<String> result, StringBuilder parentheses, int left, int right) {
        // check corner case
        if (left == n && right == n) {
            result.add(parentheses.toString());
            return;
        }

        if (left < n) {
            parentheses.append(LEFT_PARENTHESE);
            findValidPair(result, parentheses, left + 1, right);
            parentheses.setLength(parentheses.length() - 1);
        }

        if (right < n && left > right) {
            parentheses.append(RIGHT_PARENTHESE);
            findValidPair(result, parentheses, left, right + 1);
            parentheses.setLength(parentheses.length() - 1);
        }
    }
}

//version-2: better and faster, dfs
class Solution {

    private static int n; // total length;
    private static final char OPEN_BRACKET = '(';
    private static final char CLOSE_BRACKET = ')';

    public List<String> generateParenthesis(int n) {
        if (n <= 0) {
            return Collections.emptyList();
        }

        this.n = n;

        String str = "";
        List<String> results = new LinkedList<>();
        findPair(0, 0, str, results);

        return results;
    }

    // helper method
    private void findPair(int left, int right, String str, List<String> results) {
        if (left == n && right == n) {
            results.add(str);
            return;
        }

        if (left < n) {
            findPair(left + 1, right, str + OPEN_BRACKET, results);
        }

        if (right < n && right < left) {
            findPair(left, right + 1, str + CLOSE_BRACKET, results);
        }
    }
}
