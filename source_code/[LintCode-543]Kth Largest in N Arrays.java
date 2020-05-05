/***
* LintCode 543. Kth Largest in N Arrays
Find K-th largest element in N arrays.
Example
	Example 1:
		Input:
			k=3, [[9,3,2,4,7],[1,2,3,4,8]]
		Output:
			7
		Explanation:
			the 3rd largest element is 7

	Example 2:
		Input:
			k = 2, [[9,3,2,4,8],[1,2,3,4,2]]
		Output:
			8
		Explanation:
			the 1st largest element is 9, 2nd largest element is 8, 3rd largest element is 4 and etc.

Notice
	You can swap elements in the array
***/
// version-1: minHeap
public class Solution {
    /**
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        // check corner cases
        if (arrays == null || arrays.length == 0) {
            return 0;
        }
        
        if (k <= 0) {
            return 0;
        }
        
        Queue<Integer> minHeap = new PriorityQueue<Integer>();
        
        int n = arrays.length;
        for (int i = 0; i < n; i++) {
            int[] currentArray = arrays[i];
            int arraySize = currentArray.length;
            
            // check conrer case
            if (currentArray == null || arraySize == 0) {
                continue;
            }
            
            // normal case
            for (int j = 0; j < arraySize; j++) {
                minHeap.offer(currentArray[j]);
                if (minHeap.size() > k) {
                    minHeap.poll();
                }
            }
        }
        
        return minHeap.peek();
    }
}
// version-2: maxHeap
public class Solution {
    // inner class
    class Element {
		// fields
        int i, j;
        int value;
        // constructor
        public Element(int i, int j, int val) {
            this.i = i;
            this.j = j;
            this.value = val;
        }
    }
	
    /**
     * @param arrays: a list of array
     * @param k: An integer
     * @return: an integer, K-th largest element in N arrays
     */
    public int KthInArrays(int[][] arrays, int k) {
        int defaultValue = 0;
        // check corner case
        if (arrays == null || arrays.length == 0) {
            return defaultValue;
        }        
        if (k <= 0) {
            return defaultValue;
        }
        
        Queue<Element> maxHeap = new PriorityQueue<Element>((a,b)->b.value - a.value);        
        int rowSize = arrays.length;
        for (int i = 0; i < rowSize; i++) {
            int[] currentArray = arrays[i];
			
            if (currentArray == null || currentArray.length == 0) {
                continue;
            }			
            Arrays.sort(currentArray);
            int size = currentArray.length;
            maxHeap.offer(new Element(i, size - 1, currentArray[size - 1]));
        }
        
        for (int i = 1; i <= k; i++) {
            Element current = maxHeap.poll();            
            int row = current.i;
            int col = current.j;
            int value = current.value;
            
            if (i == k) {
                return value;
            }
            
            if (col - 1 < 0) {
                continue;
            }
            
            col--;
            current.i = row;
            current.j = col;
            current.value = arrays[row][col];
            
            maxHeap.offer(current);
        }
        
        return defaultValue;
    }
}