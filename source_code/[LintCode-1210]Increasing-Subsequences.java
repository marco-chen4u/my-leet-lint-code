/***
* LintCode 1210. Increasing Subsequences
Given an integer array, 
your task is to find all the different possible increasing subsequences of the given array, 
and the length of an increasing subsequence should be at least 2.

Example 1:
    Input:
        [4,6,7,7]
    Output:
        [[4,6],[4,6,7],[4,6,7,7],[4,7],[4,7,7],[6,7],[6,7,7],[7,7]]

Example 2:
    Input:
        [65,21,-44,31,-8]
    Output:
        [[-44,-8],[-44,31],[21,31]]
***/
//version-1: DFS
public class Solution {
    /**
     * @param nums: an integer array
     * @return: all the different possible increasing subsequences of the given array
     */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner case
        if (nums == null || nums.length <= 1) {
            return result;
        }

        //Arrays.sort(nums);
        List<Integer> list = new ArrayList<>();

        dfs(nums, 0, list, result);

        return result;
    }

    // helper methods
    private void dfs(int[] nums, int startIndex, List<Integer> list, List<List<Integer>> result) {
        if (list.size() >= 2 && !result.contains(list)) {
            result.add(new ArrayList<Integer>(list));
        }

        // corner case
        if (startIndex >= nums.length) {
            return;
        }

        // normal case
        for (int i = startIndex; i < nums.length; i++) {

            if (!list.isEmpty() && list.get(list.size() - 1) > nums[i]) {
                continue;
            }

            list.add(nums[i]);
            dfs(nums, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }
}

//version-2: DFS(faster)
class Solution {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        
        // corner cases
        if (nums == null || nums.length <= 1) {
            return result;
        }
        
        // regular case
        List<Integer> list = new ArrayList<>();
        dfs(nums, 0, list, result);
        
        return result;        
    }
    
    // helper method
    private void dfs(int[] nums, int startIndex, List<Integer> list, List<List<Integer>> result) {
        // check corner cases
        if (list.size() >= 2) {
            result.add(new ArrayList<>(list));
        }
        
        if (startIndex >= nums.length) {
            return;
        }
        
        // normal case
        Set<Integer> visited = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            
            if (visited.contains(nums[i]) ||
               !list.isEmpty() && list.get(list.size() - 1) > nums[i]) {
                continue;
            }
            
            list.add(nums[i]);
            visited.add(nums[i]);
            dfs(nums, i + 1, list, result);
            list.remove(list.size() - 1);
        }
    }
}
