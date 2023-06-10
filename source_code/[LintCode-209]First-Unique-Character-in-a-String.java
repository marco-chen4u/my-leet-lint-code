/***
* LintCode 209. First Unique Character in a String
Given a string and find the first unique character in a given string. 
You can assume that there is at least one unique character in the string.

Example 1:
    Input: "abaccdeff"
    Output:  'b'
    Explanation:
        There is only one 'b' and it is the first one.


Example 2:
    Input: "aabccd"
    Output:  'b'
    Explanation:
        'b' is the first one.

* LintCode link: https://www.lintcode.com/problem/209/
* LeetCode link: https://leetcode.com/problems/first-unique-character-in-a-string/ (387)
***/
//version-1: HashMap only
public class Solution {

    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (char ch : str.toCharArray()) {
            if (map.get(ch) == 1) {
                return ch;
            }
        }

        return '0';
    }
}
//version-2: HashMap + Queue
public class Solution {

    /**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        Map<Character, Integer> map = new HashMap<>();

        Queue<Character> queue = new LinkedList<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            
            int counter = map.get(ch);
            if (counter == 1) {
                queue.offer(ch);
            }
            else {
                while (!queue.isEmpty() && map.get(queue.peek()) > 1) {
                    queue.poll();
                }
            }
        }

        return queue.isEmpty() ? 0 : queue.peek();
    }
}
