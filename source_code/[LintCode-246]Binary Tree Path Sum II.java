/**
 * LintCode 246. Binary Tree Path Sum II
our are given a binary tree in which each node contains a value. 
Design an algorithm to get all paths which sum to a given value. 
The path does not need to start or end at the root or a leaf, but it must go in a straight line down.

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
public class Solution {
    
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        binaryTreePathSum2Helper(result, root, target, path, 0);
        
        return result;
    }

    // helper method
    private void binaryTreePathSum2Helper(List<List<Integer>> result, TreeNode node, 
                                            int target, List<Integer> path, int level) {
        if (node == null) {
            return;
        }
        
        path.add(node.val);
        
        int size = level;
        int index = size;
        int sum = 0;
        while (index >= 0) {
            sum += path.get(index);
            
            if (sum == target) {
                result.add(new ArrayList<Integer>(path.subList(index, size + 1)));
            }
            
            index--;
        }
        
        binaryTreePathSum2Helper(result, node.left, target, path, level + 1);
        binaryTreePathSum2Helper(result, node.right, target, path, level + 1);
        
        path.remove(path.size() - 1);
    }
}
