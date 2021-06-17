/***
* LeetCode 109. Convert Sorted List to Binary Search Tree
Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:
    Given the sorted linked list: [-10,-3,0,5,9],

    One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

          0
         / \
       -3   9
       /   /
     -10  5
***/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
//version-1: two pointers(slow&fast pointers)
class Solution {
    public TreeNode sortedListToBST(ListNode head) {
        // check corner cases
        if (head == null) {
            return null;
        }
        
        if (head.next == null) {
            return new TreeNode(head.val);
        }
        
        // regualr case process below
        // tow pointers
        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;
        
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        //System.out.println("median = " + slow.val);
        ListNode median = slow;
        ListNode next = median.next;
        
        pre.next = null;
        median.next = null;
        TreeNode node = new TreeNode(median.val);
        
        node.left = sortedListToBST(head);
        node.right = sortedListToBST(next);
        
        return node;
    }
}
