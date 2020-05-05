/***
* LintCode 411. Gray Code
The gray code is a binary numeral system where two successive values differ in only one bit.
Given a non-negative integer n representing the total number of bits in the code, find the sequence of gray code. 
A gray code sequence must begin with 0 and with cover all 2n integers.

Example
	Example 1:
		Input: 1
		Output: [0, 1]
	Example 2:
	Input: 2
		Output: [0, 1, 3, 2]
		Explanation:
		  0 - 00
		  1 - 01
		  3 - 11
		  2 - 10
  
Challenge
	O(2n) time.

Notice
	For a given n, a gray code sequence is not uniquely defined.
	When n=2, both [0,1,3,2] and [0,2,3,1] are valid gray code sequence according to the above definition.
***/
/*
* Gray code :
		  0 - 00
		  1 - 01
		  3 - 11
		  2 - 10
		  
   function : GrayCode(i) = i ^ (i/2)
   for more information : https://en.wikipedia.org/wiki/Gray_code
*/
public class Solution {
    /**
     * @param n: a number
     * @return: Gray code
     */
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        
        if (n < 0) {
            return result;
        }
        
        int count = 1 << n;// the number of gray code, where hold the gray code space
        for (int i = 0; i < count; i++) {
            result.add(getGrayCode(i));
        }
        
        return result;
    }
    
    // helper method
    private int getGrayCode(int value) {
        return value ^ (value / 2);
    }
}