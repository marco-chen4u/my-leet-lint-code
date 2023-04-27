/***
* LeetCode 371. Sum of Two Integers
Given two integers a and b, return the sum of the two integers without using the operators + and -.

Example 1
    Input: a = 1, b = 2
    Output: 3

Example 2
    Input: a = 2, b = 3
    Output: 5

Constraints:
    -1000 <= a, b <= 1000

LeetCode link: https://leetcode.com/problems/sum-of-two-integers/
***/
/**
* solution: https://leetcode.com/problems/sum-of-two-integers/solutions/84290/java-simple-easy-understand-solution-with-explanation/
**/

//version-1: Xor for adding, Add for carry value
class Solution {
    public int getSum(int a, int b) {
        while (b != 0) {
            int carry = a & b;
            a = a ^ b;
            b = carry << 1;
        }

        return a;
    }
}

//version-2: recursion
class Solution {
    public int getSum(int a, int b) {
        return b == 0 ? a : getSum(a ^ b, (a & b) << 1);
    }
}
