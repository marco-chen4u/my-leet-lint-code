/***
* LintCode 623. K Edit Distance
Given a set of strings which just has lower case letters and a target string, 
output all the strings for each the edit distance with the target no greater than k.

You have the following 3 operations permitted on a word:
    -Insert a character
    -Delete a character
    -Replace a character

Example 1:
    Given words = `["abc", "abd", "abcd", "adc"]` and target = `"ac"`, k = `1`
    Return `["abc", "adc"]`
    Input:
        ["abc", "abd", "abcd", "adc"]
        "ac"
        1
    Output:
        ["abc","adc"]
    Explanation:
        "abc" remove "b"
        "adc" remove "d"

Example 2:
    Input:
        ["acc","abcd","ade","abbcd"]
        "abc"
        2
    Output:
        ["acc","abcd","ade","abbcd"]
    Explanation:
        "acc" turns "c" into "b"
        "abcd" remove "d"
        "ade" turns "d" into "b" turns "e" into "c"
        "abbcd" gets rid of "b" and "d"
***/

public class Solution {
    // field
    int K;
    int n;
    char[] targetCharArray;
    List<String> result;
    
    /**
     * @param words: a set of stirngs
     * @param target: a target string
     * @param k: An integer
     * @return: output all the strings that meet the requirements
     */     
    public List<String> kDistance(String[] words, String target, int k) {
        result = new ArrayList<String>();
        // check corner case
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }
		
        K = k;
        TrieNode root = new TrieNode();
        int i;
        for (i = 0; i < words.length; ++i) {
            TrieNode.Insert(root, words[i]);
        }
        
        targetCharArray = target.toCharArray();
        n = targetCharArray.length;
        // state
        int[] f = new int[n + 1];
        // initialize
        for (i = 0; i <= n; ++i) {
            f[i] = i;
        }
        
        // function
        search(root, f);
		
        return result;
    }
	
    // helper method
    // node is the current TrieNode
    // f[] representss f[Sp][...]
    void search(TrieNode node, int[] f) {
        int[] newf;
        int i;
        if (node.isWord && f[n] <= K) {
            result.add(node.word);
        }
        
        for (int c = 0; c < 26; ++c) {
            if (node.sons[c] == null) {
                continue;
            }
            
            // calc newf
            newf = new int[n + 1];
            // newf[...]: f[Sp + c][....]
            
            // newf[j] = Math.min(Math.min(f[j], newf[j-1]), f[j-1]) + 1;
            for (i = 0; i <= n; ++i) {
                newf[i] = f[i] + 1;// case-1 insert
            }
            
            for (i = 1; i <= n; ++i) {
                newf[i] = Math.min(newf[i], f[i - 1] + 1);// case-2 delete
            }   
            
            for (i = 1; i <= n; ++i) {
                if (targetCharArray[i - 1] - 'a' == c) {
                    newf[i] = Math.min(newf[i], f[i - 1]);// case-4 (current character == targetCharArray[i - 1])
                }
                
                newf[i] = Math.min(newf[i - 1] + 1, newf[i]);// case-3 replace
            }
            
            search(node.sons[c], newf);
        }
    }
}

// helper class
class TrieNode{
    // fields
    public TrieNode[] sons;
    public boolean isWord;
    public String word;
    
    // constructor
    public TrieNode() {
        int i;
        sons = new TrieNode[26];
        for (i = 0; i < 26; ++i) {
            sons[i] = null;
        }
        
        isWord = false;
    }
    
    // method
    static public void Insert(TrieNode node, String word) {
        int i;
        char[] s = word.toCharArray();
        for (i = 0; i < s.length; ++i) {
            int c = s[i] - 'a';
            if (node.sons[c] == null) {
                node.sons[c] = new TrieNode();
            }
            
            node = node.sons[c];
        }
        
        node.isWord = true;
        node.word = word;
    }
}
