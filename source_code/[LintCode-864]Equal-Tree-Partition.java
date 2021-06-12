/***
* LintCode 864. Equal Tree Partition
Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees 
which have the equal sum of values after removing exactly one edge on the original tree.

Note:
    The range of tree node value is in the range of [-100000, 100000].
    1 <= n <= 10000
    You can assume that the tree is not null

Example 1:
    Input: {5,10,10,#,#,2,3}
    Output: true
    Explanation:
      origin:
         5
        / \
       10 10
         /  \
        2    3
      two subtrees:
         5       10
        /       /  \
       10      2    3

Example 2:
    Input: {1,2,10,#,#,2,20}
    Output: false
    Explanation:
      origin:
         1
        / \
       2  10
         /  \
        2    20

Link: https://www.lintcode.com/problem/864/
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
//solution: recursion
public class Solution {
    /**
     * @param root: a TreeNode
     * @return: return a boolean
     */
    public boolean checkEqualTree(TreeNode root) {
        // check corner case
        if (root == null) {
            return false;
        }

        int currentValue = root.val;
        int left = getSum(root.left);
        int right = getSum(root.right);

        return (currentValue + left == right) ||
            (currentValue + right == left);
    }

    // helper method
    private int getSum(TreeNode node) {
        // check corner case
        if (node == null) {
            return 0;
        }

        return node.val + getSum(node.left) + getSum(node.right);
    }
}
