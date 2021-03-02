/***
* LintCode-53. Reverse Words in a String
Given an input string, reverse the string word by word.

Example
Example 1:
    Input:  "the sky is blue"
    Output:  "blue is sky the"
	
    Explanation: 
    return a reverse the string word by word.

Example 2:
    Input:  "hello world"
    Output:  "world hello"
	
    Explanation: 
    return a reverse the string word by word.

Clarification
    -What constitutes a word?
    A sequence of non-space characters constitutes a word and some words have punctuation at the end.
    -Could the input string contain leading or trailing spaces?
    Yes. However, your reversed string should not contain leading or trailing spaces.
    -How about multiple spaces between two words?
    Reduce them to a single space in the reversed string.
***/
//solution-1: List<String> + String.join(SEPERATOR, List)
public class Solution {
    // fields
    private final String SEPERATOR = " ";
    
    /*
     * @param s: A string
     * @return: A string
     */
    public String reverseWords(String s) {
        // check corner case
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        String[] tokens = s.split(SEPERATOR);
        List<String> tokenList = new ArrayList<String>();
        for (String token : tokens) {
            if (token.equals(SEPERATOR) || token.isEmpty()) {
                continue;
            }
            
            tokenList.add(0, token);
        }
        
        String result = String.join(SEPERATOR, tokenList);
        
        return result;
    }
}
