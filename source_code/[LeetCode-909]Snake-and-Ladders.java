/***
* LeetCode 909. Snake and Ladders
You are given an n x n integer matrix board 
where the cells are labeled from 1 to n2 in a Boustrophedon style starting from the bottom left of the board 
(i.e. board[n - 1][0]) and alternating direction each row.

You start on square 1 of the board. In each move, starting from square curr, do the following:
    -Choose a destination square next with a label in the range [curr + 1, min(curr + 6, n2)].
      This choice simulates the result of a standard 6-sided dice roll: i.e., 
      there are always at most 6 destinations, regardless of the size of the board.
    -If next has a snake or ladder, you must move to the destination of that snake or ladder. Otherwise, you move to next.
    -The game ends when you reach the square n2.

A board square on row r and column c has a snake or ladder if board[r][c] != -1. 
The destination of that snake or ladder is board[r][c]. Squares 1 and n2 do not have a snake or ladder.

Note that you only take a snake or ladder at most once per move. 
If the destination to a snake or ladder is the start of another snake or ladder, you do not follow the subsequent snake or ladder.
    -For example, suppose the board is [[-1,4],[-1,3]], and on the first move, your destination square is 2. 
      You follow the ladder to square 3, but do not follow the subsequent ladder to 4.

Return the least number of moves required to reach the square n2. 
If it is not possible to reach the square, return -1.

Example 1
    https://assets.leetcode.com/uploads/2018/09/23/snakes.png
    Input: 
        board = [[-1,-1,-1,-1,-1,-1],
                 [-1,-1,-1,-1,-1,-1],
                 [-1,-1,-1,-1,-1,-1],
                 [-1,35,-1,-1,13,-1],
                 [-1,-1,-1,-1,-1,-1],
                 [-1,15,-1,-1,-1,-1]]
    Output: 4
    Explanation: 
        In the beginning, you start at square 1 (at row 5, column 0).
        You decide to move to square 2 and must take the ladder to square 15.
        You then decide to move to square 17 and must take the snake to square 13.
        You then decide to move to square 14 and must take the ladder to square 35.
        You then decide to move to square 36, ending the game.
        This is the lowest possible number of moves to reach the last square, so return 4.    

Example 2
    Input: 
        board = [[-1,-1],
                 [-1,3]]
    Output: 1
    
Constraints
    -n == board.length == board[i].length
    -2 <= n <= 20
    -board[i][j] is either -1 or in the range [1, n2].
    -The squares labeled 1 and n2 do not have any ladders or snakes.

LeetCode link: https://leetcode.com/problems/snakes-and-ladders/
LintCode link: https://www.lintcode.com/problem/1732/
***/
//version-1: BFS
class Solution {

    private static int destination = 0;

    public int snakesAndLadders(int[][] board) {
        // check corner case
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return -1;
        }

        int n = board.length;
        destination = n * n;
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(1);
        visited.add(1);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (current == destination) {
                    return step;
                }

                for (int next = current + 1; next <= Math.min(destination, current + 6); next++) {
                    int row = (next - 1) / n;
                    int column = (next - 1) % n;
                    // re-proejction
                    column = (row % 2 == 0) ? column : n - 1 - column;
                    row = n - 1 - row;

                    int nextPos = board[row][column] == -1 ? next : board[row][column]; // move next or jump to the ladder...

                    if (visited.contains(nextPos)) {
                        continue;
                    }

                    visited.add(nextPos);
                    queue.offer(nextPos);
                }
            }
            
            step++;
        }

        return -1;
    }
}


//version-2: BFS
class Solution {

    private static int n;

    public int snakesAndLadders(int[][] board) {
        n = board.length;

        int start = 1;
        int destination = n * n;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();

                if (destination == current) {
                    return step;
                }

                for (int next = current + 1; next <= Math.min(current + 6, destination); next++) {
                    int[] nextPosition = getNextPosition(next);
                    int row = nextPosition[0];
                    int column = nextPosition[1];

                    int nextPos = board[row][column] == -1 ? next : board[row][column];

                    if (visited.contains(nextPos)) {
                        continue;
                    }

                    visited.add(nextPos);
                    queue.offer(nextPos);
                }
            }

            step++;
        }

        return -1;
    }

    // helper method
    private int[] getNextPosition(int next) {
        int[] result = new int[2];

        int row = (next - 1) / n; // 0-based position in row
        int column = (next - 1) % n; // 0-based position in column

        // re-projection to origin point of top-left
        int newRow = n - 1 - row;
        int newColumn = (row % 2 == 0) ? column : n - 1 - column;

        result[0] = newRow;
        result[1] = newColumn;
        return result;
    }
}
