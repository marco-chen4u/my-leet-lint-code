/***
 * LintCode 16. Permutations II
 Given a list of numbers with duplicate number in it. Find all unique permutations.


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
//version-1: DFS
public class Solution {
    
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
        findPermutationsUnique(nums, visited, permutation, result);
        
        return result;
    }

    // helper method
    private void findPermutationsUnique(int[] nums, 
                                            boolean[] visited, 
                                            List<Integer> permutation, 
                                            List<List<Integer>> result) {
        // check corner cases
        if (permutation.size() == nums.length) {
            result.add(new ArrayList<Integer>(permutation));
            return;
        }
 
        // regular case
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {
                continue;
            }
            
            permutation.add(nums[i]);
            visited[i] = true;
            findPermutationsUnique(nums, visited, permutation, result);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
};

//version-2: DFS
public class Solution {
    /**
     * @param nums: A list of integers
     * @return: A list of unique permutations
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner cases
        if (nums == null) {
            return result;
        }

        List<Integer> permutation = new ArrayList<>();
        if (nums.length == 0) {
            result.add(permutation);
            return result;
        }

        // normal case
        boolean[] visited = new boolean[nums.length];
        Arrays.fill(visited, false);
        dfs(nums, visited, permutation, result);

        return result;
    }

    // helper method
    private void dfs(int[] nums, boolean[] visited, List<Integer> permutation, List<List<Integer>> result) {
        int size = nums.length;
        // check corner cases
        if (permutation.size() == size) {
            result.add(new ArrayList<Integer>(permutation));
            return;
        }

        // normal case
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < size; i++) {
            if (visited[i]) {
                continue;
            }

            if (set.contains(nums[i])) {
                continue;
            }

            permutation.add(nums[i]);
            set.add(nums[i]);
            visited[i] = true;
            dfs(nums, visited, permutation, result);
            visited[i] = false;
            permutation.remove(permutation.size() - 1);
        }
    }
}
