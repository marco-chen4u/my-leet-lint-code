/***
* LintCode 635. Boggle Game
Given a board which is a 2D matrix includes a-z and dictionary dict, 
find the largest collection of words on the board, 
the words can not overlap in the same position. 
return the size of largest collection.

Example 
Example 1
	Input:
		["abc","def","ghi"]
		{"abc","defi","gh"}
	Output:
		3
	Explanation:
		we can get the largest collection`["abc", "defi", "gh"]`
Example 2
	Input:
		["aaaa","aaaa","aaaa","aaaa"]
		{"a"}
	Output:
		16
	Explanation:
		we can get the largest collection`["abc", "defi", "gh"]`
Notice
	-- The words in the dictionary are not repeated.
	-- You can reuse the words in the dictionary.
***/
public class Solution {
	// fields
	private int n; // row size
	private int m; // column size
	
	private final int[] directionX = new int[] {0, 1, -1, 0};
	private final int[] directionY = new int[] {1, 0, 0, -1};
	
    /*
     * @param board: a list of lists of character
     * @param words: a list of string
     * @return: an integer
     */
    public int boggleGame(char[][] board, String[] words) {
		// check corner case
		if (board == null || board.length == 0 ||
			board[0] == null || board[0].length == 0 ||
			words == null || words.length == 0) {
			return 0;
		}
		
		n = board.length; // row size
		m = board[0].length;// column size
		
		TrieTree tree = new TrieTree(new TrieNode());
		for (String word : words) {
			tree.insert(word);
		}
		
		List<String> result = new ArrayList<String>();
		boolean[][] visited = new boolean[n][m];
		List<String> wordCandidates = new ArrayList<String>();
		
		search(result, wordCandidates, visited, board, 0, 0, tree.root);
		
		return result.size();
	}
	
	// helper methods
	private void search(List<String> result, List<String> wordCandidates, 
					boolean[][] visited, char[][] board, int x, int y, 
					TrieNode currentNode) {
		for (int i = x; i < n; i++) {
			for (int j = y; j < m; j++) {
				
				List<List<Integer>> nextWordPathes = new ArrayList<List<Integer>>();
				List<Integer> path = new ArrayList<Integer>();
				
				getNextWords(nextWordPathes, path, 
				                visited, board, i, j, currentNode);
				
				for (List<Integer> currentWordPath : nextWordPathes) {
					
					wordCandidates.add(getWord(board, visited, currentWordPath));
					
					checkResult(result, wordCandidates);
					
					search(result, wordCandidates, 
					        visited, board, i, j, currentNode);
					        
					resetVisitFlag(currentWordPath, visited);
					
					wordCandidates.remove(wordCandidates.size() - 1);
					
				} // for currentWordPath
				
			} // for j
			
			y = 0;
		} // for i
	}
	
	private void getNextWords(List<List<Integer>> result, List<Integer> path, 
							    boolean[][] visited, char[][] board, int x, int y, 
								TrieNode currentNode) {
		// check corner cases
		if (!isInBound(x, y) || visited[x][y]) {
			return;
		}
		
		if (!currentNode.children.containsKey(board[x][y])) {
			return;
		}
		
		TrieNode nextNode = currentNode.children.get(board[x][y]);
		if (nextNode.isEndOfWord) {
			path.add(getOneDemensionPos(x, y));
			result.add(new ArrayList<Integer>(path));// deep copy
			return;
		}
		
		visited[x][y] = true;
		path.add(getOneDemensionPos(x, y));
		for (int i = 0; i < 4; i++) {
			int nextX = x + directionX[i];
			int nextY = y + directionY[i];
			
			getNextWords(result, path, visited, board, nextX, nextY, nextNode);
			
		}
		path.remove(path.size() - 1);
		visited[x][y] = false;		
	}
	
	private String getWord(char[][] board, 
	                        boolean[][] visited, 
	                        List<Integer> currentWordPath) {
		int size = currentWordPath.size();
		char[] charArray = new char[size];
		int index = 0;
		
		for (int pos : currentWordPath) {
			int row = getRow(pos);
			int col = getColumn(pos);
			visited[row][col] = true;
			charArray[index++] = board[row][col];
		}
		
		return String.valueOf(charArray);
	}
	
	private void checkResult(List<String> result, List<String> wordCandidates) {
		if (result.size() < wordCandidates.size()) {			
			result.clear();
			result.addAll(wordCandidates);
		}
	}
	
	private void resetVisitFlag(List<Integer> currentWordPath, 
	                                boolean[][] visited) {
		for (int pos : currentWordPath) {
			visited[getRow(pos)][getColumn(pos)] = false;
		}
	}
	
	private boolean isInBound(int i, int j) {
		return (i >= 0 && i < n && // row check 
				j >= 0 && j < m); // column check
	}
	
	private int getOneDemensionPos(int i, int j) {
		if (isInBound(i, j)) {
			return i * m + j;
		}
		
		return -1;
	}
	
	private int getRow(int pos) {
		return pos / m;
	}
	
	private int getColumn(int pos) {
		return pos % m;
	}
}

// helper classes
class TrieNode {
	// fields
	String word;
	boolean isEndOfWord;
	Map<Character, TrieNode> children;
	
	// constructor
	public TrieNode() {
		this.word = null;
		this.isEndOfWord = false;
		this.children = new HashMap<Character, TrieNode>();
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
		if (isEmpty(word)) {
			return;
		}
		
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			node.children.putIfAbsent(ch, new TrieNode());
			
			node = node.children.get(ch);
		}
		
		node.isEndOfWord = true;
		node.word = word;
	}
	
	// helper methods
	private boolean isEmpty(String word) {
		return word == null || word.length() == 0;
	}	
}