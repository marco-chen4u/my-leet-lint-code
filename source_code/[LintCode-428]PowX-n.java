/***
* LintCode 428. Pow(x, n)
Implement pow(x, n). (n is an integer.)

Example 1:
    Input: x = 9.88023, n = 3
    Output: 964.498

Example 2:
    Input: x = 2.1, n = 3
    Output: 9.261

Example 3:
    Input: x = 1, n = 0
    Output: 1

Challenge
    O(logn) time

Notice
    You don't need to care about the precision of your answer, it's acceptable if the expected answer and your answer 's difference is smaller than 1e-3.
***/
//version-1
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, long n) {
        double result = 1;
        if (x == 0 || x < 1e-8) {
            return 0;
        }

        if (n < 0) {
            x = 1 / x;
            n = -1 * n;
        }

        double value = x;
        while (n != 0) {
            if (n % 2 == 1) {
                result = result * value;
            }

            value = value * value;
            n = n / 2;
        }

        return result;
    }
}

//version-2
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        double result = 1;
        if (x < 1e-8) {
            return 0;
        }

        boolean isNegative = (n < 0) ? true : false;
        if (isNegative) {
            x = 1 / x;
            n = - (n + 1); // avoid overflow when n = Integer.MIN_VALUE, which means, the -original and then - 1
        }

        double value = x;
        while (n != 0) {
            if (n % 2 == 1) {
                result = result * value;
            }

            value = value * value;
            n = n / 2;
        }

        if (isNegative) {
            result = result * x;// when turn n into non-negitive value, there's addtion substraction by 1, so need to restore back
        }

        return result;
    }
}

//version-3: recursion
public class Solution {
    /**
     * @param x: the base number
     * @param n: the power number
     * @return: the result
     */
    public double myPow(double x, int n) {
        // write your code here
        if (x == 0 || Math.abs(x) < 1e-4) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            x = 1 / x;
            n = (n != Integer.MIN_VALUE) ? -(n) : -(n + 1);
        }

        if (n % 2 == 0) {
            return myPow(x * x, n / 2);
        }

        else {
            return x * myPow(x, n - 1);
        }

    }
}
