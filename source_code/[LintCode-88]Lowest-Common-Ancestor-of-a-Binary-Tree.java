/***
* LintCode 88. Lowest Common Ancestor of a Binary Tree
Given the root and two nodes in a Binary Tree. 
Find the lowest common ancestor(LCA) of the two nodes.
The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.

Example 1:
    Input:
        tree = {1}
        A = 1
        B = 1
    Output:
        1
    Explanation:
        For the following binary tree（only one node）:
            1
        LCA(1,1) = 1        
        
Example 2:
    Input:
        tree = {4,3,7,#,#,5,6}
        A = 3
        B = 5
    Output:
        4
    Explanation:
        For the following binary tree:
             4
            / \
           3   7
              / \
             5   6
        LCA(3, 5) = 4
***/
/**
* 这是一道相对比较简单的题，因为A，B 这2个点都必定存在于root的二叉树中
* 在分别查找A和B两个节点的格子过程中，只要找到就可以立刻返回，思路比较简单。
**/
//version-1: recursion
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
    /*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if(root == null || A == null || B == null) {
            return null;
        }

        // 如果root为A或者B，立即返回，无需继续向下寻找
        if (A == root || B == root) {//这是本题的关键
            return root;
        }

        // 分别去左右子树查找A和B
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        TreeNode right = lowestCommonAncestor(root.right, A, B);

        // 如果A，B分别存在于两颗子树，root为LCA，返回root
        if (left != null && right != null) {
            return root;
        }

        // 左子树有一个点或者左子树有LCA
        if (left != null) {
            return left;
        }

        // 右子树有一个点或者右子树有LCA
        if (right !== null) {
            return right;
        }

        // 左右子树都没有
        return null;
    }
}