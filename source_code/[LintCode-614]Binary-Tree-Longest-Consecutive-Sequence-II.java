/***
* LintCode 614. Binary Tree Longest Consecutive Sequence II
Given a binary tree, find the length(number of nodes) of the longest consecutive sequence(Monotonic and adjacent node values differ by 1) path.
The path could be start and end at any node in the tree.

Example 1
    Input:
        {1,2,0,3}
    Output:
        4
    Explanation:
            1
           / \
          2   0
         /
        3
        0-1-2-3

Example 2
    Input:
       {3,2,2}
    Output:
       2
    Explanation:
            3
           / \
          2   2
        2-3
***/
//recursion: divide & conquer
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

    class Result {
        // fields
        int maxLen;
        int down;
        int up;

        // constructor
        public Result(int maxLen, int down, int up) {
            this.maxLen = maxLen;
            this.down = down;
            this.up = up;
        }
    }

    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive2(TreeNode root) {
        return find(root).maxLen;
    }

    // helper method
    private Result find(TreeNode node) {
        if (node == null) {
            return new Result(0, 0, 0);
        }

        Result left = find(node.left);
        Result right = find(node.right);

        int maxLen = 0;
        int down = 0;
        int up = 0;

        if (node.left != null && node.left.val == node.val + 1) {
            down = Math.max(down, left.down + 1);
        }

        if (node.left != null && node.left.val + 1 == node.val) {
            up = Math.max(up, left.up + 1);
        }

        if (node.right != null && node.right.val == node.val + 1) {
            down = Math.max(down, right.down + 1);
        }

        if (node.right != null && node.right.val + 1 == node.val) {
            up = Math.max(up, right.up + 1);
        }

        maxLen = down + 1 + up;

        maxLen = Math.max(maxLen, Math.max(left.maxLen, right.maxLen));

        return new Result(maxLen, down, up);
    }
}
