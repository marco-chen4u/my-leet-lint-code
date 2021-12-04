/***
* LintCode 371. Print Numbers by Recursion
Print numbers from 1 to the largest number with N digits by recursion.
It's pretty easy to do recursion like:
    recursion(i) {
        if i > largest number:
            return
        results.add(i)
        recursion(i + 1)
    }
however this cost a lot of recursion memory as the recursion depth maybe very large. 
Can you do it in another way to recursive with at most N depth?


Example 1:
    Input : N = 1 
    Output :[1,2,3,4,5,6,7,8,9]

Example 2:
    Input : N = 2 
    Output :[1,2,3,4,5,6,7,8,9,10,11,12,...,99]

Challenge
    Do it in recursion, not for-loop.
***/
//version-1:DFS
public class Solution {
    /**
     * @param n: An integer
     * @return: An array storing 1 to the largest number with n digits.
     */
    public List<Integer> numbersByRecursion(int n) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (n == 0) {
            return result;
        }

        // normal case
        result = numbersByRecursion(n - 1);

        int start = (int)Math.pow(10, n - 1);// 10
        int end = (int)Math.pow(10, n) - 1;  // 99
        for (int i = start; i <= end; i++) {
            result.add(i);
        }

        return result;
    }
}
