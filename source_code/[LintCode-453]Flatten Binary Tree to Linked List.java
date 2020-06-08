/***
* LintCode 453. Flatten Binary Tree to Linked List
Flatten a binary tree to a fake "linked list" in pre-order traversal.
Here we use the right pointer in TreeNode as the next pointer in ListNode.

Example
    Example 1:
        Input:
            {1,2,5,3,4,#,6}
                 1
                / \
               2   5
              / \   \
             3   4   6
        Output:
            {1,#,2,#,3,#,4,#,5,#,6}
                   1
                    \
                     2
                      \
                       3
                        \
                         4
                          \
                           5
                            \
                             6
			 
    Example 2:
        Input:
            {1}
            1
        Output:
            {1}
            1

Challenge
    Do it in-place without any extra memory.

Notice
    Don't forget to mark the left child of each node to null. 
    Or you will get Time Limit Exceeded or Memory Limit Exceeded.

URL
    https://www.lintcode.com/problem/flatten-binary-tree-to-linked-list/
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
 
//version-1: devide and conquar
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner case
        if (root == null) {
            return;
        }
	
        flattenHelper(root);
    }

    // helper methods
    private TreeNode flattenTree(TreeNode node) {
        if (node == null) {
            return null;
        }
	
	if (node != null && node.left == null && node.right == null) {
            return node;
	}
	
        TreeNode leftTail = flattenTree(node.left);
        TreeNode rightTail = flattenTree(node.right);

        // connect leftTail to node.right
        if (leftTail != null) {
            leftTail.right = node.right;
            node.right = node.left;
            node.left = null;
        }

        return (rightTail != null) ? rightTail : leftTail;
    }
}


//version-2: none-recursion
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner case
        if (root == null || 
            root.left == null && root.right == null) {
            return;
        }
	
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
	
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
	
            if (current.right != null) {
                stack.push(current.right);
            }
	
            if (current.left != null) {
                stack.push(current.left);
            }
	
            current.left = null;
            current.right = (!stack.isEmpty()) ? stack.peek() : null;
        }
    }	
}
