/***
* LintCode 482. Binary Tree Level Sum
Given a binary tree and an integer which is the depth of the target level.
Calculate the sum of the nodes in the target level.

Example 1:
    Input：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},2
    Output：5 
    Explanation：
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       /       \
      8         9
    2+3=5

Example 2:
    Input：{1,2,3,4,5,6,7,#,#,8,#,#,#,#,9},3
Output：22
Explanation：
         1
       /   \
      2     3
     / \   / \
    4   5 6   7
       /       \
      8         9
    4+5+6+7=22

Link: https://www.lintcode.com/problem/482/
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

//version: BFS
public class Solution {
    /**
     * @param root: the root of the binary tree
     * @param level: the depth of the target level
     * @return: An integer
     */
    public int levelSum(TreeNode root, int level) {
        int result = 0;
        // check corner case
        if (root == null || level == 0) {
            return result;
        }

        // regular case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int height = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();

                if (height == level) {
                    result += current.val;
                    continue;
                }
                

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }

            }

            if (height == level) {
                break;
            }

            height ++;
        }

        return result;
    }
}
