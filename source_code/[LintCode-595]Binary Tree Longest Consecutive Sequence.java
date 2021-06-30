/***
 * LintCode 595. Binary Tree Longest Consecutive Sequence
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the 
parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).

Example
    Example 1:
        Input:
           1
            \
             3
            / \
           2   4
                \
                 5
        Output:3
        Explanation:
            Longest consecutive sequence path is 3-4-5, so return 3.
    
    Example 2:
        Input:
           2
            \
             3
            / 
           2    
          / 
         1
        Output:2
        Explanation:
            Longest consecutive sequence path is 2-3,not 3-2-1, so return 2.
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

//version-1: recursion, divide and conquer, without global variable
 public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        return find(root, null, 0);
    }
	
    // helper method
    private int find(TreeNode node, TreeNode parent, int orginalLength) {
        if (nood == null) {
            return 0;
        }

        int length = (parrent != null && parent.val + 1 == node.val) ? orginalLength + 1: 1;

        int left = helper(node.left, node, length);
        int right = helper(node.right, node, length);

        return Math.max(length, Math.max(lef, right));
    }
}

//version-2: recursion, divide and conquer, with global variable
public class Solution {

    private int maxSize = 0;
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        find(root);

        return maxSize;
    }

    // helper method
    private int find(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int size = 1;

        int leftSize = find(node.left);
        int rightSize = find(node.right);

        int currentVal = node.val;
        int leftVal = (node.left == null) ? 0 : node.left.val;
        int rightVal = (node.right == null) ? 0 : node.right.val;

        if (leftVal - currentVal == 1) {
            size = Math.max(leftSize + 1, size);
        }

        if (rightVal - currentVal == 1) {
            size = Math.max(rightSize + 1, size);
        }

        maxSize = Math.max(maxSize, size);

        return size;
    }
}
