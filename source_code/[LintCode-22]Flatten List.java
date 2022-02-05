/***
* LintCode 22. Flatten List
Given a list, each element in the list can be a list or integer. flatten it into a simply list with integers.

Example 1:
    Input:  [[1,1],2,[1,1]]
    Output: [1,1,2,1,1]	
    Explanation:
        flatten it into a simply list with integers.
Example 2:
    Input:  [1,2,[1,2]]
    Output:[1,2,1,2]	
    Explanation:  
        flatten it into a simply list with integers.

Example 3:
    Input: [4,[3,[2,[1]]]]
    Output:[4,3,2,1]	
    Explanation: 
        flatten it into a simply list with integers.
Challenge
    Do it in non-recursive.

Notice
    If the element in the given list is a list, it can contain list too.
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
//version-1: recursion
public class Solution {

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nestedList == null || nestedList.isEmpty()) {
            return result;
        }
        
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                result.add(item.getInteger());
            }
            else {
                result.addAll(flatten(item.getList()));
            }
        }
        
        return result;
    }
} 
 
//version-2: none-recursion
public class Solution {

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        List<Integer> result = new ArrayList<Integer>();
        // check corner case
        if (nestedList == null || nestedList.isEmpty()) {
            return result;
        }

        Iterator<NestedInteger> it = nestedList.iterator();
        Stack<Iterator<NestedInteger>> stack = new Stack<Iterator<NestedInteger>>();

        while (!stack.isEmpty() || it.hasNext()) {
            if (it.hasNext()) {
                NestedInteger current = it.next();
                if (current.isInteger()) {
                    result.add(current.getInteger());
                }
                else {
                    stack.push(it);
                    it = current.getList().iterator();
                }// if current
            }//if it
            else {
                it = stack.pop();
            }// else if it
        }// while

        return result;
    }
}
