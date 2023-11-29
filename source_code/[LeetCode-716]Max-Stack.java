/***
* LeetCode 716. Max Stack
Design a max stack data structure that supports the stack operations and supports finding the stack's maximum element.

Implement the MaxStack class:
  -MaxStack() Initializes the stack object.
  -void push(int x) Pushes element x onto the stack.
  -int pop() Removes the element on top of the stack and returns it.
  -int top() Gets the element on the top of the stack without removing it.
  -int peekMax() Retrieves the maximum element in the stack without removing it.
  -int popMax() Retrieves the maximum element in the stack and removes it. 
    If there is more than one maximum element, only remove the top-most one.
  You must come up with a solution that supports O(1) for each top call and O(logn) for each other call.

Example 1:
Input
    ["MaxStack", "push", "push", "push", "top", "popMax", "top", "peekMax", "pop", "top"]
    [[], [5], [1], [5], [], [], [], [], [], []]
  Output
    [null, null, null, null, 5, 5, 1, 5, 1, 5]
  
  Explanation
    MaxStack stk = new MaxStack();
    stk.push(5);   // [5] the top of the stack and the maximum number is 5.
    stk.push(1);   // [5, 1] the top of the stack is 1, but the maximum is 5.
    stk.push(5);   // [5, 1, 5] the top of the stack is 5, which is also the maximum, because it is the top most one.
    stk.top();     // return 5, [5, 1, 5] the stack did not change.
    stk.popMax();  // return 5, [5, 1] the stack is changed now, and the top is different from the max.
    stk.top();     // return 1, [5, 1] the stack did not change.
    stk.peekMax(); // return 5, [5, 1] the stack did not change.
    stk.pop();     // return 1, [5] the top of the stack and the max element is now 5.
    stk.top();     // return 5, [5] the stack did not change.

Constraints:
  -10^7 <= x <= 10^7
  At most 10^5 calls will be made to push, pop, top, peekMax, and popMax.
  There will be at least one element in the stack when pop, top, peekMax, or popMax is called.

* Link(LeetCode): https://leetcode.com/problems/max-stack
* Link(LintCode): https://www.lintcode.com/problem/859/

* reference: (1) https://www.youtube.com/watch?v=BcZzdkvCQ0s   
             (2)
***/
Solution-1: time-limited-executed, 2-stacks to move the items to get target item
class MaxStack {

    private int max;
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;// backup

    public MaxStack() {
        max = Integer.MIN_VALUE;
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int x) {
        if (x >= max) {
            stack1.push(max);
            max = x;
        }

        stack1.push(x);
    }
    
    public int pop() {
        int result = Integer.MIN_VALUE;
        if (stack1.isEmpty()) {
            return result;
        }

        if (max == top()) {
            result = stack1.pop();
            max = top();
            stack1.pop();
            return result;
        }

        result = stack1.pop();
        return result;
    }
    
    public int top() {
        if (stack1.isEmpty()) {
            return Integer.MIN_VALUE;
        }

        //System.out.println("stack1.size() = " + stack1.size() +", stack2 is empty: " + stack2.isEmpty());

        return stack1.peek();
    }
    
    public int peekMax() {
        return max;
    }
    
    public int popMax() {
        while (!stack1.isEmpty() && max != top()) {
            stack2.push(stack1.pop());
        }

        int result = Integer.MIN_VALUE;
        if (max == top()) {
            result = stack1.pop();
            max = top();
            stack1.pop();
        }

        while(!stack2.isEmpty()) {
            push(stack2.pop());
        }

        return result;
    }
}

//solution-2: HashMap + Doubly Linked List + heap(max heap for popMAX() operaiton)
class MaxStack {

    // inner class
    class Node implements Comparable<Node> {
        int value;
        int index;
        Node prev;
        Node next;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node other) {
            if (this.value == other.value) {
                return other.index - this.index;
            }

            return other.value - this.value;
        }
    }

    // fields
    PriorityQueue<Node> heap;  // maxHeap
    Map<Integer, Integer> map; //<value, index>
    Node head;
    Node tail;

    public MaxStack() {

        heap = new PriorityQueue<Node>();
        map = new HashMap<>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);

        head.next = tail;
        tail.prev = head;
        
    }
    
    public void push(int x) {

        int index = map.getOrDefault(x, 0) + 1;
        map.put(x, index);

        Node current = new Node(x, index);

        tail.prev.next = current;
        current.prev = tail.prev;

        current.next = tail;
        tail.prev = current;

        heap.offer(current);
        
    }
    
    public int pop() {

        Node current = tail.prev;
        int value = current.value;
        map.put(value, map.get(value) - 1);
        if (map.get(value) == 0) {
            map.remove(value);
        }

        current.prev.next = tail;
        tail.prev = current.prev;

        current.next = null;
        current.prev = null;

        heap.remove(current);
        
        return value;
    }
    
    public int top() {

        return tail.prev.value;
        
    }
    
    public int peekMax() {
        return heap.peek().value;
    }
    
    public int popMax() {
        Node current = heap.poll();//max node
        int value = current.value;

        map.put(value, map.get(value) - 1);
        if (map.get(value) == 0) {
            map.remove(value);
        }

        current.prev.next = current.next;
        current.next.prev = current.prev;

        current.next = null;
        current.prev = null;

        return value;
    }
}
