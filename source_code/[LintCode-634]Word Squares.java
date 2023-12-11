/***
* LintCode 634. Word Squares
Given a set of words without duplicates, find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

    [
      ['b', 'a', 'l', 'l'],
      ['a', 'r', 'e', 'a'],
      ['r', 'e', 'a', 'd'],
      ['l', 'a', 'd', 'y']
    ]

Example 1
    Input:
        ["area","lead","wall","lady","ball"]
    Output:
        [["wall","area","lead","lady"],["ball","area","lead","lady"]]
    Explanation:
        The output consists of two word squares. The order of output does not matter (just the order of words in each word square matters).

Example 2
    Input:
        ["abat","baba","atan","atal"]
    Output:
        [["baba","abat","baba","atan"],["baba","abat","baba","atal"]]
Notice
    There are at least 1 and at most 1000 words.
    All words will have the exact same length.
    Word length is at least 1 and at most 5.
    Each word contains only lowercase English alphabet a-z.

Link
    LintCode: https://www.lintcode.com/problem/634/
    LeetCode: https://leetcode.com/problems/word-squares/
***/
// version-1: working solution: dfs + prefix hashMap + memorized search
public class Solution {
    // field
    private List<List<String>> result;
    private List<String> path;
    private int SIZE;
    private Map<String, Set<String>> prefixMap;
    private final String EMPTY = "";
    
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        result = new ArrayList<List<String>>();
        path = new ArrayList<String>();
        // check corner case
        if (words == null || words.length == 0) {
            return result;
        }
        
        SIZE = words[0].length();
        
        intializePrefix(words);
        dfs(0);
        
        return result;
    }
    
    // helper method
    private void dfs(int startIndex) {
        if (startIndex == SIZE) {
            if (!path.isEmpty()) {
                result.add(new ArrayList<String>(path));
            }
            
            return;
        }
        
        String prefix = "";
        for (String word : path) {
            prefix += word.charAt(startIndex);
        }
        
        for (String item : prefixMap.get(prefix)) {
            if (!checkHasNext(startIndex, item)) {
                continue;
            }
            
            path.add(item);
            dfs(startIndex + 1);
            path.remove(path.size() - 1);
        }
    }
    
    private boolean checkHasNext(int startIndex, String word) {
        for (int i = startIndex + 1; i < SIZE; i++) {
            String prefix = "";
            for (String item : path) {
                prefix += item.charAt(i);
            }
            
            prefix += word.charAt(i);
            
            if (!prefixMap.containsKey(prefix)) {
                return false;
            }
            
        }
        return true;
    }
    
    private void intializePrefix(String[] words) {
        prefixMap = new HashMap<String, Set<String>>();
        for (String word : words) {
            prefixMap.putIfAbsent(EMPTY, new HashSet<String>());
            prefixMap.get(EMPTY).add(word);
            
            String prefix = "";
            for (char ch : word.toCharArray()) {
                prefix += ch;
                prefixMap.putIfAbsent(prefix, new HashSet<String>());
                prefixMap.get(prefix).add(word);
            }
        }
    }
}

//solution-2: dfs + prefix HashMap
public class Solution {

    private static final String EMPTY = "";
    private static int wordSize;
    private static Map<String, Set<String>> prefixMap;

    /**
     * @param words: a set of words without duplicates
     * @return: all word squares
     *          we will sort your return value in output
     */
    public List<List<String>> wordSquares(String[] words) {
        // write your code here
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        wordSize = words[0].length();

        prefixMap = new HashMap<>();
        for (String word : words) {
            prefixMap.putIfAbsent(EMPTY, new HashSet<String>());
            prefixMap.get(EMPTY).add(word);

            String prefix = EMPTY;
            for (char ch : word.toCharArray()) {
                prefix += ch;
                prefixMap.putIfAbsent(prefix, new HashSet<String>());
                prefixMap.get(prefix).add(word);
            }
        }

        List<List<String>> results = new ArrayList<>();
        List<String> path = new ArrayList<>();

        find(0, path, results);

        return results;
    }

