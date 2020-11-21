/***
LeetCode 225. Implement Stack using Queues
Implement a last in first out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal queue (push, top, pop, and empty).

Implement the MyStack class:
    void push(int x) Pushes element x to the top of the stack.
    int pop() Removes the element on the top of the stack and returns it.
    int top() Returns the element on the top of the stack.
    boolean empty() Returns true if the stack is empty, false otherwise.

Notes:
    You must use only standard operations of a queue, which means only push to back, peek/pop from front, size, and is empty operations are valid.
    Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue), as long as you use only a queue's standard operations.

Follow-up: Can you implement the stack such that each operation is amortized 
    O(1) time complexity? In other words, performing n operations will take overall 
    O(n) time even if one of those operations may take longer.

Example 1:
    Input
        ["MyStack", "push", "push", "top", "pop", "empty"]
        [[], [1], [2], [], [], []]
    Output
        [null, null, null, 2, 2, false]

    Explanation
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        myStack.top(); // return 2
        myStack.pop(); // return 2
        myStack.empty(); // return False

Constraints:
    1 <= x <= 9
    At most 100 calls will be made to push, pop, top, and empty.
    All the calls to pop and top are valid.
***/
//solution-1
class MyStack {
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;

    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        moveItemsExceptLastOne();
        int result = queue1.poll();
        swapQueues();
        
        return result;
    }
    
    /** Get the top element. */
    public int top() {
        moveItemsExceptLastOne();
        int value = queue1.poll();
        queue2.offer(value);
        
        swapQueues();
        
        return value;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
    
    // helper methods
    private void moveItemsExceptLastOne() {
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
    }
    
    private void swapQueues() {
        Queue<Integer> tmp = queue1;
        queue1 = queue2;
        queue2 = tmp;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

//solution-2:
class MyStack {
    private Queue<Integer> queueIn;
    private Queue<Integer> queueOut;

    /** Initialize your data structure here. */
    public MyStack() {
        queueIn = new LinkedList<>();
        queueOut = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queueIn.offer(x);
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        queueInToQueueOut();
        
        while (queueOut.size() != 1){
            queueIn.offer(queueOut.poll());
        }
        
        int result = queueOut.poll();
        
        switchQueue();
        
        return result;
    }
    
    /** Get the top element. */
    public int top() {
        queueInToQueueOut();
        int value = pop();
        push(value);
        
        return value;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        if (!queueIn.isEmpty() && queueOut.isEmpty()) {
            queueInToQueueOut();
        }
        
        return queueOut.isEmpty();
    }
    
    // helper methods
    private void queueInToQueueOut() {
        if (queueIn.isEmpty()) {
            return;
        }
        
        while (!queueIn.isEmpty()) {
            queueOut.offer(queueIn.poll());
        }
    }
    
    private void switchQueue(){
        Queue tmp = queueOut;
        queueOut = queueIn;
        queueIn = tmp;
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
