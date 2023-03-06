/***
* LeetCode 200. Number of Islands
Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
You may assume all four edges of the grid are all surrounded by water.
Example
    Example 1
        Input:
            11110
            11010
            11000
            00000
        Output: 1
    Example 2
        Input:
            11000
            11000
            00100
            00011
        Output: 3
***/

// version-1: BFS
class Solution {
    // fields
    private static int m; // row size
    private static int n; // col size

    private final static int[] directionX = {1, 0, 0, -1};
    private final static int[] directionY = {0, 1, -1, 0};

    private final static char SEA = '0';
    private final static char ISLAND = '1';

    // inner class
    class Element {
        // fields
        int x;
        int y;

        // constructor
        public Element(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
    
        // corner case
        if (grid == null || grid.length <= 0) {
            return 0;
        }

        int count = 0;

        m = grid.length;// row size
        n = grid[0].length; // col size

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != ISLAND) {
                    continue;
                }

                bfs(grid, i, j);
                count++;
            }
        }

        return count;
        
    }

    // helper method
    private void bfs(char[][] grid, int x, int y) {

        Element current = new Element(x, y);
        Queue<Element> queue = new LinkedList<>();
        queue.offer(current);
        grid[x][y] = SEA;

        while (!queue.isEmpty()) {
            current = queue.poll();

            for (int index = 0; index < 4; index++) {
                int nextX = current.x + directionX[index];
                int nextY = current.y + directionY[index];

                if (nextX < 0 || nextX >= m || 
                    nextY < 0 || nextY >= n || 
                    grid[nextX][nextY] == SEA) {
                    continue;
                }

                grid[nextX][nextY] = SEA;// marked as visited
                queue.offer(new Element(nextX, nextY));
            }
        }

    }
}

// version-2: DFS
class Solution {
    
    // main method
    public int numIslands(char[][] grid) {
        int count = 0;
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return count;
        }
        
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '1') {
                    findIsland(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
	
    // helper method
    private void findIsland(char[][] grid, int x, int y) {
        int n = grid.length;
        int m = grid[0].length;
        int[] directionX = new int[] {0, 1, -1, 0};
        int[] directionY = new int[] {1, 0, 0, -1};
        
        grid[x][y] = '0';// marked as vistited
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];
            
            if (nextX >= 0 && nextX < n &&
                nextY >= 0 && nextY < m &&
                grid[nextX][nextY] == '1') {
                findIsland(grid, nextX, nextY); // recursion
            }
        }
        
    }
}

// version-3: Union Find
class Solution {
    // inner class
    class UnionFind {
        // field
        private Map<Integer, Integer> father;
        
        // constructor
        public UnionFind(boolean[] islands) {
            father = new HashMap<Integer, Integer>();
            for (int i = 0; i < islands.length; i++) {
                if (islands[i]) {
                    father.put(i, i);
                }                
            }
        }
        
        // methods
        public int find(int x) {
            int parent = father.get(x);
            while (parent != father.get(parent)) {
                parent = father.get(parent);
            }
            
            int finalParent = parent;
            return finalParent;
        }
    
        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);
            
            if (parentX != parentY) {
                father.put(parentX, parentY);
            }
        }
    }
    
    // helper method
    
    // main method
    public int numIslands(char[][] grid) {
        int count = 0;
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return count;
        }
        
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        
        int size = n * m;              
        boolean[] islands = new boolean[size];
        int[] directionX = new int[] {0, 1, -1, 0};
        int[] directionY = new int[] {1, 0, 0, -1};
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] =='1') {
                    int pos = i * m + j;
                    islands[pos] = true;
                    
                    count++;
                }              
            }
        }
        
        UnionFind unionFind = new UnionFind(islands);
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }
                
                int pos = i * m + j;                
                
                for (int k = 0; k < 4; k++) {
                    int x = i + directionX[k];
                    int y = j + directionY[k];
                    
                    if (x < 0 || x >= n || y < 0 || y >= m || grid[x][y] != '1') {
                        continue;
                    }
                    
                    int nextPos = x * m + y;
                    
                    int currentParent = unionFind.find(pos);
                    int nextParent = unionFind.find(nextPos);
                    
                    if (currentParent != nextParent) {
                        unionFind.union(currentParent, nextParent);
                        count--;//when 2 merge into 1, the count should increase by 1
                    }
                }
            }
        }
        
        return count;
    }
}

class Solution {
    // inner class
    class UnionFind {
        private Map<Integer, Integer> father;

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

            int finalParent = parent;

            int current = father.get(x);
            int next = father.get(current);			
            while (current != next) {
                father.put(current, finalParent);//compressed update

                current = next;
                next = father.get(next);
            }

            return finalParent;
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX != parentY) {
                father.put(parentX, parentY);
            }
        }
    }
	
    // main method
    public int numIslands(char[][] grid) {
        int count = 0;
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return count;
        }

        int n = grid.length;
        int m = grid[0].length;
        int size = n * m;
        int[] directionX = new int[] {0, 1, -1, 0};
        int[] directionY = new int[] {1, 0, 0, -1};

        boolean[] islands = new boolean[size];
        UnionFind unionFind = new UnionFind(size);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '1') {
                    continue;
                }
                int pos = i * m + j;
                islands[pos] = true;
                count++;
            }
        }

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                int pos = x * m + y;
                if (!islands[pos]) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int nextX = x + directionX[i];
                    int nextY = y + directionY[i];
                    int nextPos = nextX * m + nextY;

                    if (nextX < 0 || nextX >= n ||
                            nextY < 0 || nextY >= m ||
                            !islands[nextPos]) {
                        continue;
                    }

                    int currentParent = unionFind.find(pos);
                    int nextParent = unionFind.find(nextPos);

                    if (currentParent != nextParent) {
                        unionFind.union(currentParent, nextParent);
                        count--;
                    }
                }

            }
        }

        return count;
    }
}
