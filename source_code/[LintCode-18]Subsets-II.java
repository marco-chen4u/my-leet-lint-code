/***
 * LintCode 18. Subsets II
Given a collection of integers that might contain duplicates, nums,
return all possible subsets (the power set).

Example
    Example 1:
        Input: [0]
        Output:
            [
              [],
              [0]
            ]
    
    Example 2:
        Input: [1,2,2]
        Output:
            [
              [2],
              [1],
              [1,2,2],
              [2,2],
              [1,2],
              []
            ]

Challenge
    Can you do it in both recursively and iteratively?

Notice
    Each element in a subset must be in non-descending order.
    The ordering between two subsets is free.
    The solution set must not contain duplicate subsets. 
***/
public class Solution {
	
	// helper method
	private void findSubsetsWithDup(List<List<Integer>> result, 
										List<Integer> subset, 
										int[] nums, 
										int startIndex) {
		result.add(new ArrayList<Integer>(subset));// deep copy
		
		for (int i = startIndex; i < nums.length; i++) {
			
			if (i > startIndex && nums[i] == nums[i - 1]) {
				continue;
			}
			
			subset.add(nums[i]);
			findSubsetsWithDup(result, subset, nums, i + 1);
			subset.remove(subset.size() - 1);
		}
	}
	
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		List<Integer> subset = new ArrayList<Integer>();
		
		// check corner case-1
		if (nums == null) {
			return result;
		}
		
		// check corner case-2
		if (nums.length == 0) {
			result.add(subset);
			return result;
		}
		
		Arrays.sort(nums);
		
		findSubsetsWithDup(result, subset, nums, 0);
		
		return result;
    }
}