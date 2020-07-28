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
//solution-1: TrieNode with HashMap
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
    // field
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
            return node.isEndOfWord;
        }
        
        // normal case
        char key = word.charAt(index);
        Map<Character, TrieNode> children = node.children;
        
        if (key == '.') {
            for (Map.Entry<Character, TrieNode> entry : children.entrySet()) {
                // corner case
                TrieNode next = entry.getValue();
                
                if (search(word, next, index + 1)) {
                    return true;
                }
            }
        }
        
        if (children.containsKey(key)) {
            TrieNode next = children.get(key);
            
            return search(word, next, index + 1);
        }
        
        return false;
    }
}

//solution-2: TrieNode with Array
class WordDictionary {
    // inner class
    class TrieNode {
        // fields
        TrieNode[] children;
        boolean isEndOfWord;
        
        // constructor
        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    } 
    
    // field
    TrieNode root;
    
    /** Initialize your data structure here. */
    public WordDictionary() {
        this.root = new TrieNode();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        
        node.isEndOfWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        
        return search(word, root, 0);
    }
    
    // helper method
    private boolean search(String word, TrieNode node, int index) {
        // check corner cases
        if (node == null) {
            return false;
        }
        
        if (word.length() == index) {
            return node.isEndOfWord;
        }
        
        // regular case
        char current = word.charAt(index);
        if (current != '.') {
            TrieNode next = node.children[current - 'a'];
            
            return search(word, next, index + 1);
        }
        
        for (TrieNode next : node.children) {
            if (search(word, next, index + 1)) {
                return true;
            }
        }
        
        return false;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
