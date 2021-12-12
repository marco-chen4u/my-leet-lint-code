/*** LintCode 425. Letter Combinations of a Phone Number
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
//version-1: DFS
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

//version-2: DFS
public class Solution {
    private static Map<Character, char[]> keyMap = new HashMap<>();

    static {
        keyMap.put('2', new char[] {'a', 'b', 'c'});
        keyMap.put('3', new char[] {'d', 'e', 'f'});
        keyMap.put('4', new char[] {'g', 'h', 'i'});
        keyMap.put('5', new char[] {'j', 'k', 'l'});
        keyMap.put('6', new char[] {'m', 'n', 'o'});
        keyMap.put('7', new char[] {'p', 'q', 'r', 's'});
        keyMap.put('8', new char[] {'t', 'u', 'v'});
        keyMap.put('9', new char[] {'w', 'x', 'y', 'z'});
    }

    /**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        // check corner cases
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        // normal case
        StringBuilder combination = new StringBuilder();
        int size = digits.length();

        dfs(digits, size, 0, combination, result);

        // return
        return result;
    }

    // helper method
    private void dfs(String digits, int size, int rowIndex, StringBuilder combination, List<String> result) {
        // check corner cases
        if (combination.length() == size) {
            result.add(combination.toString());
            return;
        }

        // normal case
        char[] chars = keyMap.get(digits.charAt(rowIndex));
        Set<Character> set = new HashSet<>();
        for (char ch : chars) {
            // corner case-1
            if (set.contains(ch)) {
                continue;
            }

            // regular case
            set.add(ch);
            combination.append(ch);
            dfs(digits, size, rowIndex + 1, combination, result);
            combination.setLength(combination.length() - 1);
        }
    }
}
