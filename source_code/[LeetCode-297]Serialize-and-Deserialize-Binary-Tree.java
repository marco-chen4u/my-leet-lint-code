/***
* LeetCode 297. Serialize and Deserialize Binary Tree
Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, 
or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. 
You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, 
so please be creative and come up with different approaches yourself.

Example 1:
    Input：{1,2,3,null,null,4,5}
    Output：{1,2,3,null,null,4,5}
    Explanation：
        Binary tree {1,2,3,#,#,4,5},  denote the following structure:
              1
             / \
            2  3
              /  \
             4    5
        it will be serialized {1,2,3,#,#,4,5}

Example 2:
    Input: root = []
    Output: []

Constraints:
    The number of nodes in the tree is in the range [0, 104].
    -1000 <= Node.val <= 1000
    
*Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/description/
***/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//version-1: recursion
public class Codec {

    // constants
    private static final String EMPTY = "null";
    private static final String SEPARATOR = ",";

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return EMPTY + SEPARATOR;
        }

        return root.val + SEPARATOR + serialize(root.left) + serialize(root.right);        
    }

    private void doSerialize(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(EMPTY).append(SEPARATOR);
            return;
        }

        sb.append(node.val).append(SEPARATOR);

        doSerialize(node.left, sb);
        doSerialize(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if (data == null || data.isEmpty()) {
            return null;
        }

        String[] tokens = data.split(SEPARATOR);
        List<String> tokenList = Arrays.asList(tokens);
        Queue<String> queue = new LinkedList<>(tokenList);

        TreeNode root = doDeserialize(queue);
        
        return root;
    }

    private TreeNode doDeserialize(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        String current = queue.poll();

        if (EMPTY.equals(current)) {
            return null;
        }

        int value = Integer.valueOf(current);
        TreeNode node = new TreeNode(value);
        TreeNode left = doDeserialize(queue);
        TreeNode right = doDeserialize(queue);

        node.left = left;
        node.right = right;

        return node;
    }

}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
