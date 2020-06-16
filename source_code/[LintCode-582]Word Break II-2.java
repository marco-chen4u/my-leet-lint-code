/***
* LintCode 582. Word Break II-2
Given a string s and a dictionary of words dict, 
add spaces in s to construct a sentence where each word is a valid dictionary word.
Return all such possible sentences.
Example
    Gieve s = lintcode,
        dict = ["de", "ding", "co", "code", "lint"].
        A solution is ["lint code", "lint co de"].
***/
public class Solution {
    private final String SEPERATOR = " ";
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (s == null || s.length() == 0) {
            return result;
        }

        if (wordDict == null || wordDict.isEmpty()) {
            return result;
        }

        int n = s.length();
        boolean[][] isWord = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                isWord[i][j] = wordDict.contains(s.substring(i, j + 1));
            }
        }

        boolean[] canWordBreak = new boolean[n + 1];
        canWordBreak[n] = true;
        for (int i = n - 1; i >=0; i--) {
            for (int j = i; j < n; j++) {
                if (canWordBreak[j + 1] && isWord[i][j]) {
                    canWordBreak[i] = true;
                    break;
                }
            }
        }

        List<Integer> path = new ArrayList<Integer>();
        search(result, path, s, canWordBreak, isWord, 0);

        return result;
    }

    private void search(List<String> result, List<Integer> path, 
                            String s, boolean[] canWordBreak, 
                            boolean[][] isWord, int startIndex) {
        if (!canWordBreak[startIndex]) {
            return;
        }
        
        int n = s.length();
        if (startIndex == n) {
            addPathToResult(result, path, s);
            return;
        }

        for (int i = startIndex; i < n; i++) {
            if (!isWord[startIndex][i]) {
                continue;
            }

            path.add(i + 1);
            search(result, path, s, canWordBreak, isWord, i + 1);
            path.remove(path.size() - 1);//backtracking
        }
    }

    private void addPathToResult(List<String> result, List<Integer> path, String s) {
        StringBuilder sb = new StringBuilder();
        int lastIndex = 0;
        for (int currentIndex : path) {
            String keyWord = s.substring(lastIndex, currentIndex);

            if (sb.length() == 0) {
                sb.append(keyWord);
            }
            else {
                sb.append(SEPERATOR).append(keyWord);
            }

            lastIndex = currentIndex;
        }

        result.add(sb.toString());
    }
}
