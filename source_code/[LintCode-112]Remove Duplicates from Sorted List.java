/***
* LintCode 112. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example
Example 1:
	Input:  null
	Output: null


Example 2:
	Input:  1->1->2->null
	Output: 1->2->null
	
Example 3:
	Input:  1->1->2->3->3->null
	Output: 1->2->3->null
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
     * @param head: head is the head of the linked list
     * @return: head of linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // check corner case
		if (head == null || head.next == null) {
			return head;
		}
		
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode current = head;
		ListNode pre = dummy;
		
		while (current != null && current.next != null) {
			if (current.val == current.next.val) {
				int duplicate = current.val;
				while (current != null && current.next != null 
						&& current.next.val == duplicate) {
					pre.next = current.next;
					current = current.next;
				}
			}
			
			pre = pre.next;
			current = current.next;
		}
		
		return dummy.next;
    }
}