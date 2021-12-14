/***
* LintCode 90. K Sum II
Given n unique postive integers, number k (1<=k<=n1<=k<=n) and target.
Find all possible k integers where their sum is target.

Example 1:
    Input:
        array = [1,2,3,4]
        k = 2
        target = 5
    Output:
        [[1,4],[2,3]]
    Explanation:
        1+4=5,2+3=5
        
Example 2:
    Input:
        array = [1,3,4,6]
        k = 3
        target = 8
    Output:
        [[1,3,4]]
    Explanation:
        1+3+4=8        

***/

//version-1: DFS
public class Solution {
    /**
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param target: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] nums, int k, int target) {
        List<List<Integer>> result = new ArrayList<>();

        // check corner case
        if (nums == null || nums.length == 0 || k == 0 || target <= 0) {
            return result;
        }

        // normal case
        List<Integer> combination = new ArrayList<>();
        dfs(nums, 0, k, target, combination, result);

        return result;
    }

    // helper method
    private void dfs(int[] nums, int startIndex, int k, int sum, List<Integer> combination, List<List<Integer>> result) {
        // check corner case
        if (combination.size() == k && sum == 0) {
            result.add(new ArrayList<Integer>(combination));
            return;
        }

        // normal case
        for (int i = startIndex; i < nums.length; i++) {
            
            // corner case 1
            if (sum - nums[i] < 0) {
                break;
            }
            
            // regular case
            combination.add(nums[i]);
            dfs(nums, i + 1, k, sum - nums[i], combination, result);
            combination.remove(combination.size() - 1);
        }

    }
}
