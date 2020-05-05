/***
* LintCode 1363. ZigZag Conversion
Given a string s and an integer numRows. You need to place s like zigzag. 
Then read the 'zigzag' line by line. Return the string you read.

Notice that zigzag extends according to the directions of down->up-right->down->up-right...

|   /|   /|
|  / |  / | ...
| /  | /  | ...
|/   |/   |/

Example
	Example 1:
		Input: "PAYPALISHIRING", numRows = 3
		Output: "PAHNAPLSIIGYIR"
		Explanation: 
			After conversion, we get
			P   A   H   N
			A P L S I I G
			Y   I   R
			Read line by line, the answer is "PAHNAPLSIIGYIR".
	Example 2:
		Input: "PAYPALISHIRING", numRows = 4
		Output: "PINALSIGYAHRPI"
		Explanation: 
			After conversion, we get
			P     I     N
			A   L S   I G
			Y A   H R
			P     I
			Read line by line, the answer is "PINALSIGYAHRPI".
	Example 3:
		Input: "PAYPALISHIRING", numRows = 1
		Output: "PAYPALISHIRING"
		Explanation: 
			After conversion, we get
			PAYPALISHIRING
			Read line by line, the answer is "PAYPALISHIRING".
***/
public class Solution {
    // fields
    private static int TOP;
    private static int BOTTOM;
    
    /**
     * @param s: the given string
     * @param numRows: the number of rows
     * @return: the string read line by line
     */
    public String convert(String s, int numRows) {
        StringBuilder result = new StringBuilder();
        
        // check corner case
        if (s == null || s.length() <= numRows || numRows <= 1) {
            return s;
        }
        
        List<StringBuilder> list = new ArrayList<StringBuilder>();
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        
        int indexPos = 0;
        boolean down = true;
        TOP = 0;
        BOTTOM = numRows - 1;
        for (char ch : s.toCharArray()) {
            list.get(indexPos).append(ch);
            
            if (down) {
                if (indexPos == BOTTOM) {
                    down = false;//turn the next step going up
                    indexPos = BOTTOM - 1;
                }
                else {
                    indexPos++;
                }
            }
            else {
                if (indexPos == TOP) {
                    down = true;//turn the next step going up
                    indexPos = TOP + 1;
                }
                else {
                    indexPos--;
                }
            }
        }
        
        for (StringBuilder content : list) {
            result.append(content.toString());
        }
        
        return result.toString();
    }
}