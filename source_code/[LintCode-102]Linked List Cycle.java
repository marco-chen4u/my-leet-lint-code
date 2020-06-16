/***
* LintCode 102. Linked List Cycle
Given a linked list, determine if it has a cycle in it.

Example
    Example 1:
        Input: 21->10->4->5,  then tail connects to node index 1(value 10).
        Output: true

    Example 2:
        Input: 21->10->4->5->null
        Output: false

Challenge
Follow up:
    Can you solve it without using extra space?
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
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // check corner case
        if (head == null || head.next == null) {
            return false;
        }
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            
            fast = fast.next.next;
            slow = slow.next;
        }
        
        return true;
    }
}
