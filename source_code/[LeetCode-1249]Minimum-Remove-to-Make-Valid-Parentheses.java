/***
* LeetCode 1249. Minimum Remove to Make Valid Parentheses
  
Given a string s of '(' , ')' and lowercase English characters.

Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.

Formally, a parentheses string is valid if and only if:
    It is the empty string, contains only lowercase characters, or
    It can be written as AB (A concatenated with B), where A and B are valid strings, or
    It can be written as (A), where A is a valid string.

Example 1
    Input: s = "lee(t(c)o)de)"
    Output: "lee(t(c)o)de"
    Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2
    Input: s = "a)b(c)d"
    Output: "ab(c)d"

Example 3
    Input: s = "))(("
    Output: ""
    Explanation: An empty string is also valid.

Constraints
    1 <= s.length <= 105
    s[i] is either'(' , ')', or lowercase English letter.
***/
//version-1, iteration with stack, time complexity: O(n), space complexity: O(n)
class Solution {
    public String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();
        char[] chars = s.toCharArray();
        int n = chars.length;
        Set<Integer> set = new HashSet<Integer>();

        for (int i = 0; i < n; i++) {
            char ch = chars[i];

            if (Character.isLetter(ch)) {
                continue;
            }

            // if it is open bracket character
            if ('(' == ch) {
                stack.push(i);
                continue;
            }

            // if it's close bracket character
            if (')' == ch) {
                if (stack.isEmpty()) {
                    set.add(i);
                    continue;
                }

                // it's a pair = ()
                stack.pop();
                continue;
            }
            
        }

        // check if stack is some remaining open brackets, then those brackets are in valid characters to delete
        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char ch = chars[i];
            if (set.contains(i)) {
                continue;
            }

            sb.append(ch);
        }

        return sb.toString();
    }


}
