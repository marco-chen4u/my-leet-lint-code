/***
* LintCode 1122. Add One Row to Tree
Given the root of a binary tree, then value v and depth d, 
you need to add a row of nodes with value v at the given depth d. The root node is at depth 1.

The adding rule is: 
    Given a positive integer depth d, for each NOT null tree nodes N in depth d-1, 
    create two tree nodes with value v as N's left subtree root and right subtree root. 

    And N's original left subtree should be the left subtree of the new left subtree root, 
    its original right subtree should be the right subtree of the new right subtree root. 

    If depth d is 1 that means there is no depth d-1 at all, 
    then create a tree node with value v as the new root of the whole original tree, 
    and the original tree is the new root's left subtree.

Example 1:
    Input: 
        A binary tree as following:
               4
             /   \
            2     6
           / \   / 
          3   1 5   
        v = 1
        d = 2
    Output:
               4
              / \
             1   1
            /     \
           2       6
          / \     / 
         3   1   5  

Example 2:
    Input:
        A binary tree as following:
               4
             /   \
            2     6
           / \   / 
          3   1 5   
        v = 2
        d = 1
    Output:
                 2
  	        /
               4
             /   \
            2     6
           / \   / 
          3   1 5   
***/
Solution: BFS
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
     * @param root: the root of binary tree
     * @param v: a integer
     * @param d: a integer
     * @return: return a TreeNode
     */
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        // check corner cases
        if (root == null) {
            return root;
        }

        if (d == 1) {
            TreeNode node = new TreeNode(v);
            node.left = root;
            return node;
        }

        // regular case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int currentDepth = 2;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i ++) {
                TreeNode currentNode = queue.poll();

                if (currentDepth == d) {
                    TreeNode leftNode = new TreeNode(v);
                    leftNode.left = currentNode.left;
                    currentNode.left = leftNode;

                    TreeNode rightNode = new TreeNode(v);
                    rightNode.right = currentNode.right;
                    currentNode.right = rightNode;

                    continue;
                }

                if (currentNode.left != null) {
                    queue.offer(currentNode.left);
                }

                if (currentNode.right != null) {
                    queue.offer(currentNode.right);
                }
            }

            currentDepth++;
        }

        return root;
    }
}
