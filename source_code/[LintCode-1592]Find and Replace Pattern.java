/***
* LintCode 1592. Find and Replace Pattern
You have a list of words and a pattern, and you want to know which words in words matches the pattern.
A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.
(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)
Return a list of the words in words that match the given pattern.
You may return the answer in any order.

Example
	Example 1:
		Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
		Output: ["aqq","mee"]
		Explanation: 
			"mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}. 
			"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
			since a and b map to the same letter.
	Example 2:
		Input: words = ["a","b","c"], pattern = "a"
		Output: ["a","b","c"]
		Explanation: 
			All strings match.

Notice
	1 <= words.length <= 50
	1 <= pattern.length = words[i].length <= 20
***/
public class Solution {
    /**
     * @param words: word list
     * @param pattern: pattern string
     * @return: list of matching words
     */
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> result = new ArrayList<String>();
		// check corner case
		if (words == null || words.length == 0 || isEmpty(pattern)) {
			return result;
		}
		
		/**find the encoding of the same rhythm pattern of the word**/
		String patternCode = getEncodingOfRhythm(pattern);
		
		for (String word : words) {
			String code = getEncodingOfRhythm(word);
			
			if (patternCode.equals(code)) {
				result.add(word);
			}
		}
		
		return result;
    }
	
	/***
	* get the encoding of the same rhythm pattern of the word
	* (找字符串相同节奏规律的编码)	*
	***/
	private String getEncodingOfRhythm(String word) {
		String result = null;
		// check corner case
		if (isEmpty(word)) {
			return result;
		}
		
		int size = word.length();
		int label = 0;
		char[] charArray = new char[size];
		int index = 0;
		
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		
		for(char ch : word.toCharArray()) {
			map.putIfAbsent(ch, label++);			
			
			charArray[index++] = (char)(map.get(ch) + '0');
		}
		
		return new String(charArray);
	}
	
	private boolean isEmpty(String str) {
		return str == null || str.length() == 0;
	}
}