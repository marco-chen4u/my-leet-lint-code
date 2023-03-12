/***
* LintCode 95. Valid Binary Search Tree
Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:
    -The left subtree of a node contains only nodes with keys less than the node's key.
    -The right subtree of a node contains only nodes with keys greater than the node's key.
    -Both the left and right subtrees must also be binary search trees.
    -A single node tree is a BST

Example
    Example 1:
        Input:  For the following binary tree（only one node）:
            -1
        Output：true

    Example 2:
        Input:  For the following binary tree:		
              2
             / \
            1   4
               / \
              3   5			
        Output: true
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
//version-1: non-recursion, in-order traversal with stack
public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // check corner case
        if (root == null) {
            return true;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            
            if (pre != null && pre.val >= current.val) {
                return false;
            }

            pre = current;

            current = current.right;
        }

        return true;
    }
}

//version-2: all the nodes of BST are layout in order with Inorder traversal, the previous visti node should be less then current node, we should be mindful this characteristic of BST
public class Solution {

    private boolean isFirstNode = true;
    private int lastVal = Integer.MAX_VALUE;

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // check corner case
        if (root == null) {
            return true;
        }

        if (!isValidBST(root.left)) {
            return false;
        }

        if (!isFirstNode && lastVal >= root.val) {
            return false;
        }

        isFirstNode = false;
        lastVal = root.val;

        if (!isValidBST(root.right)) {
            return false;
        }

        return true;
    }
}

//vesion-3: recursion + new data type
public class Solution {
    // inner class
    class ResultType {
        // fields
        boolean isBst;
        TreeNode maxNode;
        TreeNode minNode;
        // constructors
        public ResultType(boolean isBst) {
            this.isBst = isBst;
            this.maxNode = null;
            this.minNode = null;
        }

        public ResultType(boolean isBst, TreeNode max, TreeNode min) {
            this.isBst = isBst;
            this.maxNode = max;
            this.minNode = min;
        }
    }
	
    // helper method
    private ResultType helper(TreeNode node) {
        if (node == null) {
            return new ResultType(true);
        }

        ResultType leftResult = helper(node.left);
        ResultType rightResult = helper(node.right);
	
        if (!leftResult.isBst || !rightResult.isBst) {
            return new ResultType(false);
        }
	
        if ((leftResult.maxNode != null && leftResult.maxNode.val >= node.val) ||
                (rightResult.minNode != null && rightResult.minNode.val <= node.val)) {
            return new ResultType(false);
        }

        return new ResultType(true, (rightResult.maxNode != null) ? rightResult.maxNode : node, 
                                    (leftResult.minNode != null) ? leftResult.minNode : node);
    }
	
    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
	    return true;
        }
		
        return helper(root).isBst;
    }
}

//version-4: recursion(devide&conquer) + new data type
public class Solution {

    class ResultType {
        // fields
        boolean isBST;
        TreeNode min;
        TreeNode max;

        // constructors
        public ResultType(boolean isBST, TreeNode min, TreeNode max) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }

        public ResultType(boolean isBST) {
            this.isBST = isBST;
            this.min = null;
            this.max = null;
        }
    }

    /**
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
        ResultType result = find(root);
        return result.isBST;
    }

    // helper method
    private ResultType find(TreeNode node) {
        if (node == null) {
            return new ResultType(true);
        }

        if (node.left == null && node.right == null) {
            return new ResultType(true, node, node);
        }

        ResultType left = find(node.left);
        ResultType right = find(node.right);

        ResultType result =  new ResultType(false);

        if (left.isBST && right.isBST && 
            (left.max == null || left.max.val < node.val) && 
            (right.min == null || right.min.val > node.val)) {
            result.isBST = true;
            result.min = left.min == null ? node : left.min;
            result.max = right.max == null ? node : right.max;
        }

        return result;
    }
}
