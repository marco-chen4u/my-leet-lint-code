/***
* LintCode 1385. Lucky Number Eight
8 is the lucky number of Xiaojiu. Xiaojiu wants to know how many numbers in the numbers 1~n contain 8.

Example
Example1
	Input:  n = 20
	Output: 2
	Explanation:
	Only 8,18 contains 8.
Example2
	Input:  n = 100
	Output: 19
	Explanation:
	8,18,28,38,48,58,68,78,80,81,82,83,84,85,86,87,88,89,98 contains 8.
Notice
	1 <= n <= 1000000
***/
public class Solution {
    /**
     * @param n: count lucky numbers from 1 ~ n
     * @return: the numbers of lucky number
     */
    public int luckyNumber(int n) {
        int count = 0;
		
		for (int i = 1; i <= n; i++) {
			count += has8Digit(i) ? 1 : 0;
		}
		
		return count;
    }
	
	// helper
	private boolean has8Digit(int num) {
		int currentDigit = 0;
		
		while (num > 0) {
			currentDigit = num % 10;
			
			if (currentDigit == 8) {
				return true;
			}
			
			num /= 10;
		}
		
		return false;
	}
}