/***
* LeetCode 1878. Get Biggest Three Rhombus Sums in a Grid
You are given an m x n integer matrix grid.

A rhombus sum is the sum of the elements that form the border of a regular rhombus shape in grid. 
The rhombus must have the shape of a square rotated 45 degrees with each of the corners centered in a grid cell. 
Below is an image of four valid rhombus shapes with the corresponding colored cells that should be included in each rhombus sum:
https://assets.leetcode.com/uploads/2021/04/23/pc73-q4-desc-2.png
Note that the rhombus can have an area of 0, which is depicted by the purple rhombus in the bottom right corner.

Return the biggest three distinct rhombus sums in the grid in descending order. 
If there are less than three distinct values, return all of them.

Example 1
    https://assets.leetcode.com/uploads/2021/04/23/pc73-q4-ex1.png
    Input: grid = [[3,4,5,1,3],[3,3,4,2,3],[20,30,200,40,10],[1,5,5,4,1],[4,3,2,2,5]]
    Output: [228,216,211]
    Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
    - Blue: 20 + 3 + 200 + 5 = 228
    - Red: 200 + 2 + 10 + 4 = 216
    - Green: 5 + 200 + 4 + 2 = 211
    
Example 2
    https://assets.leetcode.com/uploads/2021/04/23/pc73-q4-ex2.png
    Input: grid = [[1,2,3],[4,5,6],[7,8,9]]
    Output: [20,9,8]
    Explanation: The rhombus shapes for the three biggest distinct rhombus sums are depicted above.
    - Blue: 4 + 2 + 6 + 8 = 20
    - Red: 9 (area 0 rhombus in the bottom right corner)
    - Green: 8 (area 0 rhombus in the bottom middle)
    
Example 3
    Input: grid = [[7,7,7]]
    Output: [7]
    Explanation: All three possible rhombus sums are the same, so return [7].
    
Constraints:
    m == grid.length
    n == grid[i].length
    1 <= m, n <= 50
    1 <= grid[i][j] <= 10^5

LeetCode link: https://leetcode.com/problems/get-biggest-three-rhombus-sums-in-a-grid/
***/
//version-1: brute-force with set(remove duplicates) + minHeap(for top k largest value)
class Solution {
    // fields
    private static int m; // row size
    private static int n; // column size

    public int[] getBiggestThree(int[][] grid) {
        m = grid.length; // row size
        n = grid[0].length; // column size

        Queue<Integer> minHeap = null;;
        Set<Integer> set = new HashSet<>();// to handle the duplicates
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int radius = Math.min(i, j);
                radius = Math.min(radius, m - 1 - i);
                radius = Math.min(radius, n - 1 - j);

                set.add(grid[i][j]);
                boolean isValidToAdd = true;

                for (int r = 1; r <= radius; r++) {
                    
                    // starting from rhombus peak position
                    int row = i - r;
                    int column = j;

                    int sum = 0;
                    // down-left
                    for (int k = 0; k < r; k++) {
                        row += 1;
                        column -= 1;
                        
                        sum += isBound(row, column) ? grid[row][column] : 0;
                    }

                    // down-right
                    for (int k = 0; k < r; k++) {
                        row += 1;
                        column += 1;
                        
                        sum += isBound(row, column) ? grid[row][column] : 0;
                    }

                    // up-right
                    for (int k = 0; k < r; k++) {
                        row -= 1;
                        column += 1;

                        sum += isBound(row, column) ? grid[row][column] : 0;
                    }

                    // up-left
                    for (int k = 0; k < r; k++) {
                        row -= 1;
                        column -= 1;
                        
                        sum += isBound(row, column) ? grid[row][column] : 0;
                    }

                    //minHeap.offer(sum);
                    set.add(sum);
                }
            }
        }
        
        //System.out.println("set = " + set.toString());
        
        minHeap = new PriorityQueue<>(set);
        //System.out.println("minHeap = " + minHeap.toString());
        
        while (minHeap.size() > 3) {
            minHeap.poll();
        }

        int size = Math.min(minHeap.size(), 3);
        //System.out.println("size = " + size);

        int[] result = new int[size];
        int index = size - 1;
        while (!minHeap.isEmpty()) {
            result[index--] = minHeap.poll();
        }

        return result;
    }

    // helper method
    private boolean isBound(int row, int column) {
        return row >= 0 && row < m && column >= 0 && column < n;
    }
}
