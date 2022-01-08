/***
* LintCode 1835. Number of Ways to Stay in the Same Place After Some Steps I
You have a pointer at index 00 in an array of size arrLenarrLen.

At each step, you can move 11 position to the left, 
11 position to the right in the array or stay in the same place 
(The pointer should not be placed outside the array at any time).

Given two integers stepssteps and arrLenarrLen, 
return the number of ways such that your pointer still at index 00 after exactly stepssteps steps.

Since the answer may be too large, return it modulo 10^9 + 7.

Note:
    1≤steps≤15
    1≤arrLen≤10^6

Example 1:
    Input: 
        3
        2
    Output: 
    4
    Explanation: 
        There are 4 differents ways to stay at index 0 after 3 steps.
        Right, Left, Stay
        Stay, Right, Left
        Right, Stay, Left
        Stay, Stay, Stay

Example 2:
    Input: 
        2
        4
    Output: 
        2
    Explanation: 
        There are 2 differents ways to stay at index 0 after 2 steps
        Right, Left
        Stay, Stay

Example 3:
    Input: 
        4
        2
    Output: 
        8
***/
//version-1: DFS
public class Solution {
    // constatns
    private static final int[] DIRECTIONS = new int[]{1, 0, -1};

    // fields
    private int numberOfWays;

    /**
     * @param steps: steps you can move
     * @param arrLen: the length of the array
     * @return: Number of Ways to Stay in the Same Place After Some Steps
     */
    public int numWays(int steps, int arrLen) {
        // initialize
        numberOfWays = 0;

        // sfs
        dfs(steps, arrLen, 0, 0);

        // return
        return numberOfWays;
    }

    // helper method
    private void dfs(int stepLimit, int size, int index, int currentSteps) {
        if (currentSteps == stepLimit && index == 0) {
            numberOfWays += 1;
            return;
        }

        if (index < 0 || index >= size || currentSteps > stepLimit) {
            return;
        }

        for (int i = 0; i < 3; i++) {
            dfs(stepLimit, size, index + DIRECTIONS[i], currentSteps + 1);
        }   

    }
}

//version-2: DFS
public class Solution {
    // constatns
    private static final int[] DIRECTIONS = new int[]{1, 0, -1};

    /**
     * @param steps: steps you can move
     * @param arrLen: the length of the array
     * @return: Number of Ways to Stay in the Same Place After Some Steps
     */
    public int numWays(int steps, int arrLen) {
        return dfs(steps, arrLen, 0, 0);
    }

    // helper method
    private int dfs(int stepLimit, int size, int index, int currentSteps) {
        if (currentSteps == stepLimit && index == 0) {
            return 1;
        }

        if (index < 0 || index >= size || currentSteps > stepLimit) {
            return 0;
        }

        int numberOfWays = 0;
        for (int i = 0; i < 3; i++) {
            numberOfWays += dfs(stepLimit, size, index + DIRECTIONS[i], currentSteps + 1);
        }   

        return numberOfWays;     
    }
}
