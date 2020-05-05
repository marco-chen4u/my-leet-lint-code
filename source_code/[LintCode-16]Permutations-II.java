/***
 * LintCode 16. Permutations II
 Given a list of numbers with duplicate number in it. Find all unique permutations.

Example
Example 1:
    Input: [1,1]
    Output:
        [
          [1,1]
        ]

Example 2:
    Input: [1,2,2]
    Output:
        [
          [1,2,2],
          [2,1,2],
          [2,2,1]
        ]
        
Challenge
    Using recursion to do it is acceptable. If you can do it without recursion, that would be great!
***/

public class Solution {
    
    // helper method
    private void findPermutationsUnique(List<List<Integer>> result, 
                                            List<Integer> permutation, 
                                            boolean[] visited, 
                                            int[] nums) {
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<Integer>(permutation));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            findPermutationsUnique(result, permutation, visited, nums);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
    
    /*
     * @param :  A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> permutation = new ArrayList<Integer>();
        
        // check corner case
        if (nums == null) {
            return result;
        }
        
        if (nums.length == 0) {
            result.add(permutation);
            return result;
        }
        
        Arrays.sort(nums);
        
        boolean[] visited = new boolean[nums.length];
        findPermutationsUnique(result, permutation, visited, nums);
        
        return result;
    }
};