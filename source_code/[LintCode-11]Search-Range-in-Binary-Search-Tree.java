/***
* LintCode 11. Search Range in Binary Search Tree
Given a binary search tree and a range [k1, k2], return node values within a given range in ascending order.

Example 1
    Input:
        tree = {5}
        k1 = 6
        k2 = 10
    Output:
        []
    Explanation:
        No number between 6 and 10

Example 2
    Input:
        tree = {20,8,22,4,12}
        k1 = 10
        k2 = 22
    Output:
        [12,20,22]
    Explanation:
        [12,20,22] between 10 and 22
 
***/

//version-1: non-recursion, InOrder traversal
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
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        List<Integer> result = new ArrayList<>();
        // corner case
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (current.val >= k1 && current.val <= k2) {
                result.add(current.val);
            }

            current = current.right;
        }

        return result;
    }
}

//version-2: recursion(divide&conquer) , InOrder traversal
public class Solution {
    /**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        List<Integer> leftValues = searchRange(root.left, k1, k2);
        List<Integer> rightValues = searchRange(root.right, k1, k2);

        if (!leftValues.isEmpty()) {
            result.addAll(leftValues);
        }
        
        int currentValue = root.val;
        //System.out.println("currentVal = " + currentValue + ", k1= " + k1 + ", k2 = " + k2);
        if (currentValue >= k1 && currentValue <= k2) {
            result.add(currentValue);
        }

        if (!rightValues.isEmpty()) {
            result.addAll(rightValues);
        }    

        return result;
    }
}
