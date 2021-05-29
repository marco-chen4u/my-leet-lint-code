/***
*LintCode 12. Min Stack
Implement a stack with following functions:

push(val) push val into the stack
pop() pop the top element and return it
min() return the smallest number in the stack
All above should be in O(1) cost

note: min() will never be called when there is no number in the stack.

Example:
    Input
        push(1)
        push(1)
        push(2)
        push(2)
        pop()
        push(1)
        pop()
        min()
    output:
        [2,1,1]
***/
public class MinStack {
    // field
    private Stack<Integer> stack;
    private final int DEFAULT_VALUE = -1;
    private int minValue;
    
    // constructor
    public MinStack() {
        this.stack = new Stack<Integer>();
        minValue = Integer.MAX_VALUE;
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        if (number <= minValue){
            stack.push(minValue);
            minValue = number;
        }
        stack.push(number);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        if (isEmpty()) {
            return DEFAULT_VALUE;
        }

        int result = stack.pop();
        if (result == minValue) {
            minValue = stack.pop();
        }

        return result;
    }

    /*
     * @return: An integer
     */
    public int min() {
        if (isEmpty()) {
            return DEFAULT_VALUE;
        }

        return minValue;
    }

    // helper methods
    private boolean isEmpty() {
        return stack.isEmpty();
    }
}
