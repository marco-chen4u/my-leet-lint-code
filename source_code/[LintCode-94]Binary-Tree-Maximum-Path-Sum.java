/***
* LintCode 94. Binary Tree Maximum Path Sum
Given a binary tree, find the maximum path sum.
The path may start and end at any node in the tree.
(Path sum is the sum of the weights of nodes on the path between two nodes.)

Example 1
    Input:
        tree = {1,-2,-3,1,3,-2,#,1}
    Output:
        3
    Description:
             1
           /   \ 
         -2     -3
        /  \    /
       1    3  -2
      /
     1
Example 2
    Input:
        [2,-1]
    Output:
        2
            
    Descirption:
             2
           /
         -1    

Example 3
    Input:
        [-10,9,20,#,#,15,7]
    Output:
        42
    Description:
            -10
           /   \ 
          9     20
                / \
               15  7

Example 4
    Input:
        [1, 2, 3]
    Output:
        6
    Description:
             1
           /  \
          2    3
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
//version-1: Recursion(Divide&Conquer)
public class Solution {

    // inner class
    class ResultType {
        // fields
        int singlePathSum;
        int maxPathSum;

        // constructor
        public ResultType(int singlePathSum, int maxPathSum) {
            this.singlePathSum = singlePathSum;
            this.maxPathSum = maxPathSum;
        }
    }

    /**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
        ResultType result = find(root);

        return result.maxPathSum;
    }

    // helper method
    private ResultType find(TreeNode node) {
        if (node == null) {
            return new ResultType(0, Integer.MIN_VALUE);
        }

        ResultType left = find(node.left);
        ResultType right = find(node.right);

        int singlePathSum = Math.max(left.singlePathSum, right.singlePathSum) + node.val;
        singlePathSum = Math.max(singlePathSum, 0);

        int allPathSum = left.singlePathSum + node.val + right.singlePathSum;
        int maxPathSum = Math.max(left.maxPathSum, right.maxPathSum);
        maxPathSum = Math.max(maxPathSum, allPathSum);

        return new ResultType(singlePathSum, maxPathSum);
    }
}
