/***
* LintCode 901. Closest Binary Search Tree Value II(k number closest)
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Example
    Given root = {1}, target = 0.000000, k = 1, return [1].

Challenge
    Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Notice
    Given target value is a floating point.
    You may assume k is always valid, that is: k ≤ total nodes.
    You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
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

public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     *          we will sort your return value in output
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // write your code here
        List<Integer> defaultValue = new ArrayList<>();
        // corner case
        if (root == null) {
            return defaultValue;
        }

        List<Integer> values = new ArrayList<>();
        // InOrder Traversal to get all values
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            values.add(current.val);

            current = current.right;
        }

        int size = values.size();

        int index = 0;
        while (index < size) {
            if (values.get(index) >= target) {
                break;
            }

            index++;
        }

        if (index == size) {
            return values.subList(size - k, size);
        }

        int left = index - 1;
        int right = index;

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (isLeftCloseTarget(values, left, right, target)) {
                result.add(values.get(left--));
            }
            else {
                result.add(values.get(right++));
            }
        }

        return result;

    }

    // helper method
    private boolean isLeftCloseTarget(List<Integer> values, int left, int right, double target) {
        
        // corner cases
        if (left < 0) {
            return false;
        }

        if (right >= values.size()) {
            return true;
        }

        // normal case
        return Math.abs(values.get(left) - target) <= Math.abs(values.get(right) - target);
    }
}
