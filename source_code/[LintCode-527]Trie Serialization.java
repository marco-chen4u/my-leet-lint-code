/***
* LintCode 527. Trie Serialization
Serialize and deserialize a trie (prefix tree, search on internet for more details).

You can specify your own serialization algorithm, the online judge only cares about whether you can successfully deserialize the output from your own serialize function.

You only need to implement these two functions serialize and deserialize. We will run the following code snippet

str = serialize(old_trie)
// str can be any string used to represent this tree
new_trie = deserialize(str)
// The new tree should be identical to the old one

Example
    Example 1:
        Input: <a<b<e<>>c<>d<f<>>>>
        Output: <a<b<e<>>c<>d<f<>>>>
        Explanation:
            The trie is look like this.
                 root
                  /
                 a
               / | \
              b  c  d
             /       \
            e         f
    Example 2:
        Input: <a<>>
        Output: <a<>>
	
Notice
    You don't have to serialize like the test data, you can design your own format.
***/
/**
 * Definition of TrieNode:
 * public class TrieNode {
 *     public NavigableMap<Character, TrieNode> children;
 *     public TrieNode() {
 *         children = new TreeMap<Character, TrieNode>();
 *     }
 * }
 */


class Solution {
    // fields
    private final char SEPERATOR_START = '<';
    private final char SEPERATOR_END = '>';

    private final String EMPTY = "<>";

    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a trie which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TrieNode root) {
        String result = "";

        if (root == null || root.children == null || root.children.isEmpty()) {
            return result;
        }

        result += SEPERATOR_START;
        for (Map.Entry<Character, TrieNode> entry : root.children.entrySet()) {
            char key = entry.getKey();
            TrieNode value = entry.getValue();

            result += key + serialize(value);
        }
        result += SEPERATOR_END;

        return result;
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TrieNode deserialize(String data) {
        // check corner case
        if (data == null || data.length() == 0) {
            return null;
        }

        TrieNode root = new TrieNode();
        Stack<TrieNode> stack = new Stack<TrieNode>();

        TrieNode current = root;		
        for (char ch : data.toCharArray()) {
            switch (ch) {
                case SEPERATOR_START: 
                    stack.push(current);
                    break;
                case SEPERATOR_END:
                    stack.pop();
                    break;
                default:
                    TrieNode node = new TrieNode();
                    stack.peek().children.put(ch, node);
                    current = node;
            }
        }

        return root;
    }
}
