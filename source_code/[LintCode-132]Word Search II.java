/***
* LintCode 132. Word Search II
Given a matrix of lower alphabets and a dictionary. 
Find all words in the dictionary that can be found in the matrix. 
A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 
One character only be used once in one word. No same word in dictionary

Example
Example 1:
	Input：["doaf",
		    "agai",
		    "dcan"]，
		   
		  ["dog","dad","dgdg","can","again"]
	Output：["again","can","dad","dog"]
	Explanation：
			d o a f
			a g a i
			d c a n
		search in Matrix，so return ["again","can","dad","dog"].

Example 2:
	Input：["a"]，
	      
		  ["b"]
	Output：[]
	Explanation：
			a
		search in Matrix，return null.
***/
public class Solution {
    // fields
    int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    
    private static int MAX_WORD_LENGTH;
    
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> result = new ArrayList<>();
        if (board == null || board.length == 0) {
            return result;
        }
        if (board[0] == null || board[0].length == 0) {
            return result;
        }
        
        boolean[][] visited = new boolean[board.length][board[0].length];
        Map<String, Boolean> prefixIsWord = getPrefixSet(words);
        Set<String> resultSet = new HashSet<>();
        MAX_WORD_LENGTH = getMaxWordLength(words);
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited[i][j] = true;
                wordSearchIIHelper(resultSet, board, String.valueOf(board[i][j]), 
                                    prefixIsWord, visited, i, j);
                visited[i][j] = false;
            }
        }
        
        return new ArrayList<String>(resultSet);
    }
	
    // helper methods
    private int getMaxWordLength(List<String> words) {
        int maxLength = Integer.MIN_VALUE;
        
        for (String word : words) {
            maxLength = Math.max(maxLength, word.length());
        }
        
        return maxLength;
    }
    
    private Map<String, Boolean> getPrefixSet(List<String> words) {
        Map<String, Boolean> prefixIsWord = new HashMap<>();
        for (String word : words) {
            for (int i = 0; i < word.length() - 1; i++) {
                String prefix = word.substring(0, i + 1);
                if (!prefixIsWord.containsKey(prefix)) {
                    prefixIsWord.put(prefix, false);
                }
            }
            prefixIsWord.put(word, true);
        }
        
        return prefixIsWord;
    }
    
    private boolean isInBound(char[][] board, int x, int y) {
        int n = board.length;
        int m = board[0].length;
        
        return x >= 0 && x < n &&  // row boundary
                y >= 0 && y < m;   // column boundary
    }
    
    private void wordSearchIIHelper(Set<String> resultSet,
                                        char[][] board,
                                         String testWord,
                                         Map<String, Boolean> prefixIsWord,
                                         boolean[][] visited,
                                         int rowPos,
                                         int colPos) {
        int wordSize = testWord.length();
        if (!prefixIsWord.containsKey(testWord) || wordSize > MAX_WORD_LENGTH) {
            return;
        }
        
        if (prefixIsWord.get(testWord)) {
            resultSet.add(testWord);
        }
        
        for (int index = 0; index < 4; index++) {
            int x = rowPos + DIRECTION_X[index];
            int y = colPos + DIRECTION_Y[index];
            
            if (!isInBound(board, x, y) || visited[x][y]) {
                continue;
            }
            
            char value = board[x][y];
            visited[x][y] = true;
            wordSearchIIHelper(resultSet, board, testWord + value, 
                                    prefixIsWord, visited, x, y);
            visited[x][y] = false;
        }
    }
}