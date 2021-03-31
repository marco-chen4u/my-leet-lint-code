/***
* LeetCode 1036. Escape a Large Maze
In a 1 million by 1 million grid, the coordinates of each grid square are (x, y) with 0 <= x, y < 10^6.

We start at the source square and want to reach the target square.  
Each move, we can walk to a 4-directionally adjacent square in the grid that isn't in the given list of blocked squares.

Return true if and only if it is possible to reach the target square through a sequence of moves.

 

Example 1:
    Input: blocked = [[0,1],[1,0]], source = [0,0], target = [0,2]
    Output: false
    Explanation: 
        The target square is inaccessible starting from the source square,is because we can't walk outside the grid.


Example 2:
    Input: blocked = [], source = [0,0], target = [999999,999999]
    Output: true
    Explanation: 
    Because there are no blocked cells, it's possible to reach the target square.

Note:
    1.0 <= blocked.length <= 200
    2.blocked[i].length == 2
    3.0 <= blocked[i][j] < 10^6
    3.source.length == target.length == 2
    5.0 <= source[i][j], target[i][j] < 10^6
    6.source != target
***/
//version-1: BFS
class Solution {
    // fields
    private final int[] directionX = new int[]{0, 0, 1, -1};
    private final int[] directionY = new int[]{1, -1, 0, 0};
    private final int LIMIT = 1000000;// 1 million
    private final int MAX_SIZE = 19900;// the maximum size of area that 200-blocks could hold the wall
	
    private final String SEPERATOR = "#";

    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // check corner cases
        if (blocked == null || isEmpty(source) || isEmpty(target)) {
            return false;
        }

        Set<String> blockSet = getBlockSet(blocked);
        return (isEnclosed(source, target, blockSet) || isEnclosed(target, source, blockSet)) ? false : true;
    }

    // helper methods
    private boolean isEmpty(int[] nums) {
        return (nums == null || nums.length == 0);
    }
    
    private boolean isEnclosed(int[] source, int[] target, Set<String> blockSet) {
        int count = 0;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        String index = getValue(source[0], source[1]);
        queue.offer(index);
        visited.add(index);

        while (!queue.isEmpty()) {
            String[] currentPos = queue.poll().split(SEPERATOR);
            int x = Integer.parseInt(currentPos[0]);// row position
            int y = Integer.parseInt(currentPos[1]);// column position

            // key point here
            if (count >= MAX_SIZE) {// break throup the blocked lines
                return false;       // that means it can escape
            }

            if (x == target[0] && y == target[1]) {// reach to the termination
                return false;       //that means it can escape
            }

            for (int i = 0; i < 4; i++) {
                int nextX = x + directionX[i];
                int nextY = y + directionY[i];

                if (!isBounded(nextX, nextY)) {
                    continue;
                }

                String nextPos = getValue(nextX, nextY);
                if (blockSet.contains(nextPos) ||
                    visited.contains(nextPos)) {
                    continue;
                }

                count++;
                visited.add(nextPos);
                queue.offer(nextPos);
            }
        }

        return true;
    }
	
    private boolean isBounded(int x, int y) {
        return x >= 0 && x < LIMIT &&
                y >= 0 && y < LIMIT;
    }
	
    private String getValue(int x, int y) {
        return x + SEPERATOR + y;
    }

    private Set<String> getBlockSet(int[][] blocked) {
        Set<String> blockSet = new HashSet<String>();
        for (int[] blockPos : blocked) {
            String value = getValue(blockPos[0], blockPos[1]);
            blockSet.add(value);// keep in mind the area that the blocks get built
        }

        return blockSet;
    }
}
