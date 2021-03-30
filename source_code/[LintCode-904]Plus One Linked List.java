/***
* LintCode 904. Plus One Linked List
Given a non-negative integer represented as non-empty a singly linked list of digits, plus one to the integer.

You may assume the integer do not contain any leading zero, except the number 0 itself.

The digits are stored such that the most significant digit is at the head of the list.

Example
    Example1
        Input: 1 -> 2 -> 3 -> null
        Output: 1 -> 2 -> 4 -> null
        Explanation:
            123 + 1 = 124

    Example2
        Input: 9 -> 9 -> null
        Output: 1 -> 0 -> 0 -> null
        Explanation:
            99 + 1 = 100
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
     * @param head: the first Node
     * @return: the answer after plus one
     */
    public ListNode plusOne(ListNode head) {
        // check corner case
        if (head == null) {
            return head;
        }
        
        // normal case
        int carry = 1;
        ListNode current = reverse(head);
        ListNode dummy = current;
        
        ListNode pre = null;
        while (current != null) {
            int value = current.val + carry;
            current.val = value % 10;
            carry = value / 10;
            
            pre = current;
            current = current.next;
        }
        
        if (current == null && carry == 1) {
            pre.next = new ListNode(1);
        }
        
        head = reverse(dummy);
        
        return head;
    }
    
    // helepr method
    private ListNode reverse(ListNode node) {
        ListNode pre = null;
        
        while (node != null) {
            ListNode tmp = node.next;
            node.next = pre;
            pre = node;
            
            node = tmp;
        }
        
        return pre;
    }
}
