/**
* LeetCode 273. Integer to English Words
Convert a non-negative integer num to its English words representation.

Example 1:
    Input: num = 123
    Output: "One Hundred Twenty Three"

    Example 2:
    Input: num = 12345
    Output: "Twelve Thousand Three Hundred Forty Five"

    Example 3:
    Input: num = 1234567
    Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Constraints:
    0 <= num <= 2^31 - 1

Link: https://leetcode.com/problems/integer-to-english-words/

Tags: 
    Math, String
**/

class Solution {
    // constants
    private static final String EMPTY = "";
    private static final String ZERO = "Zero";
    private static final String SEPARATOR = " ";
    private static final String BILLION = "Billion";
    private static final String MILLION = "Million";
    private static final String THOUSAND = "Thousand";
    private static final String HUNDRED = "Hundred";
    
    public String numberToWords(int num) {
        if (num == 0) {
            return ZERO;
        }
        
        int billion = num / 1_000_000_000;
        int million = (num - billion * 1_000_000_000) / 1_000_000;
        int thousand = (num - billion * 1_000_000_000 - million * 1_000_000) / 1000;
        int rest = num % 1000;
        
        String result = EMPTY;
        if (billion != 0) {
            result = three(billion) + SEPARATOR + BILLION;
        }
        
        if (million != 0) {
            result += result.isEmpty() ? EMPTY : SEPARATOR;
            
            result += three(million) + SEPARATOR + MILLION;
        }
        
        if (thousand != 0) {
            result += result.isEmpty() ? EMPTY : SEPARATOR;
            
            result += three(thousand) + SEPARATOR + THOUSAND;
        }
        
        if (rest != 0) {
            result += result.isEmpty() ? EMPTY : SEPARATOR;
            
            result += three(rest);
        }
        
        return result;
    }
    
    // helper methods
    private String three(int num) {
        int hundreds = num / 100;
        int rest = num % 100;
        
        String result = EMPTY;
        
        if (hundreds != 0) {
            result = one(hundreds) + SEPARATOR + HUNDRED;
        }
        
        if (rest != 0) {
            result += result.isEmpty() ? EMPTY : SEPARATOR;
            result += two(rest);
        }
        
        return result;
    }
    
    private String two(int num) {
        if (num == 0) {
            return EMPTY;
        }
        
        if (num < 10) {
            return one(num);
        }
        
        if (num < 20) {
            return twoLessThan20(num);
        }
        
        int tens = num / 10;
        int rest = num % 10;
        
        String result = ten(tens);
        if (rest != 0) {
            result += SEPARATOR + one(rest);
        }
        
        return result;
    }
    
    private String ten(int num) {
        switch (num) {
            case 2: return "Twenty";
            case 3: return "Thirty";
            case 4: return "Forty";
            case 5: return "Fifty";
            case 6: return "Sixty";
            case 7: return "Seventy";
            case 8: return "Eighty";
            case 9: return "Ninety";
            default: return EMPTY;
        }                
    }
    
    private String twoLessThan20(int num) {
        switch (num) {
            case 10: return "Ten";
            case 11: return "Eleven";
            case 12: return "Twelve";
            case 13: return "Thirteen";
            case 14: return "Fourteen";
            case 15: return "Fifteen";
            case 16: return "Sixteen";
            case 17: return "Seventeen";
            case 18: return "Eighteen";
            case 19: return "Nineteen";
            default: return EMPTY;
        }
    }
    
    private String one(int num) {
        switch (num) {
            case 1: return "One";
            case 2: return "Two";
            case 3: return "Three";
            case 4: return "Four";
            case 5: return "Five";
            case 6: return "Six";
            case 7: return "Seven";
            case 8: return "Eight";
            case 9: return "Nine";
            default : return EMPTY;
        }
    }
}