    // help method
    private void find(int currentIndex, List<String> path, List<List<String>> results) {
        if (currentIndex == wordSize) {
            if (!path.isEmpty()) {
                results.add(new ArrayList<>(path));
            }

            return;
        }

        String prefix = EMPTY;
        for (String currentWord : path) {
            prefix += currentWord.charAt(currentIndex);
        }

        if (!prefixMap.containsKey(prefix)) {
            return;
        }

        for (String next : prefixMap.get(prefix)) {
            if (!isValid(prefix, currentIndex, next)) {
                continue;
            }

            path.add(next);
            find(currentIndex + 1, path, results);
            path.remove(path.size() - 1);
        }
    }

    private boolean isValid(String prefix, int currentIndex, String nextWord) {
        String nextPrefix = prefix + nextWord.charAt(currentIndex);
        return prefixMap.containsKey(nextPrefix);
    }
}

//solution-3: dfs + trie to work as prefix HashMap but better performance 
class Solution {

    private static final String EMPTY = "";

    private int wordSize = 0;
    private Trie trie;

    public List<List<String>> wordSquares(String[] words) {
        if (words == null || words.length == 0) {
            return Collections.emptyList();
        }

        wordSize = words[0].length(); // size all words have the same length
        
        trie = new Trie(); // build up a trie to accommodate all prefix which serve as prefixMap as well butter better storage performance
        for (String word : words) {
            trie.insert(word);
        }

        List<List<String>> results = new ArrayList<>();
        List<String> path = new ArrayList<>();

        find(0, path, results);

        return results;
    }

    // helper methods
    private void find(int currentIndex, List<String> path, List<List<String>> results) {
        if (currentIndex == wordSize) {
            if (!path.isEmpty()) {
                results.add(new ArrayList<>(path));
            }

            return;
        }

        String prefix = EMPTY;
        for (String currentWord : path) {
            prefix += currentWord.charAt(currentIndex);
        }

        Set<String> nextWordList = (currentIndex == 0) ? trie.getRootWordSet() : trie.getWordSet(prefix);

        if (nextWordList == null || nextWordList.isEmpty()) {
            return;
        }

        for (String next : nextWordList) {
            if (!isValid(prefix, currentIndex, next)) {
                continue;
            }

            path.add(next);
            find(currentIndex + 1, path, results);
            path.remove(path.size() - 1);
        }
    }

    private boolean isValid(String prefix, int currentIndex, String nextWord) {
        String newPrefix = prefix + nextWord.charAt(currentIndex);
        return trie.search(newPrefix);
    }

    // helper class
    class TrieNode {
        TrieNode[] children;
        Set<String> wordSet;
        boolean isEndOfWord;
        String prefix;

        public TrieNode() {
            children = new TrieNode[26];
            wordSet = new HashSet<>();
            isEndOfWord = false;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public Set<String> getRootWordSet() {
            return root.wordSet;
        }

        public void insert(String word) {
            if (word == null || word.isEmpty()) {
                return;
            }

            TrieNode node = root;
            node.wordSet.add(word);//new added
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }

                node = node.children[ch - 'a'];
                node.wordSet.add(word);
            }
            node.isEndOfWord = true;
        }

        private TrieNode find(String prefix) {
            if (prefix == null || prefix.isEmpty()) {
                return null;
            }

            TrieNode node = root;
            for (char ch : prefix.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return null;
                }

                node = node.children[ch - 'a'];
            }

            return node;
        }

        private Set<String> getWordSet(String prefix) {
            TrieNode node = find(prefix);
            if (node == null) {
                return null;
            }

            return node.wordSet;
        }

        private boolean search(String prefix) {
            TrieNode node = find(prefix);
            return node != null;
        }

    } //end of trie class
}

