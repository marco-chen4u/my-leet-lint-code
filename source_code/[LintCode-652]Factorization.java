/***
* LintCode 652. Factorization
A non-negative numbers can be regarded as product of its factors.
Write a function that takes an integer n and return all possible combinations of its factors.

Example1
    Input: 8
    Output: [[2,2,2],[2,4]]
    Explanation:
        8 = 2 x 2 x 2 = 2 x 4

Example2
    Input: 1
    Output: []
Notice
    -Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    -The solution set must not contain duplicate combination.
***/
//version-1: dfs
public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (n <= 2) {
            return result;
        }
        
        List<Integer> combination = new ArrayList<Integer>();
        dfs(result, combination, n, 2);
        
        return result;
    }
    
    // helper method
    private void dfs(List<List<Integer>> result, List<Integer> combination, int n, int index) {
        // check corner case
        if (n == 1) {
            if (combination.size() > 1) {
                result.add(new ArrayList<Integer>(combination));
            }
            
            return;
        }
        
        for (int i = index; i <= n; i++) {
            if (i > n / i) {
                break;
            }
            
            if (n % i == 0) {
                combination.add(i);
                dfs(result, combination, n / i, i);
                combination.remove(combination.size() - 1);
            }
        }
        
        // corner case check if it is a prime number
        if (n >= index) {
            combination.add(n);
            dfs(result, combination, 1, n);
            combination.remove(combination.size() - 1);
        }
    }
}

//version-2: dfs
public class Solution {
    /**
     * @param n: An integer
     * @return: a list of combination
     */
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> results = new ArrayList<>();
        // check corner case
        if (n <= 2) {
            return results;
        }

        List<Integer> combination = new ArrayList<>();
        int startIndex = 2;
        
        dfs(n, startIndex, combination, results);

        return results;
    }

    // helper method
    private void dfs(int currentValue, 
                    int startIndex, 
                    List<Integer> combination,
                    List<List<Integer>> results) {
        if (currentValue == 1) {

            if (combination.size() > 1) {
                results.add(new ArrayList<>(combination));
            }

            return;
        }

        for (int i = startIndex; i <= currentValue; i++) {
            if (i > currentValue / i) {
                break;
            }

            // check if it's divided
            if (currentValue % i != 0) {
                continue;
            }

            combination.add(i);
            dfs(currentValue / i, i, combination, results);
            combination.remove(combination.size() - 1);
        }

        // if all i is not divided, that means it's a prime
        if (currentValue >= startIndex) {
            combination.add(currentValue);
            startIndex = currentValue;
            dfs(1, startIndex, combination, results);
            combination.remove(combination.size() - 1);
        }
    }
}
