/***
* LintCode 578. Lowest Common Ancestor III
Given the root and two nodes in a Binary Tree. 
Find the lowest common ancestor(LCA) of the two nodes.

The lowest common ancestor is the node with largest depth which is the ancestor of both nodes.
Return null if LCA does not exist.

Example
	Example1
		Input: 
			{4, 3, 7, #, #, 5, 6}
			3 5
			5 6
			6 7 
			5 8
		Output: 
			4
			7
			7
			null
		Explanation:
			  4
			 / \
			3   7
			   / \
			  5   6

			LCA(3, 5) = 4
			LCA(5, 6) = 7
			LCA(6, 7) = 7
			LCA(5, 8) = null

	Example2
		Input:
			{1}
			1 1
		Output: 
			1

Notice
	node A or node B may not exist in tree.
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
 // version-2: recursion
public class Solution {
	
	// helper method
	private void preOrderTraverse(TreeNode node, TreeNode parent, Map<TreeNode, TreeNode> map) {
		if (node == null) {
			return;
		}
		
		map.put(node, parent);
		preOrderTraverse(node.left, node, map);
		preOrderTraverse(node.right, node, map);
	}
	
    /*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
		TreeNode result = null; // default value
        // check corner case
		if (root == null) {
			return result;
		}
		
		Map<TreeNode, TreeNode> parentMap = new HashMap<TreeNode, TreeNode>();
		Set<TreeNode> ancestorSet = new HashSet<TreeNode>();
		
		// (1) get all the node into the parent map 
		preOrderTraverse(root, null, parentMap);
		
		// if one of [A,B] dose not belong to this binary tree
		if (!parentMap.containsKey(A) || !parentMap.containsKey(B)) {
			return result;
		}
		
		// (2)get A's all ancestors
		while (A != null) {
			ancestorSet.add(A);
			A = parentMap.get(A);// get A's direct parent
		}
		
		// (3) find the first common ancestor of A and B
		while (!ancestorSet.contains(B)) {
			ancestorSet.add(B);
			B = parentMap.get(B);// get B's direct parent
		}
		
		if (B != null) {
			result = B;
		}
		
		return result;
    }
}