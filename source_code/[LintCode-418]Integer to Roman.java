/***
* LintCode 418. Integer to Roman
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
        Symbol       Value
        -------------------
        I             1
        V             5
        X             10
        L             50
        C             100
        D             500
        M             1000
        -------------------
For example, two is written as II in Roman numeral, just two one's added together. 
Twelve is written as, XII, which is simply X + II. 
The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. 
However, the numeral for four is not IIII. Instead, the number four is written as IV. 
Because the one is before the five we subtract it making four. 
The same principle applies to the number nine, which is written as IX. 
There are six instances where subtraction is used:
    -I can be placed before V (5) and X (10) to make 4 and 9. 
    -X can be placed before L (50) and C (100) to make 40 and 90. 
    -C can be placed before D (500) and M (1000) to make 400 and 900.
    -Given an integer, convert it to a roman numeral. 
      Input is guaranteed to be within the range from 1 to 3999.

Example 1:
    Input: 3
    Output: "III"

Example 2:
    Input: 4
    Output: "IV"

Example 3:
    Input: 9
    Output: "IX"

Example 4:
    Input: 58
    Output: "LVIII"
    Explanation: L = 50, V = 5, III = 3.

Example 5:
    Input: 1994
    Output: "MCMXCIV"
    Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
***/

//version-1: TreeMap(reverse-order) + calculation
public class Solution {
    /**
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        String result = "";
        if (n == 0) {
            return result;
        }

        NavigableMap<Integer, String> intToRomanMap = getIntToRomanMap();

        for (Map.Entry<Integer, String> entry : intToRomanMap.entrySet()) {
            while (n - entry.getKey() >= 0) {
                result += entry.getValue();

                n -= entry.getKey();
            }
        }

        return result;
    }

    // helper method
    private NavigableMap<Integer, String> getIntToRomanMap() {
        NavigableMap<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        return map;
    }
}

//version-2: Map interface(with TreeMap implementation) + reverseOrder initialization
public class Solution {

    // constant
    private static final Map<Integer, String> intToRomanMap = new TreeMap<>(Collections.reverseOrder());

    static {
        intToRomanMap.put(1000, "M");
        intToRomanMap.put(900, "CM");
        intToRomanMap.put(500, "D");
        intToRomanMap.put(400, "CD");
        intToRomanMap.put(100, "C");
        intToRomanMap.put(90, "XC");
        intToRomanMap.put(50, "L");
        intToRomanMap.put(40, "XL");
        intToRomanMap.put(10, "X");
        intToRomanMap.put(9, "IX");
        intToRomanMap.put(5, "V");
        intToRomanMap.put(4, "IV");
        intToRomanMap.put(1, "I");
    }

    /**
     * @param n: The integer
     * @return: Roman representation
     */
    public String intToRoman(int n) {
        String result = "";

        for (Map.Entry<Integer, String> entry : intToRomanMap.entrySet()) {
            int key = entry.getKey();
            String romanValue = entry.getValue();

            while (n - key >= 0) {
                n -= key;
                result += romanValue;
            }
        }

        return result;
    }
}
