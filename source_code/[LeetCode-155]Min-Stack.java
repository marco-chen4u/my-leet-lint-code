/***
* LeetCode 155. Min Stack
Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.

Example 1:
    Input
        ["MinStack","push","push","push","getMin","pop","top","getMin"]
        [[],[-2],[0],[-3],[],[],[],[]]

    Output
        [null,null,null,null,-3,null,0,-2]

    Explanation
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin(); // return -3
        minStack.pop();
        minStack.top();    // return 0
        minStack.getMin(); // return -2

Constraints:
    Methods pop, top and getMin operations will always be called on non-empty stacks.
***/
class MinStack {
    
    //fields
    private final int MAX_VALUE = Integer.MAX_VALUE;
    private final int MIN_VALUE = Integer.MIN_VALUE;
    private final int DEFAULT_VALUE = MIN_VALUE;
    
    private int min;
    private Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        min = MAX_VALUE;
        stack = new Stack<Integer>();
    }
    
    public void push(int x) {
        
        if (x <= min) {
            stack.push(min);
            min = x;
        }
        
        stack.push(x);
    }
    
    public void pop() {
        if (stack.isEmpty()) {
            return;
        }
        
        if (top() == min) {
            stack.pop();
            min = top();
        }
        
        stack.pop();
    }
    
    public int top() {
        if (!stack.isEmpty()) {
            return stack.peek();
        }
        
        return DEFAULT_VALUE;
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
