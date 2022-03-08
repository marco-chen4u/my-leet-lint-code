/***
 * LintCode 595. Binary Tree Longest Consecutive Sequence
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the 
parent-child connections. 
The longest consecutive path need to be from parent to child (cannot be the reverse).


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
    private int find(TreeNode node, TreeNode parrent, int orginalLength) {
        if (node == null) {
            return 0;
        }

        int length = (parrent != null && parrent.val + 1 == node.val) ? orginalLength + 1: 1;

        int left = find(node.left, node, length);
        int right = find(node.right, node, length);

        return Math.max(length, Math.max(left, right));
    }
}

//version-2: recursion, divide and conquer, with global variable
public class Solution {

    private int maxLength = 0;
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }

        find(root);

        return maxLength;
    }

    // helper method
    private int find(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int length = 1;
        int left = find(node.left);
        int right = find(node.right);

        if (node.left != null && node.left.val == node.val + 1) {
            length = Math.max(length, left + 1);
        }

        if (node.right != null && node.right.val == node.val + 1) {
            length = Math.max(length, right + 1);
        }

        maxLength = Math.max(length, maxLength);

        return length;
    }

}
