/***
* LintCode 380. Intersection of Two Linked Lists
Write a program to find the node at which the intersection of two singly linked lists begins.
Example
    Example 1:
        Input:
            A:          a1 → a2
                               ↘
                                 c1 → c2 → c3
                               ↗            
            B:     b1 → b2 → b3
        Output: c1
            Explanation ：begin to intersect at node c1.	
Challenge
    Your code should preferably run in O(n) time and use only O(1) memory.

Notice
    If the two linked lists have no intersection at all, return null.
    The linked lists must retain their original structure after the function returns.
    You may assume there are no cycles anywhere in the entire linked structure.
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
//version: Linked List traverse + HashSet to record the visiting nodes, time-complexity: O(n), space-complexity: O(n)
public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode result = null;
        // check corner case
        if (headA == null || headB == null) {
            return result;
        }

        // normal case
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode current = headA;
        while (current != null) {
            visited.add(current);
            current = current.next;
        }

        current = headB;
        while (current != null) {
            if (visited.contains(current)) {
                result = current;
                break;
            }
            current = current.next;
        }

        return result;
    }
}

//version-2: Linked List cycle process, time-complexity: O(n), space-complexity: O(1)
public class Solution {
    /**
     * @param headA: the first list
     * @param headB: the second list
     * @return: a ListNode
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // check corner cases
        if (headA == null || headB == null) {
            return null;
        }
        
        // (1) get the tail of a list
        ListNode tail = headA;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        tail.next = headA;// tail links to the head to make s cycle link
        ListNode result = getStartNodeOfCycleLinkedList(headB);
        tail.next = null;// retain the cycle link back to original structure;
        
        return result;
    }
    
    // helper method
    private ListNode getStartNodeOfCycleLinkedList(ListNode head) {
        
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;//no cycle
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        
        ListNode node = head;
        while (node != slow.next) {
            node = node.next;
            slow = slow.next;
        }
        
        return node;
    }
}
