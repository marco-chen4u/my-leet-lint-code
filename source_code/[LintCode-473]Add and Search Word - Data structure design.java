/***
* LintCode 473. Add and Search Word - Data structure design
Design a data structure that supports the following two operations: addWord(word) and search(word)
search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

Example
	Example 1:
		Input:
		  addWord("a")
		  search(".")
		Output:
		  true
	Example 2:
		Input:
		  addWord("bad")
		  addWord("dad")
		  addWord("mad")
		  search("pad")  
		  search("bad")  
		  search(".ad")  
		  search("b..")  
		Output:
		  false
		  true
		  true
		  true
Notice
	You may assume that all words are consist of lowercase letters a-z.
***/
class TrieNode {
    //fields
    Map<Character, TrieNode> children;
    boolean isEndOfWord;
    // constructor
    public TrieNode() {
        children = new HashMap<Character, TrieNode>();
        isEndOfWord = false;
    }
}
public class WordDictionary {
    private TrieNode root = new TrieNode();
    
    /*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    public void addWord(String word) {
        // check corner case
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (!node.children.containsKey(ch)) {
                node.children.put(ch, new TrieNode());
            }
            
            node = node.children.get(ch);
        }
        node.isEndOfWord = true;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        // check corner case
        if (word == null || word.length() == 0) {
            return false;
        }
        
        return search(word, root, 0);
    }
    
    
    // helper method
    private boolean search(String word, TrieNode node, int index) {
        // check corner case
        if (index == word.length()) {
            if (node.children.isEmpty()) {
                return true;
            }
            
            return false;
        }
        
        // normal case
        char key = word.charAt(index);
        Map<Character, TrieNode> children = node.children;
        
        if (key == '.') {
            for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
                // corner case
                if ((index == word.length() - 1) && 
                        (entry.getValue().isEndOfWord)) {
                    return true;
                }
                
                if (search(word, entry.getValue(), index + 1)) {
                    return true;
                }
            }
        }
        
        if (children.containsKey(key)) {
            node = children.get(key);
            
            //corner case
            if ((index == word.length() - 1) && node.isEndOfWord) {
                return true;
            }
            
            return search(word, node, index + 1);
        }
        
        return false;
    }
}