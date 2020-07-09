/***
* LintCode 1351. Fraction to Recurring Decimal
Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example
    Example1
        Input: numerator = 1, denominator = 2
        Output: "0.5"

    Example2
        Input: numerator = 2, denominator = 3
        Output: "0.(6)"
***/
/*
* value = numerator / denominator;
* so, value = integralValue + fractionalValue, 
* (a)get integral-part of string

* (b)get frational-part of string
    * if not exist (fractional value) {
        return integralValue string part;
    * }
    * else {
    * 	(long)value = fractionalValue;
        Map<Long, Integer> map = keep record of the start index pos of the value
        StringBuilder sb = new StringBuilder();
        while (value != 0) {
            value *= 10;
            sb.append(value / dividend);
	
            value %= dividend;

            if (map.containsKey(value)) {// that means there's reapted value to be calculated
                int indexPos = map.get(value);// get the index position where we insert at the very beginning
                sb.insert(indexPos, "(");
                sb.append(")");
                break;
            }
            map.put(value, sb.length())// record down the new value's starting position index
        }
    * }
* (c) integral-part concates fractional-pert, return result
*/
//version-1
public class Solution {
    /**
     * @param numerator: a integer
     * @param denominator: a integer
     * @return: return a string
     */
    public String fractionToDecimal(int numerator, int denominator) {
        String result = null;
        // check corner case
        if (numerator == 0) {
            return "0";
        }

        //(1)Integeral part 
        String integeralPart = "";
        int integralVal = numerator / denominator;
        int remainder = numerator % denominator;
        integeralPart = String.valueOf(integralVal);

        // if there's no fractional part
        if (remainder == 0) {
            return integeralPart;
        }		

        //(2)Fractional part
        String fractionalPart = ".";
        StringBuilder sb = new StringBuilder(fractionalPart);
        long value = remainder;
        Map<Long, Integer> map = new HashMap<Long, Integer>();// <fraction-part-value, startPos>
        map.put(value, 0);
        while (value != 0) {
            value *= 10;
            sb.append(value / denominator);

            value %= denominator;

            if (map.containsKey(value)) {
                int indexPos = map.get(value);
                // add brakets
                sb.insert(indexPos, "(");
                sb.append(")");

                break;
            }

            map.put(value, sb.length());
        }

        fractionalPart = sb.toString();


        //(3) combine together of integral and fractional parts to the result
        result = integeralPart + fractionalPart;

        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param numerator: a integer
     * @param denominator: a integer
     * @return: return a string
     */
    public String fractionToDecimal(int numerator, int denominator) {
        String result = "";
        // check corner case
        if (numerator == 0) {
            return "0";
        }
        
        // integgral part
        String integralPart = "";
        int quotientVal = numerator / denominator;
        int remainder = numerator % denominator;// remainder
        if (remainder == 0) {
            result = String.valueOf(quotientVal);
            return result;
        }
        
        if (remainder != 0) {
            integralPart = (remainder < 0) ? "-" : "";
            integralPart = integralPart + String.valueOf(quotientVal) + ".";
        }
        
        // fractional part
        String fractionalPart = "";
        long value = remainder;
        Map<Long, Integer> map = new HashMap<Long, Integer>();// record every start index position of the mod
        StringBuilder sb = new StringBuilder();
        
        map.put(value, 0);
        while (value != 0) {
            value = value * 10;
            sb.append(value / denominator);
            value = value % denominator;
            
            if (map.containsKey(value)) {
                int indexPos = map.get(value);
                sb.insert(indexPos, "(");
                sb.append(")");
                
                break;
            }
            
            map.put(value, sb.length());
        }
        
        fractionalPart = sb.toString();
        
        // merge the two parts(intergral and fractional parts)
        result = integralPart + fractionalPart;
        
        return result;
    }
}

//version-3: best solution[leetcode version]
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        String result = "";
        if (numerator == 0) {
            return "0";
        }
        
        String integralPart = "";
        String fractionalPart = "";
        
        if (numerator < 0 ^ denominator < 0) {//either one of two, is negative
            integralPart = "-";
        }
        
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        
        long integralValue = dividend / divisor;
        long remainer = dividend % divisor;
        
        if (remainer == 0) {
            result = integralPart + String.valueOf(integralValue);
            return result;
        }
        
        if (remainer != 0) {
            integralPart += String.valueOf(integralValue) + ".";
        }
        
        StringBuilder sb = new StringBuilder(fractionalPart);
        Map<Long, Integer> map = new HashMap<Long, Integer>();
        
        long value = remainer;
        map.put(value, 0);
        while (value != 0) {
            
            value *= 10;
            sb.append(value / divisor);
            
            value %= divisor;
            
            if (map.containsKey(value)) {
                int pos = map.get(value);
                sb.insert(pos, "(");
                sb.append(")");
                break;
            }            
            
            map.put(value, sb.length());
        }
        
        fractionalPart = sb.toString();
        
        result = integralPart + fractionalPart;
        
        return result;
    }
}
