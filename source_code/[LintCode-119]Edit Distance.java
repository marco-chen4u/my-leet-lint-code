/***
* LintCode 119. Edit Distance
Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
(each operation is counted as 1 step.)
You have the following 3 operations permitted on a word:
    Insert a character
    Delete a character
    Replace a character

Example 1:
    Input: 
        "horse"
        "ros"
    Output: 3
    Explanation: 
        horse -> rorse (replace 'h' with 'r')
        rorse -> rose (remove 'r')
        rose -> ros (remove 'e')
	
Example 2:
    Input: 
        "intention"
        "execution"
    Output: 5
    Explanation: 
        intention -> inention (remove 't')
        inention -> enention (replace 'i' with 'e')
        enention -> exention (replace 'n' with 'x')
        exention -> exection (replace 'n' with 'c')
        exection -> execution (insert 'u')
***/
/*
* 状态dp[i][j]为word1前i个字符word1[0..i - 1]和word2前j个字符word2[0..j - 1]的最小编辑距离
* dp[i][j] =  dp[i - 1][j - 1] (if word1[i - 1] == word2[j - 1])
                              or
              min{dp[i][j -1] + 1, dp[i - 1][j] + 1, dp[i - 1][j - 1] + 1}
	      
               -dp[i][j -1] + 1,即word1在最后插入word2[j - 1]这个字符。
	       -dp[i - 1][j] + 1， 即word1删除最后一个字符。
	       -dp[i - 1][j - 1] + 1， 即word1最后一个字符替换成word2[j - 1]。
	       
	       -dp[i - 1][j - 1] AND word1[i - 1] == word2[j - 1], 即word1和word2最后一个字符相等。
	
* 时间复杂度O(m * n), 空间复杂度O(m * n)
* 可以用滚动数据中优化空间至O(n)
*/
//version-1
public class Solution {
    public int minDistance(String word1, String word2) {
        // check corner cases
        if ((word1 == null || word1.length() == 0) &&
            (word2 == null || word2.length() == 0)){
            return 0;
        }

        // state
        int n = word1.length();
        int m = word2.length();		
        int[][] f = new int[n + 1][m + 1];
        // initialize
        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            f[i][0] = i;
        }
        for (int j = 1; j <= m; j++) {
            f[0][j] = j;
        }		
        // function
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                f[i][j] = Integer.MAX_VALUE;
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    f[i][j] = f[i - 1][j - 1];
                }
                else {
                    f[i][j] = 1 + Math.min(f[i - 1][j - 1], Math.min(f[i - 1][j], f[i][j - 1]));
                }
            }
        }
        //return
        return f[n][m];		
    }
}

//version-2: double-space dp
public class Solution {
    /**
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // check corner case
        if (isEmpty(word1) && isEmpty(word2)) {
            return 0;
        }
        
        if (isEmpty(word1)) {
            return word2.length();
        }
        
        if (isEmpty(word2)) {
            return word1.length();
        }
        
        int n = word1.length();
        int m = word2.length();
        
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        
        int[][] dp = new int[n + 1][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {// insert， insert, ...
                    dp[i][j] = j;
                    continue;
                }
                
                if (j == 0) {// delete, delete, ...
                    dp[i][j] = i;
                    continue;
                }
                
                if (charArray1[i - 1] != charArray2[j - 1]) {
                    dp[i][j] = Math.min(dp[i - 1][j], // case-1 : delete 
                                        Math.min(dp[i][j - 1], // case-2: insert
                                                dp[i - 1][j - 1])) + 1; // case-3: replace
                    continue;
                }
                
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];// case-4: them character
                }
            }
        }
        
        return dp[n][m];
    }
    
    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}

// version-3: double-space->rotated array
public class Solution {
    /**
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // check corner case
        if (isEmpty(word1) && isEmpty(word2)) {
            return 0;
        }
        
        if (isEmpty(word1)) {
            return word2.length();
        }
        
        if (isEmpty(word2)) {
            return word1.length();
        }
        
        int n = word1.length();
        int m = word2.length();
        
        char[] charArray1 = word1.toCharArray();
        char[] charArray2 = word2.toCharArray();
        
        int[][] dp = new int[2][m + 1];
        
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {// insert， insert, ...
                    dp[i%2][j] = j;
                    continue;
                }
                
                if (j == 0) {// delete, delete, ...
                    dp[i%2][j] = i;
                    continue;
                }
                
                if (charArray1[i - 1] != charArray2[j - 1]) {
                    dp[i%2][j] = Math.min(dp[(i - 1)%2][j], // case-1 : delete 
                                        Math.min(dp[i%2][j - 1], // case-2: insert
                                                dp[(i - 1)%2][j - 1])) + 1; // case-3: replace
                    continue;
                }
                
                if (charArray1[i - 1] == charArray2[j - 1]) {
                    dp[i%2][j] = dp[(i - 1)%2][j - 1];// case-4: them character
                }
            }
        }
        
        return dp[n%2][m];
    }
    
    // helper method
    private boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
