/***
* LintCode 900. Closest Binary Search Tree Value
Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Example1
    Input: root = {5,4,9,2,#,8,10} and target = 6.124780
    Output: 5

Example2
    Input: root = {3,2,4,1} and target = 4.142857
    Output: 4

Notice
    Given target value is a floating point.
    You are guaranteed to have only one unique value in the BST that is closest to the target.
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
//version-1: iteration + looping
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
    private static final int MAX = Integer.MAX_VALUE;

    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        TreeNode lower = null;
        TreeNode upper = null;

        TreeNode current = root;
        while (current != null) {
            if (target < current.val) {
                upper = current;
                current = current.left;
                continue;
            }

            if (target > current.val) {
                lower = current;
                current = current.right;
                continue;
            }

            if (target == current.val) {
                return current.val;
            }
        }

        double lowerDiff = lower != null ? getDiff(lower.val, target) : MAX;
        double upperDiff = upper != null ? getDiff(upper.val, target) : MAX;

        int result= lowerDiff < upperDiff ? lower.val : upper.val;

        return result;
    }

    // helper method
    private double getDiff(int val, double target) {
        return Math.abs(target - val);
    }
}

//version-2: recursion
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        if (root == null) {
            return 0;
        }

        TreeNode lowerNode = lowerBound(root, target);
        TreeNode upperNode = upperBound(root, target);

        double lowerDiff = lowerNode != null ? Math.abs(target - lowerNode.val) : Integer.MAX_VALUE;
        double upperDiff = upperNode != null ? Math.abs(upperNode.val - target) : Integer.MAX_VALUE;

        return lowerDiff < upperDiff ? lowerNode.val : upperNode.val;
    }

    // helper methods
    private TreeNode lowerBound(TreeNode node, double target) {
        if (node == null) {
            return null;
        }

        if (target < node.val) {
            return lowerBound(node.left, target);
        }

        // target >= node.val
        TreeNode lowerNode = lowerBound(node.right, target);
        if (lowerNode != null) {
            return lowerNode;
        }

        return node;
    }

    private TreeNode upperBound(TreeNode node, double target) {
        if (node == null) {
            return null;
        }

        if (target >= node.val) {
            return upperBound(node.right, target);
        }

        // target < node.val
        TreeNode upperNode = upperBound(node.left, target);
        if (upperNode != null) {
            return upperNode;
        }

        return node;
    }
}
//version-3: TreeSet
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        TreeSet<Integer> set = new TreeSet<>();
        dfs(root, set);

        int value = (int)target;
        int lower =set.floor(value);

        int biggest = set.last();

        int upper = (value == Integer.MAX_VALUE || value == biggest) ? biggest: set.ceiling(value + 1);

        double lowDiff = Math.abs(target - lower);
        double upperDiff = Math.abs(upper - target);

        int result = (lowDiff < upperDiff) ? lower : upper;
        return result;
    }

    // helper methods
    private void dfs(TreeNode node, TreeSet<Integer> set) {
        if (node == null) {
            return;
        }

        dfs(node.left, set);
        set.add(node.val);
        dfs(node.right, set);
    }
}

//version-4: binary tree traverse + stack
public class Solution {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        
        double diff = (double)Integer.MAX_VALUE;
        int result = -1;

        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        
        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            int curentVal = current.val;
            if (getDiff(curentVal, target) < diff) {
                result = curentVal;
                diff = getDiff(curentVal, target);
            }

            current = current.right;
        }

        return result;
    }

    // helper method
    private double getDiff(int value, double target) {
        return Math.abs(target - value);
    }
}
