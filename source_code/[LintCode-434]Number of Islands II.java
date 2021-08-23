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
                        unionFind.union(parentOfCurrent, parentOfNext);
                        count--;
                    }
                }				
            }

            result.add(count);
        }

        return result;
    }
}
