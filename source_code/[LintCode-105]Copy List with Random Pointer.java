/***
* LintCode 105. Copy List with Random Pointer
A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.

Return a deep copy of the list.

Challenge
    Could you solve it with O(1) space?
***/
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
// version-1
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

        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;

        RandomListNode current = null;

        while (head != null) {
            if (map.containsKey(head)) {
                current = map.get(head);
            }
            else {
                current = new RandomListNode(head.label);
                map.put(head, current);
            }

            pre.next = current;

            RandomListNode random = head.random;

            if (head.random != null) {
                if (!map.containsKey(random)) {
                    map.put(random, new RandomListNode(random.label));				
                }
                current.random = map.get(random);				
            }

            pre = pre.next;
            head = head.next;
        }

        return dummy.next;		
    }
}

//version-2:
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
        
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();
        RandomListNode dummy = new RandomListNode(0);
        RandomListNode pre = dummy;
        
        RandomListNode current = null;
        
        while (head != null) {
            map.putIfAbsent(head, new RandomListNode(head.label));
            
            current = map.get(head);
            
            RandomListNode random = head.random;
            if (random != null) {
                 map.putIfAbsent(random, new RandomListNode(random.label));
                 current.random = map.get(random);
            }
            
            pre.next = current;
            pre = pre.next;
            head = head.next;
        }
        
        return dummy.next;
    }
}
