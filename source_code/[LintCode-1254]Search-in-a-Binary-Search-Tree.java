/**
* LintCode 1254. Search in a Binary Search Tree
Given the root of a binary search tree (BST) and a value.

Return the node whose value equals the given value. If such node doesn't exist, return null.

Example 1:
    Input: value = 2
            4
           / \
          2   7
         / \
        1   3
    Output: node 2

Example 2:
    Input: value = 5
            4
           / \
          2   7
         / \
        1   3
    Output: null
**/

// version-1: non-recursion, in-order traversal
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
     * @param root: the tree
     * @param val: the val which should be find
     * @return: the node
     */
    public TreeNode searchBST(TreeNode root, int val) {
        // check corner case
        if (root == null) {
            return null;
        }

        // normal case
        TreeNode result = null;
        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (current.val == val) {
                result = current;
                break;
            }

            current = current.right;
        }

        return result;
    }
}

//version-2: recursion
public class Solution {
    /**
     * @param root: the tree
     * @param val: the val which should be find
     * @return: the node
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) {
            return null;
        }

        if (root.val == val) {
            return root;
        }

        TreeNode left = searchBST(root.left, val);
        TreeNode right = searchBST(root.right, val);

        if (left != null) {
            return left;
        }

        if (right != null) {
            return right;
        }

        return null;
    }
}
