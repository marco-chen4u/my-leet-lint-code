/***
* LeetCode 316. Remove Duplicate Letters
Given a string s, remove duplicate letters so that every letter appears once and only once. 
You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
    Input: s = "bcabc"
    Output: "abc"

Example 2:
    Input: s = "cbacdcbc"
    Output: "acdb"
    
Constraints:
    1 <= s.length <= 104
    s consists of lowercase English letters.
    
Note:
  This question is the same as 1081: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
  
LeetCode link: https://leetcode.com/problems/remove-duplicate-letters/  
***/
//version-1: Monotonic stack
// idea comes from : https://leetcode.com/problems/remove-duplicate-letters/solutions/1859410/java-c-detailed-visually-explained/
class Solution {
    public String removeDuplicateLetters(String s) {
        char[] chars = s.toCharArray();
        int size = s.length();

        int[] lastIndex = new int[26];
        for (int i = 0; i < size; i++) {
            lastIndex[chars[i] - 'a'] = i;
        }

        boolean[] visited = new boolean[26];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < size; i++) {
            int current = chars[i] - 'a';
            
            if (visited[current]) {
                continue;
            }

            while (!stack.isEmpty() && 
                    current < stack.peek() &&
                    i < lastIndex[stack.peek()]) {
                int prev = stack.pop();
                visited[prev] = false;    
            }

            stack.push(current);
            visited[current] = true;
        }

        String result = "";

        while (!stack.isEmpty()) {
            char ch = (char)(stack.pop() + 'a');
            result = ch + result;
        }

        return result;
    }
}

