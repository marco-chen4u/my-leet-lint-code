/***
* LeetCode 111. Minimum Depth of Binary Tree
Given a binary tree, find its minimum depth.
The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
Note: A leaf is a node with no children.

Example 1
                3
              /   \
             9     20
                  /   \
                15     7     
    Input: root = [3,9,20,null,null,15,7]
    Output: 2
    
Example 2
                2
                  \
                    3
                     \
                       4
                        \
                         5
                          \
                           6
    Input: root = [2,null,3,null,4,null,5,null,6]
    Output: 5
    
Constraints:
    The number of nodes in the tree is in the range [0, 10^5].
    -1000 <= Node.val <= 1000
    
* LintCode link: https://www.lintcode.com/problem/155/
* LeetCode link: https://leetcode.com/problems/minimum-depth-of-binary-tree/
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
//verion-1: recursion(devide & conquer)
class Solution {
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = root.left == null ? Integer.MAX_VALUE : minDepth(root.left);
        int right = root.right == null ? Integer.MAX_VALUE : minDepth(root.right);

        return 1 + Math.min(left, right);
    }
}

//version-2: inner class + BFS
class Solution {
    class Element {
        TreeNode node;
        int depth;

        public Element(TreeNode node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(root, 1));

        int minDepth = 0;
        while (!queue.isEmpty()) {
            Element current = queue.poll();
            TreeNode node = current.node;
            int currentDepth = current.depth;

            if (node.left == null && node.right == null) {
                minDepth = currentDepth;
                break;
            }

            if (node.left != null) {
                queue.offer(new Element(node.left, currentDepth + 1));
            }

            if (node.right != null) {
                queue.offer(new Element(node.right, currentDepth + 1));
            }
        }

        return minDepth;
    }
}
