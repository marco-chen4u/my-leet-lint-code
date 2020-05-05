/***
* LintCode 1327. delete string
Given a string, you need remove one character, and then stitch the remaining two substrings together in order.
You should ensure that the string which you just get ,has the smallest lexicographical order. Return the string.

Example
	Example 1:
		Input:"acd"
		Output:"ac" 
		Explanation:"ac" is the smallest lexicographical order
	Example 2:
		Input:"ozwdtvxuhx"
		Output:"owdtvxuhx"
 
Notice
	Guaranteed string length greater than 1 and less than 100000
***/
//version-1: 80% past, but got a Memory Limit Exceeded error
public class Solution {
    /**
     * @param str: the str
     * @return: the delete positon
     */
    public String deleteString(String str) {
        String result = null;
        
        // check corner case
        if (str == null || str.length() == 0) {
            return result;
        }
        
        int size = str.length();
        //System.out.println("size = " + size);
        
        int capacity = 20;
        
        Queue<String> maxHeap = new PriorityQueue<String>( capacity, (a, b)-> b.compareTo(a));
        
        for (int i = 0; i < size; i++) {
            String value = null;
            if (i == 0) {
                value = str.substring(1, size);
            }
            
            if (i == size - 1) {
                value = str.substring(0, size - 1);
            }
            
            if (value == null) {
                value = str.substring(0, i) + str.substring(i + 1);
                //System.out.println("i = " + i + ", value " + value);
            }
            
            maxHeap.offer(value);
            if (maxHeap.size() > capacity) {
                maxHeap.poll();
            }
        }
        
        while (maxHeap.size() != 1) {
            maxHeap.poll();
        }
        
        result = maxHeap.poll();
        //System.out.println("result = " + result);
        
        return result;
    }
}

//version-2:
public class Solution {
    /**
     * @param str: the str
     * @return: the delete positon
     */
    public String deleteString(String str) {
		String result = null;
		// check corner case
		if (str == null || str.length() == 0) {
			return result;
		}
		
		int size = str.length();
		
		int posIndex = -1;
		for (int i = 0; i < size - 1; i++) {
			if (str.charAt(i) > str.charAt(i + 1)) {
				posIndex = i;
				break;
			}
		}
		
		if (posIndex == -1) {
			result = str.substring(0, size - 1);
		}
		else {
			result = str.substring(0, posIndex) + str.substring(posIndex + 1, size);
		}
		
		return result;
	}
}