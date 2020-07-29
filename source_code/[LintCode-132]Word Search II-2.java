/***
* LintCode 132. Word Search II
Given a matrix of lower alphabets and a dictionary. 
Find all words in the dictionary that can be found in the matrix. 
A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 
One character only be used once in one word. No same word in dictionary

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
// version-2 : Trie data structure
public class Solution {
    // inner classes
    class TrieNode {
        // fields
        String word;
        boolean isEndOfWord;
        Map<Character, TrieNode> children;
        
        // constructor
        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
            this.isEndOfWord = true;
            this.word = null;
        }
    }
    
    class TrieTree {
        // field
        TrieNode root;
        // constructor
        public TrieTree(TrieNode node) {
            this.root = node;
        }
        
        // methods
        public void insert(String word) {
			// check corner case
			if (word == null || word.length() == 0) {
				return;
			}
			
            TrieNode node = root;
            
            for (char ch : word.toCharArray()) {
                Map<Character, TrieNode> children = node.children;
                children.putIfAbsent(ch, new TrieNode());
                node = children.get(ch);
            }
            
            node.isEndOfWord = true;
            node.word = word;
        }
    }
    
    // fields
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    private int n; // row size;
    private int m; // colum size;
	
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public List<String> wordSearchII(char[][] board, List<String> words) {
        List<String> result = new ArrayList<String>();
        // check corner cases
        if (board == null || board.length == 0 || 
            board[0] == null || board[0].length == 0 || 
            words == null || words.isEmpty()) {
            return result;
        }
        
        n = board.length; // row size
        m = board[0].length; // column size
        boolean[][] visited = new boolean[n][m];
        
        TrieTree tree = new TrieTree(new TrieNode());
        for (String word : words) {
            tree.insert(word);
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                search(result, board, i, j, visited, tree.root);
                visited[i][j] = false;
            }
        }
        
        return result;
    }
    
    // helper method
    private void search(List<String> result, char[][] board, int i, int j, 
                            boolean[][] visited, TrieNode node) {
        // check corner cases
        if (node == null) {
            return;
        }
        
        Map<Character, TrieNode> children = node.children;
        if (!children.containsKey(board[i][j])) {
            return;
        }
        
        TrieNode child = children.get(board[i][j]);
        
        if ((child.isEndOfWord && child.word != null) && 
				!result.contains(child.word)) {
            result.add(child.word);
        }
        
        // normal case
        for (int index = 0; index < 4; index++) {
            int x = i + DIRECTION_X[index];
            int y = j + DIRECTION_Y[index];
            
            if (x < 0 || x >= n ||
                y < 0 || y >= m || visited[x][y]) {
                continue;
            }
            
            visited[x][y] = true;
            search(result, board, x, y, visited, child);
            visited[x][y] = false;
        }
    }
}
