/***
* LeetCode 83. Remove Duplicates from Sorted List
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example1
	Input: 1->1->2
	Output: 1->2

Example2
	Input: 1->1->2->3->3
	Output: 1->2->3
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
        if (head == null || head.next == null) {        //corner case check-0
            return head;
        }
        
	// normal case
        ListNode dummy = new ListNode(DEFAULT);
        dummy.next= head;
        ListNode pre = dummy;
        ListNode current = dummy.next;
        
        while (current != null) {
            
            if (pre.val != current.val && 
                    (current.next == null ||              //corner case check-1
                     current.next.val != current.val)) {  //corner case check-2
                pre.next = current;
                pre = current;
            }
            
            current = current.next;
        }
        
        return dummy.next;
    }
}

