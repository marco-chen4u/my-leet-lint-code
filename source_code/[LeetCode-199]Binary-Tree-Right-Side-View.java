/***
* LeetCode 199. Binary Tree Right Side View
Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

Example:
    Input: [1,2,3,null,5,null,4]
    Output: [1, 3, 4]
    Explanation:

       1            <---
     /   \
    2     3         <---
     \     \
      5     4       <---
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
//solution-1: BFS, level orientation traversal of the binary tree
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        // regular case
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int lastValue = - 1;
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                
                if (i == last) {
                    result.add(current.val);
                }
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                lastValue = current.val;
            }
            
            result.add(lastValue);
        }
        
        return result;
    }
}

//solution-2: DFS
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        traverse(result, root, 0);
        
        return result;
    }
    
    // helper method
    private void traverse(List<Integer> result, TreeNode node, int height) {
        // check corner case
        if (node == null) {
            return;
        }
        
        if (result.size() <= height) {
            result.add(node.val);
        }
        
        traverse(result, node.right, height + 1);
        traverse(result, node.left, height + 1);
    }
}
