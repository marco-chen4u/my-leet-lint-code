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
//version-1(bad solution)
public class Solution {
    private int n;
    TrieTree tree;
    
    /*
     * @param words: a set of words without duplicates
     * @return: all word squares
     */
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<List<String>>();
        // check corner case
        if (words == null || words.length == 0) {
            return result;
        }
        
        n = getWordSize(words); // row size and column size
        this.tree = new TrieTree(new TrieNode(), n);
        for (String word : words) {
            tree.insert(word);
        }
        
        List<String> wordCandidateList = new ArrayList<String>();
        for (String word : words) {
            wordCandidateList.add(word);
            
            search(result, wordCandidateList, 0);
            
            wordCandidateList.remove(word);
        }
        
        return result;
    }
    
    // helper methods
    private void search(List<List<String>> result, 
                        List<String> wordCandidateList,
                        int index) {
        if (index == n - 1) {
            if (wordCandidateList.size() == n) {
                result.add(new ArrayList<String>(wordCandidateList));//deep copy
            }
            
            return;
        }
        
        char[] prefixWordChars = getPrefixChars(wordCandidateList, index);
        List<String> availableWords = getAvailableWords(wordCandidateList, 
                                                        prefixWordChars, 
                                                        index);
        if (availableWords.isEmpty()) {
            return;
        }
        
        int wordCount = availableWords.size();
        for (String word : availableWords) {
            wordCandidateList.add(word);
            
            search(result, wordCandidateList, index + 1);
            
            wordCandidateList.remove(word);
        }
    }
    
    private char[] getPrefixChars(List<String> wordCandidateList, int index) {
        int size = index + 1;
        char[] prefixChars = new char[size];
        for(int i = 0; i < size; i++) {
            prefixChars[i] = wordCandidateList.get(i).charAt(index + 1);
        }
        
        return prefixChars;
    }
    

    private List<String> getAvailableWords(List<String> wordCandidateList,
                                            char[] prefixWordChars, 
                                            int startIndex) {
        List<String> availbleWords = tree.getWords(prefixWordChars, 
                                                    startIndex, 
                                                    wordCandidateList);
        return availbleWords;
    }
    
    private Set<String> getWordDict(String[] words) {
        Set<String> result = new HashSet<String>();
        for (String word : words) {
            result.add(word);
        }
        
        return result;
    }

    private int getWordSize(String[] words) {
        return words[0].length();
    }
}

// helper classes
class TrieNode {
    // fields
    String word;
    boolean isEndOfWord;
    TrieNode[] children;
    // constructor
    public TrieNode() {
        this.word = null;
        this.isEndOfWord = false;
        this.children = new TrieNode[26];
    }
}

class TrieTree {
    // field
    TrieNode root;
    
    private int n;// char matrix's row& column size;
    
    // constructor
    public TrieTree(TrieNode node, int rowSize) {
        this.root = node;
        this.n = rowSize;
    }

    // public method
    public void insert(String word) {
        // check corner case
        if (isEmptyStr(word)) {
            return;
        }
        
        TrieNode currentNode = root;
        for (char ch : word.toCharArray()) {
            // check corner case of valid character
            if (!Character.isLetter(ch)) {
                return;
            }
            
            if (currentNode.children[ch - 'a'] == null) {
                currentNode.children[ch - 'a'] = new TrieNode();
            }
            
            currentNode = currentNode.children[ch - 'a'];
        }
        currentNode.word = word;
        currentNode.isEndOfWord = true;
    }
    
    public boolean search(String word) {
        if (isEmptyStr(word)) {
            return false;
        }
        
        return search(word, root, 0);
    }
    
    public List<String> getWords(char[] prefixWordChars, 
                                    int startIndex, 
                                    List<String> wordCandidateList) {
        List<String> result = new ArrayList<String>();
        if (prefixWordChars == null || prefixWordChars.length == 0) {
            return result;
        }
        
        search(result, prefixWordChars, wordCandidateList, root, 0);
        
        return result;
    }

    // helper method
    private void search(List<String> result, 
                        char[] prefixWordChars, 
                        List<String> wordCandidateList, 
                        TrieNode currentNode, 
                        int index) {
        if (index == n) {
            if (currentNode.isEndOfWord /*&& 
                !wordCandidateList.contains(currentNode.word)*/) {
                result.add(currentNode.word);
            }
            
            return;
        }
        
        if (index > prefixWordChars.length - 1) {
            for (TrieNode next : currentNode.children) {
                if (next == null) {
                    continue;
                }
                
                search(result, prefixWordChars, wordCandidateList, next, index + 1);
            }
            
            return;
        }
        
        char currentChar = prefixWordChars[index];
        TrieNode nextNode = currentNode.children[currentChar - 'a'];
        if (nextNode != null) {
            search(result, prefixWordChars, wordCandidateList, nextNode, index + 1);
        }
    }
    
    private boolean search(String word, TrieNode currentNode, int index) {
        // check corner case
        if (word.length() == index) {
            return currentNode.isEndOfWord;
        }
        
        char currentChar = word.charAt(index);
        TrieNode nextNode = currentNode.children[currentChar - 'a'];
        if (nextNode != null) {
            return search(word, nextNode, index + 1);
        }
        
        return false;
    }
    
    private boolean isEmptyStr(String s) {
        return s == null || s.length() == 0;
    }

}

// version-2: working solution: dfs + prefix hashMap + memorized search
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

//solution-3: dfs
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
