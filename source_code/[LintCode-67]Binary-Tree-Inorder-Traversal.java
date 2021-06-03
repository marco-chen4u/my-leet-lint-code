/***
* LintCode 67. Binary Tree Inorder Traversal
Given a binary tree, return the inorder traversal of its nodes' values
Note:
    The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
    The number of nodes does not exceed 20.
Example 1:
    Input:
        Binary Tree = {1, 2, 3}
    Output:
        [2, 1, 3]
    Explanation:
            1
           / \
          2   3
        It will be serialized as {1, 2, 3}
Example 2:
    Input:
        Binary Tree = {1, #, 2, 3}
    Output:
        [3, 2, 1]
    Explanation:
            1             1
           / \             \
          #   2    =>       2
             / \           /
            3   #         3
        It will be serialized as {1, #, 2, 3}
Challenge
    Can you do it without recursion?
    
Link: https://www.lintcode.com/problem/66/
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

//version-1: non-recursion with stack
public class Solution {
    /**
     * @param root: A Tree
     * @return: Inorder in ArrayList which contains node values.
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (root == null) {
            return result;
        }

        // regular case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();            
            result.add(current.val);

            current = current.right;
        }

        return result;
    }
}
