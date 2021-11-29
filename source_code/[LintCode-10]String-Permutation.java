/***
* LintCode 10. String Permutation
Given a string, find all permutations of it without duplicates.

Example 1: 
    Input:
        s = "abb"
    Output:
        ["abb", "bab", "bba"]
    Explanation:
        There are six kinds of full arrangement of abb, among which there are three kinds after removing duplicates.

Example 2:
    Input:
        s = "aabb"
    Output:
        ["aabb", "abab", "baba", "bbaa", "abba", "baab"]
***/
//version-1: DFS
public class Solution {
    /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        List<String> result = new ArrayList<>();
        // check corner case
        if (str == null) {
            return result;
        }

        if (str.length() <= 1) {
            result.add(str);
            return result;
        }

        // normal case
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        int size = charArray.length;
        boolean[] visited = new boolean[size];
        Arrays.fill(visited, false);

        StringBuilder permutation = new StringBuilder();

        dfs(charArray, visited, permutation, result);

        return result;
    }

    // helper method
    private void dfs(char[] charArray, boolean[] visited, StringBuilder permutation, List<String> result) {
        // check corner cases
        if (permutation.length() == charArray.length) {
            result.add(new String(permutation));
            return;
        }

        for (int i = 0; i < charArray.length; i++) {
            if (visited[i] || 
                i > 0 && charArray[i] == charArray[i - 1] && !visited[i -1]) {
                    continue;
            }

            permutation.append(charArray[i]);
            visited[i] = true;
            dfs(charArray, visited, permutation, result);
            visited[i] = false;
            permutation.setLength(permutation.length() - 1);
        }
    }
}
