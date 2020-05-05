/***
* LintCode 511. Swap Two Nodes in Linked List
Given a linked list and two values v1 and v2. 
Swap the two nodes in the linked list with values v1 and v2. 
It's guaranteed there is no duplicate values in the linked list. 
If v1 or v2 does not exist in the given linked list, do nothing.

Example
	Example 1:
		Input: 1->2->3->4->null, v1 = 2, v2 = 4
		Output: 1->4->3->2->null
	Example 2:
		Input: 1->null, v1 = 2, v2 = 1
		Output: 1->null

Notice
	You should swap the two nodes with values v1 and v2. 
	Do not directly swap the values of the two nodes.
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
     * @param v1: An integer
     * @param v2: An integer
     * @return: a new head of singly-linked list
     */
    public ListNode swapNodes(ListNode head, int v1, int v2) {
        // check corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        ListNode node1Pre = null;
        ListNode node2Pre = null;
        
        while (current.next != null) {
            if (current.next.val == v1) {
                node1Pre = current;
            }
            else if (current.next.val == v2) {
                node2Pre = current;
            }
            current = current.next;
        }
        
        if (node1Pre == null || node2Pre == null) {
            return head;
        }
        
        if (node2Pre.next == node1Pre) {
            ListNode tmp = node1Pre;
            node1Pre = node2Pre;
            node2Pre = tmp;
        }
        
        ListNode node1 = node1Pre.next;
        ListNode node2 = node2Pre.next;
        ListNode node2Next = node2.next;
        
        if (node1Pre.next == node2Pre) {
            node1Pre.next = node2;
            node2.next = node1;
            node1.next = node2Next;
        }
        else {
            node1Pre.next = node2;
            node2.next = node1.next;
            
            node2Pre.next = node1;
            node1.next = node2Next;
        }
        
        return dummy.next;
    }
}