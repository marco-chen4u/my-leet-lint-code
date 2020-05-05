/***
* LintCode 427. Generate Parentheses
Given n, and there are n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

Example
	Example 1:
		Input: 3
		Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]

	Example 2:
		Input: 2
		Output: ["()()", "(())"]
***/
public class Solution {
	// fileds
	private final String LEFT_PARENTHESE = "(";
	private final String RIGHT_PARENTHESE = ")";
	
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
		helper(result,  parentheses, left, right);
		
		return result;
    }
	
	// helper method
	private void helper(List<String> result, String parentheses, int left, int right) {
		// check corner case
		if (left == n && right == n) {
			result.add(parentheses.toString();
			return;
		}
		
		if (left < n) {
			parentheses.append(LEFT_PARENTHESE);
			helper(result, parentheses, left + 1, right);
			parentheses.deleteCharAt(parentheses.length() - 1);
		}
		
		if (right < n) {
			parentheses.append(RIGHT_PARENTHESE);
			helper(result, parentheses + ")", left, right + 1);
			parentheses.deleteCharAt(parentheses.length() - 1);
		}
	}
}