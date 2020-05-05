/***
 * LintCode 17. Subsets
Given a set of distinct integers, return all possible subsets.

Example
    Example 1:
        Input: [0]
        Output:
            [
              [],
              [0]
            ]

    Example 2:
        Input: [1,2,3]
        Output:
            [
              [3],
              [1],
              [2],
              [1,2,3],
              [1,3],
              [2,3],
              [1,2],
              []
            ]

Challenge
    Can you do it in both recursively and iteratively?

Notice
    Elements in a subset must be in non-descending order.
    The solution set must not contain duplicate subsets.
***/
public class Solution {
    // hleper method
    private void findSubsets(List<List<Integer>> result, List<Integer> subset, int[] nums, int startIndex) {
        result.add(new ArrayList<Integer>(subset));
        
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            findSubsets(result, subset, nums, i + 1);
            subset.remove(subset.size() - 1);
        }
    }
    
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<Integer>();
        // check corner case
        if (nums == null || nums.length == 0) {
            result.add(subset);
            return result;
        }
        
        Arrays.sort(nums);
        
        findSubsets(result, subset, nums, 0);
        
        return result;
    }
}