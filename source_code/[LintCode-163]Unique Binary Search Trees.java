/***
* LintCode 163. Unique Binary Search Trees
Given n, how many structurally unique BSTs (binary search trees) that store values 1...n?

Example
	Example 1:
		Input:n = 3,
		Output: 5
		Explanation:there are a total of 5 unique BST's.
		1           3    3       2      1
		 \         /    /       / \      \
		  3      2     1       1   3      2
		 /      /       \                  \
		2     1          2                  3
***/

/*
 int[] f = new int[n + 1]
 f[0] = 1
 f[1] = 1
 
 when i = 2,
        root node of num:    1      +      2
                            / \           / \                
             count        (0)*(1)       (1)*(0)
 when i = 3,
        root node of num:   1       +      2    +     3
                           / \            / \        / \
             count       (0)*(2)        (1)*(1)    (2)*(0)

*/
public class Solution {
    /**
     * @param n: An integer
     * @return: An integer
     */
    public int numTrees(int n) {
        // check corner cases
        if (n < 0) {
            return 0;
        }
        
        if (n == 0) {
            return 1;
        }
        
        // normal case
        // state
        int[] dp = new int[n + 1];
        
        // initialize
        dp[0] = 1;
        dp[1] = 1;
        
        // function
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += (dp[j] * dp[i - j - 1]); 
            }
        }
        
        // answer
        return dp[n];
    }
}