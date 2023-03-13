/***
* LeetCode 510. Inorder Successor in BST II
Given a node in a binary search tree, return the in-order successor of that node in the BST. 
If that node has no in-order successor, return null.

The successor of a node is the node with the smallest key greater than node.val.

You will have direct access to the node but not to the root of the tree. 
Each node will have a reference to its parent node. Below is the definition for Node:

class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
}

Example 1
    Input: tree = [2,1,3], node = 1
    Output: 2
    Explanation: 
               1
              / \
             2   3
        1's in-order successor node is 2. Note that both the node and the return value is of Node type.

Example 2
    Input: tree = [5,3,6,2,4,null,null,1], node = 6
    Output: null
    Explanation: 
               5
              / \
             3   6
            / \
           2   4
          /
         1 
        There is no in-order successor of the current node, so the answer is null.
***/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/
//version-1: iterator
class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        Node current = node;
        while (current.parent != null) {
            current = current.parent;
        }

        Node root = current;
        int targetVal = node.val;
        
        Node next = null;
        while (current != null) {
            if (current.val > targetVal) {
                next = current;
                current = current.left;
                continue;
            }

            current = current.right;
        }

        return next;

    }
}

//version-2: InOrder Travesal Iteration
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node parent;
};
*/

class Solution {
    public Node inorderSuccessor(Node node) {
        if (node == null) {
            return null;
        }

        Node current = node;
        while (current.parent != null) {
            current = current.parent;
        }

        Node root = current;
        int targetVal = node.val;
        
        Stack<Node> stack = new Stack<>();

        Node result = null;
        Node pre = null;

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if (node.equals(pre)) {
                result = current;
                break;
            }
            pre = current;
            current = current.right;
        }

        return result;

    }
}
