/**
 * LintCode 246. Binary Tree Path Sum II
Given a binary tree and a target value, 
design an algorithm to find all paths in the binary tree that sum to that target value. 
The path can start and end at any node, but it needs to be a route that goes all the way down. 
That is, the hierarchy of nodes on the path is incremented one by one.

Example 1
    Input:
        {1,2,3,4,#,2}
        6
    Output:
        [
          [2, 4],
          [1, 3, 2]
        ]
    Explanation:
        The binary tree is like this:
            1
           / \
          2   3
         /   /
        4   2
        for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.

Example 2
    Input:
        {1,2,3,4}
        10
    Output:
        []
    Explanation:
        The binary tree is like this:
            1
           / \
          2   3
         /   
        4   
        for target 10, there is no way to reach it.
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
//version-1: recursion(dfs)
public class Solution {
    /**
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     *          we will sort your return value in output
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> path = new ArrayList();
        find(root, target, path, result, 0);//DFS

        return result;
    }

    // helper method
    private void find(TreeNode node, int target, List<Integer> path, List<List<Integer>> result, int level) {
        if (node == null) {
            return;
        }

        path.add(node.val);

        int size = level + 1;

        int index = size - 1;
        int sum = 0;
        while (index >= 0) {
            sum += path.get(index);

            if (sum == target) {
                result.add(new ArrayList<>(path.subList(index, size)));
            }

            index -= 1;
        }

        find(node.left, target, path, result, level + 1);
        find(node.right, target, path, result, level + 1);

        path.remove(path.size() - 1);
    }
}
