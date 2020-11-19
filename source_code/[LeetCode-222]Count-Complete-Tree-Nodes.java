/***
Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6
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

//solution-1: binary search, time complexity: O(logn)
class Solution {
    /**
     * @param root: root of complete binary tree
     * @return: the number of nodes
     */
    public int countNodes(TreeNode root) {
        // check corner case
        if (root == null) {
            return 0;
        }
        
        int depth = getDepth(root);
        if (depth == 0) {
            return 1;
        }
        
        // search the last node at the bottom layer
        int left = 0;
        int right = getTwoOfPower(depth) - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (checkExist(mid, depth, root)) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        
        // calculate the total nodes
        return getTwoOfPower(depth) - 1 + left;
    }
    
    // helper methods
    private int getDepth(TreeNode node) {
        int depth = 0;
        while (node.left != null) {
            node = node.left;
            depth ++;
        }
        return depth;
    }
    
    private int getTwoOfPower(int n) {
        return (int) Math.pow(2, n);
    }
    
    private boolean checkExist(int index, int depth, TreeNode node) {
        int left = 0;
        int right = getTwoOfPower(depth) - 1;
        
        for (int i = 0; i < depth; i++) {
            int mid = left + (right - left) / 2;
            
            if (mid < index) {
                left = mid + 1;
                node = node.right;
            }
            else {
                right = mid;
                node = node.left;
            }
        }
        
        return node != null;
    }
}

//solution-2: recursion, time complexity: O(n), space complexity: O(logn)
public class Solution {
    /**
     * @param root: root of complete binary tree
     * @return: the number of nodes
     */
    public int countNodes(TreeNode root) {
        return root == null ? 0 : countNodes(root.left) + countNodes(root.right) + 1;
    }
}
