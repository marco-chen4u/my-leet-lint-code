/***
* LintCode 1094. Second Minimum Node In a Binary Tree
Given a non-empty special binary tree consisting of nodes with the non-negative value, 
where each node in this tree has exactly two or zero sub-node. 

If the node has two sub-nodes, then this node's value is not bigger than its two sub-nodes.

Given such a binary tree, 
you need to output the second minimum value in the set made of all the nodes' value in the whole tree.

If no such second minimum value exists, output -1 instead.

Example 1:
    Input: 
        2
       / \
      2   5
         / \
        5   7

    Output: 5
    Explanation: The smallest value is 2, the second smallest value is 5.

Example 2:
    Input: 
        2
       / \
      2   2

    Output: -1
    Explanation: The smallest value is 2, but there isn't any second smallest value.

Link: https://www.lintcode.com/problem/1094/
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
//version-1: non-recursion, preorder, with 1st min and 2nd min values to check
public class Solution {
    
    private final int DEFAULT_VALUE = Integer.MAX_VALUE;

    /**
     * @param root: the root
     * @return: the second minimum value in the set made of all the nodes' value in the whole tree
     */
    public int findSecondMinimumValue(TreeNode root) {
        // check corner cases
        if (root == null) {
            return -1;
        }

        int min1 = DEFAULT_VALUE;
        int min2 = DEFAULT_VALUE;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }

            int currentVal = current.val;
            
            if (currentVal < min1) {
                min1 = currentVal;
                continue;
            }

            if (currentVal < min2 && currentVal > min1) {
                min2 = currentVal;
            }
        }

        return min2 == DEFAULT_VALUE ? -1 : min2;
    }
}

//version-2:no-recursion, preorder traversal, maxHeap + Set(to remove duplicate)
public class Solution {
    /**
     * @param root: the root
     * @return: the second minimum value in the set made of all the nodes' value in the whole tree
     */
    public int findSecondMinimumValue(TreeNode root) {
        // check corner case
        if (root == null) {
            return -1;
        }

        if (root.left == null && root.right == null) {
            return -1;
        }

        Set<Integer> set = new HashSet<>();
        Queue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();

            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }

            int currentVal = current.val;
            if (set.contains(currentVal)) {
                continue;
            }

            set.add(currentVal);
            maxHeap.offer(currentVal);

            if (maxHeap.size() > 2) {
                maxHeap.poll();
            }
        }

        //System.out.println("set = " + set);
        //System.out.println("maxHeap = " + maxHeap);

        if (maxHeap.isEmpty() || maxHeap.size() == 1) {
            return -1;
        }

        return maxHeap.peek();
    }
}
