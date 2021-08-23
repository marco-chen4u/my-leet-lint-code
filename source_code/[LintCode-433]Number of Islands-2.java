/***
* LintCode 433. Number of Islands
Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. 
If two 1 is adjacent, we consider them in the same island. 
We only consider up/down/left/right adjacent.

Find the number of islands.


Example 1:
    Input:
        [
          [1,1,0,0,0],
          [0,1,0,0,1],
          [0,0,0,1,1],
          [0,0,0,0,0],
          [0,0,0,0,1]
        ]
    Output:
        3

Example 2:
    Input:
        [
          [1,1]
        ]
    Output:
        1
***/

//version-2: Union Find
// helper class
class UnionFind {
    // field 
    Map<Integer, Integer> father;
    
    // constructor
    public UnionFind(int size) {
        father = new HashMap<Integer, Integer>();
        
        for (int i = 0; i < size; i++) {
            father.put(i, i);
        }
    }
    
    // methods
    public int find(int x) {
        int parent = father.get(x);
        
        while (parent != father.get(parent)) {
            parent = father.get(parent);
        }
        
        return parent;
    }
    
    public void union(int x, int y) {
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX != parentY) {
            father.put(parentX, parentY);
        }
    }
}

public class Solution {
    // helper methods
    private boolean isInBound(boolean[][] grid, int x, int y) {
        int n = grid.length; // row size
        int m = grid[0].length; // column size        
        return x >= 0 && x < n && // row bound check
                y >= 0 && y < m;  // column bound check
    }
    
    /**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        int count = 0; // default value
        // check corner case
        if (grid == null || grid.length == 0) {
            return count;
        }        
        if (grid[0] == null || grid[0].length == 0) {
            return count;
        }
        
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        int[] islands = new int[n * m];
        UnionFind unionFind = new UnionFind(n * m);
        int[] directionX = new int[] {1, 0, 0, -1};
        int[] directionY = new int[] {0, 1, -1, 0};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j]) {
                    int pos = i * m + j;// 2D position transfer to 1D position
                    islands[pos] = 1;
                    count++;
                    
                }
            }
        }
        
        // merge islands and decrease count when on merging island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int pos = i * m + j;
                if (islands[pos] != 1) {
                    continue;
                }
                
                for (int k = 0; k < 4; k++) {
                    int x = i + directionX[k];
                    int y = j + directionY[k];
                    
                    int adjPos = x * m + y;
                    
                    if (!isInBound(grid, x, y) || islands[adjPos] != 1) {
                        continue;
                    }
                        
                    int currentParent = unionFind.find(pos);
                    int adjParent = unionFind.find(adjPos);                    
                    if (currentParent != adjParent) {
                        count--;
                        unionFind.union(currentParent, adjParent);
                    }
                }
            }
        }
        
        return count;
    }
}
