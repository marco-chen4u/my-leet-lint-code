/***
* LintCode 270. Letter Combination of a Phone Number II
Given some digit strings excluded 0 and 1 and a dict, 
for each digit string return the number of possible letter combinations in dict that the number could match.

If we can use a digit string represent the prefix of a word, we think they can match.

A mapping of digit to letters (just like on the telephone buttons) is given below.


----------------------------------
    1  	  2    3
         ABC  DEF
	   
    4     5    6
   GHI   JKL  MNO
   
    7     8    9
   PQRS  TUV  WXYZ
----------------------------------   


Example
    Input: query = ["2", "3", "4"]
    dict = ["a","abc","de","fg"]
    Output:[2,2,0]
    Explanation: 
        "a" "abc" match "2"
        "de" "fg" match "3"
        no word match "4"

Link
  LintCode link: https://www.lintcode.com/problem/270/
***/

//solution-1: Trie + DFS, run time excepthon: Memory Limit Exceeded
public class Solution {

    /* initialization start */
    private final static Map<Character, char[]> map;
    static {
        map = new HashMap<Character, char[]>();
        map.put('2', new char[] {'a', 'b', 'c'});
        map.put('3', new char[] {'d', 'e', 'f'});
        map.put('4', new char[] {'g', 'h', 'i'});
        map.put('5', new char[] {'j', 'k', 'l'});
        map.put('6', new char[] {'m', 'n', 'o'});
        map.put('7', new char[] {'p', 'q', 'r', 's'});
        map.put('8', new char[] {'t', 'u', 'v'});
        map.put('9', new char[] {'w', 'x', 'y', 'z'});
    }
    /* initialization end */
  
    /**
     * @param queries: the queries
     * @param dict: the words
     * @return: return the queries' answer
     */
    public int[] letterCombinationsII(String[] queries, String[] dict) {
        // write your code here
        Trie trie = new Trie();
        for (String keyword : dict) {
            trie.insert(keyword);
        }

        int size = queries.length;
        int[] results = new int[size];

        int index = 0;
        for (String digitStr : queries) {
            List<String> prefixWords = getWords(digitStr);
            int count = 0;
            for (String prefixWord : prefixWords) {
                count += trie.getCount(prefixWord);
            }
            results[index++] = count;
        }

        return results;
    }

    // helper class
    class TrieNode {
        TrieNode[] children;
        int count;
        boolean isEndOfWord;

        public TrieNode() {
            children = new TrieNode[26];
            Arrays.fill(children, null);
            count = 0;
            isEndOfWord = false;
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
                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }

                node = node.children[ch - 'a'];
                node.count += 1;
            }

            node.isEndOfWord = true;
        }

        private TrieNode find(String word) {
            if (word == null || word.isEmpty()) {
                return null;
            }

            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return null;
                }

                node = node.children[ch - 'a'];
            }

            return node;
        }

        public int getCount(String prefixWord) {
            TrieNode node = find(prefixWord);
            if (node == null) {
                return 0;
            }

            return node.count;
        }
    }

    // helper methods
    private List<String> getWords(String digitStr) {
        List<String> words = new ArrayList<String>();
        findWords(digitStr, 0, new StringBuilder(), words);

        return words;
    }

    private void findWords(String digitStr, int currentIndex, StringBuilder sb, List<String> words) {
        if (currentIndex == digitStr.length()) {
            if (sb != null || sb.length() > 0) {
                words.add(sb.toString());
            }

            return;
        }

        char digitKey = digitStr.charAt(currentIndex);
        char[] chars = map.get(digitKey);
        for (char ch : chars) {
            sb.append(ch);
            findWords(digitStr, currentIndex + 1, sb, words);
            sb.setLength(sb.length() - 1);
        }
    }

}

// solution-2: using 2 map: (1) build a map for map<key = ['a'-'z'], value = ['1'-'9']>, which is for converting character-keyword of dic into digital-keyword string; (2)build a counter map for map<key = each "query-string", value = counter of each prefix digital string[Integer tyep]>
public class Solution {

    private static final Map<Character, Integer> map;

