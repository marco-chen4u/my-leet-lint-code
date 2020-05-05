/***
* LintCode 180. Binary Representation
Given a (decimal - e.g. 3.72) number that is passed in as a string, return the binary representation that is passed in as a string. If the fractional part of the number can not be represented accurately in binary with at most 32 characters, return ERROR.

Example
	Example 1
		Input: "3.72"
		Output: "ERROR"
		Explanation: (3.72)_{10} = (11.10111000010100011111\cdots)_2(3.72)
		​​​  We can't represent it in 32 characters.

	Example 2
		Input: "3.5"
		OUtput: "11.1"
		Explanation: (3.5)_{10}=(11.1)_2(3.5)

***/
/*
* 把数值分成2部分：整数，和小数。
* 然后分别对整数做binary字符串处理输出，和对小数部分做binary字符串处理输出。
* 整数部分的binary处理是反向(reverse)连接每次整数部分值mod二的数位值， 而小数部分，处理出来[小数值*2,取整数部分(1, 0)的字符，直至小数值*2 =0结束处理]的每个字符则正向连接。
* 如果合并结果长度大于规定值(32), 则输出默认信息("ERROR")，否则正常输出binary字符串结果。
* test case data:
	"17817287.6418609619140625"
	"0.5"
	"3.5"
*/
public class Solution {
    // field
    private final String ERROR = "ERROR";
    private final int LIMIT = 42; // 二进制字符串的最大长度
    private final String DOT = ".";
    /**
     * @param n: Given a decimal number that is passed in as a string
     * @return: A string
     */
    public String binaryRepresentation(String n) {
        String result = ERROR;// default value
        double value = Double.valueOf(n);
        
        int integralValue = (int)value;
        double fractionalValue = value - integralValue;
        
        String binaryOfIntegeralStr = getBinaryOfIntegeral(integralValue);
        if (binaryOfIntegeralStr.length() > LIMIT) {
            return result;
        }
        
        if (fractionalValue == 0) {
            return binaryOfIntegeralStr;
        }
        
        int maxLength = LIMIT - 1 - binaryOfIntegeralStr.length();//including the "."
        
        String binaryOfFractionalStr = getBinaryOfFractional(fractionalValue, maxLength);
        
        if (!binaryOfFractionalStr.equals(ERROR)) {
            result = binaryOfIntegeralStr + DOT + binaryOfFractionalStr;
        }
        
        System.out.println("length of result = " + result.length());
        
        return result;
    }
    
    // helper methods
    private String getBinaryOfIntegeral(int num) {
        String result = "";
        
        // check corner case
        if (num == 0) {
            return "0";
        }
        
        while (num > 0) {
            char value = (char)((num % 2) + '0');// turns it into '0' or '1'
            result = value + result;
            
            num /= 2;
        }
        
        //System.out.println("size of Integral part = " + result.length());
        
        return result;
    }
    
    private String getBinaryOfFractional(double value, int maxLength) {
        //System.out.println("value = " + value);
        //System.out.println("maxLength = " + maxLength);
        String result = "";
        int digitCount = 0;
        
        while (value != 0) {
            // check corner case
            if (digitCount > maxLength) {
                return ERROR;
            }
            
            value *= 2;
            
            int integralValue = (int)value;
            if (integralValue == 1) {
                value -= 1;
                result = result + "1";
            }
            else {
                result = result + "0";
            }
            
            digitCount++;
        }
        
        //System.out.println("size of Fractional part = " + result.length());
        
        return result;
    }
}