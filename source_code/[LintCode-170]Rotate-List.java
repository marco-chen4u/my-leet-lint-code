/***
*LintCode 170. Rotate List
Given a list, rotate the list to the right by k places, where k is non-negative.

Example
    Example 1:
        Input: 1->2->3->4->5 k = 2
        Output: 4->5->1->2->3

    Example 2:
        Input: 3->2->1 k = 1
        Output: 1->3->2
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
//solution-1
public class Solution {
    /**
     * @param head: the List
     * @param k: rotate to the right k places
     * @return: the list after rotation
     */
    public ListNode rotateRight(ListNode head, int k) {
        // check corner cases
        if (head == null || head.next == null) {
            return head;
        }

        int size = getSize(head);
        k = k % size;

        if (k == 0) {
            return head;
        }

        // regular case
        ListNode dummy = new ListNode(-1);
        
        ListNode tail = head;
        for (int i = 1; i <= k; i++) {
            tail = tail.next;
        }

        ListNode current = head;
        while (tail.next != null) {
            current = current.next;
            tail = tail.next;
        }

        dummy.next = current.next;
        current.next = null;
        tail.next = head;

        return dummy.next;
    }

    // helper methods
    private int getSize(ListNode head) {
        ListNode current = head;
        int size = 0;
        while (current != null) {
            size ++;
            current = current.next;
        }

        return size;
    }
}
