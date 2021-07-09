/***
* LintCode 1534. Convert Binary Search Tree to Sorted Doubly Linked List
Description
Convert a BST to a sorted circular doubly-linked list in-place. 
Think of the left and right pointers as synonymous to the previous and next pointers in a doubly-linked list.

Let's take the following BST as an example, it may help you understand the problem better:

        4
       /  \
      2   5
     / \
    1   3

We want to transform this BST into a circular doubly linked list. 
Each node in a doubly linked list has a predecessor and successor. 
For a circular doubly linked list, 
the predecessor of the first element is the last element, 
and the successor of the last element is the first element.

The figure below shows the circular doubly linked list for the BST above. 
The "head" symbol means the node it points to is the smallest element of the linked list.

    head
     |
    ---> -->  --> --> --> ---|
   |    1    2   3   4   5   |
   | --- <-- <--  <-- <----  |
   | |_____________________| |
   |_________________________|
   
Specifically, we want to do the transformation in place. 
After the transformation, the left pointer of the tree node should point to its predecessor, 
and the right pointer should point to its successor. 
We should return the pointer to the first element of the linked list.

The figure below shows the transformed BST. 
The solid line indicates the successor relationship, while the dashed line means the predecessor relationship.

Example 1:
    Input: {4,2,5,1,3}
            4
           /  \
          2   5
         / \
        1   3
    Output: "left:1->5->4->3->2  right:1->2->3->4->5"
    Explanation:
        Left: reverse output
        Right: positive sequence output

Example 2:
    Input: {2,1,3}
            2
           /  \
          1   3
    Output: "left:1->3->2  right:1->2->3"

Link: https://www.lintcode.com/problem/1534/
***/
//version-1: non-recursion, in-order traversal with stack, and contructing the doubly linked list by the traversal list
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
//version-1: non-recurion, in-order traversal into a list, then build-up a doubly linked list[pre = node.left, next = node.right]
public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        // check corner cases
        if (root == null) {
            return null;
        }

        if (root.left == null && root.right == null) {
            root.right = root;
            root.left = root;

            return root;
        }

        // normal case
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        List<TreeNode> list = new ArrayList<>();

        while (!stack.isEmpty() || current != null) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            list.add(current);

            int size = list.size();
            if (size >= 2) {
                // constructing the doublely linked list
                TreeNode a = list.get(size - 2);
                TreeNode b = list.get(size - 1);

                a.right = b;
                b.left = a;
            }

            current = current.right;
        }

        // connecting the tail and head
        int size = list.size();
        TreeNode head = list.get(0);
        TreeNode tail = list.get(size - 1);

        tail.right = head;
        head.left = tail;

        return head; 
    }
}

//version: recursion
public class Solution {
    /**
     * @param root: root of a tree
     * @return: head node of a doubly linked list
     */
    public TreeNode treeToDoublyList(TreeNode root) {
	// check corner case
	if (root == null) {
	    return null;
	}

	TreeNode lhead = treeToDoublyList(root.left);
	TreeNode rhead = treeToDoublyList(root.right);

	if (lhead != null) {
	    TreeNode ltail = lhead.left;

	    root.right = lhead;
	    lhead.left = root;
            
            root.left = ltail;
	    ltail.right = root;
	}
	else {
	    lhead = root;
	    lhead.right = root;
            lhead.left = root;
	}

	if (rhead != null) {
	    TreeNode rtail = rhead.left;

	    rhead.left = root;
	    root.right = rhead;

	    rtail.right = lhead;
	    lhead.left = rtail;
	}

	return lhead;
    }
}
