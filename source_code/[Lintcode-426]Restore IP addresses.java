/***
* Lintcode 426. Restore IP addresses
Given String containing only digits, restore it by returning all possible valid IP addresses.
(Your task is to add three dots to this string to make it a valid IP address, return all possible IP address.)

Example 
    Example 1:
        Input: "25525511135"
        Output: ["255.255.11.135", "255.255.111.35"]
        Explanation: ["255.255.111.35", "255.255.11.135"] will be accepted as well.
    Example 2:
        Input: "1116512311"
        Output: ["11.165.123.11","111.65.123.11"]

Notice
    You can return the valid IP address in any order.
***/
public class Solution {
    // fields
    private final String SEPERATOR = ".";
    private final String SPLIT_SEPERATOR = "\\.";
	
    private int n;// the length of input string
	
    /**
    * @param s : the IP string
    * @return: All possible IP addresses
    */
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (s == null || s.length() <=3 || s.length() >= 13) {
            return result;
        }
        n = s.length();
        helper(result, new StringBuilder(), s, 0);
		
        return result;
    }
	
    // helper methods
    private void helper(List<String> result, StringBuilder buffer, String str, int startIndex) {
        // check corner case
        if (startIndex == n) {
            if (isValidAddress(buffer)) {
                result.add(buffer.substring(0, buffer.length() - 1));
            }
			
            return;
        }
		
        for (int i = startIndex + 1; i <= startIndex + 3 && i <= n; i++) {
            String token = str.substring(startIndex, i);
            int tokenSize = token.length();
			
            if (!isValid(token)) {
                   continue;
            }
			
            buffer.append(token).append(SEPERATOR);
            helper(result, buffer, str, i);
            buffer.setLength(buffer.length() - tokenSize - 1);
			
        }
    }
	
    private boolean isValid(String token) {
        if (token == null || token.length() == 0) {
            return false;
        }
		
        int tokenSize = token.length();
        char firstDigit = token.charAt(0);
		
        if (tokenSize > 1 && firstDigit == '0') {
            return false;
        }
		
        int value = Integer.valueOf(token);
        if (value < 0 || value > 255) {
            return false;
        }
		
        return true;
    }
	
    private boolean isValidAddress(StringBuilder buffer) {
        // check corner case
        if (buffer == null || buffer.length() == 0) {
            return false;
        }
		
        String content = buffer.toString();
        String[] tokens = content.split(SPLIT_SEPERATOR);
        if (tokens.length != 4) {
            return false;
        }
		
        for (String token : tokens) {
            if (!isValid(token)) {
                return false;
            }
        }
		
        return true;
    }
}
