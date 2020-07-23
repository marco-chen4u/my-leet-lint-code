/***
* LeetCode 203. Remove Linked List Elements
Remove all elements from a linked list of integers that have value val.

Example:
    Input:  1->2->6->3->4->5->6, val = 6
    Output: 1->2->3->4->5
***/
//solution: LinkedList manipulation with DummyNode
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // check corner cases
        if (head == null) {
            return head;
        }
        
        if (head.next == null) {
            return (head.val == val) ? null : head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        
        ListNode current = head;
        
        while (current != null) {
            
            if (current.val == val) {
                pre.next = current.next;
            }
            else {
                pre = current;                
            }
            
            current = current.next;

        }
        
        return dummy.next;
    }
}
