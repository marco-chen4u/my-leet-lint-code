/***
* LintCode 376. Binary Tree Path Sum
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.
A valid path is from root node to any of the leaf nodes.


Example 1
    Input:
        {1,2,4,2,3}
        5
    Output: [[1, 2, 2],[1, 4]]
    Explanation:
        The tree is look like this:
                 1
                / \
               2   4
              / \
             2   3
        For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
Example 2
    Input:
        {1,2,4,2,3}
        3
    Output: []
    Explanation:
        The tree is look like this:
                 1
                / \
               2   4
              / \
             2   3
        Notice we need to find all paths from root node to leaf nodes.
        1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5 
        There is no one satisfying it.
	
* LintCode link:https://www.lintcode.com/problem/376/
* LeetCode link: https://leetcode.com/problems/path-sum-ii/ (113)
* related: https://leetcode.com/problems/path-sum/
***/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

//version-1: DFS(recursion, backtrack)
public class Solution {
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> subset = new ArrayList<Integer>();
        //check corner case
        if (root == null) {
            return result;
        }
        
        findBinaryTreePathSum(result, subset, root, target);
        
        return result;
    }
    
    private void findBinaryTreePathSum(List<List<Integer>> result, List<Integer> subset,
                                        TreeNode node, int target) {
        if (node == null) {
            return;
        }
            
        subset.add(node.val);
        if (node.left == null && node.right == null && node.val == target) {
            result.add(new ArrayList<Integer>(subset));
        }
        else {
            findBinaryTreePathSum(result, subset, node.left, target - node.val);
            findBinaryTreePathSum(result, subset, node.right, target - node.val);
        }
        subset.remove(subset.size() - 1);
    }
}

//version-2: DFS(devide&conquer)
/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     *          we will sort your return value in output
     */
    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        if (root.left == null && root.right == null && root.val == target) {
            List<Integer> values = new ArrayList<>();
            values.add(root.val);
            result.add(values);
            return result;
        }

        List<List<Integer>> left = binaryTreePathSum(root.left, target - root.val);
        List<List<Integer>> right = binaryTreePathSum(root.right, target - root.val);

        for (List<Integer> leftValues : left) {
            leftValues.add(0, root.val);
            result.add(leftValues);
        }

        for (List<Integer> rightValues : right) {
            rightValues.add(0, root.val);
            result.add(rightValues);
        }

        return result;
    }
}
