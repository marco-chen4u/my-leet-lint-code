/***
* LintCode 481. Binary Tree Leaf Sum
Given a binary tree, calculate the sum of leaves.

Example 1:
    Input：{1,2,3,4}
    Output：7
    Explanation：
        1
       / \
      2   3
     /
    4
    3+4=7

Example 2:
    Input：{1,#,3}
    Output：3
    Explanation：
        1
          \
           3
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
    /**
     * @param root: the root of the binary tree
     * @return: An integer
     */
    public int leafSum(TreeNode root) {
        int result = 0;
        // check corner cases
        if (root == null) {
            return result;
        }

        if (root.left == null && root.right == null) {
            return root.val;
        }

        // regular case
        // divide 
        int left = leafSum(root.left);
        int right = leafSum(root.right);

        // conquer & return
        return left + right;
    }
}
