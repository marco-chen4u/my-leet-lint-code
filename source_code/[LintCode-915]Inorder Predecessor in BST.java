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

public class Solution {
    /**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // check corner case
		if (root == null || p == null) {
			return null;
		}
		
		TreeNode pre = null;
		TreeNode current = root;
		
		while (current != null) {
			if (current.val >= p.val) {
				current = current.left;
			}
			else {
				if (pre == null || current.val > pre.val) {
					pre = current;
				}
				
				current = current.right;
			}
		}
		
		return pre;
    }
}