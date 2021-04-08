/***
* LintCode 174. Remove Nth Node From End of List
Given a linked list, remove the nth node from the end of list and return its head.

Example
    Example 1:
        Input: list = 1->2->3->4->5->null， n = 2
        Output: 1->2->3->5->null

    Example 2:
        Input:  list = 5->4->3->2->1->null, n = 2
        Output: 5->4->3->1->null

Challenge
    Can you do it without getting the length of the linked list?

Notice
    The minimum number of nodes in list is n.
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
     * @param head: The first node of linked list.
     * @param n: An integer
     * @return: The head of linked list.
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // check corner case
        if (head == null || n < 1) {
            return null;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode current = head;
        
        for (int i = 1; i <= n; i++) {            
            head = head.next;
        }
        
        while (head != null) {
            pre = pre.next;
            current = current.next;
            head = head.next;
        }
        
        pre.next = current.next;
        
        return dummy.next;
    }
}
