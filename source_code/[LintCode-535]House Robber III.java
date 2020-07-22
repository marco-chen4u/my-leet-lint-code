/***
* LintCode 535. House Robber III
The thief has found himself a new palce for his thievery again. 
There's only one entrance to this area called the "root". 
Besides the root, every house has one and only one parent house. 
After a toure, the smart thief realized that "all houses in thise place forms a binary tree". 
It will automatically contact the plice when two directly-linked houses were broken into on the same night.

Determine the maximum amount of money the thief can rob tonight without alerting the police.

Example
    Example1
        Input:  {3,2,3,#,3,#,1}
        Output: 7
        Explanation:
            Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
              3<-
             / \
            2   3
             \   \ 
            ->3   1<-
  
    Example2
        Input:  {3,4,5,1,3,#,1}
        Output: 9
        Explanation:
            Maximum amount of money the thief can rob = 4 + 5 = 9.
                3
               / \
            ->4   5<-
             / \   \ 
            1   3   1
Note
    This problem is the extension of House Robber and House Robber II.
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
 
class ResultType {
    // fields
    public int rob;//max money to get when current house in robbed state
    public int notRob;// max money to get when current house in not robbed state
    
    // constructor
    public ResultType() {
        this.rob = 0;
        this.notRob = 0;
    }
}

public class Solution {
    /**
     * @param root: The root of binary tree.
     * @return: The maximum amount of money you can rob tonight
     */
    public int houseRobber3(TreeNode root) {
        ResultType result = visit(root);
        return Math.max(result.rob, result.notRob);
    }
    
    // helper method
    private ResultType visit(TreeNode node) {
        ResultType result = new ResultType();
        // check corner case
        if (node == null) {
            return result;
        }
        
        ResultType left = visit(node.left);
        ResultType right = visit(node.right);
        
        // if current house robed
        result.rob = node.val + left.notRob + right.notRob;
        
        // if current house not robbed
        result.notRob = Math.max(left.rob, left.notRob) + Math.max(right.rob, right.notRob);
        
        return result;
    }
}
