/***
* LintCode 35. Reverse Linked List
Reverse a linked list.


Example 1:
    Input: 1->2->3->null
    Output: 3->2->1->null

Example 2:
    Input: 1->2->3->4->null
    Output: 4->3->2->1->null

Challenge
    Reverse it in-place and in one-pass
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
     * @param head: n
     * @return: The new head of reversed linked list.
     */
    public ListNode reverse(ListNode head) {
        // check corner case
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
	ListNode current = head;

        while (current != null) {
            ListNode next = current.next;

            current.next = pre;
            pre = current;

            current = next;
        }

        return pre;
    }
}
