/***
* LeetCode 814. Binary Tree Pruning
Given the root of a binary tree, return the same tree where every subtree (of the given tree) not containing a 1 has been removed.

A subtree of a node node is node plus every node that is a descendant of node.

Example 1
      Input: root = [1,null,0,0,1]
      Output: [1,null,0,null,1]
      Explanation: 
                  1                 1
                   \                 \
                    0     =>          0
                   /  \                \
                  0    1                1
      Only the red nodes satisfy the property "every subtree not containing a 1".
      The diagram on the right represents the answer.

Example 2
    Input: root = [1,0,1,0,0,0,1]
    Output: [1,null,1,null,1]
      Explanation: 
                   1                 1
                 /   \                 \
                0      1     =>          1
               / \    /  \                \
              0   0  0    1                1
              
Example 3:
    Input: root = [1,1,0,1,1,0,1,0]
    Output: [1,1,0,1,1,null,1]
      Explanation: 
                   1                 1
                 /   \              /  \
                1      0     =>    1    0
               / \    /  \        / \     \
              1   1  0    1      1   1     1
             /
            0 
* link: https://leetcode.com/problems/binary-tree-pruning/
***/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//version-1: recursion(divide & conquer) 
class Solution {

    // inner class
    class Result {
        // fields
        boolean isValid;
        TreeNode node;

        //constructor
        public Result(boolean isValid, TreeNode node) {
            this.isValid = isValid;
            this.node = node;
        }
    }

    public TreeNode pruneTree(TreeNode root) {
        Result result = find(root);
        
        return result.isValid ? result.node : null;
    }

    // helper mehtod
    private Result find(TreeNode current) {
        if (current == null) {
            return new Result(false, null);
        }

        Result left = find(current.left);
        Result right = find(current.right);

        boolean isValid = current.val == 1 || left.isValid || right.isValid;
        Result result = new Result(isValid, current);
        if (!left.isValid) {
            result.node.left = null;
        }

        if (!right.isValid) {
            result.node.right = null;
        }

        return result;
    }
}

//version-2: recursion(divide & conquer)
class Solution {

    public TreeNode pruneTree(TreeNode root) {
        return isValid(root) ? root : null;
    }

    // helper mehtod
    private boolean isValid(TreeNode current) {
        if (current == null) {
            return false;
        }

        boolean isLeftValid = isValid(current.left);
        boolean isRightValid = isValid(current.right);

        boolean result = current.val == 1 || isLeftValid || isRightValid;
        
        if (!isLeftValid) {
            current.left = null;
        }

        if (!isRightValid) {
            current.right = null;
        }

        return result;
    }
}
