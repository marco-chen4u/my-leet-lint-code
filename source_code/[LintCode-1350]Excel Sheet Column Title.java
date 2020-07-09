/***
* LintCode 1350. Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

Example
    Example1
        Input: 28
        Output: "AB"

    Example2
        Input: 29
        Output: "AC"

Notice
    1 -> A
    2 -> B
    3 -> C
     ...
    26 -> Z
    27 -> AA
    28 -> AB 
***/
/*
字符串处理
*/
public class Solution {
    /**
     * @param n: a integer
     * @return: return a string
     */
    public String convertToTitle(int n) {
        String result = "";

        Map<Integer, Character> map = getAlphabetMap();

        while (n > 0) {

            int index = (n > 26) ? (n % 26) : n;
            index += (index == 0)  ? 26 : 0;

            //System.out.println("index = " + index);
            char ch = map.get(index);
            result = ch + result;

            n--;
            n /= 26;
        }

        return result;
    }
	
    // helper mehtod
    private Map<Integer, Character> getAlphabetMap() {
        Map<Integer, Character> map = new HashMap<Integer, Character>();

        char ch = 'A';

        for (int i = 1; i <= 26; i++) {
            map.put(i, ch);

            ch = (char)(ch + 1);
        }

        return map;
    }
}

//version-2:
public class Solution {
    /**
     * @param n: a integer
     * @return: return a string
     */
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            char ch = (char) ((--n) % 26 + 'A');
            sb.insert(0, ch);
            
            n /= 26;
        }
        
        return sb.toString();
    }
}
