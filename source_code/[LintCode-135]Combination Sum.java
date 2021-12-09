/***
 * LintCode 135. Combination Sum
Given a set of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.
The same repeated number may be chosen from C unlimited number of times.

Example
    Given candidate set [2,3,6,7] and target 7, a solution set is:
        [7]
        [2, 2, 3]
Notice
    All numbers (including target) will be positive integers.
    Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    The solution set must not contain duplicate combinations.
***/
public class Solution {
    
    /**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> combination = new ArrayList<Integer>();
        
        // check corner case
        if (candidates == null || candidates.length == 0) {
            return result;
        }
        
        Arrays.sort(candidates);
        
        dfs(candidates, target, 0, combination, result);
        
        return result;
    }
    
    // helper method
    private void dfs(int[] nums, 
                     int target, 
                     int startIndex,
                     List<Integer> combination,
                     List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            if (target < nums[i]) {
                return;
            }
            
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            
            combination.add(nums[i]);
            dfs(nums, target - nums[i], i, combination, result);
            combination.remove(combination.size() - 1);
        }
    }
}
