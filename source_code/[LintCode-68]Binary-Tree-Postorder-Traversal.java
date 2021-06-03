/***
* LintCode 68. Binary Tree Postorder Traversal
Given a binary tree, return the postorder traversal of its nodes’ values.
Note: 
    The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
    The number of nodes does not exceed 20.

Exaple 1:
    Input:
        Binary Tree = {1, 2, 3}
    Output:
        [2,3,1]

    Explanation:
            1
           / \
          2   3
        It will be serialized as {1, 2, 3}

Example 2:
    Input:
        Binary Tree = {1, #, 2, 3}
    Output:
        [3,2,1]
    Explanation:
            1                    1
           / \                    \
          #   2      =>            2 
             / \                  /
            3   #                3
         It will be serialized as {1, #, 2, 3}
Link: https://www.lintcode.com/problem/68/
***/
//version-1:non-recursion with stack
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
     * @param root: A Tree
     * @return: Postorder in ArrayList which contains node values.
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (root == null) {
            return result;
        }

        TreeNode current = root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.left != null) {
                stack.push(current.left);
            }

            if (current.right != null) {
                stack.push(current.right);
            }

            result.add(current.val);
        }

        Collections.reverse(result);
        return result;
    }
}
