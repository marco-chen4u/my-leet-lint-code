/***
* LeetCode 106. Construct Binary Tree from Inorder and Postorder Traversal
Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
    You may assume that duplicates do not exist in the tree.

For example, given
    inorder = [9,3,15,20,7]
    postorder = [9,15,7,20,3]
    Return the following binary tree:
        3
       / \
      9  20
        /  \
       15   7
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
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        // check corner cases
        if (isEmpty(inorder) || isEmpty(postorder) || 
            size(inorder) != size(postorder)) {
            return null;
        }
        
        return helper(inorder, 0, size(inorder) - 1,
                        postorder, 0, size(postorder) - 1);
    }
    
    // helper methods
    private TreeNode helper(int[] inOrder, int inStart, int inEnd,
                            int[] postOrder, int postStart, int postEnd) {
        // corner case
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }
        
        TreeNode root = new TreeNode(postOrder[postEnd]);
        int inRootPos = findPos(inOrder, inStart, inEnd, root.val);
        int leftSize = inRootPos - inStart;
        int rightSize = inRootPos - inEnd;
        
        root.left = helper(inOrder, inStart, inRootPos - 1,
                            postOrder, postStart, postStart + leftSize - 1);
        
        root.right = helper(inOrder, inRootPos + 1, inEnd, 
                            postOrder, postStart + leftSize, postEnd - 1);
        
        return root;
        
    }
    
    private int findPos(int[] nums, int startPos, int endPos, int target) {
        for (int i = startPos; i <= endPos; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        
        return -1;
    }
    
    private boolean isEmpty(int[] nums) {
        return nums == null || nums.length == 0;
    }
    
    private int size(int[] nums) {
        return nums.length;
    }
}
