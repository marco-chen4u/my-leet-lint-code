/***
* LintCode 941. Sliding Puzzle
On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
If it is impossible for the state of the board to be solved, return -1.

note:
    -board will be a 2 x 3 array as described above.
    -board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5].

Example 1:
    Given board = `[[1,2,3],[4,0,5]]`, return `1`.
    Explanation: 
        Swap the 0 and the 5 in one move.

Example 2:
    Given board = `[[1,2,3],[5,4,0]]`, return `-1`.
    Explanation: 
        No number of moves will make the board solved.

Example 3:
    Given board = `[[4,1,2],[5,0,3]]`, return `5`.
    Explanation: 
        5 is the smallest number of moves that solves the board.
        An example path:
            After move 0: [[4,1,2],[5,0,3]]
            After move 1: [[4,1,2],[0,5,3]]
            After move 2: [[0,1,2],[4,5,3]]
            After move 3: [[1,0,2],[4,5,3]]
            After move 4: [[1,2,0],[4,5,3]]
            After move 5: [[1,2,3],[4,5,0]]

Example 4:
    Given board = `[[3,2,4],[1,5,0]]`, return `14`.

* LintCode link: https://www.lintcode.com/problem/941
* LeetCode link: https://leetcode.com/problems/sliding-puzzle/description/
***/
//BFS
public class Solution {
    // fields
    private final int[] DIRECTION_X = new int[]{0, 1, -1, 0};
    private final int[] DIRECITON_Y = new int[]{1, 0, 0, -1};

    private final char EMPTY = '0';
    private final String EMPTY_VALUE = "0";

    private final int[][] FINAL_STATE = new int[][] {{1, 2, 3}, {4, 5, 0}};
    
    private int n; // row size
    private int m; // column size



    /**
     * @param board: the given board
     * @return:  the least number of moves required so that the state of the board is solved
     */
    public int slidingPuzzle(int[][] board) {
        int result = -1;
        // check corner cases
        if (board == null || board.length == 0 ||
            board[0] == null || board[0].length == 0) {
            return result;
        }

        // initialize
        n = board.length;
        m = board[0].length;

        String start = getSerialized(board);
        String end = getSerialized(FINAL_STATE);

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            step += 1;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                for (String next : getNext(current)) {
                    if (end.equals(next)) {
                        return step;
                    }

                    if (visited.contains(next)) {
                        continue;
                    }

                    queue.offer(next);
                    visited.add(next);
                }// for
            }
        }// while

        return result;
    }

    // helper methods
    private List<String> getNext(String current) {
        List<String> result = new ArrayList<>();
        // check corner case
        if (current == null || current.isEmpty()) {
            return result;
        }

        int currentPos = current.indexOf(EMPTY_VALUE);
        if (currentPos == -1) {
            return result;
        }

        int x = currentPos / m;
        int y = currentPos % m;

        if (isNotInBoard(x, y)) {
            return result;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + DIRECTION_X[i];
            int nextY = y + DIRECITON_Y[i];

            if (isNotInBoard(nextX, nextY)) {
                continue;
            }

            // convert 2D postion into 1D position
            int nextPos = nextX * m + nextY;

            //swapping
            char[] currentState = current.toCharArray();
            char tmp = currentState[nextPos];
            currentState[nextPos] = EMPTY;
            currentState[currentPos] = tmp;

            String nextState = String.valueOf(currentState);
            result.add(nextState);
        }

        return result;
    }

    private boolean isNotInBoard(int x, int y) {
        return x < 0 || x >= n || y < 0 || y >= m;
    }

    private String getSerialized(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(board[i][j]);
            }
        }

        return sb.toString();
    }
}
