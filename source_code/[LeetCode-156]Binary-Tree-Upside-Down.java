/***
* LeetCode 156. Binary Tree Upside Down
Given a binary tree where all the right nodes are either leaf nodes with a sibling 
(a left node that shares the same parent node) or empty, 
flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. 

Return the new root.

Example:
    Input: [1,2,3,4,5]

            1
           / \
          2   3
         / \
        4   5

    Output: return the root of the binary tree [4,5,2,#,#,3,1]

           4
          / \
         5   2
            / \
           3   1  


Clarification:
    Confused what [4,5,2,#,#,3,1] means? Read more below on how binary tree is serialized on OJ.

    The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.
    Here's an example:

           1
          / \
         2   3
            /
           4
            \
             5
    The above binary tree is serialized as [1,2,3,#,#,4,#,#,5].
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
//version-1: post order traverse by stack
class Solution {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        // check corner case
        if (root == null || 
            (root.left == null && root.right == null) ||
            root.left == null) {
            return root;
        }
     
        // regular case
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode current = root;
        
        while (current != null) {
            stack.push(current);
            current = current.left;
        }
        
        current = stack.pop();//left most
        TreeNode flippedRoot = current;
        while (current != root) {
            TreeNode parent = stack.pop();
            
            current.right = parent;
            parent.left = null;
            current.left = parent.right;
            parent.right = null;
            
            current = parent;
        }
        
        return flippedRoot;
    }
}
