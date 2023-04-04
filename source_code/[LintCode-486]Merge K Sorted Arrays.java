/***
* LintCode 486. Merge K Sorted Arrays
Given k sorted integer arrays, merge them into one sorted array.

Example 1:
    Input: 
      [
        [1, 3, 5, 7],
        [2, 4, 6],
        [0, 8, 9, 10, 11]
      ]
    Output: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]

Example 2:
    Input:
      [
        [1,2,3],
        [1,2]
      ]
    Output: [1,1,2,2,3]

Challenge
    Do it in O(N log k).

*N is the total number of integers.
*k is the number of arrays.
***/
//version-1: minHeap
public class Solution {
    // inner class
    class Element {
        // fields
        int row;
        int column;
        int value;
        // constructor
        public Element(int row, int column, int value) {
            this.row = row;
            this.column = column;
            this.value = value;
        }
    }
    
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // check corner case
        if (arrays == null || arrays.length == 0) {
            return new int[0];
        }
        
        int totalSize = 0;
        int rowSize = arrays.length;
        Queue<Element> minHeap = new PriorityQueue<Element>(rowSize, (a, b)->a.value - b.value);// use Lamda
        // initialize the Min Heap
        for (int i = 0; i < rowSize; i++) {
            int currentColumnSize = arrays[i].length;
            if (currentColumnSize > 0) {
                minHeap.offer(new Element(i, 0, arrays[i][0]));
                totalSize += currentColumnSize;
            }
        }
        
        int[] result = new int[totalSize];
        int index = 0;
        while (!minHeap.isEmpty()) {
            Element element = minHeap.poll();
            result[index++] = element.value;
            if (element.column + 1 < arrays[element.row].length) {
                element.column += 1;
                element.value = arrays[element.row][element.column];
                minHeap.offer(element);
            }
        }
        
        return result;
    }
}

//version-2: merge sort(divide & conquer, recursion)
public class Solution {
    /**
     * @param arrays: k sorted integer arrays
     * @return: a sorted array
     */
    public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here
        int rowSize = arrays.length;
        return mergeSortedArrays(arrays, 0, rowSize - 1);
    }

    // helper mehtods
    private int[] mergeSortedArrays(int[][] arrays, int start, int end) {
        if (start >= end) {
            return arrays[start];
        }

        int left = start;
        int right = end;
        int mid = left + (right - left) / 2;
        int[] leftNums = mergeSortedArrays(arrays, left, mid);
        int[] rightNums = mergeSortedArrays(arrays, mid + 1, right);

        return mergeSortedArray(leftNums, rightNums);
    }

    private int[] mergeSortedArray(int[] leftNums, int[] rightNums) {
        int leftSize = leftNums.length;
        int rightSize = rightNums.length;
        int size = leftSize + rightSize;

        int[] result = new int[size];

        // two pointers
        int i = 0;
        int j = 0;

        int index = 0;
        while (i < leftSize && j < rightSize) {
            if (leftNums[i] <= rightNums[j]) {
                result[index++] = leftNums[i++];
            }
            else {
                result[index++] = rightNums[j++];
            }
        }

        while (i < leftSize) {
            result[index++] = leftNums[i++];
        }

        while (j < rightSize) {
            result[index++] = rightNums[j++];
        }

        return result;
    }
}
