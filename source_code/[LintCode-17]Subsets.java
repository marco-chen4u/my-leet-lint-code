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
    
Related: LintCode 18. Subsets II
***/

/**
* 这是一种组合类的搜索问题，
* 因为这道题是return all possible subsets， 就是return所有可能的方案，一般来说，求所有具体方案的这类问题，通常你都可以朝着深度优先搜索（DFS）这个方向去分析。
*
* 一个集合的子集总个数:
* 从数学推导分析来说： C(n, 0) + C(n, 1) + C(n, 2) + ... + C(n, n) = 2^n
*
* 从排列取舍的角度分析来说
* 集合中，有n个元素，每个元素都有选与不选的2个选项，所以，每个位置都有2种状态，n个元素的组合选择，总共有2^n个子集选项。
* 比如某集合为{1,2,3},从空集出发，作为根节点，分析如下：
*                   [   ]          
*               1?/       \
*               [1]        [ ]
*            2?/   \       /   \
*          [1, 2]  [1]   [2]   [ ]      <-----------每一个节点，都是一个决策过程，就是考虑了每个元素是到底要还是不要，每一个不同的决策过程都导向了最终（最下面一层）不同的叶子节点（结果）。
*         3?/  \   ...................  
*      [1,2,3] [1,2] [1,3] [1] [2,3] [2] [3] [] <----------注意这一层，所有的叶子节点，事实上就是所搜索得到的结果。
* 注意：上述这棵搜索树的高度，刚好就是等于集合元素的个数。下面version-1就是上述分析的解决代码。
**/

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

        dfs(nums, 0, subset, result);

        return result;
    }

    // helper method
    private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> result) {
                
        // corner case
        if (index == nums.length) {
            result.add(new ArrayList<>(subset));
            return;
        }

        // regularcase
        subset.add(nums[index]);
        dfs(nums, index + 1, subset, result);
        
        subset.remove(subset.size() - 1);
        dfs(nums, index, subset, result);
    }
}

//version-2: DFS + backtracking *[best]*
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
            subset.remove(subset.size() - 1);//backtracking
        }
    }
}

//version-3: BFS
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner cases
        if (nums == null) {
            return result;
        }

        Arrays.sort(nums);
        
        // normal case
        Queue<List<Integer>> queue = new ArrayDeque<>();
        queue.offer(new ArrayList<>());

        while (!queue.isEmpty()) {
            List<Integer> subset = queue.poll();
            result.add(subset);

            for (int i = 0; i < nums.length; i++) {
                if (subset.isEmpty() || subset.get(subset.size() - 1) < nums[i]) {
                    List<Integer> newSubset = new ArrayList<>(subset);
                    newSubset.add(nums[i]);
                    queue.offer(newSubset);
                }
            }
        }

        return result;
    }
}

//version-4: enumeration
public class Solution {
    /**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // check corner cases
        if (nums == null) {
            return result;
        }

        result.add(new ArrayList<>());
        if (nums.length == 0) {            
            return result;
        }

        Arrays.sort(nums);
        
        // normal case
        for (int i = 0; i < nums.length; i++) {

            List<List<Integer>> subResult = new ArrayList<List<Integer>>();

            for (List<Integer> subset : result) {
                List<Integer> newSubset = new ArrayList<Integer>(subset);
                newSubset.add(nums[i]);
                subResult.add(newSubset);
            }

            result.addAll(subResult);

        }

        return result;
    }
}
