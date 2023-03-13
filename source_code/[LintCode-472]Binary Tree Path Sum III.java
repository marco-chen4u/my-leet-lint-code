/***
* LintCode 472. Binary Tree Path Sum III
Give a binary tree, and a target number, 
find all path that the sum of nodes equal to target, 
the path could be start and end at any node in the tree.

Example
    Example1
        Input:
            tree = {1,2,3,4}
            target = 6
        Output: 
            [
              [2, 4],
              [2, 1, 3],
              [3, 1, 2],
              [4, 2]
            ]
        Explanation:
            The tree is look like this:
                1
               / \
              2   3
             /
            4

    Example2
        Input:
            tree ={1,2,3,4}
            target = 3
        Output: 
            [
                [1,2],
                [2,1],
                [3]
            ]
***/
/**
 * Definition of ParentTreeNode:
 * 
 * class ParentTreeNode {
 *     public int val;
 *     public ParentTreeNode parent, left, right;
 * }
 */
public class Solution {
    
    /*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum3(ParentTreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        find(result, root, target);
        
        return result;
    }
	
    // helper methods
    private void find(List<List<Integer>> result,
                      ParentTreeNode node,
                      int target) {
        if (node == null) {
            return;
        }
        
        List<Integer> path = new ArrayList<Integer>();
        findBinaryTreePathSum(result, path, node, null, target);
        
        find(result, node.left, target);
        find(result, node.right, target);
    }

    private void findBinaryTreePathSum(List<List<Integer>> result,
                                            List<Integer> path,
                                            ParentTreeNode node,
                                            ParentTreeNode parent,
                                            int target) {
        if (node == null) {
            return;
        }
        
        path.add(node.val);
        
        target -= node.val;
        if (target == 0) {
            result.add(new ArrayList<Integer>(path));
        }
        
        if (node.parent != null && node.parent != parent) {
            findBinaryTreePathSum(result, path, node.parent, node, target);
        }
        
        if (node.left != parent) {
            findBinaryTreePathSum(result, path, node.left, node, target);
        }
        
        if (node.right != parent) {
            findBinaryTreePathSum(result, path, node.right, node, target);
        }
        
        path.remove(path.size() -1);
    }
}
