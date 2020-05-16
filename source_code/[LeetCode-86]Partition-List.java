/***
* LeetCode 86. Partition List
Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

You should preserve the original relative order of the nodes in each of the two partitions.

Example:
    Input: head = 1->4->3->2->5->2, x = 3
    Output: 1->2->2->4->3->5
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
class Solution {
    private final int DEFAULT = Integer.MIN_VALUE;
    
    public ListNode partition(ListNode head, int x) {
        // check corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        // two pointers
        ListNode dummy = new ListNode(DEFAULT);
        ListNode left = dummy;
        ListNode largerNodes = new ListNode(DEFAULT);
        ListNode right = largerNodes;
        
        ListNode current = head;
        
        while (current != null) {
            if (current.val < x) {
                left.next = current;
                left = current;
            }
            else {
                right.next = current;
                right = current;
            }

            current = current.next;
        }
        
        left.next = largerNodes.next;
        right.next = null; // break the potential ring
        
        return dummy.next;
        
    }
}
