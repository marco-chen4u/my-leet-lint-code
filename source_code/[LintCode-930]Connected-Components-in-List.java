/**
* LintCode 930 · Connected Components in List
Given a doubly linked list and an array of nodes.
If the nodes are connected with each other
(which means we can access any nodes through any one of them),
we can consider them as one block.

Find the number of blocks in the given array.

Example 1:
    Input：1<->2<->3<->4,[1,3,4]
    Output：2
    Explanation：There is no node connected with 1, and node 3 is connected with 4. So the number of blocks is 2.

Example 2:
    Input：1<->2<->3<->4,[1,2,3,4]
    Output：1
    Explanation：All nodes  are connected.So the number of blocks is 1.
**/

/**
 * Definition for Doubly-ListNode
 * public class DoublyListNode {
 *     int val;
 *	   DoublyListNode prev;
 *     DoublyListNode next;
 *     DoublyListNode(int x) {
 *         val = x;
 *         prev = next = null;
 *     }
 * }
 */

//version-1: iteration
public class Solution {
    /**
     * @param head: the given doubly linked list
     * @param nodes: the given nodes array
     * @return: the number of blocks in the given array
     */
    public int blockNumber(DoublyListNode head, int[] nodes) {
        Set<Integer> set = new HashSet<>();
        for (int node : nodes) {
            set.add(node);
        }

        DoublyListNode current = head;
        int count = 0;
        while (current != null) {
            if (set.contains(current.val) && 
                (current.next == null || !set.contains(current.next.val))) {
                count ++;
            }
            current = current.next;
        }

        return count;
    }
}
