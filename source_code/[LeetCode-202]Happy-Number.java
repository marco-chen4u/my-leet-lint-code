/***
* LeetCode 202. Happy Number
Write an algorithm to determine if a number n is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Return True if n is a happy number, and False if not.

Example: 
    Input: 19
    Output: true
    Explanation: 
        12 + 92 = 82
        82 + 22 = 68
        62 + 82 = 100
        12 + 02 + 02 = 1
***/
//solution: Math and HashSet
class Solution {
    public boolean isHappy(int n) {
        // check corner cases
        if (n < 1) {
            return false;
        }
        
        if (n == 1) {
            return true;
        }
        
        Set<Integer> calculated = new HashSet<>();
        
        while (n != 1) {
            if (calculated.contains(n)) {
                return false;// not a happy number
            }
            
            calculated.add(n);
            
            n = nextHappyNumberCandidate(n);
        }
        
        return true;// it is a happy number
    }
    
    // helper method
    private int nextHappyNumberCandidate(int n) {
        int sum = 0;
        
        while (n != 0) {
            int remainder = n % 10;
            sum += remainder * remainder;
            
            n /= 10;
        }
        
        return sum;
    }
}
