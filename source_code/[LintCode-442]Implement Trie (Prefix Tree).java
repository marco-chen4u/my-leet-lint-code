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

//version-3: Array
public class Trie {

    class TrieNode {
        // fields
        TrieNode[] children;
        boolean isEndOfWord;

        // constructor
        public TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
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
        // corner case
        if (isEmpty(word)) {
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
        // corner case
        if (isEmpty(word)) {
            return false;
        }

        TrieNode node = lookUp(root, word.toCharArray());

        return node != null && node.isEndOfWord;
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // corner case
        if (isEmpty(prefix)) {
            return false;
        }

        TrieNode node = lookUp(root, prefix.toCharArray());

        return node != null;
    }

    // helper methods
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private TrieNode lookUp(TrieNode root, char[] chars) {
        if (root == null || chars == null || chars.length == 0) {
            return null;
        }

        TrieNode node = root;
        for (char ch : chars) {
            if (node == null || node.children[ch - 'a'] == null) {
                return null;
            }

            node = node.children[ch - 'a'];
        }

        return node;
    }
}
