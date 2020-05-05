/***
* LintCode 664. Counting Bits
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example
	Example1
		Input: 5
		Output: [0,1,1,2,1,2]
		Explanation:
			The binary representation of 0~5 is:
			000
			001
			010
			011
			100
			101
			the count of "1" in each number is: 0,1,1,2,1,2

	Example2
		Input: 3
		Output: [0,1,1,2]

Challenge
	1.It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
	2.Space complexity should be O(n).
	3.Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
***/
//version-1: traditional way
public class Solution {
    /**
     * @param num: a non negative integer number
     * @return: an array represent the number of 1's in their binary
     */
    public int[] countBits(int num) {
        // check coenr case
        if (num == 0) {
            return new int[]{0};
        }
        
        int[] result = new int[num + 1];
        
        for (int i = 0; i <= num; i++) {
            result[i] = getBitCount(i);
        }
        
        return result;
    }
    
    // helper method
    private int getBitCount(int num) {
        int count = 0;
        
        while (num > 0) {
            count += num % 2;
            
            num /= 2;
        }
        
        return count;
    }
}

//version-2
public class Solution {
    /**
     * @param num: a non negative integer number
     * @return: an array represent the number of 1's in their binary
     */
    public int[] countBits(int num) {
        // state
        int[] dp = new int[num + 1];
        
        
        // initialize
        Arrays.fill(dp, 0);
        
        // check corner case
        if (num == 0) {
            return dp;
        }
        
        dp[1] = 1;
        
        // check corner case
        if (num == 1) {
            return dp;
        }
        
        for (int i = 2; i <= num; i++) {
            dp[i] = dp[i >> 1] + i % 2;
			/*
					dp[i >> 1],i >> 1表示 i 向右移1位，即相等于整除2， 所以dp[i >> 1]表示i的二进制数中去掉最后一位剩下1的个数,这个子问题。
					
					i % 2 ,表示i的二进制数中最后一位数是为1，如果是1则为1， 否则为0
			*/
        }
        
        return dp;
    }
}