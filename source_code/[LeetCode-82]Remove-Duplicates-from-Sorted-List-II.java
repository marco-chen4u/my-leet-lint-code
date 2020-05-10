/***
* LeetCode 82. Remove Duplicates from Sorted List II
Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.

Return the linked list sorted as well.

Example 1:
    Input: 1->2->3->3->4->4->5
    Output: 1->2->5

Example 2:
    Input: 1->1->1->2->3
    Output: 2->3
***/
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
    private final int DEFAULT = -2147483648;
    
    public ListNode deleteDuplicates(ListNode head) {
        // check corner case
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(DEFAULT);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode current = head;
        
        while (current != null && current.next != null) {
            
            while (current != null &&
                   current.next != null &&
                   current.val == current.next.val) {
                int duplicateValue = current.val;
                
                while (current != null &&
                      current.val == duplicateValue) {
                    current = current.next;
                }
            }
            
            pre.next = current;
            pre = current;
            current = (current != null) ? current.next : current;
        }
        
        return dummy.next;
    }
}
