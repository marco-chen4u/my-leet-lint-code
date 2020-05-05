/***
* LintCode 480. Binary Tree Paths
Given a binary tree, return all root-to-leaf paths.
Example
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
 
//version-1
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

//version-2
public class Solution {
    /**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<String>();
        // check corner case
        if (root == null) {
            return result;
        }
        
        List<String> leftPaths = binaryTreePaths(root.left);
        List<String> rightPaths = binaryTreePaths(root.right);
        
        for (String leftPath : leftPaths) {
            result.add(root.val + "->" + leftPath);
        }
        
        for (String rightPath : rightPaths) {
            result.add(root.val + "->" + rightPath);
        }
        
        if (result.size() == 0) {
            result.add(String.valueOf(root.val));
        }
        
        return result;
    }
}