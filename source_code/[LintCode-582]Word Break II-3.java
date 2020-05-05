/***
* LintCode 582. Word Break II
Given a string s and a dictionary of words dict, 
add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

Example
    Gieve s = lintcode,
	dict = ["de", "ding", "co", "code", "lint"].
	A solution is ["lint code", "lint co de"].
***/
public class Solution {
    
    // helper methods
    /**
     * Store every word cut tokens(word breaks) based on the the word dictionary, 
     * make if useful as for the memorized search afterword
     */
    private boolean[][] getWordCut(String s, Set<String> wordDict) {
        int n = s.length();
        boolean[][] isWord = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                String word = s.substring(i, j + 1);
                if (wordDict.contains(word)) {
                    isWord[i][j] = true;
                }
            }
        }
        
        return isWord;
    }
    
    /**
     * Using dynamic programming algorithm to determnine if the word cut has the word break solutions(possible)
     */
    private boolean[] getWordBreakPossibles(boolean[][] isWord, int n) {
        // initialize
        boolean[] possibles = new boolean[n + 1];
        
        possibles[n] = true;
        
        // function
        for (int i = n; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (isWord[i][j] && possibles[j + 1]) {
                    possibles[i] = true;
                    break;
                }
            }
        }
        
        // answer
        return possibles;
    }
    
    private String getValidWordBreakRresult(List<Integer> path, String s) {
        StringBuilder sb = new StringBuilder();
        
        int size = path.size();
        int lastIndex = 0;
        for (int i = 0; i < size; i++) {
            String word = s.substring(lastIndex, path.get(i));
            
            sb.append(word);
            if (i != size - 1) {
                sb.append(" ");
            }
            
            lastIndex = path.get(i);
        }
        
        return sb.toString();
    }
    
    private void search(List<String> results, 
                        List<Integer> path, // record of positions as to the valid word cuts
                        String s, 
                        boolean[][] isWord, 
                        boolean[] possibles, 
                        int startIndex) {
        if (!possibles[startIndex]) {
            return;
        }                  
        
        if (startIndex == s.length()) {
            String result = getValidWordBreakRresult(path, s);
            results.add(result);
            return;
        }
        
        for (int i = startIndex; i < s.length(); i++) {
            if (!isWord[startIndex][i]) {
                continue;
            }
            
            path.add(i + 1);
            search(results, path, s, isWord, possibles, i + 1);
            path.remove(path.size() - 1);//remove the last one
        }
    }
    
    /*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        List<String> results = new ArrayList<String>();
        
        // check corner case
        if (s == null || s.length() == 0 || wordDict.size() == 0) {
            return results;
        }
        
        int n = s.length();
        boolean[][] isWord = getWordCut(s, wordDict);
        
        boolean[] possibles = getWordBreakPossibles(isWord, n);
        
        List<Integer> path = new ArrayList<Integer>();
        search(results, path, s, isWord, possibles, 0);
        
        return results;
    }
}