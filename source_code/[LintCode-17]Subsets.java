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
//version-1: DFS
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        // check corner case
        if (nums == null) {
            return result;
        }

        if (nums.length == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        // regular case
        List<Integer> subset = new LinkedList<>();

        Arrays.sort(nums);

        search(nums, 0, subset, result);

        return result;
    }

    // helper method
    private void search(int[] nums, int startIndex, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        
        // corner case
        if (startIndex == nums.length) {
            return;
        }

        // regularcase
        for (int i = startIndex; i < nums.length; i++) {
            subset.add(nums[i]);
            search(nums, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }
}

//version-2: enumeration
// to do
