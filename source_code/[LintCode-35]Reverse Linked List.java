/***
* LintCode 35. Reverse Linked List
Reverse a linked list.

Example
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
		
		while (head != null) {
			ListNode tmp = head.next;
			head.next = pre;
			pre = head;
			head = tmp;
		}
		
		return pre;
	}
}