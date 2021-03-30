/***
* LintCode 924. Shortest Word Distance
Given a list of words and two words word1 and word2, 
return the shortest distance between these two words in the list.

Example
    Example 1:
        Input：["practice", "makes", "perfect", "coding", "makes"],"coding","practice"
        Output：3
        Explanation：index("coding") - index("practice") = 3

    Example 2:
        Input：["practice", "makes", "perfect", "coding", "makes"],"makes","coding"
        Output：1
        Explanation：index("makes") - index("coding") = 1

Notice
    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
***/

public class Solution {
    /**
     * @param words: a list of words
     * @param word1: a string
     * @param word2: a string
     * @return: the shortest distance between word1 and word2 in the list
     */
    public int shortestDistance(String[] words, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        
        // check corner case
        if (words == null || words.length <= 1) {
            return result;
        }
        
        int size = words.length;
        
        int index1 = -1; // for word1
        int index2 = -1; // for word2
        
        for (int i = 0; i < size; i++) {
            if (word1.equals(words[i])) {
                index1 = i;
            }
            
            if (word2.equals(words[i])) {
                index2 = i;
            }
            
            if (index1 != -1 && index2 != -1) {
                result = Math.min(result, Math.abs(index1 - index2));
            }
        }
        
        return result;
    }
}
