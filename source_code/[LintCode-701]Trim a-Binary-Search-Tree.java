/**
* LintCode 701 · Trim a Binary Search Tree
Given the root of a binary search tree and 2 numbers min and max, 
trim the tree such that all the numbers in the new tree are between min and max (inclusive).
The resulting tree should still be a valid binary search tree. So, if we get this tree as input:
                      8
                    /   \
                   3     10
                  /  \     \    
                 1    6     14
                     /  \   /
                    4    7 13

and we’re given min value as 5 and max value as 13, then the resulting binary search tree should be:
                      8
                    /   \
                   6    10
                    \     \    
                     7     13

Example 1
    Input: 
        {8,3,10,1,6,#,14,#,#,4,7,13}
        5
        13
    Output: {8, 6, 10, #, 7, #, 13}
    Explanation:
        The picture of tree is in the description.

Example 2
    Input: 
        {1,0,2}
        1
        2
    Output: {1,#,2}
    Explanation:
        Input is 
          1
         / \
        0   2
        Output is
          1
           \
            2
**/

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

//version-1: recursion, devide and conquer
public class Solution {
    /**
     * @param root: given BST
     * @param minimum: the lower limit
     * @param maximum: the upper limit
     * @return: the root of the new tree 
     */
    public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        // check corner cases
        if (root == null) {
            return null;
        }

        if (root.val < minimum) {
            return trimBST(root.right, minimum, maximum);
        }

        if (maximum < root.val) {
            return trimBST(root.left, minimum, maximum);
        }

        TreeNode left = trimBST(root.left, minimum, maximum);
        TreeNode right = trimBST(root.right, minimum, maximum);

        root.left = left;
        root.right = right;

        return root;
    }
}
