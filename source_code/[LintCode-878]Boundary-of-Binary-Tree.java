/**
* LintCode 878 · Boundary of Binary Tree
Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root. 
Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.

Left boundary is defined as the path from root to the left-most node. 
Right boundary is defined as the path from root to the right-most node. 
If the root doesn't have left subtree or right subtree, 
then the root itself is left boundary or right boundary. 
Note this definition only applies to the input binary tree, and not applies to any subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists. 
If not, travel to the right subtree. Repeat until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1:
    Input: {1,#,2,3,4}
    Output: [1,3,4,2]
    Explanation: 
        1
         \
          2
         / \
        3   4
        The root doesn't have left subtree, so the root itself is left boundary.
        The leaves are node 3 and 4.
        The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
        So order them in anti-clockwise without duplicates and we have [1,3,4,2].
  
Example 2:
    Input: {1,2,3,4,5,6,#,#,#,7,8,9,10}
    Output: [1,2,4,7,8,9,10,6,3]
    Explanation: 
                1                
           /          \
          2            3
         / \          / 
        4   5        6   
           / \      / \
          7   8    9  10  
        The left boundary are node 1,2,4. (4 is the left-most node according to definition)
        The leaves are node 4,7,8,9,10.
        The right boundary are node 1,3,6,10. (10 is the right-most node).
        So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
        
Link: https://www.lintcode.com/problem/878/

related:
    LintCode 760 · Binary Tree Right Side View https://www.lintcode.com/problem/760
**/
//version-1: dfs+recursion
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
     * @param root: a TreeNode
     * @return: a list of integer
     */
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // corner case
        if (root == null) {
            return result;
        }

        result.add(root.val);
        if (isLeaf(root)) {
            return result;
        }

        dfsLeft(root.left, result);
        dfsLeaf(root, result);
        dfsRight(root.right, result);

        return result;
    }

    // helper methods
    private void dfsLeft(TreeNode node, List<Integer> result) {
        if (node == null || isLeaf(node)) {
            return;
        }

        result.add(node.val);

        TreeNode next = node.left != null ? node.left : node.right;
        dfsLeft(next, result);
    }

    private void dfsLeaf(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }

        if (isLeaf(node)) {
            result.add(node.val);
            return;
        }

        dfsLeaf(node.left, result);
        dfsLeaf(node.right, result);
    }

    private void dfsRight(TreeNode node, List<Integer> result) {
        if (node == null || isLeaf(node)) {
            return;
        }

        TreeNode next = node.right != null ? node.right : node.left;
        dfsRight(next, result);
        
        result.add(node.val);
    }

    private boolean isLeaf(TreeNode node) {
        if (node == null) {
            return false;
        }
        return node.left == null && node.right == null;
    }
}
