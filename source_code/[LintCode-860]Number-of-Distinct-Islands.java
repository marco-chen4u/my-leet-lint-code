/***
* LintCode 860. Number of Distinct Islands
Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical). 
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. 
An island is considered to be the same as another if and only if one island has the same shape as another island (and not rotated or reflected).

Notice that:
11
1
and
 1
11
are considered different island, because we do not consider reflection / rotation.

-The length of each dimension in the given grid does not exceed 50.

Example 1:
    Input: 
      [
        [1,1,0,0,1],
        [1,0,0,0,0],
        [1,1,0,0,1],
        [0,1,0,1,1]
      ]
    Output: 3
    Explanation:
      11   1    1
      1        11   
      11
       1
       
Example 2:
    Input:
      [
        [1,1,0,0,0],
        [1,1,0,0,0],
        [0,0,0,1,1],
        [0,0,0,1,1]
      ]
    Output:
        1
    Explanation:
        11   
        11   
           11
           11
***/
//version-1: BFS
public class Solution {
    // fields
    private int n;// row size
    private int m;// column size

    private final int ISLAND = 1;
    private final int SEA = 0;

    private final int[] directionX = new int[]{0, 1, -1, 0};
    private final int[] directionY = new int[]{1, 0, 0, -1};

    private final String SEPERATOR = "";

    /**
     * @param grid: a list of lists of integers
     * @return: return an integer, denote the number of distinct islands
     */
    public int numberofDistinctIslands(int[][] grid) {
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        // initialize
        n = grid.length;
        m = grid[0].length;

        Set<String> set = new HashSet<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == SEA || visited[i][j]) {
                    continue;
                }

                set.add(bfs(grid, i, j, visited));
            }
        }

        return set.size();
    }

    // helper method
    private String bfs(int[][] grid, int x, int y, boolean[][] visited) {
        int originX = x;
        int originY = y;
        Queue<Integer> queueX = new LinkedList<>();
        Queue<Integer> queueY = new LinkedList<>();

        StringBuilder sb = new StringBuilder();
        
        queueX.offer(x);
        queueY.offer(y);
        visited[x][y] = true;

        while (!queueX.isEmpty()) {
            int size = queueX.size();
            
            for (int i = 0; i < size; i++) {
                int currentX = queueX.poll();
                int currentY = queueY.poll();

                int distanceX = currentX - originX;
                int distanceY = currentY - originY;
                sb.append(distanceX + SEPERATOR + distanceY);

                for (int index = 0; index < 4; index++) {
                    int nextX = currentX + directionX[index];
                    int nextY = currentY + directionY[index];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                        continue;
                    }

                    if (visited[nextX][nextY] || grid[nextX][nextY] == SEA) {
                        continue;
                    }

                    visited[nextX][nextY] = true;
                    queueX.offer(nextX);
                    queueY.offer(nextY);
                }
            }
        }

        return sb.toString();
    }
}

//version-2: DFS
