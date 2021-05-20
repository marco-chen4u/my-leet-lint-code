/***
* LintCode 546. Implement Queue by Interface
Implement queue by interface.

Example
    See code for more information.
***/
interface InterfaceQueue {
    void push(int element);

    // define an interface for pop method
    /* write your code here */
    int pop();

    // define an interface for top method
    /* write your code here */
    int top();
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue queue = new MyQueue();
 * queue.push(123);
 * queue.top(); will return 123;
 * queue.pop(); will return 123 and pop the first element in the queue
 */

//version-1: list
public class MyQueue implements InterfaceQueue {
    List<Integer> list = new ArrayList<>();
    /* you can declare your private attributes here */
    public MyQueue() {
       // do initialization if necessary
    }

    // implement the push method
    /* write your code here */
    @Override
    public void push(int val) {
       list.add(val);
    }
    
    // implement the pop method
    /* write your code here */
    @Override
    public int pop() {
        return list.remove(0);
    }
    
	// implement the top method
    /* write your code here */
    @Override
    public int top() {
        return list.get(0);
    }
}

//version-2: inner class node
public class MyQueue implements InterfaceQueue {
    // inner class
    class Node {
        int val;
        Node next;

        // constructor
        public Node(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // fields
    private final int DEFAULT_VALUE = -1;

    private Node head;
    private Node tail;

    /* you can declare your private attributes here */
    public MyQueue() {
       // do initialization if necessary
       this.head = new Node(DEFAULT_VALUE);
       this.tail = this.head;
    }

    // implement the push method
    /* write your code here */
    @Override
    public void push(int val) {
       Node node = new Node(val);
       tail.next = node;
       tail = node;
    }
	
    // implement the pop method
    /* write your code here */
    @Override
    public int pop() {
        if (head.next == null) {
            return -1;
        }

        Node current = head.next;
        head = current;

        return current.val;
    }
    
	// implement the top method
    /* write your code here */
    @Override
    public int top() {
        return head.next != null ? head.next.val : -1;
    }
}
