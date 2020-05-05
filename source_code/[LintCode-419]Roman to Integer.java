/***
* LintCode 419. Roman to Integer
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
	-I can be placed before V (5) and X (10) to make 4 and 9. 
	-X can be placed before L (50) and C (100) to make 40 and 90. 
	-C can be placed before D (500) and M (1000) to make 400 and 900.
Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.

Example
	Example 1:
		Input: "III"
		Output: 3
	Example 2:
		Input: "IV"
		Output: 4
	Example 3:
		Input: "IX"
		Output: 9
	Example 4:
		Input: "LVIII"
		Output: 58
		Explanation: L = 50, V= 5, III = 3.
	Example 5:
		Input: "MCMXCIV"
		Output: 1994
		Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

***/
/*
* 字符串处理，根据罗马数字的计数规则（IV = 4，即后继字符值比当前字符值大， 则俩字符的总值就进行相减）, 模拟运算即可.
*/
public class Solution {
    /**
     * @param s: Roman representation
     * @return: an integer
     */
    public int romanToInt(String s) {
        int result = 0;
        if (s == null || s.length() == 0) {
            return result;
        }
        
        int size = s.length();
        int lastPos = size - 1;
        
        char[] romanChars = s.toCharArray();
        Map<Character, Integer> romanToIntValueMap = getRomanToIntValueMap();
        
        result += romanToIntValueMap.get(romanChars[lastPos]);
        for (int i = lastPos - 1; i >= 0; i--) {
            int currentPosValue = romanToIntValueMap.get(romanChars[i]);
            int nextPosValue = romanToIntValueMap.get(romanChars[i + 1]);
            
            if (currentPosValue >= nextPosValue) {
                result += currentPosValue;
            }
            else {
                result -= currentPosValue;
            }
        }
        
        return result;
    }
    
    // helper method
    private Map<Character, Integer> getRomanToIntValueMap() {
        Map<Character, Integer> result = new HashMap<Character, Integer>();
        
        result.put('M', 1000);
        result.put('D', 500);
        result.put('C', 100);
        result.put('L', 50);
        result.put('X', 10);
        result.put('V', 5);
        result.put('I', 1);
        
        return result;
    }
}
