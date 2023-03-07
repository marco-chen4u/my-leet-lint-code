/***
* LintCode 434. Number of Islands II
Given a n,m which means the row and column of the 2D matrix and an array of pair A( size k). 
Originally, the 2D matrix is all 0 which means there is only sea in the matrix. 
The list pair has k operator and each operator has two integer A[i].x, A[i].y 
means that you can change the grid matrix[A[i].x][A[i].y] from sea to island. 
Return how many island are there in the matrix after each operator.


Example 1:
    Input: n = 4, m = 5, A = [[1,1],[0,1],[3,3],[3,4]]
    Output: [1,1,2,2]
    Explanation:
        0.  00000
            00000
            00000
            00000
        1.  00000
            01000
            00000
            00000
        2.  01000
            01000
            00000
            00000
        3.  01000
            01000
            00000
            00010
        4.  01000
            01000
            00000
            00011
Example 2:
    Input: n = 3, m = 3, A = [[0,0],[0,1],[2,2],[2,1]]
    Output: [1,1,2,2]

Notice
    0 is represented as the sea, 1 is represented as the island. 
    If two 1 is adjacent, we consider them in the same island. 
    We only consider up/down/left/right adjacent.
***/
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
// version-1: UnionFind /w HashMap
// helper class
class UnionFind {
    // fields
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
		
        while(parent != father.get(parent)) {
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
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> result =  new ArrayList<Integer>();

        // check corner case
        if ((n == 0 && m == 0) || 
                (operators == null || operators.length == 0)) {
            return result;
        }

        int count = 0;
        int[] islands = new int[n * m];
        UnionFind unionFind = new UnionFind(n * m);

        int[] directionX = new int[] {1, 0, 0, -1};
        int[] directionY = new int[] {0, 1, -1, 0};

        for (Point point : operators) {
            int pos = point.x * m + point.y;

            if (islands[pos] == 1) {
                result.add(count);
                continue;
            }

            islands[pos] = 1;
            count++;

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + directionX[i];
                int nextY = point.y + directionY[i];
                int nextPos = nextX * m + nextY;
                if (nextX >= 0 && nextX < n &&
                        nextY >= 0 && nextY < m &&
                        islands[nextPos] == 1) {
                    int parentOfCurrent = unionFind.find(pos);
                    int parentOfNext = unionFind.find(nextPos);

                    if (parentOfCurrent != parentOfNext) {
                        unionFind.union(pos, nextPos);
                        count--;//merge
                    }
                }				
            }

            result.add(count);
        }

        return result;
    }
}

//version-2: UnionFind
public class Solution {
    // inner class
    class UnionFind {
        // fields
        private Map<Integer, Integer> fathers;
        private int count;
        
        // constructor
        public UnionFind() {
            fathers = new HashMap<Integer, Integer>();

            this.count = 0;
        }

        // method
        public void add(int x) {
            if (fathers.containsKey(x)) {
                return;
            }

            fathers.put(x, x);
            count++;
        }

        public int find(int x) {
            int parent = fathers.get(x);
            while (x != parent) {
                x = parent;
                parent = fathers.get(parent);
            }

            return parent;
        }

        public boolean isSameParent(int x, int y) {
            return find(x) == find(y);
        }

        public void union(int x, int y) {
            int parentX = find(x);
            int parentY = find(y);

            if (parentX != parentY) {
                fathers.put(parentX, parentY);
                
                count += (count != 0) ? -1 : 0;
            }
        }

        public int getCount() {
            return count;
        }

    }

    // fields
    private static final int[] DIRECTION_X = new int[]{0, 1, -1, 0};
    private static final int[] DIRECTION_Y = new int[]{1, 0, 0, -1};
    private static final int SEA = 0;
    private static final int ISLAND = 1;

    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> result = new ArrayList<>();
        int size = n * m;
        // check corner case
        if (size == 0 || operators == null || operators.length == 0) {
            return result;
        }

        UnionFind unionFind = new UnionFind();
        int[][] grid = new int[n][m];

        int count = unionFind.getCount();;

        for (Point current : operators) {
            int x = current.x;
            int y = current.y;                    

            if (grid[x][y] == ISLAND) {
                result.add(count);
                continue;
            }

            grid[x][y] = ISLAND;
            int currentPos = x * m + y;
            unionFind.add(currentPos);

            for(int i = 0; i < 4; i++) {
                int nextX = x + DIRECTION_X[i];
                int nextY = y + DIRECTION_Y[i];                

                if (nextX < 0 || nextX >= n || 
                    nextY < 0 || nextY >= m || 
                    grid[nextX][nextY] == SEA) {
                    continue;
                }

                int nextPos = nextX * m + nextY;
                unionFind.add(nextPos);

                if (!unionFind.isSameParent(currentPos, nextPos)) {
                    unionFind.union(currentPos, nextPos);
                }                
            }
            
            count = unionFind.getCount();
            result.add(count);
        }

        return result;
    }
}

//version-2 UnionFind /w Array
/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */

public class Solution {
    // fields
    private final int[] DIRECTION_X = new int[] {0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[] {1, 0, 0, -1};
    private final int ISLAND = 1;
    
    /**
     * @param n: An integer
     * @param m: An integer
     * @param operators: an array of point
     * @return: an integer array
     */
    public List<Integer> numIslands2(int n, int m, Point[] operators) {
        List<Integer> result = new ArrayList<Integer>();
        
        // check corner cases
        if ((n == 0 && m == 0) || 
            operators == null || operators.length == 0) {
            return result;
        }
        
        int size = n * m;
        int[][] matrix = new int[n][m];
        UnionFind uf = new UnionFind(size);
        
        int count = 0;
        for (Point current : operators) {
            if (current.x < 0 || current.x >= n || 
                current.y < 0 || current.y >= m) {
                continue;
            }
            
            if (matrix[current.x][current.y] == ISLAND) {
                result.add(count);
                continue;
            }
            
            matrix[current.x][current.y] = ISLAND;//marked it as an island
            count++;
            
            int currentPos = current.x * m + current.y;
            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DIRECTION_X[i];
                int nextY = current.y + DIRECTION_Y[i];
                
                int nextPos = nextX * m + nextY;
                if (nextX < 0 || nextX >= n || 
                    nextY < 0 || nextY >= m ||
                    matrix[nextX][nextY] != ISLAND) {
                    continue;
                }
                
                if (!uf.isConnected(currentPos, nextPos)) {
                    uf.union(currentPos, nextPos);
                    count--;
                }
            }
            
            result.add(count);
        }
        
        return result;
    }
}

// helper class
class UnionFind {
    // field
    int[] fathers;
    
    // constructor
    public UnionFind(int size) {
        fathers = new int[size];
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

        int parent = x;
        while (parent != fathers[parent]) {
            int tmp = fathers[parent];
            fathers[parent] = superParent;
            parent = tmp;
        }

        return superParent;
    }
    
    public boolean isConnected(int x, int y) {
        return (find(x) == find(y));
    }
    
    public void union(int x, int y) {
        if (isConnected(x, y)) {
            return;
        }
        
        int parentX = find(x);
        int parentY = find(y);
        
        if (parentX != parentY) {
            fathers[parentX] = parentY;
        }
    }
}
