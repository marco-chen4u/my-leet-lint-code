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
        // check corner cases
        if (head == null || head.next == null) {
            return head;
        }

        // regular case
        int count = 0;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode current = head;
        ListNode pre1 = null;
        ListNode v1Node = null;
        ListNode pre2 = null;
        ListNode v2Node = null;

        while (current != null) {
            if (count == 2) {
                break;
            }

            if (current.val == v1) {
                count++;
                v1Node = current;
                pre1 = pre;
            }

            if (current.val == v2) {
                count++;
                v2Node = current;
                pre2 = pre;
            }
            
            pre = current;
            current = current.next;
        }

        if (v1Node == null || v2Node == null) {
            return head;
        }

        // regular case
        pre1.next = v2Node;
        pre2.next = v1Node;

        ListNode temp = v1Node.next;
        v1Node.next = v2Node.next;
        v2Node.next= temp;

        return dummy.next;
    }
}
