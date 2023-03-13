/***
* LintCode 915. Inorder Predecessor in BST
Given a binary search tree and a node in it, find the in-order predecessor of that node in the BST.

Example
    Example1
        Input: root = {2,1,3}, p = 1
        Output: null

    Example2
        Input: root = {2,1}, p = 2
        Output: 1

Notice
    If the given node has no in-order predecessor in the tree, return null
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

//version-1: iteration
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return null;
        }

        int targetVal = p.val;

        TreeNode current = root;
        TreeNode pre = null;

        while (current != null) {
            if (current.val < targetVal) {
                pre = current;
                current = current.right;

                continue;
            }

            current = current.left;
        }

        return pre;
    }
}

//version-2: InOrder traversal Iteration
public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return null;
        }

        int targetVal = p.val;

        TreeNode current = root;
        TreeNode pre = null;

        TreeNode result = null;
        Stack<TreeNode> stack = new Stack<>();
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (p.equals(current)) {
                result = pre;
                break;
            }

            pre = current;

            current = current.right;
        }

        return result;
    }
}
