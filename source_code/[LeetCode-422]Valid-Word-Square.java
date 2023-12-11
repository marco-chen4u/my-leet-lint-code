/***
* LeetCode. 422 Valid Word Square
Given an array of strings words, return true if it forms a valid word square.

A sequence of strings forms a valid word square if the kth row and column read the same string,
where 0 <= k < max(numRows, numColumns).

Example 1
    Input: words = ["abcd","bnrt","crmy","dtye"]
    Output: true
    Explanation:
        [
          ['a', 'b', 'c', 'd'],
          ['b', 'n', 'r', 't'],
          ['c', 'r', 'm', 'y'],
          ['d', 't', 'y', 'e']
        ]
        The 1st row and 1st column both read "abcd".
        The 2nd row and 2nd column both read "bnrt".
        The 3rd row and 3rd column both read "crmy".
        The 4th row and 4th column both read "dtye".
        Therefore, it is a valid word square.

Example 2
    Input: words = ["abcd","bnrt","crm","dt"]
    Output: true
    Explanation:
        [
          ['a', 'b', 'c', 'd'],
          ['b', 'n', 'r', 't'],
          ['c', 'r', 'm', ' '],
          ['d', 't', ' ', ' ']
        ]
        The 1st row and 1st column both read "abcd".
        The 2nd row and 2nd column both read "bnrt".
        The 3rd row and 3rd column both read "crm".
        The 4th row and 4th column both read "dt".
        Therefore, it is a valid word square.

Example 3
    Input: words = ["ball","area","read","lady"]
    Output: false
    Explanation:
        [
          ['b', 'a', 'l', 'l'],
          ['a', 'r', 'e', 'a'],
          ['r', 'e', 'a', 'd'],
          ['l', 'a', 'd', 'y']
        ]
        The 3rd row reads "read" while the 3rd column reads "lead".
        Therefore, it is NOT a valid word square.

Constraints:
    1 <= words.length <= 500
    1 <= words[i].length <= 500
    words[i] consists of only lowercase English letters.

Link
    LeetCode: https://leetcode.com/problems/valid-word-square/
    LintCode: https://www.lintcode.com/problem/888
***/
//solution-1: iteration, time-complexity: O(n*m)  [n = size of words, m = length of a word string]
class Solution {
    public boolean validWordSquare(List<String> words) {
        int size = words.size();
        int length = 0;
        for (String word : words) {
            length = Math.max(length, word.length());
        }

        int n = Math.max(size, length);
        char[][] square = new char[n][n];
        for (int i = 0; i < size; i++) {
            char[] chars = words.get(i).toCharArray();

            for (int j = 0; j < chars.length; j++) {
                square[i][j] = chars[j];
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (square[i][j] != square[j][i]) {
                    return false;
                }
            }
        }

        return true;
    }
}

//solution-2: TBD
