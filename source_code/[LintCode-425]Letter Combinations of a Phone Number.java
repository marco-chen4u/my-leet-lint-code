/*** LintCode 425.Letter Combinations of a Phone Number
Given a digit string excluded '0' and '1', 
return all possible letter combinations that the number could represent.
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
	Example 1:
	Input: "23"
	Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
	Explanation: 
		'2' could be 'a', 'b' or 'c'
		'3' could be 'd', 'e' or 'f'

Example 2:
	Input: "5"
	Output: ["j", "k", "l"]
Notice
	Although the answer above is in lexicographical order, your answer could be in any order you want.
***/
public class Solution {
    
    // helper methods
    private boolean isValidDigits(String digits) {
        for (char ch: digits.toCharArray()) {
            if (!Character.isDigit(ch)) {
                return false;
            }
        }
        
        return true;
    }
    
    private Map<Character, char[]> getDigitMap() {
        Map<Character, char[]> map = new HashMap<Character, char[]>();
        map.put('2', new char[] {'A', 'B', 'C'});
        map.put('3', new char[] {'D', 'E', 'F'});
        map.put('4', new char[] {'G', 'H', 'I'});
        map.put('5', new char[] {'J', 'K', 'L'});
        map.put('6', new char[] {'M', 'N', 'O'});
        map.put('7', new char[] {'P', 'Q', 'R', 'S'});
        map.put('8', new char[] {'T', 'U', 'V'});
        map.put('9', new char[] {'W', 'X', 'Y', 'Z'});
        
        return map;
    }
    
    private void findLetterCombinations(List<String> result, 
                                        StringBuilder combination, 
                                        String digits, 
                                        Map<Character, char[]> map,
                                        int digitIndex) {
        if (combination.length() == digits.length()) {
            result.add(new String(combination).toLowerCase());
            return;
        }
        
        Character digit = digits.charAt(digitIndex);
        for(char ch : map.get(digit)) {
            combination.append(ch);
            findLetterCombinations(result, combination, digits, map, digitIndex + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
        
    }
    
    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        
        // check corner case
        if (digits == null || digits.length() == 0 || !isValidDigits(digits)) {
            return result;
        }
        
        // initialize digit map
        Map<Character, char[]> map = getDigitMap();
        StringBuilder combination = new StringBuilder();
        
        
        findLetterCombinations(result, combination, digits, map, 0);
        
        return result;
    }
}