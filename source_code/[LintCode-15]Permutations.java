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

/**
* 排列组合的歌总数：n！
* 排列DFS时间复杂度通用公式： O（方案总数*构造每个方案的时间）即O（n！*n）
**/

//version-1: dfs
public class Solution {
    
    /*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        // check corner cases
        if (nums == null) {
            return results;
        }
        
        List<Integer> permutation = new ArrayList<Integer>();
        if (nums.length == 0) {
            results.add(permutation);
            return result;
        }
        
	// normal case
        boolean[] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
		
        findPermutations(nums, visited, permutation, results);
        
        return result;
    }
	
    // helper method
    private void findPermutations(int[] nums, 
				  boolean[] visited, 
				  List<Integer> permutation,
                                  List<List<Integer>> results) {
        if (permutation.size() == nums.length) {
            results.add(new ArrayList<Integer>(permutation));//deep copy
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            findPermutations(nums, visited, permutation, results);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
