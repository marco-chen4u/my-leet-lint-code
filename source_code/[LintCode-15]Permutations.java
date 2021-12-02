/*** LintCode 15. Permutations
 * Given a list of numbers, return all possible permutations.

Example
    Example 1:
        Input: [1]
        Output:
            [
              [1]
            ]
            
    Example 2:
        Input: [1,2,3]
        Output:
            [
              [1,2,3],
              [1,3,2],
              [2,1,3],
              [2,3,1],
              [3,1,2],
              [3,2,1]
            ]

Challenge
    Do it without recursion.

Notice
    You can assume that there is no duplicate numbers in the list.
***/
public class Solution {
    
    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        // check corner cases
        if (nums == null) {
            return result;
        }
        
        List<Integer> permutation = new ArrayList<Integer>();
        if (nums.length == 0) {
            result.add(permutation);
            return result;
        }
        
	// normal case
        // sort 
        Arrays.sort(nums);
        
        boolean[] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
		
        findPermutations(nums, visited, permutation, result);
        
        return result;
    }
	
    // helper method
    private void findPermutations(int[] nums, 
				  boolean[] visited, 
				  List<Integer> permutation,
                                  List<List<Integer>> result) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<Integer>(permutation));//deep copy
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            findPermutations(nums, visited, permutation, result);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
