/***
* LeetCode 25. Reverse Nodes in k-Group
Given the head of a linked list, reverse the nodes of the list k at a time, and return the modified list.

k is a positive integer and is less than or equal to the length of the linked list. 
If the number of nodes is not a multiple of k then left-out nodes, in the end, should remain as it is.

You may not alter the values in the list's nodes, only nodes themselves may be changed.


Example 1
    image link: https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg
    Input: head = [1,2,3,4,5], k = 2
    Output: [2,1,4,3,5]
    Explaination:
      list = 1->2->3->4->5->null, k= 2
      Output:
          2->1->4->3->5

Example 2
    image link: https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg
    Input: head = [1,2,3,4,5], k = 3
    Output: [3,2,1,4,5]

Constraints:
    The number of nodes in the list is n.
    1 <= k <= n <= 5000
    0 <= Node.val <= 1000
    
Follow-up: Can you solve the problem in O(1) extra memory space?

LeetCode Link: https://leetcode.com/problems/reverse-nodes-in-k-group/
LintCode Link: https://www.lintcode.com/problem/450/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */***/


//version-1: stack, space complexity: O(k)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //edge case check
        if(head == null || k == 0){
            return head;
        }
        
        Stack<ListNode> stack = new Stack<ListNode>();
        
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        dummy.next = head;
        
        ListNode nextNode = dummy.next;
        
        while(nextNode != null){
            for(int i = 0; i < k && nextNode != null; i ++){
                stack.push(nextNode);
                nextNode = nextNode.next;//move forward until it reach to the k-th link
            }
            
            //if nextNode reach to the end of the linked list, and the size of stack dosen't reach to k 
            if(stack.size() != k){
                return dummy.next;//return the result
            }
            
            //otherwise doing the reverse links connection
            while(!stack.isEmpty()){
                current.next = stack.pop();
                current = current.next;
            }
            
            //make the last node of this reversed nodes to connect with the (k+1)th link node
            current.next = nextNode;
        }
        
        return dummy.next;
    }
}

//version-2: iteration with loop, space complexity: O(1)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        //edge case check
        if(head == null || k == 0){
            return head;
        }
        
       
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode current = pre.next;
        ListNode next = current.next;

        int count = 0;
        while (current != null) {
            count += 1;
            current = current.next;
        }

        while (count >= k) {

            current = pre.next;
            next = current.next;

            for (int i = 1; i < k; i++) {
                current.next = next.next;
                next.next = pre.next;
                pre.next = next;
                next = current.next;
            }

            pre = current;

            count -= k;
        }

        
        return dummy.next;
    }
}
