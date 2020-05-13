/***
* LintCode 36. Reverse Linked List II
Reverse a linked list from position m to n.

Example
	Example 1:
		Input: 1->2->3->4->5->NULL, m = 2 and n = 4, 
		Output: 1->4->3->2->5->NULL.
	Example 2:
		Input: 1->2->3->4->NULL, m = 2 and n = 3, 
		Output: 1->3->2->4->NULL.

Challenge
	Reverse it in-place and in one-pass]

Notice
	Given m, n satisfy the following condition: 1 ≤ m ≤ n ≤ length of list.
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
//version-1
public class Solution {
    /**
     * @param head: ListNode head is the head of the linked list 
     * @param m: An integer
     * @param n: An integer
     * @return: The head of the reversed ListNode
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // check corner case
        if (head == null || head.next == null || m > n) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = dummy;
        
        // find the (m - 1)th node
        for (int i = 1; i < m; i++) {
            if (head == null) {
                return null;
            }
            
            head = head.next;
        }
        ListNode start = head;//located the (m - 1)th node
        
        ListNode pre = null;
        head = head.next; // point the the (m)th node
        ListNode rear = head;
        
        // reverse m~n nodes
        for (int i = m; i <= n; i++) {
            if (head == null) {
                return null;
            }
            
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        
        // connect m- 1 -> n
        start.next = pre;
        // connect m -> n + 1;
        rear.next = head;
        
        return dummy.next;
    }
}

//version-2
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // check corner case
        if (head == null || head.next == null || m == n) {
            return head;
        }
        
        // normal case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode current = head;
        ListNode start = dummy;
        ListNode end = null;
        
        for (int i = 1; i < m; i++) {
            start = current;
            current = current.next;
        }
        
        end = current;//after reverse m-n;
        
        // reversiing m-n nodes
        ListNode pre = null;
        for (int i = m; i <= n; i++) {
            ListNode next = current.next;
            current.next = pre;
            pre = current;
            
            current = next;
        }
        
        end.next = current;
        start.next = pre;
        
        return dummy.next;
    }
}

//version-3
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        // check corner case
        if (head == null || head.next == null || m == n) {
            return head;
        }
        
        // normal case
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode preM = dummy;
        ListNode mNode = head;
        ListNode nNode = head;
        
        for (int i = 1; i < m; i++) {
            preM = mNode;
            mNode = mNode.next;
        }
        
        for (int i = 1; i < n; i++) {
            nNode = nNode.next;
        }
        
        while(mNode != nNode) {
            preM.next = mNode.next;
            mNode.next = nNode.next;
            nNode.next = mNode;
            mNode = preM.next;
        }
        
        return dummy.next;
    }
}
