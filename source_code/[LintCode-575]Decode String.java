/***
* LintCode 575. Decode String
Given an expression s contains numbers, letters and brackets. 
Number represents the number of repetitions inside the brackets
(can be a string or another expression)．Please expand expression to be a string.

Example
	Example1
		Input: S = abc3[a]
		Output: "abcaaa"
	Example2
		Input: S = 3[2[ad]3[pf]]xyz
		Output: "adadpfpfpfadadpfpfpfadadpfpfpfxyz"
Challenge
	Can you do it without recursion?
***/
public class Solution {
	// helper method
	private String popStack(Stack<Object> stack) {
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty() && stack.peek() instanceof String) {
			sb.insert(0, stack.pop());
		}
		
		return sb.toString();
	}
	
    /**
     * @param s: an expression includes numbers, letters and brackets
     * @return: a string
     */
    public String expressionExpand(String s) {
        // check corner case
		if (s == null || s.length() == 0) {
			return s;
		}
		
		Stack<Object> stack = new Stack<Object>();
		int number = 0;
		for (char c : s.toCharArray()) {
			if (Character.isDigit(c)) {
				number = number * 10 + (c - '0');				
			}
			else if (c == '[') {
				stack.push(Integer.valueOf(number));
				number = 0; // reset
			}
			else if (c == ']') {
				String content = popStack(stack);
				int count = (Integer)(stack.pop());
				for (int i = 1; i <= count; i++) {
					stack.push(content);
				}
			}
			else {
				stack.push(String.valueOf(c));
			}
		}
		
		return popStack(stack);
    }
}