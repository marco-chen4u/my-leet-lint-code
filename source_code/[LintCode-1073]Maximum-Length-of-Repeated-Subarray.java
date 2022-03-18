/**
* LintCode 1073 · Maximum Length of Repeated Subarray
Given two integer arrays A and B, return the maximum length of an subarray that appears in both arrays.

Note:
    1 <= len(A), len(B) <= 1000
    0 <= A[i], B[i] < 100
    
Example 1:
    Input:
        [1,2,3,2,1]
        [3,2,1,4,7]
    Output:
        3
    Explanation:
        The repeated subarray with maximum length is [3, 2, 1].

Example 2:
    Input:
        [1,2,3,4,5]
        [6,7,8,9,10]
    Output:
        0
    Explanation:
        No repeated subarray.
**/
//version-1: DP
public class Solution {
    /**
     * @param A: 
     * @param B: 
     * @return: return a integer 
     */
    public int findLength(List<Integer> A, List<Integer> B) {
        int result = 0;
        if (A == null || A.isEmpty() ||
            B == null || B.isEmpty()) {
            return result;
        }

        int n = A.size();
        int m = B.size();
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = A.get(i - 1) == B.get(j - 1) ? dp[i - 1][j - 1] + 1 : 0;
                result = Math.max(result, dp[i][j]);
            }
        }

        return result;
    }
}

//verion-2: two pointers
public class Solution {
    /**
     * @param A: 
     * @param B: 
     * @return: return a integer 
     */
    public int findLength(List<Integer> A, List<Integer> B) {
        int result = 0;
        if (A == null || A.isEmpty() ||
            B == null || B.isEmpty()) {
            return result;
        }

        int n = A.size();
        int m = B.size();

        for (int i = 0; i < n; i++) {

            int target = A.get(i);
            for (int j = 0; j < m; j++) {
                if (B.get(j) != target) {
                    continue;
                }

                int indexA = i;
                int indexB = j;
                while (indexA < n && indexB < m && 
                        A.get(indexA) == B.get(indexB)) {
                    indexA++;
                    indexB++;
                }

                result = Math.max(result, indexA - i);
            }

        }

        return result;
    }
}
