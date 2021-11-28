/***
* LintCode. 730  Sum of All Subsets
Given a number n, we need to find the sum of all the elements from all possible subsets of a set formed by first n natural numbers. 

Example 1:
    Input : n = 2
    Output : 6
    Explanation : 
        Possible subsets are {{1}, {2}, {1, 2}}. Sum of elements in subsets is 1 + 2 + 1 + 2 = 6
        
Example 2:
    Input : n = 3
    Output : 24
    Explanation : 
        Possible subsets are {{1}, {2}, {3}, {1, 2}, {1, 3}, {2, 3}, {1, 2, 3}}
        Sum of subsets is : 
            1 + 2 + 3 + (1 + 2) + (1 + 3) + (2 + 3) + (1 + 2 + 3) = 24
***/
//version-1: dfs
public class Solution {

    private int result;

    /**
     * @param n: the given number
     * @return: Sum of elements in subsets
     */
    public int subSum(int n) {
        if (n <= 1) {
            return n;
        }

        // normal case
        // initialize
        result = 0;

        dfs(new ArrayList<Integer>(), 1, n);

        return result;
    }

    // helper methods
    private void dfs(List<Integer> path, int start, int end) {
        if (start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            path.add(i);
            
            result += getSum(path);
            dfs(path, i + 1, end);

            path.remove(path.size() - 1);
        }
    }

    private int getSum(List<Integer> path) {
        int sum = 0;

        for (int value : path) {
            sum += value;
        }

        return sum;
    }
}
