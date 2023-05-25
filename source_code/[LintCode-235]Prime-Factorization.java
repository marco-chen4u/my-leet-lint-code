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
                num /= i; /* keep in mind that this num has change in this loop, so the upper bound of the look has also changed */
                result.add(i);
            }
        }
        
        if (num != 1) {
            result.add(num);
        }
        
        return result;
    }
}
