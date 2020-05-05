/***
* LintCode 1108. Find Duplicate Subtrees
Given a binary tree, return all duplicate subtrees. 
For each kind of duplicate subtrees, you only need to return the root node of any one of them.
Two trees are duplicate if they have the same structure with same node values.
Example
	Example 1:

			1
		   / \
		  2   3
		 /   / \
		4   2   4
		   /
		  4
		The following are two duplicate subtrees:

		  2
		 /
		4
		and

		4
		Therefore, you need to return above trees' root in the form of a list.
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
//version-1:hashmap+hashmap
public class Solution {
	
	//fields
	private final String EMPTY = "#";
	private final String SEPERATOR = ";";
	
	// helper method
	private String serializeHelper(List<TreeNode> result, 
									TreeNode node, 
									Map<TreeNode, String> map, 
									Map<String, Integer> mem) {
		// check corner case
		if (node == null) {
			return EMPTY;
		}
		
		if (map.containsKey(node)) {
			return map.get(node);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(node.val).append(SEPERATOR);
		sb.append(serializeHelper(result, node.left, map, mem));
		sb.append(SEPERATOR);
		sb.append(serializeHelper(result, node.right, map, mem));
		
		String serial = sb.toString();
		int count = mem.getOrDefault(serial, 0);
		if (count == 1) {
			result.add(node);
		}
		count++;
		
		map.put(node, serial);
		mem.put(serial, count);	
		
		return serial;
	}
    /**
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> result = new ArrayList<TreeNode>();
		// check corner case
		if (root == null) {
			return result;
		}
		
		Map<TreeNode, String> map = new HashMap<TreeNode, String>();
		Map<String, Integer> mem = new HashMap<String, Integer>();
		
		serializeHelper(result, root, map, mem);
		
		return result;
    }
}

//version-2: hashmap+hashset
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
     * @paramn n: An integer
     * @return: A list of root
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        // write your code here
        List<TreeNode> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        
        Map<String, TreeNode> dupTreeToRoot = new HashMap<>();
        Set<String> set = new HashSet<>();
        printTree(root, set, dupTreeToRoot);
        
        for (TreeNode node : dupTreeToRoot.values()){
            list.add(node);
        }
        
        return list;
    }   
    
    private String printTree(TreeNode root, Set<String> set, Map<String, TreeNode> dupTreeToRoot) {
        if (root == null) {
            return "";
        }
        
        String left = printTree(root.left, set, dupTreeToRoot);
        String right = printTree(root.right, set, dupTreeToRoot);
        String currTree = left + root.val + right;
        if (dupTreeToRoot.containsKey(currTree)) {
            return currTree;
        }
        
        if (set.contains(currTree)) {
            dupTreeToRoot.put(currTree, root);
            return currTree;
        }
        
        set.add(currTree);
        return currTree;
    }
}