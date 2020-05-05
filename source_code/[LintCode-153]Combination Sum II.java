/***
 * LintCode 153. Combination Sum II
Given a collection of candidate numbers (C) and a target number (T), 
find all unique combinations in C where the candidate numbers sums to T.
Each number in C may only be used once in the combination.

Example
    Given candidate set [10,1,6,7,2,1,5] and target 8,
    A solution set is:
        [
          [1,7],
          [1,2,5],
          [2,6],
          [1,1,6]
        ]
    
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
            result.add(new ArrayList<Integer>(combination));// deep copy
            return;
        }
        
        for (int i = startIndex; i < nums.length; i++) {
            if (target < nums[i]) {
                return;
            }
            
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            }
            
            combination.add(nums[i]);
            findCombinationSum(result, combination, nums, target - nums[i], i + 1);
            combination.remove(combination.size() - 1);
        }
    }
    
    /**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // check corner case
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        Arrays.sort(nums);
        
        List<Integer> combination = new ArrayList<Integer>();
        findCombinationSum(result, combination, nums, target, 0);
        
        return result;
    }
}