/***
* LintCode 66. Binary Tree Preorder Traversal
Given a binary tree, return the preorder traversal of its nodes' values

Note:
    -The first data is the root node, followed by the value of the left and right son nodes, 
     and "#" indicates that there is no child node.
    -The number of nodes does not exceed 20.

Example 1:
    Input:
        Binary Tree = {1, 2, 3}
    Output:
        [1, 2, 3]
    Explanation:
            1
           / \
          2   3
        It will be serialized as {1, 2, 3}

Example 2:
    Input:
        Binary Tree = {1, #, 2, 3}
    Output:
        [1, 2, 3]
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

//version-1: non-recursion
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
       List<Integer> result = new ArrayList<>();
        // check corner case
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        stack.push(current);

        while (!stack.isEmpty()) {
            current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }

            result.add(current.val);
        }

        return result;
    }
}

//version-2: recursion（Divide and Conquer）
public class Solution {
    /**
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        result.add(root.val);
        List<Integer> left = preorderTraversal(root.left);
        result.addAll(left);
        List<Integer> right = preorderTraversal(root.right);
        result.addAll(right);

        return result;
    }
}
