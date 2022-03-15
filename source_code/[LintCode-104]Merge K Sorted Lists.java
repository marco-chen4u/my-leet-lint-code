/***
* LintCode 104. Merge K Sorted Lists
Merge k sorted linked lists and return it as one sorted list.

Analyze and describe its complexity.

Example 1:
	Input:   [2->4->null,null,-1->null]
	Output:  -1->2->4->null

Example 2:
	Input: [2->6->null,5->null,7->null]
	Output:  2->5->6->7->null
***/
/**
 * Definition for ListNode.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int val) {
 *         this.val = val;
 *         this.next = null;
 *     }
 * }
 */

//version-1: Divide&Conquer, time complexity: O(n)
public class Solution {
    
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        // corner cases
        if (lists == null || lists.size() == 0) {
            return null;
        }
        
        return mergeKListsHelper(lists, 0, lists.size() - 1);
    }

    // helper methods
    private ListNode mergeKListsHelper(List<ListNode> lists, int start, int end) {
        // corner case
        if (start >= end) {
            return lists.get(start);
        }
        
        // divide
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        
        // conquer
        return merge2Lists(left, right);
    }
    
    private ListNode merge2Lists(ListNode l1, ListNode l2) {
        // check corner cases
        if (l1 == null) {
            return l2;
        }
        
        if (l2 == null) {
            return l1;
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            }
            else {
                current.next = l2;
                l2 = l2.next;
            }
            
            current = current.next;
        }
        
        current.next = (l1 != null) ? l1 : l2;
        
        return dummy.next;
    }
}

//version-2: minHeap, time complexity: O(n*logk), space complexity: O(logk)
public class Solution {
    /**
     * @param lists: a list of ListNode
     * @return: The head of one sorted list.
     */
    public ListNode mergeKLists(List<ListNode> lists) {  
        // check corner case
        if (lists == null || lists.size() == 0) {
            return null;
        }        
        // initialize
        int size = lists.size();
        Queue<ListNode> minHeap = new PriorityQueue<ListNode>(size, 		
                                                                (ListNode a, ListNode b) -> a.val - b.val);
        
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;        
        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            current.next = node;
            
            if (node.next != null) {
                minHeap.offer(node.next);
            }
            
            current = current.next;
        }
        
        return dummy.next;
    }
}
