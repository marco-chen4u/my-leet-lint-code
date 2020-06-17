/***
* LeetCode 151. Reverse Words in a String
Given an input string, reverse the string word by word.

Example 1:
    Input: "the sky is blue"
    Output: "blue is sky the"

Example 2:
    Input: "  hello world!  "
    Output: "world! hello"
    Explanation: Your reversed string should not contain leading or trailing spaces.

Example 3:
    Input: "a good   example"
    Output: "example good a"
    Explanation: You need to reduce multiple spaces between two words to a single space in the reversed string.

Note:
    A word is defined as a sequence of non-space characters.
    Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
    You need to reduce multiple spaces between two words to a single space in the reversed string.

Follow up:
    For C programmers, try to solve it in-place in O(1) extra space.
***/
//version-1
class Solution {
    private final String SEPERATOR = " ";
    
    public String reverseWords(String s) {
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
        
        int size = tokenList.size();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (String token : tokenList) {
            sb.append(token);
            index++;
            
            if (index != size) {
                sb.append(SEPERATOR);
            }
        }
        
        return sb.toString();
    }
}

//version-2
class Solution {
    private final String SEPERATOR = " ";
    
    public String reverseWords(String s) {
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
