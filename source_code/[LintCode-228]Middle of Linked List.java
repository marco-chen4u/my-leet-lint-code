/***
* LintCode 228. Middle of Linked List
Find the middle node of a linked list.

Example 1:
    Input:  1->2->3
    Output: 2
    Explanation: return the value of the middle node.

Example 2:
    Input:  1->2
    Output: 1
    Explanation: If the length of list is  even return the value of center left one.

Challenge
    If the linked list is in a data stream, can you find the middle without iterating the linked list again?
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
     * @param head: the head of linked list.
     * @return: a middle node of the linked list
     */
    public ListNode middleNode(ListNode head) {
        // check corner case
        if (head == null) {
            return null;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return slow;
    }
}
