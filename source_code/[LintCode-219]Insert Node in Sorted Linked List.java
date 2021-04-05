/***
 * LintCode 219. Insert Node in Sorted Linked List
Insert a node in a sorted linked list.

Example
    Example 1:
        Input: head = 1->4->6->8->null, val = 5
        Output: 1->4->5->6->8->null
    Example 2:
        Input: head = 1->null, val = 2
        Output: 1->2->null
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
// solution-1
public class Solution {
    /**
     * @param head: The head of linked list.
     * @param val: An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) {
            return node;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.val <= val) {
            current = current.next;
        }
        
        node.next = current.next;
        current.next = node;
        
        return dummy.next;
    }
}

// solution-2
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
     * @param head: The head of linked list.
     * @param val: An integer.
     * @return: The head of new linked list.
     */
    public ListNode insertNode(ListNode head, int val) {
        ListNode newNode = new ListNode(val);
        // check corner case
        if (head == null) {
            return newNode;
        }

        if (head.val >= val) {
            newNode.next = head;
            return newNode;
        }

        // regular case
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode current = head;

        while (current != null) {
            if (current.val >= val) {
                break;
            }

            pre = current;
            current = current.next;
        }

        pre.next = newNode;
        newNode.next = current;

        return dummy.next;
    }
}
