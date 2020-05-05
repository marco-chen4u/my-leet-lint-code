/***
 * LintCode 1. A + B Problem
Write a function that add two numbers A and B.

Example
    Example 1:
        Input:  a = 1, b = 2
        Output: 3	
        Explanation: return the result of a + b.
    
    Example 2:
        Input:  a = -1, b = 1
        Output: 0
        Explanation: return the result of a + b.

Challenge
    Of course you can just return a + b to get accepted. 
    But Can you challenge not do it like that?
    (You should not use + or any arithmetic operators.)

Clarification
    Are a and b both 32-bit integers?
    -Yes.
    Can I use bit operation?
    -Sure you can.
Notice
    There is no need to read data from standard input stream. 
    Both parameters are given in function aplusb, you job is to calculate the sum and return it.
***/
//version-1: traditional plus operation
public class Solution {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b 
     */
    public int aplusb(int a, int b) {
        int result = 0;
        int max = Integer.MAX_VALUE;
        
        if (a < max || b < max) {
            result =  a + b;
        }
        else {
            result = max;
        }
        
        return result;
    }
}


//version-2: bitwise plus operation
public class Solution {
    /**
     * @param a: An integer
     * @param b: An integer
     * @return: The sum of a and b 
     */
    public int aplusb(int a, int b) {
        
        //a + b = (a ^ b) + (a & b << 1) 
        
        while (b != 0) {
            /*
            * this operation is the same way as the ALU unit process 
            * inside of the CPU
            */
            int xOr = a ^ b;// calculated result without carry value
            int leftShit = (a & b) << 1;// carry
            
            a = xOr;
            b = leftShit;//until there's no carry value(=0) any more, then stop
        }
        
        
        return a;
    }
}