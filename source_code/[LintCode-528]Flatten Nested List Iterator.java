/***
* LintCode 528. Flatten Nested List Iterator
Given a nested list of integers, implement an iterator to flatten it.
Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example
	Example1
		Input: list = [[1,1],2,[1,1]]
		Output: [1,1,2,1,1]
	Example2
		Input: list = [1,[4,[6]]]
		Output: [1,4,6]
Notice
	You don't need to implement the remove method.
***/
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
import java.util.Iterator;

public class NestedIterator implements Iterator<Integer> {
    private Stack<NestedInteger> stack;

    public NestedIterator(List<NestedInteger> nestedList) {
        // Initialize your data structure here.
        stack = new Stack<NestedInteger>();
        
        if (nestedList == null || nestedList.isEmpty()) {
            return;
        }
        
        int size = nestedList.size();
        for (int i = size - 1; i >= 0; i--) {
            NestedInteger item = nestedList.get(i);
            stack.push(item);
        }
    }

    // @return {int} the next element in the iteration
    @Override
    public Integer next() {
        Integer result = null;
        // Write your code here
        if (hasNext()) {
            result = stack.pop().getInteger();
        }
        
        return result;
    }

    // @return {boolean} true if the iteration has more element or false
    @Override
    public boolean hasNext() {
        // Write your code here
        while (!stack.isEmpty()) {
            if (stack.peek().isInteger()) {
                return true;
            }
            
            NestedInteger nestedItem = stack.pop();
            List<NestedInteger> list = nestedItem.getList();
            if (list == null || list.isEmpty()) {
                continue;
            }
            
            int size = list.size();
            for (int i = size - 1; i >= 0; i--) {
                stack.push(list.get(i));
            }
        }
        
        return false;
    }

    @Override
    public void remove() {}
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v.add(i.next());
 */