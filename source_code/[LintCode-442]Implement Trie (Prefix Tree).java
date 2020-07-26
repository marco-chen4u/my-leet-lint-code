/***
* LintCode 442. Implement Trie (Prefix Tree)
Implement a Trie with insert, search, and startsWith methods.
Example
    Example 1:
        Input:
            insert("lintcode")
            search("lint")
            startsWith("lint")
        Output:
            false
            true
    Example 2:
        Input:
            insert("lintcode")
            search("code")
            startsWith("lint")
            startsWith("linterror")
            insert("linterror")
            search("lintcode)
            startsWith("linterror")
        Output:
            false
            true
            false
            true
            true  
Notice
    You may assume that all inputs are consist of lowercase letters a-z.
***/
//solution-1 : HashMap
public class Trie {
    // inner class
    class TrieNode {
        // fields
        Map<Character, TrieNode> children;
        boolean isEndOfWord;
        // constructor
        public TrieNode() {
            this.children = new HashMap<Character, TrieNode>();
            this.isEndOfWord = false;
        }
    }
    
    // field
    private TrieNode root;
    
	// constructor
    public Trie() {
        // do intialization if necessary
        this.root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // check corner case
        if (word == null || word.length()== 0) {
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
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // check corner case
        if (word == null || word.length() == 0) {
            return false;
        }
        
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node == null || !node.children.containsKey(ch)) {
                return false;
            }
            
            node = node.children.get(ch);
        }
        
        return (node != null && node.isEndOfWord);
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // check corner case
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (node == null || !node.children.containsKey(ch)) {
                return false;
            }
            
            node = node.children.get(ch);
        }
        
        return (node != null);
    }
}

//solution-2 : Array
public class Trie {
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
    private TrieNode root;
    
    public Trie() {
        // do intialization if necessary
        this.root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // check corner case
        if (word == null || word.length()== 0) {
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

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // check corner case
        if (word == null || word.length() == 0) {
            return false;
        }
        
        TrieNode node = root;
        for (char ch : word.toCharArray()) {
            if (node == null || node.children[ch - 'a'] == null) {
                return false;
            }
            
            node = node.children[ch - 'a'];
        }
        
        return (node != null && node.isEndOfWord);
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // check corner case
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        
        TrieNode node = root;
        for (char ch : prefix.toCharArray()) {
            if (node == null || node.children[ch - 'a'] == null) {
                return false;
            }
            
            node = node.children[ch - 'a'];
        }
        
        return (node != null);
    }
}
