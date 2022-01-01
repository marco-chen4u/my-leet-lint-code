/***
* LintCode 43. Maximum Subarray III
Given an array of integers and a number k, find k non-overlapping subarrays which have the largest sum.
The number in each subarray should be contiguous.
Return the largest sum.

Example 1
    Input: 
        List = [1,2,3]
        k = 1
    Output: 6
    Explanation: 1 + 2 + 3 = 6

Example 2
    Input:
        List = [-1,4,-2,3,-2,3]
        k = 2
    Output: 8
    Explanation: 4 + (3 + -2 + 3) = 8

Notice
    The subarray should contain at least one number
***/

/***
(1)算法思路
用两个二维数组locallocal和globalglobal分别来记录局部最优解和全局最优解，局部最优解就是必须选取当前元素的最优解，全局最优解就是不一定选取当前元素的最优解。
local[i][j]表示整数数组nums的前i个元素被分成j个不重叠子数组时的最大值（必须选取元素nums[i]）。
global[i][j]表示整数数组nums的前i个元素被分成j个不重叠子数组时的最大值（不一定选取元素nums[i]）。
当i<j时不可能存在可行解，且边界值为i==j时，每个元素各自为一组，答案就是nums的前i项之和。
所以我们从边界值往回递推（j从k递推回1），状态转移方程为（此时i >= j）：
---------------------------------------------------------------------
if i == j:
    local[i][j] = local[i-1][j-1] + nums[i]
    global[i][j] = global[i-1][j-1] + nums[i]
else:
    // local[i-1][j]表示nums[i]加入上一个子数组成为一部分
    // global[i-1][j-1]表示nums[i]重新开始一个新的子数组
    // 第 i 个元素贪心地选择自己加入第 j-1 组还是自己成为独立第 j 组
    local[i][j] = max(local[i-1][j], global[i-1][j-1]) + nums[i] 
    // 全局贪心的选择包括第 i 个元素的还是不包括第 i 个元素的情况
    global[i][j] = max(global[i-1][j], local[i][j])
---------------------------------------------------------------------
进一步的，我们可以把二维数组优化为一维：
---------------------------------------------------------------------
if i == j:
    local[j] = local[j-1] + nums[i]
    global[j] = global[j-1] + nums[i]
else:
    local[j] = max(local[j], global[j-1]) + nums[i]
    global[j] = max(global[j], local[j])
---------------------------------------------------------------------
最后global[k]即为答案。
注意：以上式子的nums[i]表示的是数组nums的第几项，若下标从0开始，则nums[i]表示为nums[i-1]。

(2)复杂度分析
假设数组大小为n，划分为k个不重叠的子数组。
因为用到了两个数组记录局部最优解和全局最优解，如果记录当前位置使用二维数组，则空间复杂度为O(nk)；
若不记录当前位置使用一维数组，则空间复杂度为O(k)。
递推过程中依次推出到位置i分为j个部分的最优解，时间复杂度为O(nk)。
***/

//version-1: DP
public class Solution {
    // field 
    private int DEFAULT_MIN =  Integer.MIN_VALUE;
    
    /**
     * @param nums: A list of integers
     * @param k: An integer denote to find k non-overlapping subarrays
     * @return: An integer denote the sum of max k non-overlapping subarrays
     */
    public int maxSubArray(int[] nums, int k) {
        // check corner case
        if (nums == null || nums.length < k) {
            return 0;
        }
        
        int size = nums.length;
        
        // state
        int[][] localMax = new int[k + 1][size + 1];
        int[][] globalMax = new int[k + 1][size + 1];
        
        // initialize
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j <= size; j++) {
                localMax[i][j] = (i == 0) ? 0 : DEFAULT_MIN;
                globalMax[i][j] = 0;
            }
        }
        
        // function
        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= size; j++) {
                localMax[i][j] = Math.max(localMax[i][j - 1], globalMax[i - 1][j - 1]) + nums[j - 1];
                
                if (i == j) {
                    globalMax[i][j] = localMax[i][j];
                    continue;
                }
                
                globalMax[i][j] = Math.max(globalMax[i][j - 1], localMax[i][j]);
            }
            
        }
        
        // return
        return globalMax[k][size];
    }
}
