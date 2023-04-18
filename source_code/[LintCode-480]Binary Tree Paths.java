/***
* LintCode 480. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.

Example 1:
    Input：{1,2,3,#,5}
    Output：["1->2->5","1->3"]
    Explanation：
           1
         /   \
        2     3
         \
          5	  
Example 2:
    Input：{1,2}
    Output：["1->2"]
    Explanation：
           1
         /   
        2     
***/
/*
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
//version-1: recursion
public class Solution {
    private final String SEPARATOR = "->";

    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        // check corner case
        if (root == null) {
            return result;
        }

        String path = String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            result.add(path);
            return result;
        }

        // normal case
        if (root.left != null) {
            helper(result, path, root.left);
        }

        if (root.right != null) {
            helper(result, path, root.right);
        }

        return result;
    }

    // helper method
    private void helper(List<String> result, String path, TreeNode node) {
        path += SEPARATOR + String.valueOf(node.val);

        if (node.left == null && node.right == null) {
            result.add(path);
            return;
        }

        if (node.left != null) {
            helper(result, path, node.left);
        }

        if (node.right != null) {
            helper(result, path, node.right);
        }
    }
}

//version-2: recurision with Divide&Conquer
public class Solution {
    private final String SEPARATOR = "->";

    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();
        // check corner cases
        if (root == null) {
            return result;
        }

        int currentVal = root.val;
        if (root.left == null && root.right == null) {
            result.add(String.valueOf(currentVal));
            return result;
        }

        // regular case
        // divide
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);

        // conquer
        for (String path : leftPaths) {
            result.add(currentVal + SEPARATOR + path);
        }

        for (String path : rightPaths) {
            result.add(currentVal + SEPARATOR + path);
        }

        // return
        return result;
    }
}

//version-3: recursion
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        StringBuilder path = new StringBuilder();
        helper(result, path, root);
        
        return result;
    }
    
    private void helper(List<String> result, StringBuilder path, TreeNode node) {
        if (node == null) {
            return;
        }
        
        String value = String.valueOf(node.val);
        if (path.length() != 0) {
            value = "->" + value;
        }
        
        path.append(value);        
        if (node.left == null && node.right == null) {
            result.add(path.toString());
        }
        else {
            helper(result, path, node.left);
            helper(result, path, node.right);
        }        
        path.delete(path.length() - value.length(), path.length());
    }
}
