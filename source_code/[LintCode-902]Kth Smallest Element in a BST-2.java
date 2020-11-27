/***
* LintCode 902. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example
    Given root = {1,#,2}, k = 2, return 2.

Challenge
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
    How would you optimize the kthSmallest routine?

Notice
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
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
// version-1: no-recursion & stack
public class Solution {
    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // check corner case
        if (root == null || k < 1) {
            return 0;
        }
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        
        for (int i = 1; i < k; i++) {
            current = stack.pop();
            
            if (current.right != null) {
                current = current.right;
                
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
            }
        }
        
        return stack.isEmpty() ? 0 : stack.peek().val;
    }
}
