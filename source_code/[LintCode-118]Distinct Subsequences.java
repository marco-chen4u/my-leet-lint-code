/***
* LintCode 118. Distinct Subsequences
Given two strings S and T. 
Count the number of distinct subsequences of S which equals T.

A subsequence of a string is a new string 
which is formed from the original string by deleting some (can be none) of the characters 
without disturbing the relative positions of the remaining characters. 
(ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not)

Example 1
    Input: S = "rabbbit", T = "rabbit"
    Output: 3
    Explanation: You could remove any 'b' in S, so there are 3 ways to get T.

Example 2
    Input: S = "abcd", T = ""
    Output: 1
    Explanation: There is only 1 way to get T - remove all chars in S.

Challenge
    Do it in O(n^2) time and O(n) memory.
    O(n^2n) memory is also acceptable if you do not know how to optimize memory.
***/
/*
* T在S中出现多少次，即T中的每一个字符都要在S中出现
* 从最后一步反过来开始分析，即T的尾巴，是否和S的尾巴（字符）匹配结成对子
* 状态： dp[i][j], 表示T前j个字符T[0..j-1]，在S前i个字符S[0..i-1]中，出现多少次。
		dp[i][j] = dp[i - 1][j]   【即S的尾巴与T的尾巴匹配不上，故舍弃当期S的尾巴第i个字符】
		               +          【注意，这是求总的个数，所以这里采用加法计算，既不是max，也不是min】
				   dp[i - 1][j - 1] 【S和T的尾巴匹配结对，即S[i - 1] == T[j - 1]】

*/
//version-1: DP with double sequence ,Time complexity O(m * n), Space complexity O(m * n)
public class Solution {
    /**
     * @param S: A string
     * @param T: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // check corner case
        if (T == null || T.length() == 0) {
            return 1;
        }
        
        if (S == null || S.length() == 0) {
            return 0;
        }
        
        // state
        int m = S.length();
        int n = T.length();
        int[][] nums = new int[m + 1][n + 1];
        
        // initialize
        /*if T is empty there's only 1  that appears in S*/
        /*for (int i = 0; i <= m; i++) {
            nums[i][0] = 1;
        }*/
        
        /*if S is empty, and T is not empty, there's 0 for T appears in S*/
        
        // function
        for (int i = 1; i <= m; i++) {
            for (int j =1; j <= n; j++) {
				
                if (j == 0) {
                    nums[i][j] = 1;
                    continue;
                }
	
                nums[i][j] = nums[i - 1][j];
				
                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    nums[i][j] += nums[i - 1][j - 1]; 
                }
                
            }
        }
        
        // answer
        return nums[m][n];
    }
}

//version-2: DP with Rotated Array, Time complexity O(m * n), Space complexity O(n)
public class Solution {
    /**
     * @param S: A string
     * @param T: A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // check corner case
        if (T == null || T.length() == 0) {
            return 1;
        }
        
        if (S == null || S.length() == 0) {
            return 0;
        }
        
        // state
        int m = S.length();
        int n = T.length();
        int[][] nums = new int[2][n + 1];
        
        // initialize
        /*if T is empty there's only 1  that appears in S*/
        /*for (int i = 0; i <= m; i++) {
            nums[i][0] = 1;
        }*/
        
        /*if S is empty, and T is not empty, there's 0 for T appears in S*/
        
        // function
        for (int i = 0; i <= m; i++) {
            for (int j =0; j <= n; j++) {
				
                if (j == 0) {
                    nums[i%2][j] = 1;// initialize
                    continue;
                }
	
                if (i == 0) {
                    nums[i%2][j] = 0;// initialize
                    continue;
                }

                nums[i%2][j] = nums[(i - 1)%2][j];

                if (S.charAt(i - 1) == T.charAt(j - 1)) {
                    nums[i%2][j] += nums[(i - 1)%2][j - 1]; 
                }
                
            }
        }
        
        // answer
        return nums[m%2][n];
    }
}
