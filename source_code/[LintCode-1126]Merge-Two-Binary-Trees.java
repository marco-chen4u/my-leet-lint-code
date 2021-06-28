/***
* LintCode 1126. Merge Two Binary Trees
Given two binary trees and imagine that when you put one of them to cover the other, 
some nodes of the two trees are overlapped while the others are not.

You need to merge them into a new binary tree. 
The merge rule is that if two nodes overlap, 
then sum node values up as the new value of the merged node. 
Otherwise, the NOT null node will be used as the node of new tree.

Note: The merging process must start from the root nodes of both trees.

Example 1:
    Input: 
        {1,3,2,5}
        {2,1,3,#,4,#,7}
    Output:
        {3,4,5,5,4,#,7}
    Explanation:
	    Tree 1                     Tree 2                  
              1                         2                             
             / \                       / \                            
            3   2                     1   3                        
           /                           \   \                      
          5                             4   7                  

Merged tree:
	     3
	    / \
	   4   5
	  / \   \ 
	 5   4   7


Example 2:
    Input: 
        {1}
        {1,2}
    Output:
        {2,2}

Link: https://www.lintcode.com/problem/1126/
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

// version-1: recuirsion, divid&conquer
public class Solution {
    /**
     * @param t1: the root of the first tree
     * @param t2: the root of the second tree
     * @return: the new binary tree after merge
     */
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        // check corner cases
        if (t1 == null && t2 == null) {
            return null;
        }

        if (t1 == null) {
            return t2;
        }

        if (t2 == null) {
            return t1;
        }

        // normal case
        TreeNode node = new TreeNode(t1.val + t2.val);
        // divide
        node.left = mergeTrees(t1.left, t2.left);
        node.right = mergeTrees(t1.right, t2.right);

        // conquer
        return node;
    }
}
