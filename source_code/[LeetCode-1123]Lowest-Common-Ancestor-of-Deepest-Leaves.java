/***
* LeetCode 1123. Lowest Common Ancestor of Deepest Leaves
Given the root of a binary tree, return the lowest common ancestor of its deepest leaves.

Recall that:
    The node of a binary tree is a leaf if and only if it has no children
    The depth of the root of the tree is 0. if the depth of a node is d, the depth of each of its children is d + 1.
    The lowest common ancestor of a set S of nodes, is the node A with the largest depth such that every node in S is in the subtree with root A.

Example 1
                            3
                        /      \
                       5         1
                     /   \     /   \
                    6     2    0    8
                        /   \
                       7     4
    Input: root = [3,5,1,6,2,0,8,null,null,7,4]
    Output: [2,7,4]
        Explanation: We return the node with value 2, colored in yellow in the diagram.
        The nodes coloured in blue are the deepest leaf-nodes of the tree.
        Note that nodes 6, 0, and 8 are also leaf nodes, but the depth of them is 2, but the depth of nodes 7 and 4 is 3.
        
Example 2
    Input: root = [1]
    Output: [1]
    Explanation: The root is the deepest node in the tree, and it's the lca of itself.

Example 3
    Input: root = [0,1,3,null,2]
    Output: [2]
    Explanation: The deepest leaf node in the tree is 2, the lca of one node is itself.
    
Constraints:
    The number of nodes in the tree will be in the range [1, 1000].
    0 <= Node.val <= 1000
    The values of the nodes in the tree are unique.
 

Note: This question is the same as 865: https://leetcode.com/problems/smallest-subtree-with-all-the-deepest-nodes/
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
class Solution {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        int height = getHeight(root);

        List<List<TreeNode>> nodesList = getLevelTraversalList(root);
        List<TreeNode> lowestLevelNodes = nodesList.get(height - 1);
        return findLCA(root, lowestLevelNodes);
    }

    private TreeNode findLCA(TreeNode root, List<TreeNode> nodes) {
        if (root == null) {
            return null;
        }

        for (TreeNode node : nodes) {
            if (root == node) {
                return root;
            }
        }

        TreeNode left = findLCA(root.left, nodes);
        TreeNode right = findLCA(root.right, nodes);

        if (left != null && right != null) {
            return root;
        }

        if (left == null) {
            return right;
        }

        if (right == null) {
            return left;
        }

        return null;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = getHeight(root.left);
        int right = getHeight(root.right);

        return 1 + Math.max(left, right);
    }

    private List<List<TreeNode>> getLevelTraversalList(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            
            List<TreeNode> currentLevelNodes = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                currentLevelNodes.add(current);

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            result.add(currentLevelNodes);
        }

        return result;
    }
}
