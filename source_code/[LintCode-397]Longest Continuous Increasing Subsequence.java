/***
* LintCode 397. Longest Continuous Increasing Subsequence
Give an integer array，find the longest increasing continuous subsequence in this array.

An increasing continuous subsequence:
    -Can be from right to left or from left to right.
    -Indices of the integers in the subsequence should be continuous.

Example 1
    Input: [5, 4, 2, 1, 3]
    Output: 4
    Explanation:
        For [5, 4, 2, 1, 3], the LICS  is [5, 4, 2, 1], return 4.

Example 2
    Input: [5, 1, 2, 3, 4]
    Output: 4
    Explanation:
        For [5, 1, 2, 3, 4], the LICS  is [1, 2, 3, 4], return 4.

Challenge
    O(n) time and O(1) extra space.
***/
//version-1: scanning line
public class Solution {
    /**
     * @param num: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] nums) {
        int result = 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }        
        
	result = 1;
        if (nums.length == 1) {
            return result;
        }
        
        int size = nums.length;
        int currentLength = 0;
        
        // left to right
        currentLength = 1;
        for (int i = 1; i < size; i++) {
            if (nums[i - 1] <= nums[i]) {
                currentLength++;
            }
            else {// otherwise reset currentLength to the original value(1)
                currentLength = 1;
            }
            
            result = Math.max(result, currentLength);
        }
        
        // right to left
        currentLength = 1;
        for (int j = size - 2; j  >= 0; j--) {
            if (nums[j] >= nums[j + 1]) {
                currentLength++;
            }
            else { // otherwise reset currentLength to the original value(1)
                currentLength = 1;
            }
            
            result = Math.max(result, currentLength);
        }
        
        return result;
    }
}

//version-2： DP
public class Solution {
    /**
     * @param num: An array of Integer
     * @return: an integer
     */
    public int longestIncreasingContinuousSubsequence(int[] nums) {
        int result = 0;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }        

        if (nums.length == 1) {
            result++;
            return result;// return 1
        }

        int size = nums.length;
        // state
        int[] dpInc = new int[size];
        int[] dpDec = new int[size];

        // initialize
        Arrays.fill(dpInc, 1);
        Arrays.fill(dpDec, 1);

        // function
        for (int i = 1; i < size; i++) {

            if (nums[i - 1] < nums[i]) {
                dpInc[i] = Math.max(dpInc[i], dpInc[i - 1] + 1);
            }
            else if (nums[i - 1] > nums[i]){
                dpDec[i] = Math.max(dpDec[i], dpDec[i - 1] + 1);
            }

            result = Math.max(result, Math.max(dpInc[i], dpDec[i]));
        }

        return result;
    }
}
