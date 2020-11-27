/***
* LintCode 902. Kth Smallest Element in a BST
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example
    Given root = {1,#,2}, k = 2, return 2.

Challenge
    What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? 
    How would you optimize the kthSmallest routine?

Notice
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
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
// version-2: recursion(divide&conquer) with HashMap(keep record of children count by current node)
public class Solution {

    // helper methods
    private int countNodes(TreeNode node, Map<TreeNode, Integer> numberOfNodesMap) {
        if (node == null) {
            return 0;
        }

        // divide
        int countOfLeftChildren = countNodes(node.left, numberOfNodesMap);
        int countOfRightChidren = countNodes(node.right, numberOfNodesMap);

        // conquer
        int countOfNodes = 1 + countOfLeftChildren + countOfRightChidren;
        numberOfNodesMap.put(node, countOfNodes);

        return countOfNodes;
    }

    private int getKthElementValue(TreeNode node, int k, numberOfNodesMap) {
        int result = 0;//default value
        if (node == null) {
            return 0;
        }

        int countOfLeftChildren = (node.left == null) ? 0 : numberOfNodesMap.get(node.left);

        if (countOfLeftChildren + 1 == k) {
            return node.val;
        }
        else if (countOfLeftChildren >= k) {
            return getKthElementValue(node.left, k, numberOfNodesMap);
        }
        else if (countOfLeftChildren < k) {
            return getKthElementValue(node.right, k - (countOfLeftChildren + 1), numberOfNodesMap);
        }		

        return result;
    }

    /**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // check corner case
        if (root == null || k < 1) {
            return 0;
        }

        Map<TreeNode, Integer> numberOfNodesMap = new HashMap<TreeNode, Integer>();
        countNodes(root, numberOfNodesMap);

        return getKthElementValue(root, k, numberOfNodesMap);
    }
}
