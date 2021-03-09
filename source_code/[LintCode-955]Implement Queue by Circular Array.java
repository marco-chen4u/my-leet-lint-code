/***
* LintCode 955. Implement Queue by Circular Array
Implement queue by circulant array. You need to support the following methods:
    1.CircularQueue(n): initialize a circular array with size n to store elements
    2.boolean isFull(): return true if the array is full
    3.boolean isEmpty(): return true if there is no element in the array
    4.void enqueue(element): add an element to the queue
    5.int dequeue(): pop an element from the queue
Example
Example 1:
    Input:
        CircularQueue(5)
        isFull()
        isEmpty()
        enqueue(1)
        enqueue(2)
        dequeue()
    Output:
        ["false","true","1"]
Example 2:
    Input:
        CircularQueue(5)
        isFull()
        isEmpty()
        enqueue(1)
        enqueue(2)
        dequeue()
        dequeue()
        enqueue(1)
        enqueue(2)
        enqueue(3)
        enqueue(4)
        enqueue(5)
        isFull()
        dequeue()
        dequeue()
        dequeue()
        dequeue()
        dequeue()
    Output:
        ["false","true","1","2","true","1","2","3","4","5"]
Notice
    it's guaranteed in the test cases we won't call enqueue if it's full and we also won't call dequeue if it's empty. 
    So it's ok to raise exception in scenarios described above.
***/
public class CircularQueue {
	// fields
	private int[] array;
	private int front;
	private int rear;
	private int size;
	
	// constructor
    public CircularQueue(int n) {
        this.array = new int[n];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }
    /**
     * @return:  return true if the array is full
     */
    public boolean isFull() {
        return (size == array.length);
    }

    /**
     * @return: return true if there is no element in the array
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * @param element: the element given to be added
     * @return: nothing
     */
    public void enqueue(int element) {
        if (isFull()) {
            throw new Exception("It is full!");
        }

        rear = (front + size) % array.length;
        array[rear] = element;
        size++;
    }

    /**
     * @return: pop an element from the queue
     */
    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("It is Empty!");			
        }

        int element = array[front];
        front = (front + 1) % array.length;
        size--;

        return element;
    }
}
