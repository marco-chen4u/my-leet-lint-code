/***
* LintCode 217. Remove Duplicates from Unsorted List
Write code to remove duplicates from an unsorted linked list.

Example
    Example 1:
        Input: 1->2->1->3->3->5->6->3->null
        Output: 1->2->3->5->6->null
    Example 2:
        Input: 2->2->2->2->2->null
        Output: 2->null

Challenge
    (hard) How would you solve this problem if a temporary buffer is not allowed? In this case, you don't need to keep the order of nodes.
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
     * @return: Head node.
     */
    public ListNode removeDuplicates(ListNode head) {
        // check corner case
	if (head == null) {
            return head;
	}

	Set<Integer> set = new HashSet<Integer>();
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode pre = dummy;
	ListNode current = head;

	while (current != null) {
	    int value = current.val;

	    if (set.contains(value)) {
                pre.next = current.next;
                current = current.next;
                continue;
	    }

	    set.add(value);
	    pre = pre.next;
	    current = current.next;
	}

        return dummy.next;
    }
}
