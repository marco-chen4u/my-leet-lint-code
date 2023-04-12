/***
* LintCode 845.Greatest Common Divisor
Given two numbers, number a and number b. 
Find the greatest common divisor of the given two numbers.

Example 1
    Input: a = 10, b = 15
    Output: 5
    Explanation:
        10 % 5 == 0
        15 % 5 == 0

Example 2
    Input: a = 15, b = 30
    Output: 15
    Explanation:
        15 % 15 == 0
        30 % 15 == 0
        
LintCode link: https://www.lintcode.com/problem/845/
***/
//version-1: Math + iteration
public class Solution {
    /**
     * @param a: the given number
     * @param b: another number
     * @return: the greatest common divisor of two numbers
     */
    public int gcd(int a, int b) {
        // write your code here
        Set<Integer> divisorsA = getDivisors(a);
        Set<Integer> divisorsB = getDivisors(b);

        int result = 0;
        for (int divisor : divisorsA) {
            if (!divisorsB.contains(divisor)) {
                continue;
            }
            result = Math.max(result, divisor);
        }

        return result;
    }

    private Set<Integer> getDivisors(int num) {
        if (num == 0) {
            return Collections.EMPTY_SET;
        }

        Set<Integer> divisors = new HashSet<>();
        divisors.add(1);
        divisors.add(num);

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i != 0) {
                continue;
            }

            if (i * i == num) {
                divisors.add(i);
                continue;
            }

            divisors.add(i);
            divisors.add(num / i);
        }

        return divisors;
    }
}
