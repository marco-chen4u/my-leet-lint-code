/***
* LintCode 152. Combinations
Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

Example
    Given n = 4 and k = 2, a solution is:
        [
            [2,4],
            [3,4],
            [2,3],
            [1,2],
            [1,3],
            [1,4]
        ]
Notice
    You don't need to care the order of combinations, but you should make sure the numbers in a combination are sorted.
***/
public class Solution {
	
    /**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> combination = new ArrayList<Integer>();

        // check corner case
        if (n < 1 || k <= 0) {
            return result;
        }

        dfs(n, k, 1, combination, result);

        return result;
    }
	
    // helper method
    private void dfs(int n, int k, int startIndex, List<Integer> combination, List<List<Integer>> result) {
        if (combination.size() == k) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
		
        for (int i = startIndex; i <= n; i++) {
            combination.add(i);
            dfs(n, k, i + 1, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
}
