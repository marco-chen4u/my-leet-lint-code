/***
 * LintCode 451. Swap Nodes in Pairs
Given a linked list, swap every two adjacent nodes and return its head.

Example
    Example 1:
        Input: 1->2->3->4->null
        Output: 2->1->4->3->null
    
    Example 2:
        Input: 5->null
        Output: 5->null

Challenge
    Your algorithm should use only constant space. 
    You may not modify the values in the list, only nodes itself can be changed.
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
     * @return: a ListNode
     */
    public ListNode swapPairs(ListNode head) {
        // check corner case
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode pre = dummy;
		
		while (pre.next != null && pre.next.next != null) {
			ListNode n1 = pre.next;
			ListNode n2 = pre.next.next;
			
			n1.next = n2.next;
			n2.next = n1;
			pre.next = n2;
			
			pre = n1;
		}
		
		return dummy.next;
    }
}