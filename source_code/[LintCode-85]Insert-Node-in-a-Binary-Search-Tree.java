/***
* LintCode 85. Insert Node in a Binary Search Tree
Given a binary search tree and a new tree node, insert the node into the tree. You should keep the tree still be a valid binary search tree.

Example 1:
    Input:
        tree = {}
        node= 1
    Output:
        {1}
    Explanation:
        Insert node 1 into the empty tree, so there is only one node on the tree.

Example 2:
    Input:
        tree = {2,1,4,#,#,3}
        node = 6
    Output:
        {2,1,4,#,#,3,6}
    Explanation:
            2             2
           / \           / \
          1   4   -->   1   4
             /             / \
            3             3   6

Link: https://www.lintcode.com/problem/85/
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
//version-1: recursion
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // check corner cases
        if (root == null) {
            return node;
        }

        if (node == null) {
            return root;
        }

        // regular case
        if (root.val >= node.val) {
            root.left = insertNode(root.left, node);
        }
        else {
            root.right = insertNode(root.right, node);
        }

        return root;
    }
}

//version-2: none-recursion
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param node: insert this node into the binary search tree
     * @return: The root of the new binary search tree.
     */
    public TreeNode insertNode(TreeNode root, TreeNode node) {
        // check corner cases
        if (node == null) {
            return root;
        }

        if (root == null) {
            return node;
        }

        TreeNode current = root;
        TreeNode pre = null;

        while (current != null) {
            pre = current;
            if (node.val >= current.val) {
                current = current.right;
                continue;
            }
            
            
            current = current.left;
            
        }

        if (pre == null) {
            return root;
        }

        if (node.val >= pre.val) {
            pre.right = node;
        }

        if (node.val < pre.val) {
            pre.left = node;
        }

        return root;
    }
}