    static {
        map = new HashMap<>();
        map.put('a', 2); map.put('b', 2); map.put('c', 2);
        map.put('d', 3); map.put('e', 3); map.put('f', 3);
        map.put('g', 4); map.put('h', 4); map.put('i', 4);
        map.put('j', 5); map.put('k', 5); map.put('l', 5);
        map.put('m', 6); map.put('n', 6); map.put('o', 6);
        map.put('p', 7); map.put('q', 7); map.put('r', 7); map.put('s', 7);
        map.put('t', 8); map.put('u', 8); map.put('v', 8);
        map.put('w', 9); map.put('x', 9); map.put('y', 9); map.put('z', 9);
    }

    /**
     * @param queries: the queries
     * @param dict: the words
     * @return: return the queries' answer
     */
    public int[] letterCombinationsII(String[] queries, String[] dict) {
        // write your code here
        Map<String, Integer> counterMap = new HashMap<>();
        int size = queries.length;
        int[] results = new int[size];

        for (String digitStr : queries) {
            counterMap.put(digitStr, 0);
        }

        for (String keyword: dict) {
            String keyDigitStr = "";
            //System.out.println("keyword = " + keyword);
            for (char ch : keyword.toCharArray()) {
                keyDigitStr += map.get(ch);

                counterMap.put(keyDigitStr, counterMap.getOrDefault(keyDigitStr, 0) + 1);
            }
            //System.out.println("keyword = " + keyword + ", keyDigitStr= " + keyDigitStr + ", value = " + counterMap.get(keyDigitStr));
        }

        int index = 0;
        for(String digitStr : queries) {
            results[index ++] = counterMap.get(digitStr);
        }

        return results;
    }
}

// solution-3: convert keyword inside of dic into all digit string keywords, build Map<Character, Integer> for accomdate those reversed map, build a digit character trie to get the counter
public class Solution {

    private static final Map<Character, Integer> map;

    static {
        map = new HashMap<>();
        map.put('a', 2); map.put('b', 2); map.put('c', 2);
        map.put('d', 3); map.put('e', 3); map.put('f', 3);
        map.put('g', 4); map.put('h', 4); map.put('i', 4);
        map.put('j', 5); map.put('k', 5); map.put('l', 5);
        map.put('m', 6); map.put('n', 6); map.put('o', 6);
        map.put('p', 7); map.put('q', 7); map.put('r', 7); map.put('s', 7);
        map.put('t', 8); map.put('u', 8); map.put('v', 8);
        map.put('w', 9); map.put('x', 9); map.put('y', 9); map.put('z', 9);
    }

    /**
     * @param queries: the queries
     * @param dict: the words
     * @return: return the queries' answer
     */
    public int[] letterCombinationsII(String[] queries, String[] dict) {
        // write your code here
        Map<String, Integer> counterMap = new HashMap<>();
        int size = queries.length;
        int[] results = new int[size];

        Trie trie = new Trie();

        for (String keyword: dict) {
            String keyDigitStr = "";
            for (char ch : keyword.toCharArray()) {
                keyDigitStr += map.get(ch);
            }

            trie.insert(keyDigitStr);
        }

        int index = 0;
        for(String digitStr : queries) {
            results[index ++] = trie.getCount(digitStr);
        }

        return results;
    }

    // helper class
    class TrieNode {
        TrieNode[] children;
        boolean isQueryDigitStr;
        int count;

        public TrieNode() {
            children = new TrieNode[10];// for digital node other than alphabetic node
            Arrays.fill(children, null);
            isQueryDigitStr = false;
            count = 0;
        }
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String digitStr) {
            TrieNode node = root;
            for (char digitKey : digitStr.toCharArray()) {
                if (node.children[digitKey - '1'] == null) {
                    node.children[digitKey - '1'] = new TrieNode();
                }

                node = node.children[digitKey - '1'];
                node.count += 1;
            }

            node.isQueryDigitStr = true;
        }

        private TrieNode find(String digitPrefixStr) {
            TrieNode node = root;
            for (char digitKey : digitPrefixStr.toCharArray()) {
                if (node.children[digitKey - '1'] == null) {
                    return null;
                }

                node = node.children[digitKey - '1'];
            }

            return node;
        }

        public int getCount(String digitStr) {
            TrieNode node = find(digitStr);
            if (node == null) {
                return 0;
            }

            return node.count;
        }
    }
}
