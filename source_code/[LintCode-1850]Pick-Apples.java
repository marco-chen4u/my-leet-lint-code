/***
* LintCode 1850. Pick Apples
Alice and Bob work in a beautiful orchard. 
There are N apple trees in the orchard. 
The apple trees are arranged in a row and they are numbered from 1 to N.

Alice is planning to collect all the apples from K consecutive trees 
and Bob is planning to collect all the apples from L consecutive trees.

They want to choose to disjoint segements 
(one consisting of K trees for Alice, and the other consisting of L trees for Bob) 
so as not to disturb each other. 
you should return the maximum number of apples that they can collect.

Note:
    -N is an integer within the range: [2, 600]
    -K and L are integers within the range: [1, N - 1]
    -each element of array A is an integer within the range: [1, 500]

Example 1:
    input:
        A = [6, 1, 4, 6, 3, 2, 7, 4]
        K = 3
        L = 2
    Output: 
        24
    Explanation: 
        Beacuse Alice can choose tree 3 to 5 and collect 4 + 6 + 3 = 13 apples, 
        and Bob can choose trees 7 to 8 and collect 7 + 4 = 11 apples.
        Thus, they will collect 13 + 11 = 24.

Example 2:
    Input:
        A = [10, 19, 15]
        K = 2
        L = 2
    Output: 
        -1
    Explanation: 
        Beacause it is not possible for Alice and Bob to choose two disjoint intervals.

***/
//version-1: two pointers, prefix Sum, scanning line, time-comeplexity: O(n^2)
public class Solution {
    /**
     * @param nums: a list of integer
     * @param k: a integer
     * @param l: a integer
     * @return: return the maximum number of apples that they can collect.
     */
    public int PickApples(int[] nums, int k, int l) {
        int result = -1;
        // check corner cases
        if (nums == null || nums.length == 0) {
            return result;
        }

        int size = nums.length;
        if (size < k + l) {
            return result;
        }

        // normal case
        int[] preSum = new int[size + 1];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }

        // scanning by [k-left, l-right] windows
        for (int i = 0; i < size - l; i++) {
            if (i + k > size) {
                break;
            }
            int kLeftSum = getRange(preSum, i, i + k);

            int lRightSum = 0;
            for (int j = i + k; j < size - l + 1; j++) {
                int currentLRightSum = getRange(preSum, j, j + l);
                lRightSum = Math.max(lRightSum, currentLRightSum);
            }
            
            result = Math.max(result, kLeftSum + lRightSum);
        }

        // scanning by [l-left, k-right] windows
        for (int i = 0; i < size - k; i++) {
            if (i + l > size) {
                break;
            }
            int lLeftSum = getRange(preSum, i, i + l);

            int kRightSum = 0;
            for (int j = i + l; j< size - k + 1; j++) {
                int currentKRightSum = getRange(preSum, j, j + k);
                kRightSum = Math.max(kRightSum, currentKRightSum);
            }

            result = Math.max(result, lLeftSum + kRightSum);
        }

        // return 
        return result;
    }

    // helper method
    private int getRange(int[] preSum, int i, int j) {
        if (j > preSum.length) {
            return 0;
        }
        return preSum[j] - preSum[i];
    }
}
