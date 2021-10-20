/***
* LintCode 804. Number of Distinct Islands II
Given a non-empty 2D array grid of 0's and 1's, 
an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
You may assume all four edges of the grid are surrounded by water.

Count the number of distinct islands. 
An island is considered to be the same as another if they have the same shape, 
or have the same shape after rotation (90, 180, or 270 degrees only) 
or reflection (left/right direction or up/down direction).
Example
    Example 1:
        Input: [[1,1,0,0,0],[1,0,0,0,0],[0,0,0,0,1],[0,0,0,1,1]]
        Output: 1
        Explanation:
            The island is look like this:
                11000
                10000
                00001
                00011
        Notice that:
                11
                1
            and
                 1
                11
            are considered same island shapes. 
            Because if we make a 180 degrees clockwise rotation on the first island, 
            then two islands will have the same shapes.
    Example 2:
        Input: [[1,1,1,0,0],[1,0,0,0,1],[0,1,0,0,1],[0,1,1,1,0]]
        Output: 2
        Explanation:
            The island is look like this:
                11100
                10001
                01001
                01110
            Here are the two distinct islands:
                111
                1
            and
                1
                1
        Notice that:
            111
            1
        and
            1
            111
        are considered same island shapes. 
        Because if we flip the first array in the up/down direction, 
        then they have the same shapes.
Notice
    The length of each dimension in the given grid does not exceed 50.
***/
// version-1: DFS
// helper class
class Point implements Comparable<Point> {
    // fields
    public int x, y;
    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // method
    @Override
    public int compareTo(Point point) {
        return (this.x == point.x) ? (this.y - point.y) : (this.x - point.x);
    }
}

public class Solution {
    /**
     * @param grid: the 2D grid
     * @return: the number of distinct islands
     */
    public int numDistinctIslands2(int[][] grid) {
        // check corner case
        if (grid == null || grid.length == 0 || 
                grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int n = grid.length; // row size
        int m = grid[0].length; // column size
        Set<String> result = new HashSet<String>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (grid[i][j] != 1) {
                    continue;
                }

                List<Point> islands = new ArrayList<Point>();
                findIslands(islands, grid, i, j);

                result.add(getUnique(islands));
            }
        }

        return result.size();
    }

    // helper methods
    private void findIslands(List<Point> islands, int[][] grid, int x, int y) {
        int n = grid.length; // row size
        int m = grid[0].length; // column size
        int[] directionX = new int[] {0, 1, -1, 0};
        int[] directionY = new int[] {1, 0, 0, -1};

        grid[x][y] = 0;// marked visited
        islands.add(new Point(x, y));
		
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];

            if (nextX >= 0 && nextX < n &&
                nextY >= 0 && nextY < m &&
                grid[nextX][nextY] == 1) {
                findIslands(islands, grid, nextX, nextY);//DFS
            }
        }
    }	

    /**
     * transform 1 island to 8 island, and pick only 1 as their representative
     **/
    private String getUnique(List<Point> islands) {
        List<String> sameIslands = new ArrayList<String>();
        int[][] transition = new int[][] {{1, 1}, {-1, 1}, {1, -1}, {-1, -1}}; // 8 rotations

        for (int i = 0; i < 4; i++) {
            List<Point> l1 = new ArrayList<Point>();
            List<Point> l2 = new ArrayList<Point>();

            for (Point island : islands) {

                int x = island.x;
                int y = island.y;

                // rotation by 180 degree
                Point island1 = new Point(transition[i][0] * x , transition[i][1] * y);
                // rotation by 90 or 270 degree
                Point island2 = new Point(transition[i][0] * y , transition[i][1] * x);

                l1.add(island1);
                l2.add(island2);
            }

            sameIslands.add(getString(l1));
            sameIslands.add(getString(l2));
        }

        Collections.sort(sameIslands);

        return sameIslands.get(0);
    }

    private String getString(List<Point> islands) {
        Collections.sort(islands);

        StringBuilder sb = new StringBuilder();
        String seperator = " ";

        int x = islands.get(0).x;
        int y = islands.get(0).y;		

        for (Point island : islands) {
            // move(pan) this island to the coordinate point
            int posX = island.x - x;
            int posY = island.y - y;
            sb.append(posX + seperator + posY + seperator);
        }

        return sb.toString();
    }
}

//version-2: BFS
public class Solution {
    // inner class
    class Point implements Comparable<Point>{
        // fields
        int x;
        int y;

        // constructor
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point other) {
            if (this.x != other.x) {
                return this.x - other.x;
            }
            else {
                return this.y - other.y;
            }
        }
    }

    // fields
    private final int SEA = 0;
    private final int ISLAND = 1;

    private final int[] DIRECTION_X = new int[]{0, 1, -1, 0};
    private final int[] DIRECTION_Y = new int[]{1, 0, 0, -1};
    private final int[][] ROTATION = new int[][]{{1, 1}, {-1, 1}, {-1, -1}, {1, -1}};
    private final String SEPERATOR = "-";

    private int n; // row size
    private int m; // column size

    /**
     * @param grid: the 2D grid
     * @return: the number of distinct islands
     */
    public int numDistinctIslands2(int[][] grid) {
        // check corner cases
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        // initialize
        n = grid.length;
        m = grid[0].length;

        Set<String> set = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == SEA) {
                    continue;
                }

                List<Point> islands = new ArrayList<>();
                findIslands(grid, i, j, islands);
                set.add(getUnique(islands));
            }
        }

        return set.size();
    }

    // helper methods
    private void findIslands(int[][] grid, int x, int y, List<Point> islands) {

        Queue<Point> queue = new LinkedList<Point>();
        queue.offer(new Point(x, y));
        grid[x][y] = SEA;

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            islands.add(current);

            for (int i = 0; i < 4; i++) {
                int nextX = current.x + DIRECTION_X[i];
                int nextY = current.y + DIRECTION_Y[i];

                if (nextX < 0 || nextX >= n ||
                    nextY < 0 || nextY >= m ||
                    grid[nextX][nextY] == SEA) {
                    continue;
                }

                queue.offer(new Point(nextX, nextY));
                grid[nextX][nextY] = SEA;
            }
        }
    }	


    private String getUnique(List<Point> islands) {
        List<String> mappingIslands = new ArrayList<String>();

        for (int i = 0; i < 4; i++) {
            List<Point> l1 = new ArrayList<Point>();
            List<Point> l2 = new ArrayList<Point>();

            for (Point island : islands) {

                int x = island.x;
                int y = island.y;

                // rotation by 180 degree
                Point island1 = new Point(ROTATION[i][0] * x , ROTATION[i][1] * y);
                // rotation by 90 or 270 degree
                Point island2 = new Point(ROTATION[i][0] * y , ROTATION[i][1] * x);

                l1.add(island1);
                l2.add(island2);
            }

            mappingIslands.add(getString(l1));
            mappingIslands.add(getString(l2));
        }

        Collections.sort(mappingIslands);

        return mappingIslands.get(0);
    }

    private String getString(List<Point> islands) {
        Collections.sort(islands);

        StringBuilder sb = new StringBuilder();

        int x = islands.get(0).x;
        int y = islands.get(0).y;		

        for (Point island : islands) {
            // move(pan) this island to the coordinate point
            int posX = island.x - x;
            int posY = island.y - y;
            sb.append(posX + SEPERATOR + posY + SEPERATOR);
        }

        return sb.toString();
    }
}
