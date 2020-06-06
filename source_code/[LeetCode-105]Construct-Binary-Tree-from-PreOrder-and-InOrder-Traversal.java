/***
* LeetCode 105. Construct Binary Tree from Preorder and Inorder Traversal
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
    You may assume that duplicates do not exist in the tree.

For example, given
    preorder = [3,9,20,15,7]
    inorder = [9,3,15,20,7]
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // check corner case
        if (isEmpty(preorder) || isEmpty(inorder)) {
            return null;
        }
        
        if (size(preorder) != size(inorder)) {
            return null;
        }
        
        return helper(preorder, 0, size(preorder) - 1,
                        inorder, 0, size(inorder) - 1);
    }
    
    // helper methods
    private TreeNode helper(int[] preorder, int preStart, int preEnd,
                            int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd ||
            inStart > inEnd) {
            return null;
        }
        
        int rootVal = preorder[preStart];
        TreeNode root = new TreeNode(rootVal);
        
        int rootPos = findIndex(inorder, inStart, inEnd, rootVal);
        int leftSize = rootPos - inStart;
        int rightSize = inEnd - rootPos;
        
        
        
        
        TreeNode leftNode = helper(preorder, preStart + 1, preStart + leftSize, 
                                    inorder, inStart, rootPos - 1);
        TreeNode rightNode = helper(preorder, preStart + leftSize + 1, preEnd,
                                    inorder, rootPos + 1, inEnd);
        
        root.left = leftNode;
        root.right = rightNode;
        
        return root;
    }
    
    private int findIndex(int[] nums, int startPos, int endPos, int value) {
        for (int i = startPos; i <= endPos; i++) {
            if (nums[i] == value) {
                return i;
            }
        }
        
        return -1;
    }
    
    private boolean isEmpty(int[] array) {
        return array == null || array.length == 0;
    }
    
    private int size(int[] array) {
        return isEmpty(array) ? 0 : array.length;
    }
}
