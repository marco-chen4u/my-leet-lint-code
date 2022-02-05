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

//recursion-2: recursion
public class Solution {
    /**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // check corner cases
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            return;
        }

        if (root.left == null) {
            flatten(root.right);
            return;
        }

        // normal case
        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        // divide
        flatten(leftNode);
        flatten(rightNode);

        // conquer
        root.right = leftNode;
        root.left = null;//remove its left branch, since left branch has been flattened and shift to right side

         if (rightNode == null) {
            return;
        }

        // check the flattened left branch nodes, find the last node to link the right branch headed node
        TreeNode current = leftNode;

        while (current.right != null) {
            current = current.right;
        }

        // let the last node to connected right branch headed node
        current.right = rightNode;
    }
}

//version-3 none-recursion
/**
* DFS或者分递归前序遍历这棵树，然后把结果一路向右串联起来
* 小结： 遇到二叉树的问题，就想想整棵树在该问题上的结果和左右子树在该问题结果之间有什么联系
**/
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
