/***
* LeetCode 117. Populating Next Right Pointers in Each Node II
Given a binary tree

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
    The number of nodes in the given tree is less than 6000.
    -100 <= node.val <= 100

Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node-ii/
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

//version-1: BFS(with Queue)
class Solution {
    public Node connect(Node root) {
        // check corner case
        if (root == null ||
           root.left == null && root.right == null) {
            return root;
        }
        
        // regular case
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            Node current = null;
            Node next = null;
            for (int i = 0; i < size; i++) {
                current = queue.poll();
                
                if (current.right != null) {
                    queue.offer(current.right);
                }
                
                if (current.left != null) {
                    queue.offer(current.left);
                }
                
                current.next = next;
                next = current;
            }
        }
        
        return root;
    }
}

//version-2: BFS(without Queue)
class Solution {
    public Node connect(Node root) {
        // check corner case
        if (root == null) {
            return root;
        }
        // regular case
        Node levelPivot = root;
        while (levelPivot != null) {
            
            Node current = levelPivot;
            while (current != null) {
                
                Node left = current.left;
                Node right = current.right;
                
                if (left != null) {
                    left.next = (right != null) ? right : findClosestNeighbor(current);
                }
                
                if (right != null) {
                    right.next = findClosestNeighbor(current);
                }
                
                current = current.next;
            }
            
            //System.out.println("setp -4");
            levelPivot = findNextLevel(levelPivot);
        }
        
        return root;
    }
    
    // helper method
    private Node findNextLevel(Node node) {
        if (node == null) {
            return null;
        }
        
        Node current = node;
        
        while (current != null) {
            if (current.left != null) {
                return current.left;
            }
            
            if (current.right != null) {
                return current.right;
            }
            
            current = current.next;
        }
        
        return current;
    }
    
    private Node findClosestNeighbor(Node node) {
        if (node == null) {
            return null;
        }
        
        Node current = node.next;
        while (current != null) {
            if (current.left != null) {
                return current.left;
            }
            
            if (current.right != null) {
                return current.right;
            }
            
            current = current.next;
        }
        
        return null;
    }
} 


