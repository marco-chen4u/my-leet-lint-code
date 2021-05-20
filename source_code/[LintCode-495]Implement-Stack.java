/***
* LintCode 495. Implement Stack
Implement a stack. You can use any data structure inside a stack except stack itself to implement it.

Example 1:
    Input:
    push(1)
    pop()
    push(2)
    top()  // return 2
    pop()
    isEmpty() // return true
    push(3)
    isEmpty() // return false

Example 2:
    Input:
    isEmpty()
***/
//version-1: list
public class Stack {

    // fields
    private List<Integer> list = new ArrayList<>();

    /*
     * @param x: An integer
     * @return: nothing
     */
    public void push(int x) {
        list.add(0, x);
    }

    /*
     * @return: nothing
     */
    public void pop() {
        if (isEmpty()) {
            return;
        }

        list.remove(0);
    }

    /*
     * @return: An integer
     */
    public int top() {
        return list.get(0);
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }
}

//version-2: inner calss node
public class Stack {
    // inner class
    class Node {
        // fields
        int val;
        Node next;
        
        // constructor
        public Node(int val) {
            this.val = val;
        }
    }

    // fields
    private final int DEFAULT_VALUE = -1;
    private Node head;
    private Node tail;
    private int size;

    // constructor
    public Stack() {
        this.head = new Node(DEFAULT_VALUE);
        this.tail = this.head;
        this.size = 0;
    }

    /*
     * @param x: An integer
     * @return: nothing
     */
    public void push(int x) {
        Node node = new Node(x);
        node.next = head.next;
        head.next = node;
        size++;
    }

    /*
     * @return: nothing
     */
    public void pop() {
        if (size == 0) {
            return;
        }

        Node current = head.next;
        head.next = current.next;
        size--;
    }

    /*
     * @return: An integer
     */
    public int top() {
        return size !=0 ? head.next.val : -1; 
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
}
