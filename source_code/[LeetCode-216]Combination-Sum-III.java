/***
* LeetCode 216. Combination Sum III
Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:
    All numbers will be positive integers.
    The solution set must not contain duplicate combinations.

Example 1:
    Input: k = 3, n = 7
    Output: [[1,2,4]]

Example 2:
    Input: k = 3, n = 9
    Output: [[1,2,6], [1,3,5], [2,3,4]]

***/
//solution-1: recursion
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (k == 0 || n < 1) {
            return result;
        }
        
        List<Integer> list = new ArrayList<>();
        search(result, list, 1, k, n);
        
        return result;
    }
    
    // helper method
    private void search(List<List<Integer>> result, List<Integer> list, int i, int k, int sum) {
        // check corner cases
        if (sum == 0 && list.size() == k) {
            result.add(new ArrayList<Integer>(list));
            return;
        }
        
        if (sum < 0 || list.size() > k || i > 9) {
            return;
        }
        
        // regular case
        // choose current i
        list.add(i);
        search(result, list, i + 1, k, sum - i);
        list.remove(list.size() - 1);
        
        // not choose current i, choose the next candidate
        search(result, list, i + 1, k, sum);
        
    }
}
