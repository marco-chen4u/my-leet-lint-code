/***
* LintCode 224. Implement Three Stacks by Single Array
Implement three stacks by single array.
You can assume the three stacks has the same size and big enough, 
you don't need to care about how to extend it if one of the stack is full.

Example
	ThreeStacks(5)  // create 3 stacks with size 5 in single array. stack index from 0 to 2
	push(0, 10) // push 10 in the 1st stack.
	push(0, 11)
	push(1, 20) // push 20 in the 2nd stack.
	push(1, 21)
	pop(0) // return 11
	pop(1) // return 21
	peek(1) // return 20
	push(2, 30)  // push 30 in the 3rd stack.
	pop(2) // return 30
	isEmpty(2) // return true
	isEmpty(0) // return false
***/
public class ThreeStacks {
	// inner class
	class StackNode {
		// fields
		int pre, next;
		int value;
		// constructor
		public StackNode(int pre, int value, int next) {
			this.pre = pre;
			this.value = value;
			this.next = next;
		}
	}
	
	// fields
	private int stackSize;
	private int indexUsed;
	private int[] stackPointer;
	private StackNode[] buffer;
	private final int MAX_NUM = 3;
	
	// helper method
	private void swap(int lastIndex, int topIndex, int stackNum) {
		// check corner case
		if (buffer[lastIndex].pre == topIndex) {
			int tmp = buffer[lastIndex].value;
			buffer[lastIndex].value = buffer[topIndex].value;
			buffer[topIndex].value = tmp;
			
			int preOfTopIndex = buffer[topIndex].pre;
			if (preOfTopIndex != -1) {
				buffer[preOfTopIndex].next = lastIndex;
			}
			
			buffer[lastIndex].pre = preOfTopIndex;
			
			buffer[lastIndex].next = topIndex;
			buffer[topIndex].pre = lastIndex;
			buffer[topIndex].next = -1;
			
			stackPointer[stackNum] = topIndex;
			return;
		}
		
		// normal case (lastIndex <= topIndex)
		int preOfLastIndex = buffer[lastIndex].pre;
		if (preOfLastIndex != -1) {
			buffer[preOfLastIndex].next = topIndex;
		}
		
		int preOfTopIndex = buffer[topIndex].pre;
		if (preOfTopIndex != -1) {
			buffer[preOfTopIndex].next = lastIndex;
		}
		
		int nextOfTopIndex = buffer[topIndex].next;
		if (nextOfTopIndex == -1) {
			for (int i = 0; i < MAX_NUM; i++) {
				if (stackPointer[i] == topIndex) {
					stackPointer[i] = lastIndex;
				}
			}
		}
		else {
			buffer[nextOfTopIndex].pre = lastIndex;
		}
		
		StackNode tmp = buffer[lastIndex];
		buffer[lastIndex] = buffer[topIndex];
		buffer[topIndex] = tmp;
		
		stackPointer[stackNum] = topIndex;
	}
	
    /*
    * @param size: An integer
    */public ThreeStacks(int size) {
        // do intialization if necessary
		stackSize = size;
		buffer = new StackNode[size * MAX_NUM];
		stackPointer = new int[MAX_NUM];
		for (int i = 0; i < MAX_NUM; i++) {
			stackPointer[i] = -1;
		}
		indexUsed = 0;		
    }

    /*
     * @param stackNum: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void push(int stackNum, int value) {
        int lastIndex = stackPointer[stackNum];
		stackPointer[stackNum] = indexUsed;
		buffer[indexUsed++] = new StackNode(lastIndex, value, -1);
		
		if (lastIndex != -1) {
			buffer[lastIndex].next = stackPointer[stackNum];
		}
    }

    /*
     * @param stackNum: An integer
     * @return: the top element
     */
    public int pop(int stackNum) {
        int value = buffer[stackPointer[stackNum]].value;
		int lastIndex = stackPointer[stackNum];
		if (lastIndex == indexUsed - 1) {
			return value;
		}
		
		swap(lastIndex, indexUsed - 1, stackNum);		
		
		stackPointer[stackNum] = buffer[stackPointer[stackNum]].pre;
		if (stackPointer[stackNum] != -1) {
			buffer[stackPointer[stackNum]].next = -1;
		}
		
		buffer[indexUsed - 1] = null;
		indexUsed--;
		
		return value;
    }

    /*
     * @param stackNum: An integer
     * @return: the top element
     */
    public int peek(int stackNum) {
        int index = stackPointer[stackNum];
        if (index == -1) {
            return Integer.MAX_VALUE;
        }
		return buffer[index].value;
    }

    /*
     * @param stackNum: An integer
     * @return: true if the stack is empty else false
     */
    public boolean isEmpty(int stackNum) {
        return (stackPointer[stackNum] == -1);
    }
}