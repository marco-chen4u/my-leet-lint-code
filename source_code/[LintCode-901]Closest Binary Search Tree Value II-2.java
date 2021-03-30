/***
* LintCode 901. Closest Binary Search Tree Value II
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
    // helper method
    private void inOrderTraverse(TreeNode node, List<Integer> values) {
        if (node == null) {
            return;
        }
		
        inOrderTraverse(node.left, values);
        values.add(node.val);
        inOrderTraverse(node.right, values);
    }	
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (root == null || k < 1) {
            return result;
        }
		
        List<Integer> values = new ArrayList<Integer>();
        inOrderTraverse(root, values);
		
        int index = 0; 
        int size = values.size();		
        for (; index < size; index++) {
            if (values.get(index) >= target) {
                break;
            }
        }
		
        if (index > size) {
            values.subList(size - k, size);
        }
		
        int count = 0;
        int left = index - 1; 
        int right = index;
        while (left >= 0 && right < size && count < k) {
            if (Math.abs(target - values.get(left)) < Math.abs(values(right) - target)) {
                result.add(values.get(left--));
            }
            else {
                result.add(values.get(right++));
            }
			
            count++;
        }
		
        while (right < size && count < k) {
            result.add(values.get(right--));
            count++;
        }
		
        while (left >= 0 && count < k) {
            result.add(values.get(left--));
            count++;
        }
		
        return result;
    }
}
