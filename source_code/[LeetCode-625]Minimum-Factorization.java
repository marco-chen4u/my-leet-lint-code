/***
* LeetCode 625. Minimum Factorization
Given a positive integer num, return the smallest positive integer x whose multiplication of each digit equals num. 
If there is no answer or the answer is not fit in 32-bit signed integer, return 0.

Example 1
    Input: num = 48
    Output: 68

Example 2
    Input: num = 15
    Output: 35

Constraints:
    1 <= num <= 231 - 1

* LintCode link: https://www.lintcode.com/problem/871/
* LeetCode link: https://leetcode.com/problems/minimum-factorization/
***/
/**
the idea is:
    1. use long to check if the answer > Integer.MAX_VALUE
    2. to build the smallest res
        2.1 if we know the res is composed by 5,4,5,2 -> we want let the smallest one occurs higher digit -> 2455
        2.2 So we want try from 9 to 2, the reason why from large to small is for ex: 16 -> correct answer is 44, not 2222..
        2.3 use dfs to build the number.
    3. if a number can not been devided by 2 to 9 like -> 23, return 0 directly.
*/

//version-1: iteration
class Solution {
    public int smallestFactorization(int num) {
        if (num <= 2) {
            return num;
        }

        List<Integer> digits = new ArrayList<>();

        for (int i = 9; i >= 2; i--) {
            while (num % i == 0) {
                digits.add(i);
                num /= i;
            }
        }

        System.out.println("num = " + num);
        System.out.println("digits.size() = " + digits.size());
        

        if (num != 1) {
            return 0;
        }

        long result = 0;
        for (int i = digits.size() - 1; i >= 0; i--) {
            result = result * 10 + digits.get(i);

            if (result >= Integer.MAX_VALUE) {
                return 0;
            }
        }

        return (int)result;
    }
}

//version-2: dfs
class Solution {
    public int smallestFactorization(int num) {
        return (int)dfs(num);
    }

    private long dfs(int num) {
        if (num < 10) {
            return num;
        }

        long result = 0;
        for (int i = 9; i >= 2; i--) {
            if (num % i != 0) {
                continue;
            }

            int nextResult = (int)dfs(num / i);
            if (nextResult == 0) {
                return 0;
            }

            result = nextResult * 10L + i;

            if (result > Integer.MAX_VALUE) {
                return 0;
            }

            break;
        }

        return result;
    }
}
