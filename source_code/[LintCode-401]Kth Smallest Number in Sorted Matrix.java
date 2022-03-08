/***
* LintCode 401. Kth Smallest Number in Sorted Matrix
Find the kth smallest number in a row and column sorted matrix.
Each row and each column of the matrix is incremental.


Example 1:
    Input:
        [
          [1 ,5 ,7],
          [3 ,7 ,8],
          [4 ,8 ,9],
        ]
        k = 4
    Output: 5

Example 2:
    Input:
        [
          [1, 2],
          [3, 4]
        ]
        k = 3
    Output: 3

Challenge
    O*(klogn*) time, n is the maximum of the width and height of the matrix.
***/
public class Solution {
    // inner class
    class Element {
        //fields
        int row;
        int column;
        int val;
        //constructor
        public Element(int row, int column, int val) {
            this.row = row;
            this.column = column;
            this.val = val;
        }
    }
    /**
     * @param matrix: a matrix of integers
     * @param k: An integer
     * @return: the kth smallest number in the matrix
     */
    public int kthSmallest(int[][] matrix, int k) {
        // check corner case
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }
        
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        boolean[][] visited = new boolean[rowSize][columnSize];
        int[] dx = new int[] {0, 1};
        int[] dy = new int[] {1, 0};
        
        //initialize the Min Heap, use lamda expression as to make the comparator
        Queue<Element> minHeap = new PriorityQueue<Element>(k, (a, b) -> a.val - b.val);
        minHeap.offer(new Element(0, 0, matrix[0][0]));
        
        // 1st -> (k-1)th step operation
        for (int i = 0; i < k - 1; i++) {
            Element current = minHeap.poll();
            for (int j = 0; j < 2; j++) {
                int next_x = current.row + dx[j];
                int next_y = current.column + dy[j];
                
                if (next_x < rowSize && next_y < columnSize && !visited[next_x][next_y]) {
                    visited[next_x][next_y] = true;
                    Element nextElement = new Element(next_x, next_y, matrix[next_x][next_y]);
                    minHeap.offer(nextElement);
                }
            }
        }
        
        // kth step operation
        return minHeap.peek().val;
    }
}
