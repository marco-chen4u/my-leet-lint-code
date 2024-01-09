/***
* LintCode 123. Word Search
Given a 2D board and a word, find if the word exists in the grid.
The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once.
Example 1:
    Input：["ABCE","SFCS","ADEE"]，"ABCCED"
    Output：true
    Explanation：
        [    
             A B C E
             S F C S 
             A D E E
        ]
        (0,0)A->(0,1)B->(0,2)C->(1,2)C->(2,2)E->(2,1)D
Example 2:
    Input：["z"]，"z"
    Output：true
    Explanation：
        [ z ]
        (0,0)z
***/
// verison-1 : DFS-1
public class Solution {
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    private final char MARKED = '#';    
    private int n; // row size
    private int m; // column size
    
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // check corner case
        if (board == null || board.length == 0) {
            return false;
        }        
        if (board[0] == null || board[0].length == 0) {
            return false;
        }        
        if (word == null || word.length() == 0) {
            return false;
        }
        
        // initialize
        n = board.length;
        m = board[0].length;
        boolean[] result = new boolean[1];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                search(result, board, i, j, 
                        word,
                        0);
                
                if (result[0]) {
                    break;
                }
            }
        }
        
        return result[0];
    }
    
    // helper methods
    private void search(boolean[] result, char[][] board, int i, int j, 
                            String word, int startIndex) {
        // check corner case
        if (board[i][j] != word.charAt(startIndex)) {
            return;
        }        
        if (startIndex == word.length() - 1) {
            result[0] = true;
            return;
        }
        
        char tmp = board[i][j];
        board[i][j] = MARKED;// maked it as visited
        
        for (int k = 0; k < 4; k++) {
            int x = i + DIRECTION_X[k];
            int y = j + DIRECTION_Y[k];
            
            if (x < 0 || x >= n ||
                y < 0 || y >= m) {
                continue;
            }
            
            search(result, board, x, y, word, startIndex + 1);
            
            if (result[0]) {
                break;
            }
        }
        
        board[i][j] = tmp; // reverse it back for backtracking
    } 
}

// verison-2 : DFS-2
public class Solution {
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    private final char MARKED = '#';    
    private int n; // row size
    private int m; // column size
    
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // check corner case
        if (board == null || board.length == 0) {
            return false;
        }        
        if (board[0] == null || board[0].length == 0) {
            return false;
        }        
        if (word == null || word.length() == 0) {
            return false;
        }
        
        // initialize
        n = board.length;
        m = board[0].length;
        boolean[] result = new boolean[1];
        
        // function
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                
                search(result, board, i, j, 
                        word,
                        0);
                
                if (result[0]) {
                    break;
                }
            }
        }
        
        return result[0];
    }
    
    // helper methods
    private void search(boolean[] result, char[][] board, int i, int j, 
                            String word, int startIndex) {
        // check corner case
        if (startIndex == word.length()) {
            result[0] = true;
            return;
        }
        
        if (i < 0 || i >= n || 
            j < 0 || j >= m || board[i][j] != word.charAt(startIndex)) {
            return;
        }
        
        char tmp = board[i][j];
        board[i][j] = MARKED;// maked it as visited
        
        for (int k = 0; k < 4; k++) {
            int x = i + DIRECTION_X[k];
            int y = j + DIRECTION_Y[k];
            
            search(result, board, x, y, word, startIndex + 1);
            if (result[0]) {
                break;
            }
        }
        
        board[i][j] = tmp; // reverse it back for backtracking
    } 
}

// version-3: prefix Map
public class Solution {
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    
    private int n; // row size
    private int m; // column size
    private int maxWordLength;
    
