/**
* LintCode 1300. Bash Game
You are playing the following game with your friend: There is a pile of stones on the table, 
each time one of you take turns to remove 1 to 3 stones. 
The one who removes the last stone will be the winner. 
You will take the first turn to remove stones.

Both of you are very clever and have optimal strategies for the game. 
Write a function to determine whether you can win the game given the number of stones.

For example, if there are 4 stones, then you will never win the game: no matter 1, 2, or 3 stones you remove, 
the last stone will always be removed by your friend.

Example 1
    Input：n = 4 
    Output：False
    Explanation：Take 1, 2 or 3 first, the other party will take the last one
    
Example 2
    Input：n = 5 
    Output：True
    Explanation：Take 1 first，Than，we can win the game
    
link: https://www.lintcode.com/problem/1300/
**/

//version-1: recursion
public class Solution {
    /**
     * @param n: an integer
     * @return: whether you can win the game given the number of stones in the heap
     */
    public boolean canWinBash(int n) {
        cornrer case-1
        if (n <= 0) { /*when there's no stone left, than means I lost the game, the counter part(firend) wind the game*/
            return false;
        }

        if (n <= 3) { /*when there's 1-3 stones left, I could take 1-3 stones and left nothing left, then I win the game*/
            return true;
        }*/

        /*(1)when I attempt to take 1 stone, then if the counter part could take the aggrive stategy from the n - 1 stones to let me lost, then I lost the game, so if canWinBash(n-1) = false, then I win*/
        /*(2)if the counter part could take the strategy to win from n - 2 stones, then I lost, if not [canWinBash(n - 2) = false] then I win*/
        /*(3)if the counter part could win from the left n - 3 stones, then I lost, if not[canWinBasn(n - 3) = false] then I win the game*/
        return !canWinBash(n - 1) || 
                !canWinBash(n - 2) ||
                !canWinBash(n - 3);
    }
}

//version-2: dp
public class Solution {
    /**
     * @param n: an integer
     * @return: whether you can win the game given the number of stones in the heap
     */
    public boolean canWinBash(int n) {
        if (n <= 0) {
            return false;
        }

        if (n <= 3) {
            return true;
        }

        boolean[] dp = new boolean[n + 1];
        dp[0] = false;
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;

        for (int i = 4; i <= n; i++) {
            dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i -3];
        }

        return dp[n];
    }
}
