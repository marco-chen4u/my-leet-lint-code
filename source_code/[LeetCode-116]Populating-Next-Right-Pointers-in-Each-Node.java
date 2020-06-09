/***
* LeetCode 106. Populating Next Right Pointers in Each Node
You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

    struct Node {
      int val;
      Node *left;
      Node *right;
      Node *next;
    }

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

Follow up:
    You may only use constant extra space.
    Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.

Constraints:
    The number of nodes in the given tree is less than 4096.
    -1000 <= node.val <= 1000

link : https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
***/
/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if (root == null || isLeaf(root)) {
            return root;
        }
        
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            
            int size = queue.size();
            Node next = null;
            Node current = null;
            
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                current.next = next;
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                next = current;
            }// for i
        }// while queue
        
        return root;
    }
    
    // helper method
    private boolean isLeaf(Node node) {
        return node != null && node.left == null && node.right == null;
    }
}