    /**
     * @param board: A list of lists of character
     * @param word: A string
     * @return: A boolean
     */
    public boolean exist(char[][] board, String word) {
        // check corner case
        if (board == null || board.length == 0) {
            return false;
        }
        
        if (board[0] == null || board[0].length == 0) {
            return false;
        }
        
        if (word == null || word.length() == 0) {
            return false;
        }
        
        // initialize
        n = board.length;
        m = board[0].length;
        Map<String, Boolean> prefixMap = getPrefixMap(word);
        boolean[][] visited = new boolean[n][m];
        boolean[] result = new boolean[1];
        maxWordLength = word.length();
        //System.out.println("maxWordLength=" + maxWordLength);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                
                search(result, board, i, j, 
                        String.valueOf(board[i][j]), 
                        visited, 
                        prefixMap);
                        
                visited[i][j] = false;
                
                if (result[0]) {
                    break;
                }
            }
        }
        
        return result[0];
    }
    
    // helper methods
    private void search(boolean[] result, char[][] board, int i, int j, 
                            String testString, boolean[][] visited, 
                            Map<String, Boolean> prefixMap) {
        // check corner case
        if (!prefixMap.containsKey(testString) || 
                testString.length() > maxWordLength) {
            return;
        }
        
        if (prefixMap.get(testString)) {
            result[0] = true;
            return;
        }
        
        for (int k = 0; k < 4; k++) {
            int x = i + DIRECTION_X[k];
            int y = j + DIRECTION_Y[k];
            
            if (x < 0 || x >= n ||
                y < 0 || y >= m || visited[x][y]) {
                continue;
            }
            
            visited[x][y] = true;
            search(result, board, x, y, testString + board[x][y], visited, prefixMap);
            visited[x][y] = false;
            if (result[0]) {
                break;
            }
        }
    } 
    
    private Map<String, Boolean> getPrefixMap(String word) {
        Map<String, Boolean> result = new HashMap<String, Boolean>();
        // check corner case
        if (word == null || word.length() == 0) {
            return result;
        }
        
        for (int i = 0; i < word.length() - 1; i++) {
            String key = word.substring(0, i + 1);
            result.putIfAbsent(key, false);
        }
        
        result.put(word, true);
        
        return result;
    }
}

//version-4: DFS + trie
class Solution {

    private static final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private static final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};

    private static int m; // row size
    private static int n; // column size

    private Trie trie;

    public boolean exist(char[][] board, String word) {
        m = board.length;
        n = board[0].length;

        trie = new Trie();
        trie.insert(word);

        boolean[][] visited = new boolean[m][n];

        boolean isExist = false;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j] = true;
                if (find(board, i, j, visited, String.valueOf(board[i][j]))) {
                    isExist = true;
                    break;
                }
                visited[i][j] = false;
            }
        }

        return isExist;
    }

    // helper method
    private boolean find(char[][] board, int x, int y, boolean[][] visited, String str) {

        if (!trie.search(str)) {
            return false;
        }

        if (trie.searchWord(str)) {
            return true;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + DIRECTION_X[i];
            int nextY = y + DIRECTION_Y[i];

            if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n || visited[nextX][nextY]) {
                continue;
            }

            visited[nextX][nextY] = true;
            if (find(board, nextX, nextY, visited, str + board[nextX][nextY])) {
                return true;
            }
            visited[nextX][nextY] = false;
        }

        return false;
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }

        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch] == null) {
                node.children[ch] = new TrieNode();
            }
            node = node.children[ch];
        }

        node.isWord = true;
    }

    public boolean search(String prefix) {
        TrieNode node = find(prefix);
        return node != null;
    }

    public boolean searchWord(String word) {
        TrieNode node = find(word);
        return node != null && node.isWord;
    }

    private TrieNode find(String word) {
        if (word == null || word.isEmpty()) {
            return null;
        }

        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node == null || node.children[ch] == null) {
                return null;
            }

            node = node.children[ch];
        }

        return node;
    }
}

class TrieNode {
    // field
    TrieNode[] children;
    boolean isWord;

    // constructor
    public TrieNode() {
        children = new TrieNode[256];
        isWord = false;
    }
}
