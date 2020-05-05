/***
 * LintCode 452.Remove Linked List Elements
Remove all elements from a linked list of integers that have value val.

Example
    Example 1:
        Input: head = 1->2->3->3->4->5->3->null, val = 3
        Output: 1->2->4->5->null
    Example 2:
        Input: head = 1->1->null, val = 1
        Output: null
***/

/**
 * Definition for ListNode
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

public class Solution {
    /**
     * @param head: a ListNode
     * @param val: An integer
     * @return: a ListNode
     */
    public ListNode removeElements(ListNode head, int val) {
        // check corner case
        if (head == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = head;
        ListNode pre = dummy;
        
        while (current != null) {
            if (current.val == val) {
                pre.next = current.next;
                current = current.next;
            }
            else {
                pre = pre.next;
                current = current.next;
            }
        }
        
        return dummy.next;
    }
}