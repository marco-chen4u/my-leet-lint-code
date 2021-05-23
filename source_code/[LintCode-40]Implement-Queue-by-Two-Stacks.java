/***
* LintCode 40 · Implement Queue by Two Stacks 
As the title described, you should only use two stacks to implement a queue's actions.
The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.
Both pop and top methods should return the value of first element.

Example 1:
    Input:
    Queue Operations = 
        push(1)
        pop()    
        push(2)
        push(3)
        top()    
        pop()  
    Output:
        1
        2
        2
    Explanation:
        Both pop and top methods should return the value of the first element.

Example 2:
    Input:
    Queue Operations = 
        push(1)
        push(2)
        push(2)
        push(3)
        push(4)
        push(5)
        push(6)
        push(7)
        push(1)
    Output:
        []
    Explanation:
        There is no output.

Challenge
    implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
***/
public class MyQueue {
    // fields
    private final int DEFAULT_VALUE = -1;
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    // constructor
    public MyQueue() {
        // do intialization if necessary
        this.stack1 = new Stack<Integer>();
        this.stack2 = new Stack<Integer>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        stack1.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        if (isEmpty()) {
            return DEFAULT_VALUE;
        }

        while (stack1.size() != 1) {
            stack2.push(stack1.pop());
        }

        int result = stack1.pop();
        
        moveItemsBack();

        return result;
    }

    /*
     * @return: An integer
     */
    public int top() {
        if (isEmpty()) {
            return DEFAULT_VALUE;
        }

        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        int result = stack2.peek();

        moveItemsBack();

        return result;
    }

    // helper methods
    private boolean isEmpty() {
        return stack1.isEmpty();
    }

    private void moveItemsBack() {
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }
}
