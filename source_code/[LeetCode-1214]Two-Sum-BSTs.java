/***
* LeetCode 1214. Two Sum BSTs
Given the roots of two binary search trees, root1 and root2, 
return true if and only if there is a node in the first tree 
and a node in the second tree 
whose values sum up to a given integer target.

Example 1
                2                     0
              /   \                 /   \
             1     4               1     3
    Input: root1 = [2,1,4], root2 = [1,0,3], target = 5
    Output: true
    Explanation: 2 and 3 sum up to 5.

Example 2
               -10                    5
              /    \                /   \
             0      10             1     7
                                 /   \
                                0     2 
    Input: root1 = [0,-10,10], root2 = [5,1,7,0,2], target = 18
    Output: false

LeetCode link: https://leetcode.com/problems/two-sum-bsts/
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

//version-1: in-order traverse(non-recursion) + hashset, time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }

        Set<Integer> set1 = inOrderTraverse(root1);
        Set<Integer> set2 = inOrderTraverse(root2);

        for (int value : set2) {
            int candidate = target - value;
            if (set1.contains(candidate)) {
                return true;
            }
        }

        return false;
    }

    private Set<Integer> inOrderTraverse(TreeNode node) {
        Set<Integer> set = new HashSet<>();
        TreeNode current = node;
        Stack<TreeNode> stack = new Stack<>();

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            set.add(current.val);

            current = current.right;
        }

        return set;
    }
}
Console

//version-1: in-order traverse(recursion) + two pointer, time complexity: O(n), space complexity: O(n)
class Solution {
    public boolean twoSumBSTs(TreeNode root1, TreeNode root2, int target) {
        if (root1 == null || root2 == null) {
            return false;
        }

        List<Integer> l1 = inOrderTraverse(root1);
        List<Integer> l2 = inOrderTraverse(root2);

        int left = 0; 
        int right = l2.size() - 1;

        while (left < l1.size() &&  right >= 0) {
            int sum = l1.get(left) + l2.get(right);
            if (sum < target) {
                left++;
                continue;
            }

            if (sum > target) {
                right--;
                continue;
            }

            if (sum == target) {
                return true;
            }
        }

        return false;
    }

    // helper method
    private List<Integer> inOrderTraverse(TreeNode node) {
        if (node == null) {
            return Collections.EMPTY_LIST;
        }

        List<Integer> result = new ArrayList<>();
        List<Integer> leftValues = inOrderTraverse(node.left);
        result.addAll(leftValues);
        result.add(node.val);
        List<Integer> rightValues = inOrderTraverse(node.right);
        result.addAll(rightValues);

        return result;
    }
}
