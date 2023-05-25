/***
* LintCode 235. Prime Factorization
Prime factorize a given integer.

Example 1
    Input: 10
    Output: [2, 5]

Example 2
    Input: 660
    Output: [2, 2, 3, 5, 11]

LintCode link: https://www.lintcode.com/problem/235/
***/
//version-1: need to understand the iterate scope of a prime product for a number
public class Solution {
    /**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case 
        if (num <= 2) {
            return result;
        }
        
        //int upperLimit = (int)Math.sqrt(num);
        int i = 2;
        for (; i * i <= num; i++) {
            while (num % i == 0) {
                num /= i;
                result.add(i);
            }
        }
        
        if (num != 1) {
            result.add(num);
        }
        
        return result;
    }
}

//version-2: need to understand the iterate scope of a prime product for a number, and what is the optimization for prime number check.
public class Solution {
    /**
     * @param num: An integer
     * @return: an integer array
     */
    public List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> values = new ArrayList<>();

        for (int i = 2; i <= num; i++) {
            if (!isPrime(i)) {
                continue;
            }

            if (num % i != 0) {
                continue;
            }

            values.add(i);
            num /= i;
        }

        if (num != 1) {
            values.add(num);
        }

        return values;
    }

    private boolean isPrime(int number) {
        if (number == 2) {
            return true;
        }

        for (int i = 2; i <= (int)Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }
}

// version-3: need special alg to check prime value which is the products of num
// to continue
