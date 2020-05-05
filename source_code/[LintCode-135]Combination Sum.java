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
    
    // helper method
    private void findCombinationSum(List<List<Integer>> result, 
                                        List<Integer> combination,
                                        int[] nums, 
                                        int target, 
                                        int startIndex) {
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
            findCombinationSum(result, combination, nums, target - nums[i], i);
            combination.remove(combination.size() - 1);
        }
    }
    
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
        
        findCombinationSum(result, combination, candidates, target, 0);
        
        return result;
    }
}