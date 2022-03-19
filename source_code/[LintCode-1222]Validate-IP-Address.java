/**
* LintCode 1222 · Validate IP Address
Write a function to check whether an input string is a valid IPv4 address or IPv6 address or neither.

IPv4 addresses are canonically represented in dot-decimal notation, which consists of four decimal numbers, 
each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;

Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is invalid.

IPv6 addresses are represented as eight groups of four hexadecimal digits, each group representing 16 bits. 
The groups are separated by colons (":"). For example, the address 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. 
Also, we could omit some leading zeros among four hexadecimal digits and some low-case characters in the address to upper-case ones, 
so 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper cases).

However, we don't replace a consecutive group of zero value with a single empty group using two consecutive colons ("::") to pursue simplicity. 
For example, 2001:0db8:85a3::8A2E:0370:7334 is an invalid IPv6 address.

Besides, extra leading zeros in the IPv6 is also invalid. For example, the address 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.

Note:
    You may assume there is no extra space or special characters in the input string.

Example 1:
    Input: "172.16.254.1"
    Output: "IPv4"
    Explanation: This is a valid IPv4 address, return "IPv4".

Example 2:
    Input: "2001:0db8:85a3:0:0:8A2E:0370:7334"
    Output: "IPv6"
    Explanation: This is a valid IPv6 address, return "IPv6".

Example 3:
    Input: "256.256.256.256"
    Output: "Neither"
    Explanation: This is neither a IPv4 address nor a IPv6 address.
    
Link: https://www.lintcode.com/problem/1222/

tags: String, Emumeration
**/
//version-1: iteration
public class Solution {
    // constants
    private static final String IP_V6_CHARS = "0123456789ABCDEFabcdef";

    private static final String IP_V4 = "IPv4";
    private static final String IP_V6 = "IPv6";
    private static final String NEITHOR = "Neither";

    /**
     * @param IP: the given IP
     * @return: whether an input string is a valid IPv4 address or IPv6 address or neither
     */
    public String validIPAddress(String IP) {
        // corner case
        if (isEmpty(IP)) {
            return NEITHOR;
        }

        String[] ipV4Tokens = IP.split("\\.");
        if (isValidIPv4(ipV4Tokens, IP)) {
            return IP_V4;
        }
        
        String[] ipV6Tokens = IP.split(":");
        if (isValidIPv6(ipV6Tokens, IP)) {
            return IP_V6;
        }

        return NEITHOR;
    }

    // helper methods
    private boolean isValidIPv4(String[] ipV4Tokens, String ip) {
        if (ipV4Tokens.length != 4 && numberOfToken(ip, '.') != 3) {
            return false;
        }

        for (String token : ipV4Tokens) {
            if (!isValidIPv4Token(token)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidIPv4Token(String token) {
        if (isEmpty(token)) {
            return false;
        }

        int size = token.length();
        char[] chars = token.toCharArray();
        if (size > 1 && chars[0] == '0') {
            return false;
        }

        if (size > 3) {
            return false;
        }

        int num = 0;
        for (char ch : chars) {
            if (!Character.isDigit(ch)) {
                return false;
            }

            num = num * 10 + ch - '0';
        }

        return num > 0 && num < 256;
    }



    private boolean isValidIPv6(String[] ipV6Tokens, String ip) {
        if (ipV6Tokens.length != 8 || numberOfToken(ip, ':') != 7) {
            return false;
        }

        for (String token : ipV6Tokens) {
            if (!isValidIPv6Token(token)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidIPv6Token(String token) {
        if (isEmpty(token)) {
            return false;
        }

        int size = token.length();
        if (size > 4) {
            return false;
        }

        for (char ch : token.toCharArray()) {
            if (IP_V6_CHARS.indexOf(ch) == -1) {
                return false;
            }
        }

        return true;
    }

    private int numberOfToken(String str, char delema) {
        int count = 0;

        for (char ch : str.toCharArray()) {
            count += ch == delema ? 1 : 0;
        }

        return count;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
