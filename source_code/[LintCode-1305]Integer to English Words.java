/***
* LintCode 1305. Integer to English Words
Convert a non-negative integer to its english words representation. 
Given input is guaranteed to be less than 2^31 - 1.

Example
	123 -> "One Hundred Twenty Three"
	12345 -> "Twelve Thousand Three Hundred Forty Five"
	1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
***/
public class Solution {
	//fields
	private final String SEPARATOR = " ";
	private final String[] LEVEL1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	private final String[] LEVEL2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private final String LEVEL3 = "Hundred";
	private final String[] LEVEL4 = {"", "Thousand", "Million", "Billion"};
	
	/**
     * @param num: a non-negative integer
     * @return: english words representation
     */
    public String numberToWords(int num) {
		// check corner case
		if (num == 0) {
			return "Zero";
		}
		
		String result = "";
		int commas = 0;
		while (num > 0) {
			
			String prefix = helper(num % 1000);
			
			if (prefix.length() > 0) {
				result = prefix + SEPARATOR + LEVEL4[commas] + SEPARATOR +result;
			}
			
			num /= 1000;
			commas++;
		}
		
		return result.trim();
	}
	
	// helper method
	private String helper(int num) {
		String prefix = "";
		
		if (num < 20) {
			prefix = LEVEL1[num];
		}
		else if (num < 100) {
			prefix = LEVEL2[num/10] + SEPARATOR + LEVEL1[num % 10];
		}
		else {
			prefix = LEVEL1[num/100] + SEPARATOR + LEVEL3 + SEPARATOR + helper(num % 100);
		}
		
		return prefix.trim();
	}
}