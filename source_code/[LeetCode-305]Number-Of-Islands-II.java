/***
* Number of Islands II
You are given an empty 2D binary grid grid of size m x n. The grid represents a map where 0's represent water and 1's represent land. 
Initially, all the cells of grid are water cells (i.e., all the cells are 0's).

We may perform an add land operation which turns the water at position into a land. 
You are given an array positions where positions[i] = [ri, ci] is the position (ri, ci) at which we should operate the ith operation.

Return an array of integers answer where answer[i] is the number of islands after turning the cell (ri, ci) into a land.

An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.


Example 1:
    Input: m = 3, n = 3, positions = [[0,0],[0,1],[1,2],[2,1]]
    Output: [1,1,2,3]
    Explanation:
        Initially, the 2d grid is filled with water.
        - Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land. We have 1 island.
        - Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land. We still have 1 island.
        - Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land. We have 2 islands.
        - Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land. We have 3 islands.

Example 2:
    Input: m = 1, n = 1, positions = [[0,0]]
    Output: [1]

Constrains:
    1 <= m, n, positions.length <= 104
    1 <= m * n <= 104
    positions[i].length == 2
    0 <= ri < m
    0 <= ci < n

Follow up: 
    Could you solve it in time complexity O(k log(mn)), where k == positions.length?
    
* Link: https://leetcode.com/problems/number-of-islands-ii/description/
***/
//version-1 UnionFind /w Array
class Solution {
    // constant
    private static final int[] directionX = {0, 1, -1, 0};
    private static final int[] directionY = {1, 0, 0, -1};

    private static final int WATER = 0;
    private static final int ISLAND = 1;

    // fields

    public List<Integer> numIslands2(int m, int n, int[][] positions) {

        List<Integer> result = new ArrayList<>();
        if (m == 0 || n == 0 || positions == null || positions.length == 0) {
            return result;
        }

        int[][] matrix = new int[m][n];
        int count = 0;

        UnionFind unionFind = new UnionFind(m * n);

        for (int[] current : positions) {
            int currentX = current[0];
            int currentY = current[1];

            if (currentX < 0 || currentX >= m || 
                currentY < 0 || currentY >= n) {
                continue;
            }

            if (matrix[currentX][currentY] == ISLAND) {
                result.add(count);
                continue;
            }

            matrix[currentX][currentY] = ISLAND; // marked
            count++;

            int currentPos = currentX * n + currentY; // transform 2-demension position into 1-demension position value
            for (int index = 0; index < 4; index++) {
                int nextX = currentX + directionX[index];
                int nextY = currentY + directionY[index];

                if (nextX < 0 || nextX >= m || 
                    nextY < 0 || nextY >= n || 
                    matrix[nextX][nextY] != ISLAND) {
                    continue;
                }

                int nextPos = nextX * n + nextY; // transform 2-demension position into 1-demension position value
                if (!unionFind.isConnected(currentPos, nextPos)) {
                    unionFind.union(currentPos, nextPos);
                    count--;
                }
                
            }


            result.add(count);
        }

        return result;  
    }

    // helper method
    private class UnionFind {
        // field
        private int[] fathers;

        // constructor
        public UnionFind(int size) {
            this.fathers = new int[size];
            for (int i = 0; i < size; i++) {
                fathers[i] = i;
            }
        }

        // methods

        public int find(int x) {
            int superParent = fathers[x];
            while (superParent != fathers[superParent]) {
                superParent = fathers[superParent];
            }

            // flatten the found value
            int parent = x;
            while (parent != fathers[parent]) {
                int tmp = fathers[parent];
                fathers[parent] = superParent;
                parent = tmp;
            }

            return superParent;
        }

        public void union(int x, int y) {
            int parrentX = find(x);
            int parrentY = find(y);

            if (parrentX != parrentY) {
                fathers[parrentX] = parrentY;
            }
        }

        public boolean isConnected(int x, int y) {
            int parrentX = find(x);
            int parrentY = find(y);

            return parrentX == parrentY;
        }

    }
}
