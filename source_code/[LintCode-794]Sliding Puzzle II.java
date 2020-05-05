/***
 * LintCode 794. Sliding Puzzle II
On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, 
and an empty square represented by 0.
A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
Given an initial state of the puzzle board and final state, 
return the least number of moves required so that the initial state to final state.
If it is impossible to move from initial state to final state, return -1.

Example
    Example 1:
        Input:
            [
             [2,8,3],
             [1,0,4],
             [7,6,5]
            ]
            [
             [1,2,3],
             [8,0,4],
             [7,6,5]
            ]
        Output:
            4
        Explanation:
            [                 [
             [2,8,3],          [2,0,3],
             [1,0,4],   -->    [1,8,4],
             [7,6,5]           [7,6,5]
            ]                 ]
            
            [                 [
             [2,0,3],          [0,2,3],
             [1,8,4],   -->    [1,8,4],
             [7,6,5]           [7,6,5]
            ]                 ]
            
            [                 [
             [0,2,3],          [1,2,3],
             [1,8,4],   -->    [0,8,4],
             [7,6,5]           [7,6,5]
            ]                 ]
            
            [                 [
             [1,2,3],          [1,2,3],
             [0,8,4],   -->    [8,0,4],
             [7,6,5]           [7,6,5]
            ]                 ]
    Example 2：
        Input:
            [[2,3,8],[7,0,5],[1,6,4]]
            [[1,2,3],[8,0,4],[7,6,5]]
        Output:
            -1

Challenge
    How to optimize the memory?
    Can you solve it with A* algorithm?
***/
public class Solution {
    // fields
    private int[] directionX = new int[] {0, 1, -1, 0};
    private int[] directionY = new int[] {1, 0, 0, -1};
    private int n; //matrix rowSize
    private int m; //matrix columnSize
    private final int ZERO = 0;
    private final char EMPTY_SQUARE = '0';
    
    // methods
    /**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        int result = -1;
        // check corner case
        if (init_state == null || init_state.length == 0) {
            return 0;
        }
        
        if (final_state == null || final_state.length == 0) {
            return 0;
        }
        
        n = init_state.length;
        m = init_state[0].length;
        
        String start = matrixToString(init_state);
        String destination = matrixToString(final_state);
        
        Queue<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        
        queue.offer(start);
        visited.add(start);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            steps++;
            
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                
                for (String next : getNext(current)) {
                    if (visited.contains(next)) {
                        continue;
                    }
                    
                    if (destination.equals(next)) {
                        return steps;
                    }
                    
                    queue.offer(next);
                    visited.add(next);
                }
            }
        }
        
        return result;
    }
	
    // helper methods
    private String matrixToString(int[][] state) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(state[i][j]);
            }
        }
        
        return sb.toString();
    }
    
    private List<String> getNext(String current) {
        List<String> result = new ArrayList<String>();
        int zeroIndex = current.indexOf(EMPTY_SQUARE);
        if (zeroIndex == -1) {
            return result;
        }
        
        int x = zeroIndex / m; //EMPTY_SQUARE's row position in original matrix state 
        int y = zeroIndex % m; //EMPTY_SQUARE's column position in original matrix state 
        
        for (int i = 0; i < 4; i++) {
            int nextX = x + directionX[i];
            int nextY = y + directionY[i];
            
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) {
                continue;
            }
            
            char[] wordCharArray = current.toCharArray();
            int nextPos = nextX * m + nextY;
            
            //swap the character content
            wordCharArray[zeroIndex] = wordCharArray[nextPos];
            wordCharArray[nextPos] = EMPTY_SQUARE;
            
            String next = String.valueOf(wordCharArray);
            result.add(next);
        }
        
        return result;
    }
}