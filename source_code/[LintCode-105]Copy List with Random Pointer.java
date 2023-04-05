/***
* LintCode 105. Copy List with Random Pointer
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Challenge
    Could you solve it with O(1) space?
    
lintcode link: https://www.lintcode.com/problem/105/
leetcode link: https://leetcode.com/problems/copy-list-with-random-pointer/
***/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
// version-1: Map + dummy node.  O(n)space
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // check corner case
        if (head == null) {
            return head;
        }

        // regular case
        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode dummy = new RandomListNode(-1);
        RandomListNode pre = dummy;
        RandomListNode current = head;
        while (current != null) {
            map.putIfAbsent(current, new RandomListNode(current.label));

            RandomListNode copy = map.get(current);

            if (current.random != null) {
                RandomListNode random = current.random;
                map.putIfAbsent(random, new RandomListNode(random.label));

                copy.random = map.get(random);
            }

            pre.next = copy;
            pre = copy;

            current = current.next;
        }

        return dummy.next;

    }
}

//version-2: link current node next to the copy node, then split. O(1) space
public class Solution {
    /**
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
    public RandomListNode copyRandomList(RandomListNode head) {
        // check corner case
        if (head == null) {
            return head;
        }
        
        copyNext(head);
        copyRandom(head);
        
        return splitList(head);
    }
    
    // helper methods
    private void copyNext(RandomListNode head) {
        while (head != null) {
            RandomListNode newNode = new RandomListNode(head.label);
            newNode.next = head.next;
            newNode.random = head.random;
            head.next = newNode;
            
            head = head.next.next;
        }
    }
    
    private void copyRandom(RandomListNode head) {
        while (head != null) {
            if (head.random != null) {
                head.next.random = head.random.next;
            }
            
            head = head.next.next;
        }
    }
    
    private RandomListNode splitList(RandomListNode head) {
        RandomListNode dummy = new RandomListNode(0);
        dummy.next = head.next;
        
        while (head != null) {
            RandomListNode tmp = head.next;
            head.next = tmp.next;
            
            if (tmp.next != null) {
                tmp.next = tmp.next.next;
            }
            
            head = head.next;
        }
        
        return dummy.next;
    }
}
