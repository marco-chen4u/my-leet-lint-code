/***
* LintCode 87. Remove Node in Binary Search Tree
Given a root of Binary Search Tree with unique value for each node. 
Remove the node with given value. 

If there is no such a node with given value in the binary search tree, do nothing. 

You should keep the tree still a binary search tree after removal.

Example 1
    Input: 
        Tree = {5,3,6,2,4}
        k = 3
    Output: {5,2,6,#,4} or {5,4,6,2}
    Explanation:
        Given binary search tree:
            5
           / \
          3   6
         / \
        2   4
        Remove 3, you can either return:
            5
           / \
          2   6
           \
            4
        or
            5
           / \
          4   6
         /
        2

Example 2
    Input: 
        Tree = {5,3,6,2,4}
        k = 4
    Output: {5,3,6,2}
    Explanation:
        Given binary search tree:
            5
           / \
          3   6
         / \
        2   4
        Remove 4, you should return:
            5
           / \
          3   6
         /
        2
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

//version-1:recursion (divide & conquer)
public class Solution {
    /*
     * @param root: The root of the binary search tree.
     * @param value: Remove the node with given value.
     * @return: The root of the binary search tree after removal.
     */
    public TreeNode removeNode(TreeNode root, int value) {
        // corner cases
        if (root == null) {
            return null;
        }

        if (root.val > value) {
            root.left = removeNode(root.left, value);
            return root;
        }

        if (root.val < value) {
            root.right = removeNode(root.right, value);
            return root;
        }

        // if root.val == value
        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        TreeNode current = root.right;
        while (current != null && current.left != null) {
            current = current.left;
        }

        root.val = current.val;
        root.right = removeNode(root.right, current.val);

        return root;    
}
