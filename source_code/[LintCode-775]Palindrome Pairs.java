/***
* LintCode 775. Palindrome Pairs
Given a list of unique words, find all pairs of** distinct** indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.

Example
	Example 1:
		Input:
			["bat", "tab", "cat"]
		Output:
			[[0, 1], [1, 0]]
		Explanation:
			The palindromes are `["battab", "tabbat"]`

	Example 2:
		Input:
		[	"abcd", "dcba", "lls", "s", "sssll"]
		Output:
			[[0, 1], [1, 0], [3, 2], [2, 4]]
		Explanation:
			The palindromes are `["dcbaabcd", "abcddcba", "slls", "llssssll"]`
***/
/*
* (1)构造TrieNode根结点，节点中，存着，待处理的words中每一个word的反序字典序信息，如果在Trie的单词insert（）处理中，发现某个其前缀存在回文串，则把它记录下来。完成所有单词的反序字典序的建立。
* (2)进行搜素，根据每一个单词，结合已经建立好的每个单词的反序字典序，进行匹配查找处理，如果发现有适合配对的反序回文串存在，则把其所在的postion index跟当前所被匹配查找单词的所在数组的下标位置，进行配对，放置入到result中。
* (3)完成搜索，返回结果result
*/
public class Solution {	
	// fields
	private TrieNode root;
	private final int DEFAULT_VALUE = -1;
	
    /**
     * @param words: a list of unique words
     * @return: all pairs of distinct indices
     */
    public List<List<Integer>> palindromePairs(String[] words) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		
		// check corner cases
		if (words == null || words.length == 0) {
			return result;
		}
		
		root = new TrieNode();
		int size = words.length;
		for (int i = 0; i < size; i++) {
			insert(words[i], i);
		}
		
		for (int i = 0; i < size; i++) {
			search(result, words[i], i);
		}
		
		return result;
    }
	
	// helper methods
	private void search(List<List<Integer>> result, String word, int currentIndex) {
		int size = word.length();
		TrieNode node = root;
		for (int i = 0; i < size; i++) {
			if (node.isEndOfWord && 
				node.index != currentIndex && // the prefix of word[substring(0, i+ 1)] has found one reverse pair word
				isPalindrome(word, i, size - 1)) {// and the rest of this substring is also palindromic
				List<Integer> list = Arrays.asList(currentIndex, node.index);/*new ArrayList<Integer>();
				list.add(currentIndex);
				list.add(node.index);*/
				result.add(list);
			}
			
			int index = word.charAt(i) - 'a';
			if (node.children[index] == null) {
				return;
			}
			
			node = node.children[index];
		}
		
		for (int i : node.positions) {
		    if (i == currentIndex) {
		        continue;
		    }
		    
			List<Integer> list = Arrays.asList(currentIndex, i);
			result.add(list);
		}
	}
	
	private void insert(String word, int index) {
		TrieNode current = root;
		
		int size = word.length();
		
		for (int i = size - 1; i >= 0; i--) {
			if (isPalindrome(word, 0, i)) {
				current.positions.add(index);
			}
			
			int k = word.charAt(i) - 'a';
			if (current.children[k] == null) {
				current.children[k] = new TrieNode();
			}
			
			current = current.children[k];
		}
		
		current.isEndOfWord = true;
		current.positions.add(index);
		current.index = index;
	}
	
	private boolean isPalindrome(String word, int left, int right) {
		if (left > right) {
			return false;
		}
		
		while (left < right) {
			if (word.charAt(left) != word.charAt(right)) {
				return false;
			}
			
			left++;
			right--;
		}		
		
		return true;
	}
}

// helper class
class TrieNode {
	// fields
	TrieNode[] children;
	int index;
	List<Integer> positions;
	boolean isEndOfWord;
	String word;
	
	// constructor
	public TrieNode() {
		this.children = new TrieNode[26];
		this.index = -1;
		this.isEndOfWord = false;
		this.word = null;
		this.positions = new ArrayList<Integer>();
	}
}

/*
test cases
(1)
["bat", "tab", "cat"]
(2)
["abcd", "dcba", "lls", "s", "sssll"]
(3)
["abcd", "dcba", "lls", "s", "sssll", ""]
(4)
["bbbbbabbabbbb","baabbaa","bbab","bbbabbaaab","abababbbbbab","abb","baaaabbb","babbaaaba","aab","aaaab","baabbbbabbaaaba","baaab","abbbab","abaabbbabbabba","aa","aabbbaabba","aaabbbbbaaabbbb","bbaaaaba","ababaaa","aaaaa","aaaaabbbbaaaba","abbabbbaabbaabbb","bbaba","aaaaabbbabbbbaaaab","abbbaa","bbbabbaaa","bbbaaabaabbbaaaaabaa","aaaabbabb","ababbababbbab","aaaaababaababbbabaaa","ba","bbbbababbbabab","baaaba","aababbaaabbb","aabbaaabbabaaababaab","abbbb","babaabaaababb","bbbbabaaaab","babbbbb","babaaba","aaba","abababba","a","bb","abaaab","babbabaababbabaaba","aaaaaababbbabaaabaa","baabaaabb","b","bbaaaabbb","abaaaaabaabbbaa","ab","bababaaaba","aabababb","ababaabbaababba","bbb","ababbaabababbbbbabb","bbbbb","abbbbaabaaaabb","baba","bbaabbabaaababaabbaa","bbaabaabbabbaab","bbbaabbab","babbbbbaaaaabaa","abbbbbbabbbabb","abaa","bbbbaababaab","abaaababa","aaaababaaababbaaba","bbabbbabbbbbbaab","abbabbabaabbabbbba","abbbbbaabbbaaabaaaa","bbaabababb","aaabababaabbaaaaaaab","ababbaabaaababb","abbbbabaaabbaaabbab","aababababbabaaa","baabbaabbbaaaaaa","bbbbbbbabbabbbbbb","bbbabbabbabbabaabba","babbbbabaaaabbabaab","baabaabaabababaaabba","bbaaaabbbbabbbaaaa","aaaaabaabaa","bbabaaabbbabaa","baaabaaaaaab","ababbbbbbbabaaaba","abbbabaababbbbbaaa","baaaaaabab","aabbabba","baaabbaabbbbaba","aaaaabba","babaaabbba","bbbbab","bbbbaabbaabab","baa","baababaa","abbbbb","babbaa","abbbabbaa"]

*/