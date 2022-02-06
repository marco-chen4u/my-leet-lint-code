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
//version-1: dfs
public class Solution {
	
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
		
        findSubsetsWithDup(nums, 0, subset, result);
	
        return result;
    }
	
    // helper method
    private void findSubsetsWithDup(int[] nums, 
					int startIndex,
				        List<Integer> subset,
				        List<List<Integer>> result) {
        result.add(new ArrayList<Integer>(subset));// deep copy
	    
	// check corner case
        if (startIndex >= nums.length) {
            return;
        }
	
        for (int i = startIndex; i < nums.length; i++) {
			
            if (i > startIndex && nums[i] == nums[i - 1]) {
                continue;
            } //end of if
	
            subset.add(nums[i]);
            findSubsetsWithDup(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }//end for i
    }
}

//version-2: dfs, remove duplicates with hash function
public class Solution {
    private static final String SEPARATOR = "->";
    
    private StringBuilder sb = new StringBuilder();

    /**
     * @param nums: A set of numbers.
     * @return: A list of lists. All valid subsets.
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        
        Map<String, Boolean> visited = new HashMap<String, Boolean>();
        Arrays.sort(nums);
        List<Integer> subset = new ArrayList<>();

        dfs(nums, visited, 0, subset, results);
        
        return results;
    }
    
    // helper methods
    private void dfs(int[] nums, 
             Map<String, Boolean> visited,
             int startIndex, 
             List<Integer> subset,
             List<List<Integer>> results) {
	// check corner cases
        String hashString = getHash(subset);
        
        if (visited.containsKey(hashString)) {
            return ;
        }
        
        visited.put(hashString, true);
        results.add(new ArrayList<Integer>(subset));
	    
	if (startIndex == nums.length) {
            return;
        }

        for (int i = startIndex;i < nums.length; i++) {
            subset.add(nums[i]);
            dfs(nums, visited, i + 1, subset, results);
            subset.remove(subset.size() - 1);
        }
        
    }

    private String getHash(List<Integer> subset) {
        sb.setLength(0);
        for (int num : subset) {
            sb.append(num).append(SEPARATOR);
        }
        
        return sb.toString();
    }
    
}
