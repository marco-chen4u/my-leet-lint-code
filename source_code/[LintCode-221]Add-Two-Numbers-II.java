/**
* LintCode 221 · Add Two Numbers II
You have two numbers represented by linked list, where each node contains a single digit. 
The digits are stored in forward order, such that the 1's digit is at the head of the list. 
Write a function that adds the two numbers and returns the sum as a linked list.

Example 1:
  Input: 6->1->7   
         2->9->5
  Output: 9->1->2

Example 2:
Input: 1->2->3   
       4->5->6
Output: 5->7->9

LeetCode link: https://www.lintcode.com/problem/221/
LeetCode link: https://leetcode.com/problems/add-two-numbers-ii/
**/
//version-1: iteration
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
     * @param l1: The first list.
     * @param l2: The second list.
     * @return: the sum list of l1 and l2.
     */
    public ListNode addLists2(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode result = add2List(l1, l2);

        return reverse(result);
    }

    // helper method
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;
        ListNode pre = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }

        return pre;
    }

    private ListNode add2List(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            sum += l1 != null ? l1.val : 0;
            sum += l2 != null ? l2.val : 0;

            current.next = new ListNode(sum % 10);
            carry = sum / 10;

            l1 = l1 != null ? l1.next : l1;
            l2 = l2 != null ? l2.next : l2;
            
            current = current.next;
        }

        if (carry != 0) {
            current.next = new ListNode(carry);
        }

        return dummy.next;
    }
}
