/***
* LintCode 364. Trapping Rain Water II
Given n x m non-negative integers representing an elevation map 2d where the area of each cell is 1 x 1, 
compute how much water it is able to trap after raining.

Example 1
    Given `5*4` matrix 
    Input:
        [[12,13,0,12],
         [13,4,13,12],
         [13,8,10,12],
         [12,13,12,12],
         [13,13,13,13]]
    Output:
        14

Example 2
    Input:
        [[2,2,2,2],
         [2,2,3,4],
         [3,3,3,1],
         [2,3,4,5]]
    Output:
        0

Link: 
    LintCode: https://www.lintcode.com/problem/364/
    LeetCode : https://leetcode.com/problems/trapping-rain-water-ii/
***/
//version-1: minHeap, time complexity is O(m * n * log(2*(m + n)) -> O(m * n * log(m + n))
class Element {
    // fields
    int x;
    int y;
    int height;

    // constructors
    public Element(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}

public class Solution {
    // fields
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
	
    /**
     * @param heights: a matrix of integers
     * @return: an integer
     */
    public int trapRainWater(int[][] heights) {
        int result = 0;
        // check corner case
        if (heights == null || heights.length == 0) {
            return result;
        }

        int n = heights.length; // row size
        int m = heights[0].length; // column size;
        boolean[][] visited = new boolean[n][m];

        Comparator<Element> comparator = new Comparator<Element>() {
            @Override
            public int compare(Element a, Element b) {
                return a.height - b.height;
            }
        };

        Queue<Element> minHeap = new PriorityQueue<Element>(comparator);

        // upper line
        for (int j = 0; j < m; j++) {
            minHeap.offer(new Element(0, j, heights[0][j]));
            visited[0][j] = true;
        }

        // bottom line 
        for (int j = 0; j < m; j++) {
            minHeap.offer(new Element(n - 1, j, heights[n - 1][j]));
            visited[n - 1][j] = true;
        }

        // left line
        for (int i = 1; i < n - 1; i++) {
            minHeap.offer(new Element(i, 0, heights[i][0]));
            visited[i][0] = true;
        }

        // right line
        for (int i = 1; i < n - 1; i++) {
            minHeap.offer(new Element(i, m - 1, heights[i][m - 1]));
            visited[i][m - 1] = true;
        }

        while (!minHeap.isEmpty()) { // minHeap could help us to find the lowest pillar of the surrounding bucket for the first time
            Element current = minHeap.poll();

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DIRECTION_X[i];
                int nextY = current.y + DIRECTION_Y[i];

                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                minHeap.offer(new Element(nextX, nextY, Math.max(current.height, heights[nextX][nextY]))); // *note* it's important here to guarentee the surrounding bucket to hold the water 
                result += Math.max(0, current.height - heights[nextX][nextY]);
            }
	
        }

        return result;
    }
}

//version-2: minHeap, time-complexity: O(m * n * log(m + n))
class Solution {

    private static final int[] DIRECTION_X = new int[] {1, 0, 0, -1};
    private static final int[] DIRECTION_Y = new int[] {0, 1, -1, 0};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length; // row size
        int n = heightMap[0].length; // column size

        Queue<Element> minHeap = new PriorityQueue<>();

        boolean[][] visited = new boolean[m][n];

        // for the couter to get the lowest bucket pillar
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0 || i == m - 1 || j == n - 1) {
                    minHeap.offer(new Element(i, j, heightMap[i][j]));
                    visited[i][j] = true;
                }
            }
        }

        int result = 0;

        while (!minHeap.isEmpty()) {
            Element current = minHeap.poll();
            int currentX = current.x;
            int currentY = current.y;
            int currentHeight = current.height;

            for (int i = 0; i < 4; i++) {
                int nextX = currentX + DIRECTION_X[i];
                int nextY = currentY + DIRECTION_Y[i];

                if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {
                    continue;
                }

                if (visited[nextX][nextY]) {
                    continue;
                }

                int nextHeight = heightMap[nextX][nextY];

                visited[nextX][nextY] = true;
                minHeap.offer(new Element(nextX, nextY, Math.max(currentHeight, nextHeight)));
                result += Math.max(0, currentHeight - nextHeight);
            }
        }

        return result;
    }
}

// helper class
class Element implements Comparable<Element> {
    int x;
    int y;
    int height;

    public Element(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    @Override
    public int compareTo(Element other) {
        return this.height - other.height;
    }
}


