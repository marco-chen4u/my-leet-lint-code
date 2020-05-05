/***
* LintCode 7. Serialize and Deserialize Binary Tree
Design an algorithm and write code to serialize and deserialize a binary tree. 
Writing the tree to a file is called 'serialization' and reading back 
from the file to reconstruct the exact same binary tree is 'deserialization'.

Example
Example 1:
	Input：{3,9,20,#,#,15,7}
	Output：{3,9,20,#,#,15,7}
	Explanation：
		Binary tree {3,9,20,#,#,15,7},  denote the following structure:
			  3
			 / \
			9  20
			  /  \
			 15   7
		it will be serialized {3,9,20,#,#,15,7}

Example 2:
	Input：{1,2,3}
	Output：{1,2,3}
	Explanation：
		Binary tree {1,2,3},  denote the following structure:
		   1
		  / \
		 2   3
		it will be serialized {1,2,3}
		Our data serialization use BFS traversal. 
		This is just for when you got Wrong Answer and want to debug the input.

	You can use other method to do serializaiton and deserialization.

Notice
	There is no limit of how you deserialize or serialize a binary tree, 
	LintCode will take your output of serialize as the input of deserialize, 
	it won't check the result of serialize.
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
//version-1: recursion
public class Solution {
	
	// fields
	private final String EMPTY = "#";
	private final String SEPERATOR = ";";
	
	// helper methods
	private void serializeHelper(TreeNode node, StringBuilder sb) {
		// check corner case
		if (node == null) {
			sb.append(EMPTY).append(SEPERATOR);
			return;
		}
		
		sb.append(node.val).append(SEPERATOR);
		serializeHelper(node.left, sb);
		serializeHelper(node.right, sb);
	}
	
	private TreeNode deserializeHelper(Queue<String> queue) {
		// check corner case
		if (queue.isEmpty()) {
			return null;
		}
		
		String currentVal = queue.poll();
		if (EMPTY.equals(currentVal)) {
			return null;
		}
		
		TreeNode node = new TreeNode(Integer.valueOf(currentVal));
		node.left = deserializeHelper(queue);
		node.right = deserializeHelper(queue);
		
		return node;
	}	
	
	 /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
		// check corner case
		if (root == null) {
			return null;
		}
		
		StringBuilder sb = new StringBuilder();
		serializeHelper(root, sb);
		
		return sb.toString();
	}
	
	/**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
		// check corner case
		if (data == null || data.length() == 0) {
			return null;
		}
		
		Queue<String> queue = new LinkedList<String>();
		queue.addAll(Arrays.asList(data.split(SEPERATOR)));
		TreeNode root = deserializeHelper(queue);
		
		return root;
	}
}