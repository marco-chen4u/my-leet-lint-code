/***
* LintCode 448. Inorder Successor in BST
Given a binary search tree (See Definition) and a node in it, 
find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.


Example 1:
    Input: tree = {1,#,2}, node value = 1
    Output: 2
    Explanation:
          1
           \
            2
		
Example 2:
    Input: tree = {2,1,3}, node value = 1
    Output: 2
    Explanation: 
            2
           / \
          1   3
        Binary Tree Representation

Challenge
    O(h), where h is the height of the BST.

Notice
    It's guaranteed p is one node in the given tree. 
    (You can directly compare the memory address to find p)
***/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

//version-1: iteration
public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root == null) {
            return null;
        }

        int target = p.val;

        TreeNode current = root;
        TreeNode next = null;

        while (current != null) {

            if (current.val > target) {
                next = current;
                current = current.left;
                continue;
            }

            current = current.right;
        }

        return next;
    }
}

//version-2: InOrder traversal iteration
public class Solution {
    /*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // corner case
        if (root == null) {
            return null;
	}

        // normal case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        TreeNode result = null;

        TreeNode pre = null;
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            if (pre != null && pre.equals(p)) {
                result = current;
                break;
            }
            
            pre = current;

            current = current.right;
        }

        return result;
    }
}
